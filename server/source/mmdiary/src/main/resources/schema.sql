drop table if exists user;
drop table if exists diary;
drop table if exists comment;

create table user (
	id bigint not null auto_increment,
	name varchar(20) not null,
	mobile char(11) not null,
	password char(32) not null,
  gender tinyint not null,
	primary key (id)
);

create table diary (
	id bigint not null auto_increment,
	title varchar(20) not null,
	content char(11) not null,
	password char(32) not null,
  gender tinyint not null,
	primary key (id)
);

create table comment (
	id bigint not null auto_increment,
	content varchar(20) not null,
	primary key (id)
);