/*
 Navicat Premium Data Transfer

 Source Server         : 106
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 106.13.160.198:3306
 Source Schema         : authority

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 06/03/2020 15:59:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `menu_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单编号',
  `menu_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `parent_menu` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父菜单编号',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单地址',
  `menu_status` tinyint(0) NULL DEFAULT NULL COMMENT '菜单状态(1-正常 -2-删除)',
  `menu_order_num` int(0) NULL DEFAULT NULL COMMENT '同级菜单排列编号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间(yyyy-MM-dd HH:mm:ss)',
  `open_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户编号',
  `meta` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附加信息',
  `remark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `menu_kind` tinyint(0) NULL DEFAULT NULL COMMENT '菜单类别（1-web端，2-企业微信）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_org
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `org_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构编号',
  `org_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `org_addr` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构地址',
  `org_status` tinyint(0) NULL DEFAULT NULL COMMENT '机构状态(1-正常 -1-删除)',
  `org_type` tinyint(0) NULL DEFAULT NULL COMMENT '机构类型(1-品牌，2-销售大区，3-营销区域，4-代理，5-门店)',
  `org_qualit` tinyint(0) NULL DEFAULT NULL COMMENT '机构性质（1-直营，2-加盟商）',
  `perm_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `perm_value` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_org_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级机构编号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间(yyyy-MM-dd HH:mm:ss)',
  `user_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户编号',
  `remark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `T_ORG_ORG_CODE_INDEX`(`org_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编号',
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_status` tinyint(0) NULL DEFAULT NULL COMMENT '角色状态(1-正常 -1-删除)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间(yyyy-MM-dd HH:mm:ss)',
  `open_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户编号',
  `remark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编号',
  `menu_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
  `open_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `user_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `real_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `user_tel` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户电话',
  `role_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编号',
  `user_status` tinyint(0) NULL DEFAULT NULL COMMENT '用户状态(1-正常 -1-删除)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间(yyyy-MM-dd HH:mm:ss)',
  `open_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户编号',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信注册id',
  `remark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `can_login` tinyint(1) NULL DEFAULT 2 COMMENT '是否允许登陆（1-是，2-否）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user_data
-- ----------------------------
DROP TABLE IF EXISTS `t_user_data`;
CREATE TABLE `t_user_data`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号',
  `org_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构编号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
  `open_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户数据权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `role_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
  `open_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;



-- 初始化默认账户：test/test
INSERT INTO `authority`.`t_user`(`user_code`, `password`, `real_name`, `user_tel`, `role_code`, `user_status`, `create_time`, `update_time`, `open_user`, `user_id`, `remark`, `can_login`) VALUES ('test', '670B14728AD9902AECBA32E22FA4F6BD', '测试账号', '17600000000', '41J20200107173540963w359622560', 1, '2020-02-26 09:42:25', '2020-03-05 12:38:46', 'test', NULL, NULL, 1);

