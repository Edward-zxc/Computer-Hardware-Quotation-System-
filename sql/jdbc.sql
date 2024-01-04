/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : jdbc

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 14/11/2023 10:27:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin123', '123456');
INSERT INTO `admin` VALUES (2, 'admin456', '123456');

-- ----------------------------
-- Table structure for adminlog
-- ----------------------------
DROP TABLE IF EXISTS `adminlog`;
CREATE TABLE `adminlog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NULL DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Adminlog_Admin`(`aid`) USING BTREE,
  CONSTRAINT `FK_Adminlog_Admin` FOREIGN KEY (`aid`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adminlog
-- ----------------------------

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `goodsid` int(11) NULL DEFAULT NULL,
  `cnum` int(11) NULL DEFAULT NULL,
  `userid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE,
  INDEX `FK_Cart_Users`(`userid`) USING BTREE,
  INDEX `FK_Cart_Goods`(`goodsid`) USING BTREE,
  CONSTRAINT `FK_Cart_Goods` FOREIGN KEY (`goodsid`) REFERENCES `goods` (`gid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Cart_Users` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (1, 1, 3, 1);
INSERT INTO `cart` VALUES (2, 2, 2, 1);
INSERT INTO `cart` VALUES (3, 1, 5, 2);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gnum` int(11) NULL DEFAULT NULL,
  `gprice` double NULL DEFAULT NULL,
  `typeid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`gid`) USING BTREE,
  INDEX `FK_Goods_GoodsType`(`typeid`) USING BTREE,
  CONSTRAINT `FK_Goods_GoodsType` FOREIGN KEY (`typeid`) REFERENCES `goodstype` (`typeid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '西凤', 200, 158, 1);
INSERT INTO `goods` VALUES (2, '矿泉水', 500, 2, 1);
INSERT INTO `goods` VALUES (3, '可乐', 500, 3, 1);
INSERT INTO `goods` VALUES (4, '薯片', 400, 3.5, 2);
INSERT INTO `goods` VALUES (5, '巧克力', 300, 3, 2);
INSERT INTO `goods` VALUES (6, '牙刷', 400, 3, 3);
INSERT INTO `goods` VALUES (7, '洗面奶', 300, 19, 3);

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype`  (
  `typeid` int(11) NOT NULL AUTO_INCREMENT,
  `typename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`typeid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES (1, '酒水类');
INSERT INTO `goodstype` VALUES (2, '零食类');
INSERT INTO `goodstype` VALUES (3, '日用品');

-- ----------------------------
-- Table structure for shopdetail
-- ----------------------------
DROP TABLE IF EXISTS `shopdetail`;
CREATE TABLE `shopdetail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL,
  `cost` double NULL DEFAULT NULL,
  `time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Shopdetail_Users`(`uid`) USING BTREE,
  CONSTRAINT `FK_Shopdetail_Users` FOREIGN KEY (`uid`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopdetail
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `upwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ustatus` int(11) NULL DEFAULT NULL,
  `uscore` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'zhangsan', '123456', 1, NULL);
INSERT INTO `users` VALUES (2, 'lisi', '147258', 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
