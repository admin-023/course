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