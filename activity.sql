/*
SQLyog Professional v12.08 (64 bit)
MySQL - 5.7.22-log : Database - activity
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`activity` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `activity`;

/*Table structure for table `t_activity` */

DROP TABLE IF EXISTS `t_activity`;

CREATE TABLE `t_activity` (
  `id` varchar(100) NOT NULL,
  `activity_name` varchar(100) DEFAULT NULL,
  `activity_dsc` varchar(255) DEFAULT NULL,
  `activity_place` varchar(100) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `is_race` int(100) DEFAULT '0',
  `state` int(100) DEFAULT '0',
  `prize1` int(100) DEFAULT '0',
  `prize2` int(100) DEFAULT '0',
  `prize3` int(100) DEFAULT '0',
  `prize4` int(100) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_activity` */

/*Table structure for table `t_apply` */

DROP TABLE IF EXISTS `t_apply`;

CREATE TABLE `t_apply` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `activity_id` varchar(50) DEFAULT NULL,
  `dsc` varchar(255) DEFAULT NULL,
  `apply_type` int(11) DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_name` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_apply` */

/*Table structure for table `t_integral` */

DROP TABLE IF EXISTS `t_integral`;

CREATE TABLE `t_integral` (
  `id` varchar(100) NOT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `stu_no` varchar(100) DEFAULT NULL,
  `activity_id` varchar(100) DEFAULT NULL,
  `activity_name` varchar(100) DEFAULT NULL,
  `activity_time` datetime DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `integral` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `year` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_integral` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` varchar(50) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `stu_no` varchar(50) NOT NULL,
  `tel_num` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `is_admin` int(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`stu_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`user_name`,`password`,`stu_no`,`tel_num`,`email`,`is_admin`,`create_time`,`update_time`) values ('1','admin','111222','202020202020','111222111222','111222111222@qq.com',1,'2020-03-07 23:02:25',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
