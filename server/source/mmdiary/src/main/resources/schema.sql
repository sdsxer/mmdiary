drop table if exists user;
create table user (
	id bigint not null auto_increment,
	name varchar(20) not null,
	mobile char(11) not null,
	password char(32) not null,
  gender tinyint not null,
	primary key (id)
);