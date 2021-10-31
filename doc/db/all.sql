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