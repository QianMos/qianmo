create table userinfo(
userid nvarchar(50) primary key,
username nvarchar(50),
useraccount nvarchar(50),
userpassword nvarchar(50),
userphone nvarchar(50)
)
create table userbk(
userbkid nvarchar(50) primary key,
userid nvarchar(50),
bktitle nvarchar(50),
bktext text,
bkopen int,
bkcreatetime date
)
create table usermsg(
usermid nvarchar(50) primary key,
username nvarchar(50),
usertext text
)