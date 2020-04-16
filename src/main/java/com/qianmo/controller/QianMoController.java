package com.qianmo.controller;

import com.qianmo.entity.AllUser;
import com.qianmo.entity.MyParms;
import com.qianmo.entity.UserBk;
import com.qianmo.entity.UserMsg;
import com.qianmo.services.UserBkService;
import com.qianmo.services.UserMsgService;
import com.qianmo.services.UserService;
import com.qianmo.untils.MD5Untils;
import com.qianmo.untils.SmsUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: QianMo
 * @Date: Create in 18:22 2020/4/5
 */
@Controller
public class QianMoController {
    @Autowired
    private SmsUntil smsUntil;
    @Autowired
    private UserService userService;
    @Autowired
    private UserBkService userBkService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMsgService userMsgService;
    //首页
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request){
        List<UserBk> list = userBkService.getAllBk(0);
        model.addAttribute("list",list);
        return "index";
    }
    //登录
    @PostMapping("/user/login")
    @ResponseBody
    public Boolean login(String username,String password,HttpServletRequest request){
        AllUser user = userService.login(username);
        if(user==null){
            return false;
        }
        String md5 = MD5Untils.string2MD5(password);
        if(!user.getUserpassword().equals(md5)){
            return false;
        }
        request.getSession().setAttribute("username",user.getUsername());
        request.getSession().setAttribute("userid",user.getUserid());
        return true;
    }
    @PostMapping("/user/sendSms")
    @ResponseBody
    public String sendSms(String phone,HttpServletRequest request){
        //生成6位随机数
        Random random=new Random();
        int max=999999;
        //最大数
        int min=100000;
        // 最小数
        int code = random.nextInt(max);
        //随机生成
        if(code<min){ code=code+min; }
        redisTemplate.opsForValue().set(phone+"",code,5, TimeUnit.MINUTES);
        return smsUntil.sendSms(phone,code+"");
    }
    //注册用户
    @PostMapping("/user/register")
    @ResponseBody
    public Boolean register(String useraccount,String username,String password,Integer verify,String phone,HttpServletRequest request){
        int sms = (int) redisTemplate.opsForValue().get(phone + "");
        if(verify!=sms){
            return false;
        }
        AllUser user = new AllUser();
        user.setUsername(username);
        user.setUseraccount(useraccount);
        user.setUserpassword(MD5Untils.string2MD5(password));
        user.setUserphone(phone);
        userService.addUser(user);
        return true;
    }
    @GetMapping("/loginout")
    public String loginout(HttpServletRequest request){
        request.getSession().removeAttribute("userid");
        request.getSession().removeAttribute("username");
        return "redirect:/";
    }
    //流加载分页
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> page(@RequestParam("page") Integer page){
        List<UserBk> list = userBkService.getAllBk(page * 4);
        Integer count = userBkService.getCount();
        Map<String, Object> map = new HashMap<>();
        map.put("data",list);
        map.put("pages",(count/4));
        return map;
    }
    @GetMapping("/getUser")
    @ResponseBody
    public Map<String,Object> getUser(@RequestParam(name = "useraccount") String useraccount){
        AllUser user = userService.login(useraccount);
        Map<String, Object> map = new HashMap<>();
        if(user==null){
            map.put("msg",false);
            return map;
        }
        map.put("msg",true);
        map.put("phone",user.getUserphone());
        return map;
    }
    //流加载分页
    @GetMapping("/mylist")
    @ResponseBody
    public Map<String,Object> mypage(@RequestParam("page") Integer page,HttpServletRequest request){
        System.out.println(page);
        String userid = (String) request.getSession().getAttribute("userid");
        MyParms myParms = new MyParms();
        myParms.setPage(page*4);
        myParms.setUid(userid);
        List<UserBk> list = userBkService.getMyBk(myParms);
        Integer count = userBkService.getMyCount(userid);
        Map<String, Object> map = new HashMap<>();
        map.put("data",list);
        map.put("pages",(count/4));
        return map;
    }
    //我的
    @GetMapping("/comment")
    public String comment(Model model, HttpServletRequest request){
        String userid = (String) request.getSession().getAttribute("userid");
        MyParms myParms = new MyParms();
        myParms.setUid(userid);
        myParms.setPage(0);
        List<UserBk> list = userBkService.getMyBk(myParms);
        model.addAttribute("mylist",list);
        return "comment";
    }
    //删除文章
    @DeleteMapping("/delete/{bkid}")
    @ResponseBody
    public Boolean del(@PathVariable String bkid){
        userBkService.delBk(bkid);
        return true;
    }
    //留言
    @PostMapping("/setmsg")
    @ResponseBody
    public Boolean setmsg(@RequestParam("msg") String msg,HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        if(StringUtils.isEmpty(username)){
            userMsgService.setMsg("匿名用户",msg);
            return true;
        }
        userMsgService.setMsg(username,msg);
        return true;
    }
    //留言页
    @GetMapping("/message")
    public String message(Model model){
        List<UserMsg> list = userMsgService.getAllMsg(0);
        model.addAttribute("mlist",list);
        return "message";
    }
    @GetMapping("/mlist")
    @ResponseBody
    public Map<String,Object> mpage(@RequestParam("page") Integer page,HttpServletRequest request){
        List<UserMsg> list = userMsgService.getAllMsg(page * 4);
        Integer count = userMsgService.getCount();
        Map<String, Object> map = new HashMap<>();
        map.put("data",list);
        map.put("pages",(count/4));
        return map;
    }
    //添加博客
    @PostMapping("/setBk")
    @ResponseBody
    public Map<String,Object> setBk(@RequestParam("bktitle")String bktitle,@RequestParam("bkswitch")Integer bkswitch, @RequestParam("bktext")String bktext,HttpServletRequest request){
        String userid = (String) request.getSession().getAttribute("userid");
        String s = UUID.randomUUID().toString();
        UserBk bk = new UserBk();
        bk.setUserbkid(s);
        bk.setUserid(userid);
        bk.setBktitle(bktitle);
        bk.setBktext(bktext);
        bk.setBkopen(bkswitch);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        bk.setBkcreatetime(timestamp);
        userBkService.addBk(bk);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Map<String, Object> map = new HashMap<>();
        map.put("time",format.format(date));
        map.put("userbkid",s);
        return map;
    }
    //搜索
    @GetMapping("/search")
    public String search(@RequestParam("search") String search,Model model){
        MyParms parms = new MyParms();
        parms.setUid("%"+search+"%");
        parms.setPage(0);
        List<UserBk> list = userBkService.searchBk(parms);
        model.addAttribute("search",search);
        model.addAttribute("searchlist",list);
        return "search";
    }
    @GetMapping("/searchlist")
    @ResponseBody
    public Map<String,Object> searchlist(@RequestParam("page") Integer page,@RequestParam("search") String search){
        MyParms parms = new MyParms();
        parms.setUid("%"+search+"%");
        parms.setPage(page*4);
        Integer count = userBkService.searchCount(parms);
        List<UserBk> list = userBkService.searchBk(parms);
        Map<String, Object> map = new HashMap<>();
        map.put("data",list);
        map.put("pages",(count/4));
        return map;
    }
    @GetMapping("/my")
    public String my(){
        return "me";
    }
    //修改用户信息
    @PutMapping("/update")
    @ResponseBody
    public Boolean update(@RequestParam("username")String username,@RequestParam("password")String password,HttpServletRequest request){
        String  userid = (String) request.getSession().getAttribute("userid");
        AllUser user = new AllUser();
        user.setUserid(userid);
        user.setUsername(username);
        user.setUserpassword(MD5Untils.string2MD5(password));
        userService.updUser(user);
        return true;
    }
}
