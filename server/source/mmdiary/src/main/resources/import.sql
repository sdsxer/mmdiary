INSERT INTO `user`(`id`, `name`, `mobile`, `password`, `gender`, `avatar_url`) VALUES (1, '李昂', '18810789600', 'E10ADC3949BA59ABBE56E057F20F883E', 1, '');
INSERT INTO `user`(`id`, `name`, `mobile`, `password`, `gender`, `avatar_url`) VALUES (2, '黄青', '15011210071', 'E10ADC3949BA59ABBE56E057F20F883E', 2, '');
INSERT INTO `user`(`id`, `name`, `mobile`, `password`, `gender`, `avatar_url`) VALUES (3, '王凤娟', '15506566117', 'E10ADC3949BA59ABBE56E057F20F883E', 2, '');
INSERT INTO `user`(`id`, `name`, `mobile`, `password`, `gender`, `avatar_url`) VALUES (4, '李晓益', '13583029338', 'E10ADC3949BA59ABBE56E057F20F883E', 1, '');

INSERT INTO `diary`(`id`, `title`, `content`, `cover_url`, `create_time`, `last_modified_time`, `user_id`) VALUES (1, '宝宝出生啦', '来个出生图片看一下', '', '2017-06-10 06:22:11', '2017-06-10 06:22:11', 1);
INSERT INTO `diary`(`id`, `title`, `content`, `cover_url`, `create_time`, `last_modified_time`, `user_id`) VALUES (2, '宝宝1天啦', '来个一天图片看一下', '', '2017-06-11 06:22:11', '2017-06-11 06:22:11', 2);
INSERT INTO `diary`(`id`, `title`, `content`, `cover_url`, `create_time`, `last_modified_time`, `user_id`) VALUES (3, '宝宝2天啦', '来个两天图片看一下', '', '2017-06-12 06:22:11', '2017-06-12 06:22:11', 3);
INSERT INTO `diary`(`id`, `title`, `content`, `cover_url`, `create_time`, `last_modified_time`, `user_id`) VALUES (4, '宝宝3天啦', '来个三天图片看一下', '', '2017-06-13 06:22:11', '2017-06-13 06:22:11', 4);
INSERT INTO `diary`(`id`, `title`, `content`, `cover_url`, `create_time`, `last_modified_time`, `user_id`) VALUES (5, '宝宝4天啦', '来个四天图片看一下', '', '2017-06-14 06:22:11', '2017-06-14 06:22:11', 1);
INSERT INTO `diary`(`id`, `title`, `content`, `cover_url`, `create_time`, `last_modified_time`, `user_id`) VALUES (6, '宝宝5天啦', '来个五天图片看一下', '', '2017-06-15 06:22:11', '2017-06-15 06:22:11', 2);
INSERT INTO `diary`(`id`, `title`, `content`, `cover_url`, `create_time`, `last_modified_time`, `user_id`) VALUES (7, '宝宝6天啦', '来个六天图片看一下', '', '2017-06-16 06:22:11', '2017-06-16 06:22:11', 3);
INSERT INTO `diary`(`id`, `title`, `content`, `cover_url`, `create_time`, `last_modified_time`, `user_id`) VALUES (8, '宝宝7天啦', '来个七天图片看一下', '', '2017-06-17 06:22:11', '2017-06-17 06:22:11', 4);
INSERT INTO `diary`(`id`, `title`, `content`, `cover_url`, `create_time`, `last_modified_time`, `user_id`) VALUES (9, '宝宝8天啦', '来个八天图片看一下', '', '2017-06-18 06:22:11', '2017-06-18 06:22:11', 1);
INSERT INTO `diary`(`id`, `title`, `content`, `cover_url`, `create_time`, `last_modified_time`, `user_id`) VALUES (10, '宝宝9天啦', '来个九天图片看一下', '', '2017-06-19 06:22:11', '2017-06-19 06:22:11', 2);

INSERT INTO `comment`(`id`, `content`, `create_time`, `last_modified_time`, `user_id`, `diary_id`) VALUES (1, '哈哈', '2017-06-10 06:22:11', '2017-06-10 06:22:11', 1, 1);
INSERT INTO `comment`(`id`, `content`, `create_time`, `last_modified_time`, `user_id`, `diary_id`) VALUES (2, '呵呵', '2017-06-10 06:22:11', '2017-06-10 06:22:11', 1, 1);
INSERT INTO `comment`(`id`, `content`, `create_time`, `last_modified_time`, `user_id`, `diary_id`) VALUES (3, '哦哦', '2017-06-10 06:22:11', '2017-06-10 06:22:11', 1, 1);
INSERT INTO `comment`(`id`, `content`, `create_time`, `last_modified_time`, `user_id`, `diary_id`) VALUES (4, '啦啦', '2017-06-10 06:22:11', '2017-06-10 06:22:11', 1, 1);