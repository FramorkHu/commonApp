/*
SQLyog Ultimate v11.24 (64 bit)
MySQL - 5.5.20 : Database - media
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`media` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `role_resource` */

DROP TABLE IF EXISTS `role_resource`;

CREATE TABLE `role_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `role_resource` */

insert  into `role_resource`(`id`,`role_id`,`resource_id`) values (1,1,1),(2,1,2),(3,1,3),(4,2,1),(5,2,2),(6,2,3);

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(128) DEFAULT NULL COMMENT '父id',
  `resource_name` varchar(128) DEFAULT NULL COMMENT '资源名称',
  `resource_permission` varchar(128) DEFAULT NULL COMMENT '资源权限',
  `resource_url` varchar(128) DEFAULT NULL COMMENT '资源地址',
  `priority` int(11) DEFAULT NULL COMMENT '资源优先级',
  `resource_desc` varchar(128) DEFAULT NULL COMMENT '资源描述',
  `enable` int(11) DEFAULT NULL COMMENT '0:禁用；1:启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`id`,`parent_id`,`resource_name`,`resource_permission`,`resource_url`,`priority`,`resource_desc`,`enable`) values (1,NULL,'index',NULL,'/admin/login',1,'添加用户',1),(2,NULL,NULL,NULL,'/admin/user',2,'添加用户',1);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(128) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(128) DEFAULT NULL COMMENT '角色描述',
  `enable` int(11) DEFAULT NULL,
  `is_system` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`role_desc`,`enable`,`is_system`) values (1,'管理员','管理员',1,1),(2,'普通用户','普通用户',1,0);

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(128) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `enabled` int(10) DEFAULT NULL COMMENT '0：禁用，1：启用',
  `is_super_admin` int(11) DEFAULT NULL COMMENT '是否超级用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`user_name`,`password`,`enabled`,`is_super_admin`) values (1,'admin','e6e061838856bf47e1de730719fb2609',1,1),(2,'users','e6e061838856bf47e1de730719fb2609',1,0);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`) values (1,1,1),(2,2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
