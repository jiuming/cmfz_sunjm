/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : cmfz

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-09-28 19:17:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` varchar(36) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` varchar(36) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `cover` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `broadcast` varchar(50) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `crea_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album
-- ----------------------------
INSERT INTO `album` VALUES ('1', 'album1', '15694023567572.jpg', '故事', '78', null, '测试转啊及', null);
INSERT INTO `album` VALUES ('2', 'album2', '15695034145161.jpg', 'sunjium', '45', null, '测试内容', null);
INSERT INTO `album` VALUES ('ec2823f35b7a4cd18dc95a92edd7f4e1', '熊猫', '15694006151021.jpg', 'jiuming', '98', null, '测试内容', '2019-09-25 16:36:55');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` varchar(36) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `content` longtext,
  `crea_date` datetime DEFAULT NULL,
  `guru_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('61964c795c744bd681544559a4aabbac', '红楼梦', '曹雪芹', '红楼梦红楼梦红楼梦红楼梦红楼梦<span>石头记&nbsp;</span><span>石头记&nbsp;</span><span>石头记&nbsp;</span><span>石头记&nbsp;</span><span>石头记&nbsp;</span><span>石头记&nbsp;</span>', '2019-09-27 22:27:52', '2');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` varchar(36) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `img_path` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  `up_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES ('27ceb74e121b4127a2174b8c47e61329', '十八罗汉', '1568605713131ceshi2.PNG', '', '-1', '2019-09-16 11:48:33');
INSERT INTO `banner` VALUES ('42fe6450daa14400913f993ff1026a2a', '阿斯蒂芬', '1568603105108ceshi2.PNG', '叫阿斯蒂芬估计', '-1', '2019-09-16 16:18:48');
INSERT INTO `banner` VALUES ('50c0066f78fa43708d891cd256ba020c', '阿斯蒂芬', '1568624453451ceshi2.PNG', '委员', '1', '2019-09-16 16:18:27');
INSERT INTO `banner` VALUES ('8a2095075352476a90dcd52a35f3a7e2', null, '15686218919131.jpg', '这时正是那个', '-1', '2019-09-16 16:18:11');
INSERT INTO `banner` VALUES ('8b2b4ffdbd124da7a8086088abe67481', '', '15686238861512.jpg', '', '1', '2019-09-16 16:51:26');
INSERT INTO `banner` VALUES ('b0a391e4b96d437483d4422b78aac47f', null, '1568604010796ceshi2.PNG', '', '-1', '2019-09-16 11:20:10');
INSERT INTO `banner` VALUES ('f0789ac4d4544646a0ed305a519c8e81', null, '15686034334291.jpg', '', '1', '2019-09-16 11:10:33');
INSERT INTO `banner` VALUES ('f88df88f05454a949972538587561565', null, '15685228024352.jpg', '', '1', '2019-09-15 12:46:42');

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `size` varchar(50) DEFAULT NULL,
  `duration` varchar(20) DEFAULT NULL,
  `up_date` datetime DEFAULT NULL,
  `album_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('2', null, null, null, null, null, '2');
INSERT INTO `chapter` VALUES ('3fa506b829914214a6a43a62f7213d77', '', '1568708522553qifengle起风了.mp3', '12.35MB', '5分23秒', '2019-09-17 16:22:01', '2');
INSERT INTO `chapter` VALUES ('66309d36bb88454382649c40aff81af5', '', '1568708453934qifengle.mp3', '12.35MB', '5分23秒', '2019-09-17 16:20:53', '2');
INSERT INTO `chapter` VALUES ('aafabc2083564ee7a5a301fade9fee0d', '', '1568703687355买辣椒也用券+-+起风了(原版非新改版).mp3', '12.35MB', '5分23秒', '2019-09-17 15:01:26', '09ba5bb1e72148f6b04a7f4ffcd1a59f');
INSERT INTO `chapter` VALUES ('c244fcffb00c4e2c848e3f8c4504730d', '', 'C:\\fakepath\\莫艳琳 - 风吹麦浪(Live).wav', null, null, '2019-09-17 15:01:05', '09ba5bb1e72148f6b04a7f4ffcd1a59f');
INSERT INTO `chapter` VALUES ('cbc9e0b0effb41e3a9a5db9e2ef6acf7', '张三', '1569396101238qifengle.mp3', '12.35MB', '5分23秒', '2019-09-25 15:21:40', '1');

-- ----------------------------
-- Table structure for counter
-- ----------------------------
DROP TABLE IF EXISTS `counter`;
CREATE TABLE `counter` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `count` int(10) DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `task_id` varchar(36) DEFAULT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of counter
-- ----------------------------

-- ----------------------------
-- Table structure for guru
-- ----------------------------
DROP TABLE IF EXISTS `guru`;
CREATE TABLE `guru` (
  `id` varchar(36) NOT NULL,
  `law_number` varchar(50) DEFAULT NULL,
  `avatar` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `salt` varchar(36) DEFAULT NULL,
  `crea_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of guru
-- ----------------------------

-- ----------------------------
-- Table structure for month
-- ----------------------------
DROP TABLE IF EXISTS `month`;
CREATE TABLE `month` (
  `id` int(11) NOT NULL,
  `name` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of month
-- ----------------------------
INSERT INTO `month` VALUES ('1', '1');
INSERT INTO `month` VALUES ('2', '2');
INSERT INTO `month` VALUES ('3', '3');
INSERT INTO `month` VALUES ('4', '4');
INSERT INTO `month` VALUES ('5', '5');
INSERT INTO `month` VALUES ('6', '6');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `mark` varchar(50) DEFAULT NULL,
  `crea_date` datetime DEFAULT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(36) NOT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `salt` varchar(36) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `law_name` varchar(50) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `sign` varchar(100) DEFAULT NULL,
  `crea_date` datetime DEFAULT NULL,
  `guru_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1568634772739ceshi2.PNG', null, null, null, '-1', null, null, '男', '北京', null, '2019-02-14 19:45:21', null);
INSERT INTO `user` VALUES ('2', '1568639181266ceshi2.PNG', null, null, null, '-1', null, null, '男', '北京', null, '2019-02-12 19:45:26', null);
INSERT INTO `user` VALUES ('3', '1568805325763ceshi2.PNG', null, null, null, '1', null, null, '男', '山东', null, '2019-04-18 19:45:29', null);
INSERT INTO `user` VALUES ('4', '15686347356562.jpg', null, null, null, '-1', null, null, '女', '上海', null, '2019-05-01 19:45:33', '1');

-- ----------------------------
-- Table structure for usertask
-- ----------------------------
DROP TABLE IF EXISTS `usertask`;
CREATE TABLE `usertask` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `task_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usertask
-- ----------------------------
