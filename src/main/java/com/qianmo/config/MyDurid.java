package com.qianmo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: QianMo
 * @Date: Create in 13:00 2020/4/10
 * 阿里连接池配置
 */
@Configuration
public class MyDurid {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource(){
        return new DruidDataSource();
    }
    //配饰Druid监控
    //配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String,String> parms=new HashMap<>();
        parms.put("loginUsername","qianmoit");
        parms.put("loginPassword","1314521ssw");
//        parms.put("allow", "");// 默认就是允许所有访问
//        parms.put("deny", "10.18.172.124");
        bean.setInitParameters(parms);
        return bean;
    }
    //配置一个web监控filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        Map<String,String> parms=new HashMap<>();
        parms.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(parms);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
