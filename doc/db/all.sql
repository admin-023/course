-- 课程
drop table if exists course;
create table course(
    id char(8) not null default '' comment  'id',
    name varchar(50) not null comment '名称',
    summary varchar(2000) comment '概述',
    time int default 0 comment '时长|单位秒',
    price decimal(8,2)  default  0.00 comment '价格（元）',
    image varchar(100) comment  '封面',
    level char(1)  comment '级别|枚举[CourseLevelEnum]:ONE("1","初级")，TWO("2","中级")，THREE("3","高级")',
    charge char(1) comment '收费|枚举[CourseChargeEnum]:CHARGE（"C","收费"），FREE（"F","免费"）',
    status char(1) comment '状态|枚举[CourseStatusEnum]:PUBLISH("P","发布"),DRAFT("D","草稿")',
    enroll integer default 0 comment '报名数',
    sort int comment '顺序',
    created_at datetime comment '创建时间',
    updated_at datetime comment '修改时间',
    primary key (id)
)engine=innodb default charset=utf8mb4 comment='课程';
insert into course(id,name,summary,time,price,image,level,charge,status,enroll,sort,created_at,updated_at)
values ('00000001','测试课程01','这是一门测试课程',7200,19.9,'',0,'C','D',100,0,now(),now());
alter table `course` add column (`teacher_id` char(8)  comment '讲师|teacher.id');

-- 大章
drop table if exists `chapter`;
create table   `chapter` (
`id` char(8) not null comment 'ID',
`course_id` char(8) comment '课程ID',
`name` varchar(50) comment '名称',
primary key (`id`)
)engine=innodb default charset =utf8mb4 comment ='大章';

insert into chapter(id,course_id,name) values (000001,000000,'测试大章1');
insert into chapter(id,course_id,name) values (000002,000000,'测试大章2');
insert into chapter(id,course_id,name) values (000003,000000,'测试大章3');
insert into chapter(id,course_id,name) values (000004,000000,'测试大章4');
insert into chapter(id,course_id,name) values (000005,000000,'测试大章5');
insert into chapter(id,course_id,name) values (000006,000000,'测试大章6');
insert into chapter(id,course_id,name) values (000007,000000,'测试大章7');
insert into chapter(id,course_id,name) values (000008,000000,'测试大章8');
insert into chapter(id,course_id,name) values (000009,000000,'测试大章9');

# 小节
drop table if exists `section`;
create table `section` (
`id` char(8) not null default '' comment 'ID',
`title` char(8) not null comment '标题',
`course_id` char(8) comment '课程|course.id',
`chapter_id` char(8) comment '大章|chapter.id',
`video` varchar(200) comment '视频',
`time` int comment '时长|单位秒',
`charge` char(1) comment '收费|c 收费；f 免费',
`sort` int comment '顺序',
`created_at` datetime comment '创建时间',
`updated_at` datetime comment '修改时间',
 primary key (`id`)
)engine=innodb default charset =utf8mb4 comment ='小节';

insert into `section`(id,title,course_id,chapter_id,video,time,charge,sort,created_at,updated_at)
values ('00000001','测试小节01','00000001','00000000','',500,'f',1,now(),now());

# 分类
drop table if exists `category`;
create table `category`(
`id` char(8) not null default ' ' comment 'id',
`parent`  char(8) not null default ' ' comment '父id',
`name` varchar(50) not null comment '名称',
`sort` int comment '顺序',
primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='分类';

Insert into `category` (id, parent, name, sort) values('00000100','00000000','前端技术',100);
Insert into `category` (id, parent, name, sort) values('00000101','00000100','html/css',101);
Insert into `category` (id, parent, name, sort) values('00000102','00000100','javascript',102);
Insert into `category` (id, parent, name, sort) values('00000103','00000100','vue.js',103);
Insert into `category` (id, parent, name, sort) values('00000104','00000100','react.js',104);
Insert into `category` (id, parent, name, sort) values('00000105','00000100','angular',105);
Insert into `category` (id, parent, name, sort) values('00000106','00000100','node.js',106);
Insert into `category` (id, parent, name, sort) values('00000107','00000100','jquery',107);
Insert into `category` (id, parent, name, sort) values('00000108','00000100','小程序',108);
Insert into `category` (id, parent, name, sort) values('00000200','00000000','后端技术',200);
Insert into `category` (id, parent, name, sort) values('00000201','00000200','springboot',201);
Insert into `category` (id, parent, name, sort) values('00000202','00000200','springcloud',202);
Insert into `category` (id, parent, name, sort) values('00000203','00000200','ssm',203);
Insert into `category` (id, parent, name, sort) values('00000204','00000200','python',204);
Insert into `category` (id, parent, name, sort) values('00000205','00000200','爬虫',205);
Insert into `category` (id, parent, name, sort) values('00000206','00000200','java',206);
Insert into `category` (id, parent, name, sort) values('00000300','00000000','移动开发',300);
Insert into `category` (id, parent, name, sort) values('00000301','00000300','android',301);
Insert into `category` (id, parent, name, sort) values('00000302','00000300','ios',302);
Insert into `category` (id, parent, name, sort) values('00000303','00000300','react native',303);
Insert into `category` (id, parent, name, sort) values('00000304','00000300','ionic',304);
Insert into `category` (id, parent, name, sort) values('00000400','00000000','前沿技术',400);
Insert into `category` (id, parent, name, sort) values('00000401','00000400','微服务',401);
Insert into `category` (id, parent, name, sort) values('00000402','00000400','区块链',402);
Insert into `category` (id, parent, name, sort) values('00000403','00000400','机器学习',403);
Insert into `category` (id, parent, name, sort) values('00000404','00000400','深度学习',404);
Insert into `category` (id, parent, name, sort) values('00000405','00000400','数据分析&挖掘',405);
Insert into `category` (id, parent, name, sort) values('00000500','00000000','云计算&大数据',500);
Insert into `category` (id, parent, name, sort) values('00000501','00000500','大数据',501);
Insert into `category` (id, parent, name, sort) values('00000502','00000500','Hadoop',502);
Insert into `category` (id, parent, name, sort) values('00000503','00000500','spark',503);
Insert into `category` (id, parent, name, sort) values('00000504','00000500','hbase',504);
Insert into `category` (id, parent, name, sort) values('00000505','00000500','阿里云',505);
Insert into `category` (id, parent, name, sort) values('00000506','00000500','docker',506);
Insert into `category` (id, parent, name, sort) values('00000507','00000500','kubernetes',507);
Insert into `category` (id, parent, name, sort) values('00000600','00000000','运维&测试',600);
Insert into `category` (id, parent, name, sort) values('00000601','00000600','测试',601);
Insert into `category` (id, parent, name, sort) values('00000602','00000600','运维',602);
Insert into `category` (id, parent, name, sort) values('00000603','00000600','Linux',603);
Insert into `category` (id, parent, name, sort) values('00000604','00000600','功能测试',604);
Insert into `category` (id, parent, name, sort) values('00000605','00000600','性能测试',605);
Insert into `category` (id, parent, name, sort) values('00000700','00000000','数据库',700);
Insert into `category` (id, parent, name, sort) values('00000701','00000700','MySQL',701);
Insert into `category` (id, parent, name, sort) values('00000702','00000700','Redis',702);
Insert into `category` (id, parent, name, sort) values('00000703','00000700','MongoDB',703);

# 课程分类
drop table  if exists `course_category`;
create table `course_category`(
`id` char(8) not null default ' ' comment 'id',
`course_id` char(8) comment '课程|course.id',
`category_id` char(8) comment '分类|category.id',
primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='课程分类';

# 课程内容
drop table if exists `course_content`;
create table `course_content`(
`id` char(8) not null default ' ' comment '课程id',
`content` mediumtext not null  comment '课程内容',
primary key (id)
)engine=innodb default charset=utf8mb4 comment='课程内容';

# 讲师
drop table if exists `teacher`;
create table `teacher`(
`id` char(8) not null default ' ' comment  '讲师id',
`name` varchar(50) not null comment '姓名',
`nickname` varchar(50) comment '昵称',
`image` varchar(100) comment '头像',
`position` varchar(50) comment '职位',
`motto` varchar(50) comment '座右铭',
`intro` varchar(500) comment '间介',
primary key (id)
)engine=innodb default charset=utf8mb4 comment='讲师';


