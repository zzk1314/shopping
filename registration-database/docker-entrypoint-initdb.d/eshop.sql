/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : eshop

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-03-05 19:55:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'tuShOfiBrA8+br7ENrMS8A==');
INSERT INTO `admin` VALUES ('2', '1', 'iUOoOdMAl7FsB1Kig37hmg==');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '奔驰');
INSERT INTO `category` VALUES ('2', '宝马');
INSERT INTO `category` VALUES ('3', '奥迪');
INSERT INTO `category` VALUES ('4', '其他');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `product_id` int(11) DEFAULT NULL,
  `reply` text,
  `replytime` varchar(255) DEFAULT NULL,
  `texttime` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('2', '111', '1', null, null, '2018-02-25 22:16:48', '1');
INSERT INTO `comments` VALUES ('3', 'good', '7', null, null, '2018-02-27 21:02:01', '1');
INSERT INTO `comments` VALUES ('4', 'good', '9', null, null, '2018-03-05 19:54:08', '1');

-- ----------------------------
-- Table structure for indent
-- ----------------------------
DROP TABLE IF EXISTS `indent`;
CREATE TABLE `indent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` float DEFAULT NULL COMMENT '总价',
  `amount` int(11) DEFAULT NULL COMMENT '商品总数',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态(1未处理/2已处理)',
  `systime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `user_id` int(11) DEFAULT NULL COMMENT '下单用户',
  `address` varchar(255) DEFAULT NULL,
  `paytype` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of indent
-- ----------------------------
INSERT INTO `indent` VALUES ('1', '253.32', '2', '2', '2018-02-18 15:11:25', '1', '12', '1');
INSERT INTO `indent` VALUES ('3', '166.98', '1', '0', '2018-02-18 15:11:25', '1', '北京', '2');
INSERT INTO `indent` VALUES ('4', '335.61', '2', '0', '2018-02-18 15:11:25', '1', '北京', '1');
INSERT INTO `indent` VALUES ('5', '96.51', '1', '2', '2018-01-18 15:33:51', '1', '北京', '1');
INSERT INTO `indent` VALUES ('6', '96.51', '1', '2', '2018-02-25 16:38:21', '1', '北京', '1');
INSERT INTO `indent` VALUES ('7', '6899.99', '1', '0', '2018-02-25 16:44:20', '1', '北京', '1');
INSERT INTO `indent` VALUES ('8', '2093.17', '1', '0', '2018-02-27 21:00:00', '1', '北京', '1');
INSERT INTO `indent` VALUES ('9', '255.62', '1', '0', '2018-03-05 19:52:25', '1', '北京', '1');

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` float DEFAULT NULL COMMENT '购买时价格',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  `indent_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO `items` VALUES ('1', '111', '11', '11', '1', null);
INSERT INTO `items` VALUES ('2', '126.66', '2', '1', '13', '1');
INSERT INTO `items` VALUES ('5', '79.99', '1', '4', '12', '1');
INSERT INTO `items` VALUES ('6', '255.62', '1', '4', '9', '1');
INSERT INTO `items` VALUES ('7', '96.51', '1', '5', '16', '1');
INSERT INTO `items` VALUES ('8', '96.51', '1', '6', '16', '1');
INSERT INTO `items` VALUES ('9', '6899.99', '1', '7', '1', '1');
INSERT INTO `items` VALUES ('10', '2093.17', '1', '8', '7', '1');
INSERT INTO `items` VALUES ('11', '255.62', '1', '9', '9', '1');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `price` float DEFAULT NULL COMMENT '价格',
  `intro` varchar(255) DEFAULT NULL COMMENT '介绍',
  `category_id` int(11) DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '奔驰', '/picture/1.jpg', '6899.99', '简介', '1');
INSERT INTO `product` VALUES ('2', '奔驰', '/picture/2.jpg', '8871.31', '简介', '1');
INSERT INTO `product` VALUES ('3', '奔驰', '/picture/3.jpg', '1299.85', '简介', '1');
INSERT INTO `product` VALUES ('4', '奔驰', '/picture/4.jpg', '350.88', '简介', '1');
INSERT INTO `product` VALUES ('5', '奔驰', '/picture/5.jpg', '589.88', '简介', '1');
INSERT INTO `product` VALUES ('6', '宝马', '/picture/6.jpg', '456.28', '简介', '2');
INSERT INTO `product` VALUES ('7', '宝马', '/picture/7.jpg', '2552.65', '简介', '2');
INSERT INTO `product` VALUES ('8', '宝马', '/picture/8.jpg', '123.45', '简介', '2');
INSERT INTO `product` VALUES ('9', '宝马', '/picture/9.jpg', '255.62', '简介', '2');
INSERT INTO `product` VALUES ('10', '宝马', '/picture/10.jpg', '514.05', '简介', '2');
INSERT INTO `product` VALUES ('11', '奥迪', '/picture/11.jpg', '99.99', '简介', '3');
INSERT INTO `product` VALUES ('12', '奥迪', '/picture/12.jpg', '79.99', '简介', '3');
INSERT INTO `product` VALUES ('13', '奥迪', '/picture/13.jpg', '126.66', '简介', '3');
INSERT INTO `product` VALUES ('14', '奥迪', '/picture/14.jpg', '523.87', '简介', '3');
INSERT INTO `product` VALUES ('15', '奥迪', '/picture/15.jpg', '98.54', '简介', '3');
INSERT INTO `product` VALUES ('16', '其他', '/picture/16.jpg', '122.17', '简介', '4');

-- ----------------------------
-- Table structure for product_new
-- ----------------------------
DROP TABLE IF EXISTS `product_new`;
CREATE TABLE `product_new` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_new
-- ----------------------------
INSERT INTO `product_new` VALUES ('1', '11');
INSERT INTO `product_new` VALUES ('2', '5');
INSERT INTO `product_new` VALUES ('3', '8');
INSERT INTO `product_new` VALUES ('4', '13');

-- ----------------------------
-- Table structure for product_sale
-- ----------------------------
DROP TABLE IF EXISTS `product_sale`;
CREATE TABLE `product_sale` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `discount` tinyint(4) DEFAULT '100' COMMENT '折扣(x%)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_sale
-- ----------------------------
INSERT INTO `product_sale` VALUES ('1', '2', '92');
INSERT INTO `product_sale` VALUES ('2', '7', '82');
INSERT INTO `product_sale` VALUES ('3', '10', '89');
INSERT INTO `product_sale` VALUES ('4', '16', '79');

-- ----------------------------
-- Table structure for product_show
-- ----------------------------
DROP TABLE IF EXISTS `product_show`;
CREATE TABLE `product_show` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `showtext` varchar(255) DEFAULT NULL COMMENT '展示图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_show
-- ----------------------------
INSERT INTO `product_show` VALUES ('1', '14', '推荐理由');
INSERT INTO `product_show` VALUES ('2', '4', '推荐理由');
INSERT INTO `product_show` VALUES ('3', '1', '推荐理由');
INSERT INTO `product_show` VALUES ('4', '6', '推荐理由');
INSERT INTO `product_show` VALUES ('5', '7', '推荐理由');
INSERT INTO `product_show` VALUES ('6', '9', '推荐理由');
INSERT INTO `product_show` VALUES ('7', '12', '推荐理由');
INSERT INTO `product_show` VALUES ('8', '16', '推荐理由');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `email` varchar(255) DEFAULT NULL,
  `meidou` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '1', 'iUOoOdMAl7FsB1Kig37hmg==', '12312341234', '北京北京北京', null, '1');
INSERT INTO `users` VALUES ('2', '2', 'y7Iw49oiM8L3NdfYWQHTLQ==', null, '22', null, '0');
INSERT INTO `users` VALUES ('3', '3', 'y7Iw49oiM8L3NdfYWQHTLQ==', null, '33', null, '0');

-- ----------------------------
-- View structure for productsearchview
-- ----------------------------
DROP VIEW IF EXISTS `productsearchview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `productsearchview` AS select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`cover` AS `cover`,`a`.`price` AS `price`,`a`.`intro` AS `intro`,`a`.`category_id` AS `category_id`,`b`.`name` AS `categoryName` from (`product` `a` left join `category` `b` on((`a`.`category_id` = `b`.`id`))) ;
