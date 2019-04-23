/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : mall

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 04/23/2019 23:10:33 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_attribute`
-- ----------------------------
DROP TABLE IF EXISTS `t_attribute`;
CREATE TABLE `t_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_attribute`
-- ----------------------------
BEGIN;
INSERT INTO `t_attribute` VALUES ('11', '衣服', '2019-04-16 10:04:07', '2019-04-16 10:04:07');
COMMIT;

-- ----------------------------
--  Table structure for `t_brand`
-- ----------------------------
DROP TABLE IF EXISTS `t_brand`;
CREATE TABLE `t_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `first_char` char(50) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `brand_company` varchar(255) DEFAULT NULL,
  `brand_logo` varchar(255) DEFAULT NULL,
  `brand_big_icon` varchar(255) DEFAULT NULL,
  `show` char(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_brand`
-- ----------------------------
BEGIN;
INSERT INTO `t_brand` VALUES ('4', '小米', 'M', '1', '小米', '/image/25f1ca4a-faa7-43dd-b3c6-8ed0d340f237.png', '/image/94203984-30eb-456f-8e27-ba64b987fc85.png', '1', '2019-04-12 08:17:45', '2019-04-23 09:15:30');
COMMIT;

-- ----------------------------
--  Table structure for `t_goods`
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `brand_id` int(11) DEFAULT NULL,
  `sub_head` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `item_num` varchar(11) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `market_price` varchar(255) DEFAULT NULL,
  `stock` int(255) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `order_num` char(50) DEFAULT NULL,
  `picture` text,
  `detail` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_goods`
-- ----------------------------
BEGIN;
INSERT INTO `t_goods` VALUES ('6', '小米9手机', '1', '1', '小米9,值得拥有', 'cc', 'er45', '1', '1.2', '123', '3.21', '1', null, null, '2019-04-14 01:55:58', '2019-04-14 01:55:58');
COMMIT;

-- ----------------------------
--  Table structure for `t_goods_sku`
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_sku`;
CREATE TABLE `t_goods_sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) DEFAULT NULL,
  `sku_id` bigint(20) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `stock` varchar(255) DEFAULT NULL,
  `stock_alarm` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_property`
-- ----------------------------
DROP TABLE IF EXISTS `t_property`;
CREATE TABLE `t_property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `property_type` tinyint(1) DEFAULT NULL,
  `attribute_id` int(11) DEFAULT NULL,
  `need_pic` tinyint(1) DEFAULT NULL,
  `properties` varchar(255) DEFAULT NULL,
  `select_model` int(11) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_property`
-- ----------------------------
BEGIN;
INSERT INTO `t_property` VALUES ('11', '颜色', '1', '11', '1', '红色,黑色,黄色', '2', '1', '2019-04-16 10:12:28', '2019-04-16 10:12:28'), ('14', '尺寸', '1', '11', '0', 'X,XX,XL,XXL', '1', '2', '2019-04-16 10:16:23', '2019-04-23 09:23:05'), ('15', '屏幕尺寸', '2', '11', '0', '5.0,6.0,7.0', '1', '1', '2019-04-16 10:20:33', '2019-04-16 10:20:33'), ('16', '网络', '1', '0', '0', '4G,5G', '1', '2', '2019-04-16 10:21:34', '2019-04-16 10:21:34'), ('17', '网络', '2', '11', '0', '4G,5G', '1', '2', '2019-04-16 10:22:18', '2019-04-16 10:22:18'), ('18', '系统', '1', '0', '0', 'Andriod,IOS', '1', '3', '2019-04-16 10:22:58', '2019-04-16 10:22:58'), ('19', '系统', '2', '11', '0', 'Andriod,IOS', '1', '3', '2019-04-16 10:28:25', '2019-04-16 10:28:25');
COMMIT;

-- ----------------------------
--  Table structure for `t_sku`
-- ----------------------------
DROP TABLE IF EXISTS `t_sku`;
CREATE TABLE `t_sku` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `param` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_spu`
-- ----------------------------
DROP TABLE IF EXISTS `t_spu`;
CREATE TABLE `t_spu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `spu` text,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order` tinyint(4) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_type`
-- ----------------------------
BEGIN;
INSERT INTO `t_type` VALUES ('5', null, '蔬菜', '2', '斤', '2019-04-12', '2019-04-12');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
