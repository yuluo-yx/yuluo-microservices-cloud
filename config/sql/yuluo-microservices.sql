/*
 Navicat Premium Data Transfer

 Source Server         : local-mysql
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : yuluo-microservices

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 25/05/2023 11:41:18
*/

create yuluo-microservices

use yuluo-microservices


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_login_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_info`;
CREATE TABLE `sys_login_info`  (
  `info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '信息id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '访问系统用户名',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '访问状态 0 成功 1 失败',
  `ipaddr` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '登录ip地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '登录浏览器类型',
  `os` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL COMMENT '操作系统类型',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '登录信息',
  `login_time` datetime NULL DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_info
-- ----------------------------
INSERT INTO `sys_login_info` VALUES ('1660979717901316097', 'yuluo', '0', '127.0.0.1', NULL, NULL, NULL, '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES ('1660983370867470338', 'yuluo', '0', '127.0.0.1', NULL, NULL, NULL, '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES ('1660986378338590721', 'yuluo', '0', '127.0.0.1', NULL, NULL, NULL, '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES ('1660988852348784641', 'yuluo', '0', '127.0.0.1', NULL, NULL, NULL, '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES ('1660988925740716033', 'yuluo', '0', '127.0.0.1', NULL, NULL, NULL, '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES ('1660995641974902785', 'yuluo', '0', '127.0.0.1', NULL, NULL, NULL, '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES ('1661004926062620673', 'yuluo', '0', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10 or Windows Server 2016', '登录成功', '2023-05-23 13:43:29');
INSERT INTO `sys_login_info` VALUES ('1661573949384982530', 'yuluo', '0', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '登录成功', '2023-05-25 03:24:35');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '操作模块',
  `business_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '业务类型（0其它 1新增 2修改 3删除',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '请求方法',
  `request_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '请求方式',
  `operator_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '操作人员',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '操作路径',
  `oper_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '操作ip地址',
  `oper_param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '请求参数',
  `json_result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '返回参数',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '操作状态',
  `error_msg` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL COMMENT '错误信息',
  `oper_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '操作时间',
  `cost_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '消耗时间',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES ('1660996241441607681', '更新权限测试操作', '2', 'indi.yuluo.module.system.controller.SysRoleController.updateRoleTest()', 'PUT', '1', 'yuluo', '/role/updateTest', '127.0.0.1', '', NULL, '1', '\r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'123457\' for key \'sys_role.PRIMARY\'\r\n### The error may exist in indi/yuluo/module/system/mapper/SysRoleMapper.java (best guess)\r\n### The error may involve indi.yuluo.module.system.mapper.SysRoleMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_role  ( role_id, role_name, role_key, role_sort, status, del_flag, create_time, update_time )  VALUES  ( ?, ?, ?, ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'123457\' for key \'sys_role.PRIMARY\'\n; Duplicate entry \'123457\' for key \'sys_role.PRIMARY\'', NULL, '1', NULL, NULL);
INSERT INTO `sys_oper_log` VALUES ('1660996320567152641', '更新权限测试操作', '2', 'indi.yuluo.module.system.controller.SysRoleController.updateRoleTest()', 'PUT', '1', 'yuluo', '/role/updateTest', '127.0.0.1', '', '{\"code\":8291,\"data\":true,\"message\":\"接口调用成功\"}', '0', NULL, NULL, '6', NULL, NULL);
INSERT INTO `sys_oper_log` VALUES ('1661005119604584449', '更新权限测试操作', '2', 'indi.yuluo.module.system.controller.SysRoleController.updateRoleTest()', 'PUT', '1', 'yuluo', '/role/updateTest', '127.0.0.1', '', '{\"code\":8291,\"data\":true,\"message\":\"接口调用成功\"}', '0', NULL, NULL, '161', '2023-05-23 13:44:15', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '角色权限',
  `role_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '角色权限',
  `role_sort` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '角色排序',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '删除标志（0代表存在 2代表删除',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('123456', 'admin', 'system::admin', '1', '0', '0', NULL, NULL);
INSERT INTO `sys_role` VALUES ('123457', 'test-update', 'system::test-update', '2', '0', '0', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '用户id',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '用户账户名',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '用户角色id',
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '用户性别',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '用户头像信息地址',
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '用户删除标志',
  `login_ip` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '用户最后登录ip地址',
  `login_date` datetime NULL DEFAULT NULL COMMENT '用户最后登录时间',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '0 正常 1 停用',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '$2a$10$9jL5Lu3X7.KmI0TVJpgYeOwoEvkOKloDBsfSBS9lzpb1Hgbp9n8pe', 'yuluo', 'yuluo', 'yuluo829@aliyun.com', '18198086793', '123456', '男', '123.jpg', '0', '127.0.0.1', NULL, '0', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
