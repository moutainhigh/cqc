/*
 Navicat Premium Data Transfer

 Source Server         : 百度云
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 182.61.134.5:3306
 Source Schema         : cqc

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 07/02/2020 19:09:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bank
-- ----------------------------
DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID，主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '银行名称',
  `logo` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '银行logo',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '银行' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bank
-- ----------------------------
INSERT INTO `bank` VALUES ('1', '中国农业银行', 'img/ABC.png');
INSERT INTO `bank` VALUES ('2', '中国建设银行', 'img/js.png');
INSERT INTO `bank` VALUES ('3', '中国银行', 'img/China.png');
INSERT INTO `bank` VALUES ('4', '招商银行', 'img/zs.png');

-- ----------------------------
-- Table structure for faq
-- ----------------------------
DROP TABLE IF EXISTS `faq`;
CREATE TABLE `faq`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'ID, 主键',
  `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `img` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `author` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建者 后台用户账号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `editor` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修改者 后台用户账号',
  `modify_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态 1-有效 0无效',
  `display_order` int(255) NULL DEFAULT 0 COMMENT '排序 升序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '常见问题' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of faq
-- ----------------------------
INSERT INTO `faq` VALUES ('1', '注册', '/img/pro1.png', '<p><em></em>邀请码有效期十分钟，请在拿到邀请码后第一时间注册</p>\r\n                        <p><em></em>手机一天只能接收3次验证码</p>\r\n                        <p><em></em>注册手机号码必须和实名制手机号码一致</p>', 'admin', '2020-01-16 23:14:13', NULL, '2020-01-19 17:38:06', 1, 1);
INSERT INTO `faq` VALUES ('2', '实名认证', './img/pro1.png', '<p><em></em>实名信息必须要真实，一旦确认无法更改</p>\r\n                        <p><em></em>手机号码必须和注册号码一致</p>', 'admin', '2020-01-16 23:14:31', NULL, '2020-01-19 17:38:13', 1, 2);
INSERT INTO `faq` VALUES ('3', '添加银行卡', NULL, '<p><em></em>绑定的银行卡必须和实名制的信息相符</p>\r\n                        <p><em></em>一个账户可以绑定多张银行卡</p>', 'admin', '2020-01-16 23:14:50', NULL, '2020-01-18 00:14:17', 1, 3);
INSERT INTO `faq` VALUES ('4', '绑定二维码', NULL, '<p><em></em>收款账号名称的格式只允许是数字加字幕组合，且要以字母开头</p>\r\n                        <p><em></em>设置收款账号的时候一定要记得对应收款二维码是哪个账号</p>\r\n                        <p><em></em>上传二维码之后要自己查看二维码是否正确</p>', 'admin', '2020-01-16 23:15:06', NULL, '2020-01-18 00:14:18', 1, 4);
INSERT INTO `faq` VALUES ('5', '充值', NULL, '<p><em></em>每次充值需要打开充值页面查看最新的银行卡账号，不能保存账号存款</p>\r\n                        <p><em></em>不可以代充，您付款的账号和平台绑定的账号必须一致的</p>\r\n                        <p><em></em>存款金额必须和提交订单的金额一致，否则无法到账</p>', 'admin', '2020-01-16 23:15:26', NULL, '2020-01-18 00:14:20', 1, 5);
INSERT INTO `faq` VALUES ('6', '做单', NULL, '<p><em></em>您在抢单的过程中，如果有出现重复扫码、提交但是修改了金额或者超市支付的，一定要在第一时间联系在线客服帮你处理</p>\r\n                        <p><em></em>您必须时刻检查自己的二维码是否异常，及时更换，以便于能收到款</p>\r\n                        <p><em></em>不能回复付款方留言，一旦发现，您的会员账号将永久冻结，您的会员余额将不予出款</p>\r\n                        <p><em></em>如果付款方给收款方留言没有到账问题，一律不回，也不要退款给会员，要第一时间向导师反馈，并且配合处理问题</p>\r\n                        <p><em></em>必须在清醒时抢单，避免在情绪不稳定，喝酒后等时间段抢单，否则会导致平台出现损失或严重平台运行，情况严重会冻结您的会员账号</p>\r\n                        <p><em></em>受到款项，必须及时点击确认收款，不给平台设置不必要的阻碍</p>', 'admin', '2020-01-16 23:15:47', NULL, '2020-01-18 00:14:21', 1, 6);
INSERT INTO `faq` VALUES ('7', '提现', NULL, '<p><em></em>除出款高峰期外，提现十分钟内到账</p>', 'admin', '2020-01-16 23:16:08', NULL, '2020-01-19 17:42:07', 1, 7);

-- ----------------------------
-- Table structure for invite_code
-- ----------------------------
DROP TABLE IF EXISTS `invite_code`;
CREATE TABLE `invite_code`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `invite_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '邀请码，唯一',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户id',
  `expire` int(11) NULL DEFAULT NULL COMMENT '过期时间',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_invite_code`(`invite_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of invite_code
-- ----------------------------
INSERT INTO `invite_code` VALUES ('c5a2d09d5b3779b4155058860ce52469', 'MA35U2J3', 'b7353d3952169bfdb1a33e256eaad180', 1580799367, '2020-02-04 14:46:06');
INSERT INTO `invite_code` VALUES ('dc2d8b1d1f112a486804e749c31cf451', 'CDBU8U2M', '257ad4a0ba8003cd3a533f5ee662b9da', 1580795346, '2020-02-04 13:39:06');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键，ID',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '用户id',
  `type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1-佣金加成 2-全天佣金加成 3-重要通知 4-夜间佣金加成 5-全天加成 6-夜间活动 7-温馨提示 8-爆单提醒 9-同城派单提醒 10-充值注意事项 10-信用积分说明',
  `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `author` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建者 后台用户账号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态：0-无效 1-通过',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '消息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('070aac38c4f8c22a0f6d7e323badd1cd', '', 6, 'qaaa', '1111222', 'admin', '2020-02-02 18:30:38', 1);
INSERT INTO `message` VALUES ('0c8310dd3ffe0cdc81e9f1900b933a3b', '', 3, '1111111', '1111111111', 'admin', '2020-01-23 23:05:55', 1);
INSERT INTO `message` VALUES ('1', '1', 1, '一个提醒', '这是内容', NULL, '2020-01-22 10:01:21', 1);
INSERT INTO `message` VALUES ('2', '1', 2, '全天加成说明', '全天做单的话就是这样', NULL, '2020-01-22 10:20:22', 1);
INSERT INTO `message` VALUES ('2fb549b6547a3c6e2fdcfb549270ae8c', '', 3, '测试-重要通知', '重要通知', 'admin', '2020-01-24 23:37:27', 1);
INSERT INTO `message` VALUES ('b44e44aa83376c5e23c54c68523350ab', '', 5, '添加一条消息', '这是第3条消息了不要管我', 'admin', '2020-01-22 12:14:53', 1);
INSERT INTO `message` VALUES ('e89911c8055860a2ff8468fda6d76d30', '', 1, '1111', '1111', 'admin', '2020-01-23 23:05:18', 1);
INSERT INTO `message` VALUES ('ee0d0562364fce54ff0f2e5aa38a515a', '', 1, '测试-佣金加成消息', '佣金加成消息', 'admin', '2020-01-24 23:36:37', 1);
INSERT INTO `message` VALUES ('fccfdb7da9893ad648e3eeafae2a5e77', '', 1, '1', '2', 'admin', '2020-02-02 18:31:01', 1);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键，ID',
  `type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1-首页弹窗 2-慢放 3-取款弹窗',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态：0-无效 1-通过',
  `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `author` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建者 后台用户账号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('494b1852eacc0de8e7f718103c005c52', 3, 1, '测试-存取款公告', '存取款公告', 'admin', '2020-01-24 23:39:45');
INSERT INTO `notice` VALUES ('5063ddd010bb9072a9cd5dab9aaf48ce', 2, 1, '测试-首页慢放公告', '首页慢放公告', 'admin', '2020-01-24 23:39:13');
INSERT INTO `notice` VALUES ('915f45492cd25e6cb2a15064b49d3163', 1, 1, 'ww', '1122', 'admin', '2020-02-02 18:32:57');
INSERT INTO `notice` VALUES ('a4ad266da5f2260e8a03f413c966288d', 2, 1, '1q', '11', 'admin', '2020-02-02 18:34:00');
INSERT INTO `notice` VALUES ('f8e3b5e099b7800fcf099ae8367bd729', 3, 1, '1qq', 'aa', 'admin', '2020-02-02 18:34:51');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'ID，主键',
  `order_sn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单号',
  `seller_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '放单用户id',
  `publisher` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发布者昵称',
  `channel` tinyint(32) NOT NULL COMMENT '渠道 1 支付宝 2微信',
  `amount` decimal(16, 4) NOT NULL DEFAULT 0.0000 COMMENT '金额',
  `status` tinyint(1) NOT NULL DEFAULT -1 COMMENT '-1放单中 0 代付款 1已付款 2已入账 3订单取消 4异常 5已过期 6金额不符 7付款失败',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '抢单用户id',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '抢单用户账号',
  `receive_code_img` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '收款码图片',
  `receive_code_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '收款码id',
  `rate` decimal(5, 4) NULL DEFAULT 0.0000 COMMENT '费率',
  `income` decimal(16, 8) NULL DEFAULT 0.00000000 COMMENT '佣金',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `expire_time` timestamp(0) NULL DEFAULT NULL COMMENT '过期时间',
  `pay_time` timestamp(0) NULL DEFAULT NULL COMMENT '付款时间',
  `buy_time` timestamp(0) NULL DEFAULT NULL COMMENT '抢单时间',
  `country` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '国家名称',
  `province` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '省',
  `city` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '市',
  `region` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '区',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_order_sn`(`order_sn`) USING BTREE COMMENT '订单号唯一'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('2f05094d06ea7d336841693ab1127d62', '202002041609531000001205795124', '', '山哥', 2, 50.0000, 1, '2d74b21b4d8a94f4de5ef63513576d7d', 'test_two', 'http://182.61.134.5:8092/img/receive/9fe151ddb155d1ae229384767c2e407.png', '08a3d22453f274498c38d4e6c65b7489', 0.0080, 0.40000000, '2020-02-04 16:09:53', NULL, '2020-02-04 16:20:37', '2020-02-04 16:19:11', '中国', '湖南省', '衡阳市', '雁峰区');
INSERT INTO `order` VALUES ('64356d9265d8cd64befca5fa1391efca', '202002041654341000000688911084', '', '小华', 2, 20.0000, 1, '2d74b21b4d8a94f4de5ef63513576d7d', 'test_two', 'http://182.61.134.5:8092/img/receive/9fe151ddb155d1ae229384767c2e407.png', '08a3d22453f274498c38d4e6c65b7489', 0.0080, 0.16000000, '2020-02-04 16:54:34', NULL, '2020-02-04 16:55:34', '2020-02-04 16:54:57', '中国', '湖南省', '衡阳市', '雁峰区');

-- ----------------------------
-- Table structure for order_publish
-- ----------------------------
DROP TABLE IF EXISTS `order_publish`;
CREATE TABLE `order_publish`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'ID，主键',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '放单用户id',
  `publisher` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发布者昵称',
  `order_sn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单号',
  `channel` tinyint(32) NOT NULL COMMENT '渠道 1 支付宝 2微信',
  `amount` decimal(16, 4) NOT NULL DEFAULT 0.0000 COMMENT '金额',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0 代付款 1被抢  2已支付 3已入账 3订单已过期',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `pay_time` timestamp(0) NULL DEFAULT NULL COMMENT '付款时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rate
-- ----------------------------
DROP TABLE IF EXISTS `rate`;
CREATE TABLE `rate`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id，硬编码 1-支付宝 2-微信',
  `rate` decimal(8, 7) NOT NULL DEFAULT 0.0000000 COMMENT '费率',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rate
-- ----------------------------
INSERT INTO `rate` VALUES ('1', 0.0100000);
INSERT INTO `rate` VALUES ('2', 0.0100000);

-- ----------------------------
-- Table structure for receive_code
-- ----------------------------
DROP TABLE IF EXISTS `receive_code`;
CREATE TABLE `receive_code`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户id',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户账号',
  `channel` tinyint(1) NOT NULL DEFAULT 1 COMMENT '收款渠道 1-支付宝 2-微信',
  `receive_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收款人名称',
  `code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '收款码',
  `code_img` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '收款码图片',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '1 支付宝 2微信',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态 1-启动 0关闭',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of receive_code
-- ----------------------------
INSERT INTO `receive_code` VALUES ('08a3d22453f274498c38d4e6c65b7489', '2d74b21b4d8a94f4de5ef63513576d7d', 'test_two', 2, '王乐之', '', 'http://182.61.134.5:8092/img/receive/9fe151ddb155d1ae229384767c2e407.png', 0, 1);
INSERT INTO `receive_code` VALUES ('5431aab92c038262a64cab615669b8a2', '2d74b21b4d8a94f4de5ef63513576d7d', 'test_two', 2, '王', '', 'http://182.61.134.5:8092/img/receive/9fe151ddb155d1ae229384767c2e407.png', 0, 1);
INSERT INTO `receive_code` VALUES ('ae49d9c59bbeb12a37b28257add87ac1', 'b7353d3952169bfdb1a33e256eaad180', 'test_one', 2, '王乐之', '', 'http://182.61.134.5:8092/img/receive/9fe151ddb155d1ae229384767c2e407.png', 0, 1);
INSERT INTO `receive_code` VALUES ('edfef115fd9666b4a2c2923b17301aef', '257ad4a0ba8003cd3a533f5ee662b9da', 'test', 2, '吴', '', 'http://182.61.134.5:8092/img/receive/9fe151ddb155d1ae229384767c2e407.png', 0, 1);

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting`  (
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编码，主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '值',
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
INSERT INTO `ums_admin` VALUES (3, 'admin', '$2a$10$8Cq2GIM6u9SvZ3QC4dD6uOdQEFrYsRxBaEHsP8KoUpBUY/UT1o0Nq', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/170157_yIl3_1767531.jpg', 'admin@163.com', '系统管理员', '系统管理员', '2018-10-08 13:32:47', '2019-03-20 15:38:50', 1);

-- ----------------------------
-- Table structure for ums_admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_login_log`;
CREATE TABLE `ums_admin_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_agent` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器登录类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_admin_login_log
-- ----------------------------
INSERT INTO `ums_admin_login_log` VALUES (21, 3, '2020-01-08 06:05:10', '0:0:0:0:0:0:0:1', NULL, NULL);

-- ----------------------------
-- Table structure for ums_admin_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_permission_relation`;
CREATE TABLE `ums_admin_permission_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NULL DEFAULT NULL,
  `permission_id` bigint(20) NULL DEFAULT NULL,
  `type` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户和权限关系表(除角色中定义的权限以外的加减权限)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role_relation`;
CREATE TABLE `ums_admin_role_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NULL DEFAULT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户和角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_admin_role_relation
-- ----------------------------
INSERT INTO `ums_admin_role_relation` VALUES (13, 3, 1);
INSERT INTO `ums_admin_role_relation` VALUES (15, 3, 2);
INSERT INTO `ums_admin_role_relation` VALUES (16, 3, 4);

-- ----------------------------
-- Table structure for ums_permission
-- ----------------------------
DROP TABLE IF EXISTS `ums_permission`;
CREATE TABLE `ums_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `type` int(1) NULL DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端资源路径',
  `status` int(1) NULL DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_permission
-- ----------------------------
INSERT INTO `ums_permission` VALUES (1, 0, '商品', NULL, NULL, 0, NULL, 1, '2018-09-29 16:15:14', 0);
INSERT INTO `ums_permission` VALUES (2, 1, '商品列表', 'pms:product:read', NULL, 1, '/pms/product/index', 1, '2018-09-29 16:17:01', 0);
INSERT INTO `ums_permission` VALUES (3, 1, '添加商品', 'pms:product:create', NULL, 1, '/pms/product/add', 1, '2018-09-29 16:18:51', 0);
INSERT INTO `ums_permission` VALUES (4, 1, '商品分类', 'pms:productCategory:read', NULL, 1, '/pms/productCate/index', 1, '2018-09-29 16:23:07', 0);
INSERT INTO `ums_permission` VALUES (5, 1, '商品类型', 'pms:productAttribute:read', NULL, 1, '/pms/productAttr/index', 1, '2018-09-29 16:24:43', 0);
INSERT INTO `ums_permission` VALUES (6, 1, '品牌管理', 'pms:brand:read', NULL, 1, '/pms/brand/index', 1, '2018-09-29 16:25:45', 0);
INSERT INTO `ums_permission` VALUES (7, 2, '编辑商品', 'pms:product:update', NULL, 2, '/pms/product/updateProduct', 1, '2018-09-29 16:34:23', 0);
INSERT INTO `ums_permission` VALUES (8, 2, '删除商品', 'pms:product:delete', NULL, 2, '/pms/product/delete', 1, '2018-09-29 16:38:33', 0);
INSERT INTO `ums_permission` VALUES (9, 4, '添加商品分类', 'pms:productCategory:create', NULL, 2, '/pms/productCate/create', 1, '2018-09-29 16:43:23', 0);
INSERT INTO `ums_permission` VALUES (10, 4, '修改商品分类', 'pms:productCategory:update', NULL, 2, '/pms/productCate/update', 1, '2018-09-29 16:43:55', 0);
INSERT INTO `ums_permission` VALUES (11, 4, '删除商品分类', 'pms:productCategory:delete', NULL, 2, '/pms/productAttr/delete', 1, '2018-09-29 16:44:38', 0);
INSERT INTO `ums_permission` VALUES (12, 5, '添加商品类型', 'pms:productAttribute:create', NULL, 2, '/pms/productAttr/create', 1, '2018-09-29 16:45:25', 0);
INSERT INTO `ums_permission` VALUES (13, 5, '修改商品类型', 'pms:productAttribute:update', NULL, 2, '/pms/productAttr/update', 1, '2018-09-29 16:48:08', 0);
INSERT INTO `ums_permission` VALUES (14, 5, '删除商品类型', 'pms:productAttribute:delete', NULL, 2, '/pms/productAttr/delete', 1, '2018-09-29 16:48:44', 0);
INSERT INTO `ums_permission` VALUES (15, 6, '添加品牌', 'pms:brand:create', NULL, 2, '/pms/brand/add', 1, '2018-09-29 16:49:34', 0);
INSERT INTO `ums_permission` VALUES (16, 6, '修改品牌', 'pms:brand:update', NULL, 2, '/pms/brand/update', 1, '2018-09-29 16:50:55', 0);
INSERT INTO `ums_permission` VALUES (17, 6, '删除品牌', 'pms:brand:delete', NULL, 2, '/pms/brand/delete', 1, '2018-09-29 16:50:59', 0);
INSERT INTO `ums_permission` VALUES (18, 0, '首页', NULL, NULL, 0, NULL, 1, '2018-09-29 16:51:57', 0);

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `admin_count` int(11) NULL DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role` VALUES (1, '商品管理员', '商品管理员', 0, '2018-09-30 15:46:11', 1, 0);
INSERT INTO `ums_role` VALUES (2, '商品分类管理员', '商品分类管理员', 0, '2018-09-30 15:53:45', 1, 0);
INSERT INTO `ums_role` VALUES (3, '商品类型管理员', '商品类型管理员', 0, '2018-09-30 15:53:56', 1, 0);
INSERT INTO `ums_role` VALUES (4, '品牌管理员', '品牌管理员', 0, '2018-09-30 15:54:12', 1, 0);

-- ----------------------------
-- Table structure for ums_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_permission_relation`;
CREATE TABLE `ums_role_permission_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `permission_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户角色和权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role_permission_relation
-- ----------------------------
INSERT INTO `ums_role_permission_relation` VALUES (1, 1, 1);
INSERT INTO `ums_role_permission_relation` VALUES (2, 1, 2);
INSERT INTO `ums_role_permission_relation` VALUES (3, 1, 3);
INSERT INTO `ums_role_permission_relation` VALUES (4, 1, 7);
INSERT INTO `ums_role_permission_relation` VALUES (5, 1, 8);
INSERT INTO `ums_role_permission_relation` VALUES (6, 2, 4);
INSERT INTO `ums_role_permission_relation` VALUES (7, 2, 9);
INSERT INTO `ums_role_permission_relation` VALUES (8, 2, 10);
INSERT INTO `ums_role_permission_relation` VALUES (9, 2, 11);
INSERT INTO `ums_role_permission_relation` VALUES (10, 3, 5);
INSERT INTO `ums_role_permission_relation` VALUES (11, 3, 12);
INSERT INTO `ums_role_permission_relation` VALUES (12, 3, 13);
INSERT INTO `ums_role_permission_relation` VALUES (13, 3, 14);
INSERT INTO `ums_role_permission_relation` VALUES (14, 4, 6);
INSERT INTO `ums_role_permission_relation` VALUES (15, 4, 15);
INSERT INTO `ums_role_permission_relation` VALUES (16, 4, 16);
INSERT INTO `ums_role_permission_relation` VALUES (17, 4, 17);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'ID,主键',
  `ref_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '邀请人id',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `pay_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付密码',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '昵称',
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '真实姓名',
  `sex` tinyint(1) NULL DEFAULT 0 COMMENT '性别： 0 女 1 男',
  `img_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `birthdate` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `country` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '国家名称',
  `province` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市',
  `region` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态 -1-删除 0-锁定 1-正常 2封禁',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `del_time` timestamp(0) NULL DEFAULT NULL COMMENT '删除时间',
  `close_time` timestamp(0) NULL DEFAULT NULL COMMENT '封号时间',
  `auto_order_status` bit(1) NOT NULL DEFAULT b'0' COMMENT '抢单状态 0-未自动抢单 1-自动抢单中',
  `google_secret` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '谷歌验证器密钥',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_ref_uer_id`(`ref_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('257ad4a0ba8003cd3a533f5ee662b9da', '', 'test', '', '$2a$10$AYzBNbwCOVZzWXumrgCyT.0sfe2/.nzbmkkYMyZN412uUjWJgN8JC', NULL, '', '', 0, NULL, NULL, NULL, '', NULL, NULL, NULL, 1, '2020-02-03 22:06:13', NULL, NULL, b'0', 'PGSWXWOUBLESQSJT');
INSERT INTO `user` VALUES ('2d74b21b4d8a94f4de5ef63513576d7d', 'b7353d3952169bfdb1a33e256eaad180', 'test_two', '13590303546', '$2a$10$5WofxFD5FIAnvtllFXKVS.CsO17G3bgBnimEMHNYK79VrdtmfWZS2', NULL, '', '', 0, NULL, NULL, NULL, '中国', '湖南省', '衡阳市', '雁峰区', 1, '2020-02-04 14:46:38', NULL, NULL, b'0', '');
INSERT INTO `user` VALUES ('5e94c7ff134f5dd82bc92f49a57c8d15', '', '123456789011', '', '$2a$10$cJIM2RFC.Z80YQQ3LMqvU.aNK6zD/zp.0nYZ8juWSTLlcRwRmM4zG', NULL, '', '', 0, NULL, NULL, NULL, '', NULL, NULL, NULL, 1, '2020-02-05 21:18:09', NULL, NULL, b'0', '');
INSERT INTO `user` VALUES ('b7353d3952169bfdb1a33e256eaad180', '257ad4a0ba8003cd3a533f5ee662b9da', 'test_one', '13590303547', '$2a$10$uqfxieFniQv6zzhhho3uEuIZJ7eixS1u4ZyR1ufMHC63Te7aEfxaG', NULL, '', '', 0, NULL, NULL, NULL, '', NULL, NULL, NULL, 1, '2020-02-04 13:39:59', NULL, NULL, b'0', '');
INSERT INTO `user` VALUES ('f0395ae2f69b257f3597ad2fda6b3ab5', '', 'test_sdfjsfk', '13590303545', '$2a$10$TDSvrw59uXfbshGtsK/P5OkVX3Dp/cuKAFx2Ui7VTnQQopoYZJcWO', NULL, '', '', 0, NULL, NULL, NULL, '', NULL, NULL, NULL, 1, '2020-02-05 18:15:33', NULL, NULL, b'0', '');

-- ----------------------------
-- Table structure for user_bank_card
-- ----------------------------
DROP TABLE IF EXISTS `user_bank_card`;
CREATE TABLE `user_bank_card`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `bank_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '银行名称',
  `bank_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '银行简称',
  `bank_logo` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '银行logo',
  `card_type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '银行卡类型：1-借记卡 2-贷记合一 3-贷记卡',
  `card_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '银行卡号',
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `account_type` tinyint(1) NULL DEFAULT 1 COMMENT '账户类型 1-个人账户 2企业账户',
  `mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态 0认证中 1认证通过 2 不通过',
  `is_default` bit(1) NULL DEFAULT b'0' COMMENT '是否是默认',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_card_no`(`card_no`) USING BTREE COMMENT '银行卡号必须唯一'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户-银行卡' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_bank_card
-- ----------------------------
INSERT INTO `user_bank_card` VALUES ('755f83e1eaa0b5872c9db0746e65f814', '257ad4a0ba8003cd3a533f5ee662b9da', '中国农业银行', '', 'img/ABC.png', 1, '62248904946030499', '', 1, NULL, '2020-02-05 16:16:41', 1, b'0');

-- ----------------------------
-- Table structure for user_date_income
-- ----------------------------
DROP TABLE IF EXISTS `user_date_income`;
CREATE TABLE `user_date_income`  (
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id，联合主键',
  `user_account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日期，联合主键',
  `ref_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '推荐用户id',
  `income` decimal(16, 8) NOT NULL DEFAULT 0.00000000 COMMENT '自身收益',
  `team_income` decimal(16, 8) NULL DEFAULT 0.00000000 COMMENT '团队收益',
  PRIMARY KEY (`user_id`, `date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_date_income
-- ----------------------------
INSERT INTO `user_date_income` VALUES ('257ad4a0ba8003cd3a533f5ee662b9da', 'test', '20200204', '', 0.07000000, 0.07000000);
INSERT INTO `user_date_income` VALUES ('2d74b21b4d8a94f4de5ef63513576d7d', 'test_two', '20200204', 'b7353d3952169bfdb1a33e256eaad180', 0.56000000, 0.56000000);
INSERT INTO `user_date_income` VALUES ('b7353d3952169bfdb1a33e256eaad180', 'test_one', '20200204', '257ad4a0ba8003cd3a533f5ee662b9da', 0.07000000, 0.07000000);

-- ----------------------------
-- Table structure for user_fund
-- ----------------------------
DROP TABLE IF EXISTS `user_fund`;
CREATE TABLE `user_fund`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID，主键',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `balance` decimal(20, 8) NOT NULL DEFAULT 0.00000000 COMMENT '余额',
  `freeze_balance` decimal(20, 8) NOT NULL DEFAULT 0.00000000 COMMENT '冻结金额',
  `available_balance` decimal(20, 8) NOT NULL DEFAULT 0.00000000 COMMENT '可用金额',
  `deposit` decimal(20, 8) NOT NULL DEFAULT 0.00000000 COMMENT '押金',
  `commission` decimal(20, 8) NOT NULL DEFAULT 0.00000000 COMMENT '佣金',
  `cqc` decimal(20, 8) NOT NULL DEFAULT 0.00000000 COMMENT 'cqc金额',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户金额' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_fund
-- ----------------------------
INSERT INTO `user_fund` VALUES ('1', '257ad4a0ba8003cd3a533f5ee662b9da', 0.07000000, 0.00000000, 0.07000000, 0.00000000, 0.00000000, 0.00000000);
INSERT INTO `user_fund` VALUES ('2', '2d74b21b4d8a94f4de5ef63513576d7d', 130.56000000, 0.00000000, 130.56000000, 0.00000000, 0.00000000, 0.00000000);
INSERT INTO `user_fund` VALUES ('3', 'b7353d3952169bfdb1a33e256eaad180', 0.07000000, 0.00000000, 0.07000000, 0.00000000, 0.00000000, 0.00000000);
INSERT INTO `user_fund` VALUES ('cc10d0865652da4c1856be2f40f3fc52', '5e94c7ff134f5dd82bc92f49a57c8d15', 0.00000000, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 0.00000000);
INSERT INTO `user_fund` VALUES ('e4eb29b8479835b923a032fdc4796f23', 'f0395ae2f69b257f3597ad2fda6b3ab5', 0.00000000, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 0.00000000);

-- ----------------------------
-- Table structure for user_fund_record
-- ----------------------------
DROP TABLE IF EXISTS `user_fund_record`;
CREATE TABLE `user_fund_record`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'ID，主键',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户Id',
  `amount` decimal(20, 8) NOT NULL COMMENT '金额',
  `type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '业务类型 1人工加款 2人工扣款 3银行存款 4提现 5提款失败 6确认收款 7任务佣金 8推广佣金 9人工提单 10加成佣金 11激活账号 12直推奖励\r\n13 拼多多佣金 14 拼多多补偿金 15外挂激活',
  `direct` tinyint(255) NOT NULL DEFAULT 1 COMMENT '方向 1 增加 2减少',
  `balance` decimal(20, 8) NOT NULL DEFAULT 0.00000000 COMMENT '当前余额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户-虚拟币记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_fund_record
-- ----------------------------
INSERT INTO `user_fund_record` VALUES ('016af407b9198774a45c94c37a1bee2b', '257ad4a0ba8003cd3a533f5ee662b9da', 0.05000000, 8, 1, 0.05000000, '推广佣金', '2020-02-04 16:21:32');
INSERT INTO `user_fund_record` VALUES ('534c75650d244ab082e02550ca9d3f05', '257ad4a0ba8003cd3a533f5ee662b9da', 0.02000000, 8, 1, 0.07000000, '推广佣金', '2020-02-04 16:55:45');
INSERT INTO `user_fund_record` VALUES ('5b0105137461af8404020ea20d6f5d86', '2d74b21b4d8a94f4de5ef63513576d7d', 0.40000000, 7, 1, 150.40000000, '抢单佣金', '2020-02-04 16:20:36');
INSERT INTO `user_fund_record` VALUES ('6b5e0c6ad1f289fe4a327858c2f141d8', 'b7353d3952169bfdb1a33e256eaad180', 0.05000000, 8, 1, 0.05000000, '推广佣金', '2020-02-04 16:20:59');
INSERT INTO `user_fund_record` VALUES ('94fa2102cdfb6780a3500bb056968af1', '2d74b21b4d8a94f4de5ef63513576d7d', 50.00000000, 6, 2, 150.00000000, '确认付款', '2020-02-04 16:20:35');
INSERT INTO `user_fund_record` VALUES ('96993d6f5573a22ebe09d7ba3f9e6b5d', '2d74b21b4d8a94f4de5ef63513576d7d', 0.16000000, 7, 1, 130.56000000, '抢单佣金', '2020-02-04 16:55:33');
INSERT INTO `user_fund_record` VALUES ('9fb280028bfb658eb7081bba9398bae5', '2d74b21b4d8a94f4de5ef63513576d7d', 200.00000000, 1, 1, 200.00000000, '后台管理员手工充值', '2020-02-04 16:12:49');
INSERT INTO `user_fund_record` VALUES ('b3acce1b9177c17692825365021767d9', '2d74b21b4d8a94f4de5ef63513576d7d', 20.00000000, 6, 2, 130.40000000, '确认付款', '2020-02-04 16:55:32');
INSERT INTO `user_fund_record` VALUES ('eac3d1d9194cdc5c858c2c3fde0fb5f0', 'b7353d3952169bfdb1a33e256eaad180', 0.02000000, 8, 1, 0.07000000, '推广佣金', '2020-02-04 16:55:41');

-- ----------------------------
-- Table structure for user_rate
-- ----------------------------
DROP TABLE IF EXISTS `user_rate`;
CREATE TABLE `user_rate`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'ID，主键',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户id',
  `channel` tinyint(1) NOT NULL COMMENT '1 支付宝 2微信',
  `rate` decimal(5, 4) NOT NULL DEFAULT 0.0000 COMMENT '费率',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户费率' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_rate
-- ----------------------------
INSERT INTO `user_rate` VALUES ('1', '1', 2, 0.0085);
INSERT INTO `user_rate` VALUES ('10c83b533382e1107368c4e79be3b6b9', 'b8860bb194b5409894d68f84d8e509f6', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('1873d3f60fb4940080b368a2ef84ad2b', '8fbe2f5b65eece0a3f7095c1ee23e026', 2, 0.0000);
INSERT INTO `user_rate` VALUES ('1962d99077a7d6d4501d5e2c1ffeb770', '2350cc1c23a050a6b7f43b26779b2eb0', 2, 0.0000);
INSERT INTO `user_rate` VALUES ('2', '1', 1, 0.0084);
INSERT INTO `user_rate` VALUES ('2b9cba5734c947f68a369ddee4c2bedf', '2dc99ce2ac112ad6e4fddafe4430fff3', 2, 0.0000);
INSERT INTO `user_rate` VALUES ('3', '2', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('30f1e2221f2382ac0779b0a99a1a57b4', '6f50411a3c0ec9e95db01c4819376437', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('36fb4dd4663fa8ace6c6634637291252', '32d11153b6a9676620875b08750e153c', 2, 0.0000);
INSERT INTO `user_rate` VALUES ('374a25c79a46c23f995d292164568d78', '9a356b11aeca0661b09a005d7e6457c6', 2, 1.0000);
INSERT INTO `user_rate` VALUES ('3934c151174ee4bcf290a4e008c9755a', 'f410aaa4483927c21bdd7c7ce7d22b24', 2, 0.0000);
INSERT INTO `user_rate` VALUES ('4', '2', 2, 0.0000);
INSERT INTO `user_rate` VALUES ('55e4e4619c619bcec2aec8a202aa2d93', '0b7ee8434341c6490232d21dc97aa430', 2, 0.0000);
INSERT INTO `user_rate` VALUES ('5ff4b144b763c222216c6735cf91c013', 'f410aaa4483927c21bdd7c7ce7d22b24', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('61d3d37417e73191606270a81afe21b9', '53a21ff9ec643be6eff8db808a21b18d', 1, 1.0000);
INSERT INTO `user_rate` VALUES ('64f873bda13341173464651b6408dc13', '257ad4a0ba8003cd3a533f5ee662b9da', 2, 0.0000);
INSERT INTO `user_rate` VALUES ('6c29e03b98a44c0c955cb4c6fa8bbae1', '5e94c7ff134f5dd82bc92f49a57c8d15', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('7ae552c7cdfbd01456cf98c2a5701005', '81eaba47169a917a0590f3b646c7274a', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('7e0e7ce5f93d99eb1ad027044b686883', '0b7ee8434341c6490232d21dc97aa430', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('811fe08515758f9f15c3aabf08edbd23', 'b933daeebaa2d3ab2102a7050d582a21', 2, 0.0000);
INSERT INTO `user_rate` VALUES ('91622e1d6921558bd13e97c5fe7cb067', '257ad4a0ba8003cd3a533f5ee662b9da', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('98da290d6a5442ae6b40309ecf0a5274', '81eaba47169a917a0590f3b646c7274a', 2, 0.0000);
INSERT INTO `user_rate` VALUES ('a4427c558547d64d0c79e67acf881453', 'b8860bb194b5409894d68f84d8e509f6', 2, 0.0000);
INSERT INTO `user_rate` VALUES ('a85180ed929b7a5165b9c4a7140da3df', '5f1ee8d709d7a782f5fc68c63ba3966d', 2, 0.0000);
INSERT INTO `user_rate` VALUES ('aa4de35d17fd95de0c4b2a9e8ee1762a', '5f1ee8d709d7a782f5fc68c63ba3966d', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('b12be83fafc92987b34fd7193a1c939c', '9a356b11aeca0661b09a005d7e6457c6', 1, 1.0000);
INSERT INTO `user_rate` VALUES ('bd76f8a65ab4818d932806f634f9d1f5', '8fbe2f5b65eece0a3f7095c1ee23e026', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('c37dee82174290c38d8f47d31653dd4d', '53a21ff9ec643be6eff8db808a21b18d', 2, 2.0000);
INSERT INTO `user_rate` VALUES ('c5776b3aa04fda6cabd874a6dd52d412', '2dc99ce2ac112ad6e4fddafe4430fff3', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('c82c2bbb209de57e745c7f02332b3dda', '2350cc1c23a050a6b7f43b26779b2eb0', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('cacca74aefad554d2b8fefe7cd88e827', 'b933daeebaa2d3ab2102a7050d582a21', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('deda9c9b5d3a73a2abb26195ac6d21d6', '9df17dc55103608c2b0e71cd6b1c843c', 2, 0.0000);
INSERT INTO `user_rate` VALUES ('e6b2a57646c4c7bb949cff7ce62f9f87', '5e94c7ff134f5dd82bc92f49a57c8d15', 2, 0.0000);
INSERT INTO `user_rate` VALUES ('e864342e48674993021989d5e0339157', '9df17dc55103608c2b0e71cd6b1c843c', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('f4111f2523b285e1dab9f09a06491d88', '32d11153b6a9676620875b08750e153c', 1, 0.0000);
INSERT INTO `user_rate` VALUES ('f9172b16cf7cb746ae4c0c184bd8738c', '6f50411a3c0ec9e95db01c4819376437', 2, 0.0000);

-- ----------------------------
-- Table structure for user_real_audit
-- ----------------------------
DROP TABLE IF EXISTS `user_real_audit`;
CREATE TABLE `user_real_audit`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键，ID',
  `apply_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '申请id',
  `type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1- 人工审核 2-系统审核',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态：1 通过 2不通过',
  `audit_account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核人用户id',
  `audit_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审核时间',
  `audit_opinion` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户实名审核记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_real_info
-- ----------------------------
DROP TABLE IF EXISTS `user_real_info`;
CREATE TABLE `user_real_info`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID，主键',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户Id',
  `type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '认证类型 1-身份证 2-护照',
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `id_number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件号码',
  `img1` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件照1',
  `img2` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件照2',
  `qq` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'qq',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `telephone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '电话',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态 -1删除 0 审核中 1 审核通过 ',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_id_type_status`(`user_id`, `type`, `status`) USING BTREE COMMENT '一个用户只有一种类型的实名信息'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户实名信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_real_info
-- ----------------------------
INSERT INTO `user_real_info` VALUES ('10c9ef84bf6e6c0674dd5a9e32f51ef1', '2350cc1c23a050a6b7f43b26779b2eb0', 1, '张三', '440232432525255265', NULL, NULL, '1111222222', '1234@qq.com', '15627553211', 1, '2020-01-23 23:00:15');
INSERT INTO `user_real_info` VALUES ('6ad10aec7bc775a512d792ef4ab6782c', 'f410aaa4483927c21bdd7c7ce7d22b24', 1, '小王', '430422199408263928', NULL, NULL, '719031672', '719031672@qq.com', '13590303545', 1, '2020-01-27 16:06:13');
INSERT INTO `user_real_info` VALUES ('7777fe9eb37a0fe82b61f9c8e4c84f49', 'b7353d3952169bfdb1a33e256eaad180', 1, '王乐之', '430422199408263928', NULL, NULL, '719031672', '719031672@qq.com', '13590303545', 1, '2020-02-04 13:52:28');
INSERT INTO `user_real_info` VALUES ('8df8bff5695013ef0858d4d7006b53bc', '8df6257560d1aa0a5dc2cf3d1fdcd44d', 1, '张三', '440302111111111111', NULL, NULL, '111111', '111111111@qq.com', '13262662661', 1, '2020-02-02 20:03:44');
INSERT INTO `user_real_info` VALUES ('970662ad32b736f22a368fc792afe3ff', '1', 1, '张三', '430422199408263930', NULL, NULL, '719031672', '719031672@qq.com', '13590303545', 0, '2020-01-19 15:50:24');
INSERT INTO `user_real_info` VALUES ('cd6f23cb750e918e3c6547567d9147cc', '2d74b21b4d8a94f4de5ef63513576d7d', 1, '小王', '430422199408263928', NULL, NULL, '719031672', '719031672@qq.com', '13590303545', 1, '2020-02-04 14:48:09');
INSERT INTO `user_real_info` VALUES ('e6fa00614003f918a44b2c9b9b7b1bc6', '53a21ff9ec643be6eff8db808a21b18d', 1, '张三三', '441376766686868686', NULL, NULL, '46566868', '4949646464@qq.com', '15683466645', 1, '2020-01-31 11:16:09');

-- ----------------------------
-- Table structure for user_recommend
-- ----------------------------
DROP TABLE IF EXISTS `user_recommend`;
CREATE TABLE `user_recommend`  (
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `level` tinyint(1) NOT NULL COMMENT '1表示只有1级上级 2表示有2级上级，最大5  注册的时候有程序决定',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `ref_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级用户id',
  `ref_user_account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级用户账号',
  PRIMARY KEY (`user_id`, `level`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_recommend
-- ----------------------------
INSERT INTO `user_recommend` VALUES ('2d74b21b4d8a94f4de5ef63513576d7d', 1, 'test_two', 'b7353d3952169bfdb1a33e256eaad180', 'test_one');
INSERT INTO `user_recommend` VALUES ('2d74b21b4d8a94f4de5ef63513576d7d', 2, 'test_two', '257ad4a0ba8003cd3a533f5ee662b9da', 'test');
INSERT INTO `user_recommend` VALUES ('b7353d3952169bfdb1a33e256eaad180', 1, 'test_one', '257ad4a0ba8003cd3a533f5ee662b9da', 'test');

-- ----------------------------
-- Table structure for user_withdraw
-- ----------------------------
DROP TABLE IF EXISTS `user_withdraw`;
CREATE TABLE `user_withdraw`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `amount` decimal(16, 2) NOT NULL COMMENT '提现金额',
  `bank_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '银行名称',
  `card_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '银行卡号',
  `bank_logo` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '银行logo',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '方式：1-人工转账 2-系统转账',
  `cost` decimal(16, 4) NULL DEFAULT 0.0000 COMMENT '提现费用',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态：0-提现中 1-成功 2-后台退回 4-失败',
  `third_trade_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第三方转账单号',
  `remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `success_time` timestamp(0) NULL DEFAULT NULL COMMENT '到账时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '提现记录' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
