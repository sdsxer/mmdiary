/**
	Version: 0.0.0.1
	Date: 2017-09-20
	Author: leon
**/

DROP TABLE IF EXISTS `diary`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(20) NOT NULL DEFAULT '',
	`mobile` CHAR(11) NOT NULL DEFAULT '',
	`password` CHAR(32) NOT NULL DEFAULT '',
  `gender` TINYINT NOT NULL DEFAULT 0,
	`avatar_url` VARCHAR(128) NOT NULL DEFAULT '',
	`testex` VARCHAR(128) NOT NULL DEFAULT '',
	PRIMARY KEY (`id`)
);

CREATE TABLE `diary` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(128) NOT NULL DEFAULT '',
	`content` VARCHAR(1024) NOT NULL DEFAULT '',
	`cover_url` VARCHAR(128) NOT NULL DEFAULT '',
	`user_id` BIGINT(20) NOT NULL,
	`site_url` INT,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
);

CREATE TABLE `comment` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`content` VARCHAR(512) NOT NULL DEFAULT '',
	`diary_id` BIGINT(20) NOT NULL,
	`user_id` BIGINT(20) NOT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`diary_id`) REFERENCES `diary`(`id`),
	FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
);