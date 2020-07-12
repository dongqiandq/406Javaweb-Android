/*
Navicat MySQL Data Transfer

Source Server         : hll
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : quietnight

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2020-05-31 23:38:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` int(11) DEFAULT NULL,
  `picture` varchar(50) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `content` varchar(3000) DEFAULT NULL,
  `visitors` int(11) DEFAULT NULL,
  `candle` int(11) DEFAULT NULL,
  `commentNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '2', 't1.jpg', '铭记所有在疫情期间牺牲的医护人员', '2020-04-29', '金银潭医院，是这次疫情阻击战中众人皆知的标志性地点。', '30', '5', '2');
INSERT INTO `article` VALUES ('2', '3', 't2.jpg', '我必须跑得更快，才能跑赢时间，才能从病毒手里抢回更多病人。', '2020-05-10', '连日来，各乡镇、社区工作人员穿梭在各家各户宣传、排查，', '8', '4', '1');
INSERT INTO `article` VALUES ('3', '4', 't3.jpg', '医护人员也应该追加烈士！', '2020-05-20', '1月30日23时30分，卓尼县公安局的视频监控里捕捉到这样一幕画面：', '10', '4', '0');
INSERT INTO `article` VALUES ('4', '1', 't4.jpg', '111', '2020-05-06', 'aca', '102', '3', '4');
INSERT INTO `article` VALUES ('5', '1', 't1.jpg', '范围看能否可如果IEhi喀什地区偶见覅人然后是崔阿和地方不如', '2020-05-12', '并即可那个人那棵树的急哦我解耦人防门外部文件快思聪如果hi分为几个为还未别红日二极管为为偶尔会定而后', '44', '3', '0');
INSERT INTO `article` VALUES ('6', '2', 't5.jpg', '浮世绘v为何物为博迪回复', '2020-05-27', '仍无法且风趣', '3', '3', '0');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `article_id` (`article_id`),
  CONSTRAINT `article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '4', '好');
INSERT INTO `comment` VALUES ('2', '2', '4', '中');
INSERT INTO `comment` VALUES ('3', '2', '2', '差');
INSERT INTO `comment` VALUES ('4', '1', '1', '啦啦啦啦');
INSERT INTO `comment` VALUES ('5', '2', '1', '哈哈哈');
INSERT INTO `comment` VALUES ('7', '1', '4', '很好');
INSERT INTO `comment` VALUES ('8', '1', '4', 'good');

-- ----------------------------
-- Table structure for `flower`
-- ----------------------------
DROP TABLE IF EXISTS `flower`;
CREATE TABLE `flower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `picture` varchar(50) DEFAULT NULL,
  `flanguage` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flower
-- ----------------------------
INSERT INTO `flower` VALUES ('1', '111', null, null);
INSERT INTO `flower` VALUES ('2', '111', null, null);

-- ----------------------------
-- Table structure for `galaxy`
-- ----------------------------
DROP TABLE IF EXISTS `galaxy`;
CREATE TABLE `galaxy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `header` varchar(500) DEFAULT NULL,
  `birth` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `life` varchar(2000) DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `visitors` int(11) DEFAULT NULL,
  `candle` int(11) DEFAULT NULL,
  `flower` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `tag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of galaxy
-- ----------------------------
INSERT INTO `galaxy` VALUES ('1', '111', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `galaxy` VALUES ('2', '222', null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `moving`
-- ----------------------------
DROP TABLE IF EXISTS `moving`;
CREATE TABLE `moving` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of moving
-- ----------------------------
INSERT INTO `moving` VALUES ('3', '1', '4');
INSERT INTO `moving` VALUES ('4', '1', '4');
INSERT INTO `moving` VALUES ('5', '1', '5');
INSERT INTO `moving` VALUES ('6', '1', '2');

-- ----------------------------
-- Table structure for `recollection`
-- ----------------------------
DROP TABLE IF EXISTS `recollection`;
CREATE TABLE `recollection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `galaxy_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recollection
-- ----------------------------

-- ----------------------------
-- Table structure for `track`
-- ----------------------------
DROP TABLE IF EXISTS `track`;
CREATE TABLE `track` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of track
-- ----------------------------
INSERT INTO `track` VALUES ('1', '1', '1');
INSERT INTO `track` VALUES ('2', '1', '2');
INSERT INTO `track` VALUES ('3', '1', '4');
INSERT INTO `track` VALUES ('4', '1', '2');
INSERT INTO `track` VALUES ('5', '1', '4');
INSERT INTO `track` VALUES ('6', '1', '4');
INSERT INTO `track` VALUES ('7', '1', '4');
INSERT INTO `track` VALUES ('8', '1', '4');
INSERT INTO `track` VALUES ('9', '1', '4');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `telephone` varchar(20) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT '怀思',
  `sex` int(11) DEFAULT '0',
  `area` varchar(50) DEFAULT NULL,
  `introduction` varchar(3000) DEFAULT NULL,
  `header` varchar(500) DEFAULT 'ping.png',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '111', '111', 'zs', '0', '河北省石家庄', '石家庄', 'ren1.png');
INSERT INTO `user` VALUES ('2', '222', '111', '李四', '0', '河北省唐山市', '唐山', 'ren2.png');

-- ----------------------------
-- Table structure for `words`
-- ----------------------------
DROP TABLE IF EXISTS `words`;
CREATE TABLE `words` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `galaxy_id` int(11) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of words
-- ----------------------------
