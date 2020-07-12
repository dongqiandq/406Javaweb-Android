
drop table if exists user;
drop table if exists article;
drop table if exists comment;
drop table if exists moving;
drop table if exists galaxy;
drop table if exists recollection;
drop table if exists words;
drop table if exists flower;


CREATE TABLE `user` (
`id`  int NOT NULL AUTO_INCREMENT ,
`telephone`  varchar(20) NULL ,
`password`  varchar(30) NULL ,
`name`  varchar(30) NULL ,
`sex`  int NULL ,
`birthday`  date NULL ,
`area`  varchar(50) NULL ,
`introduction`  varchar(3000) NULL ,
`header`  varchar(500) NULL ,
PRIMARY KEY (`id`)
);

CREATE TABLE `article` (
`id`  int NOT NULL AUTO_INCREMENT ,
`tag`  int NULL ,
`picture`  varchar(50) NULL ,
`title`  varchar(200) NULL ,
`time`  date NULL ,
`content`  varchar(3000) NULL ,
`visitors`  int NULL ,
`candle`  int NULL ,
PRIMARY KEY (`id`)
)
;


CREATE TABLE `comment` (
`id`  int NOT NULL AUTO_INCREMENT ,
`user_id`  int NULL ,
`article_id`  int NULL ,
`content`  varchar(200) NULL ,
PRIMARY KEY (`id`)
)
;

CREATE TABLE `moving` (
`id`  int NOT NULL AUTO_INCREMENT ,
`user_id`  int NULL ,
`article_id`  int NULL ,
PRIMARY KEY (`id`)
)
;

CREATE TABLE `galaxy` (
`id`  int NOT NULL AUTO_INCREMENT ,
`name`  varchar(20) NULL ,
`header`  varchar(500) NULL ,
`birth`  varchar(50) NULL ,
`description`  varchar(50) NULL ,
`life`  varchar(2000) NULL ,
`location`  varchar(200) NULL ,
`visitors`  int NULL ,
`candle`  int NULL ,
`flower`  int NULL ,
`user_id`  int NULL ,
`tag`  int NULL ,
PRIMARY KEY (`id`)
)
;

CREATE TABLE `recollection` (
`id`  int NOT NULL AUTO_INCREMENT ,
`user_id`  int NULL ,
`galaxy_id`  int NULL ,
PRIMARY KEY (`id`)
)
;

CREATE TABLE `words` (
`id`  int NOT NULL AUTO_INCREMENT ,
`user_id`  int NULL ,
`galaxy_id`  int NULL ,
`content`  varchar(200) NULL ,
PRIMARY KEY (`id`)
)
;
 
CREATE TABLE `flower` (
`id`  int NOT NULL AUTO_INCREMENT ,
`name`  varchar(20) NULL ,
`picture`  varchar(50) NULL ,
`flanguage`  varchar(200) NULL ,
PRIMARY KEY (`id`)
)
;

