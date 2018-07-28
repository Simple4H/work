drop database db_qiansheng;
-- 创建数据库
create database db_qiansheng default character set utf8 COLLATE utf8_general_ci;
use db_qiansheng;
-- 用户表
create table `qs_user`(
`id`          int(11)     not null auto_increment
comment '用户ID',
`username`    varchar(20) not null
comment '用户名',
`password`    varchar(30) not null
comment '密码',
`email`       varchar(30) not null
comment '邮箱',
`phone`       varchar(15) not null
comment '手机号码',
`status`      int(2)      default 0
comment '状态',
`authority`   int(2)      default 0
comment '权限',
`create_time` datetime    not null
comment '创建时间',
`update_time` datetime    not null
comment '更新时间',
primary key (`id`),
unique key `user_name_unique` (`username`) using btree
)
engine=InnoDB
auto_increment = 1
default charset = utf8;

-- 数据表主表
create table `qs_data` (
`id`          int(11)     not null auto_increment
comment '数据表ID',
`UUID`        int(50)     not null
comment 'UUID',
`number`      int(100)    not null
comment '次数',
`personnel`   varchar(20) not null
comment '操作人员',
`create_time` datetime    not null
comment '开始时间',
`close_time`  datetime    not null
comment '结束时间',
primary key (`id`),
unique key `uuid_unique` (`UUID`) using btree
)
engine = InnoDB
auto_increment = 1
default charset = utf8

-- 数据表从表
create table `qs_detail`(
`id` int(11) not null auto_increment
comment '',
`UUID` int(50) not null
comment '',
`times` int(10) not null
comment '',
`temperature` varchar(20) not null
comment '',
`weight` varchar(20) not null
comment '',
`create_time` datetime not null
comment '',
primary key (`id`)
)
engine = InnoDB
auto_increment = 1
default charset = utf8
