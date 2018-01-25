-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hotrobanhang
-- ------------------------------------------------------
-- Server version	5.6.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bophan`
--

DROP TABLE IF EXISTS `bophan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bophan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenbophan` varchar(45) DEFAULT NULL,
  `mota` varchar(255) DEFAULT NULL,
  `vitri` varchar(45) DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `ghichu` varchar(95) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idbophan_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bophan`
--

LOCK TABLES `bophan` WRITE;
/*!40000 ALTER TABLE `bophan` DISABLE KEYS */;
INSERT INTO `bophan` VALUES (1,'Kinh doanh',NULL,'Nhan vien','active',NULL),(2,'Kinh doanh 1','121','kinh doah','deleted',NULL),(3,'Quản Lý','Quản Lý','Quản Lý','active',NULL);
/*!40000 ALTER TABLE `bophan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chamsoc`
--

DROP TABLE IF EXISTS `chamsoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chamsoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nhanvienchamsoc` int(11) DEFAULT '0',
  `nhanvienbanhang` int(11) DEFAULT '0',
  `nhanviengiaohang` int(11) DEFAULT '0',
  `ngay` datetime DEFAULT CURRENT_TIMESTAMP,
  `lan` int(11) DEFAULT '0',
  `noidung` varchar(255) DEFAULT '',
  `ngaycstiep` datetime DEFAULT NULL,
  `ghichu` varchar(255) DEFAULT '',
  `hoadon_id` int(11) DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `khachhang_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chamsoc_khachhang1_idx` (`khachhang_id`),
  CONSTRAINT `FKm0l09ssmksxb0t1c05dj7iemu` FOREIGN KEY (`khachhang_id`) REFERENCES `khachhang` (`id`),
  CONSTRAINT `fk_chamsoc_khachhang1` FOREIGN KEY (`khachhang_id`) REFERENCES `khachhang` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chamsoc`
--

LOCK TABLES `chamsoc` WRITE;
/*!40000 ALTER TABLE `chamsoc` DISABLE KEYS */;
INSERT INTO `chamsoc` VALUES (1,1,1,1,'2034-07-12 00:00:00',2,'3232','2034-11-03 00:00:00','1',NULL,'deleted',1),(2,1,4,4,'2033-11-02 00:00:00',21,'2323232323','2033-11-02 00:00:00','23232323232323232332',NULL,'deleted',1),(3,1,4,4,'2014-07-12 00:00:00',1212121,'1212121','2012-12-12 00:00:00','21212121212221',NULL,'deleted',1),(4,1,4,4,'2011-11-11 00:00:00',111111,'1111','2011-11-11 00:00:00','1111111',NULL,'dachamsoc',1),(5,1,4,4,'2011-11-11 00:00:00',111,'111','2011-11-11 00:00:00','11111',NULL,'dachamsoc',1),(6,1,4,4,'2011-11-11 00:00:00',1111,'111','2011-11-11 00:00:00','11111',NULL,'dachamsoc',1),(7,1,4,4,'2011-11-11 00:00:00',1111,'111','2011-11-11 00:00:00','1111',NULL,'dachamsoc',1),(8,1,4,4,'2011-11-11 00:00:00',111,'1111','2011-11-11 00:00:00','111111',NULL,'dachamsoc',1),(9,1,4,4,'2011-11-11 00:00:00',1111,'1','2011-11-11 00:00:00','1111111',NULL,'dachamsoc',1),(10,1,4,4,'2011-11-11 00:00:00',1111,'111','2011-11-11 00:00:00','11111',NULL,'dachamsoc',1),(11,1,4,4,'2011-11-11 00:00:00',11,'111','2011-11-11 00:00:00','11111',NULL,'dachamsoc',1),(12,1,4,4,'2014-02-01 00:00:00',123,'123','2031-03-12 00:00:00','213',1,'deleted',1),(13,1,4,4,'2011-11-11 00:00:00',1,'111','2018-01-25 00:00:00','11111',0,'dachamsoc',2),(14,1,4,4,'2011-11-11 00:00:00',1,'111','2018-01-24 00:00:00','11111',0,'dachamsoc',2),(15,1,4,4,'2011-11-11 00:00:00',111,'111','2018-01-23 00:00:00','23232332',0,'chochamsoc',2),(16,1,4,4,'2011-11-11 00:00:00',1111,'1111','2018-01-20 00:00:00','1231231231',0,'chochamsoc',2),(17,1,4,4,'2011-11-11 00:00:00',1,'111','2018-01-21 00:00:00','11111',0,'dachamsoc',2),(18,1,4,4,'2019-04-01 00:00:00',3,'','2018-01-28 00:00:00','',0,'dachamsoc',2),(19,1,4,4,'2018-01-21 17:26:57',2,NULL,'2018-01-22 17:26:57',NULL,0,'dachamsoc',2),(20,1,4,4,'2018-01-21 17:31:01',3,NULL,'2018-01-28 17:31:01',NULL,0,'dachamsoc',2),(21,1,5,5,'2019-10-01 00:00:00',12,'123131231','2020-05-01 00:00:00','12321321313',0,'dachamsoc',3),(22,1,4,4,'2018-01-24 16:12:34',4,NULL,'2018-01-31 16:12:34',NULL,0,'dachamsoc',2),(23,1,6,6,'2018-01-25 00:00:00',1,'123213123','2018-11-01 00:00:00','',0,'deleted',5),(24,1,6,6,'2018-01-25 00:00:00',1,'12321213213','2018-11-01 00:00:00','',0,'deleted',5);
/*!40000 ALTER TABLE `chamsoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietchamsoc`
--

DROP TABLE IF EXISTS `chitietchamsoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chitietchamsoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idchamsoc` int(11) DEFAULT NULL,
  `tieuchichamsoc` int(11) DEFAULT NULL,
  `diem` int(11) DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `cokhong` bit(1) DEFAULT NULL,
  `tienchamsoc` decimal(10,0) DEFAULT NULL,
  `mota` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `fk_chitietchamsoc_chamsoc1_idx` (`idchamsoc`),
  KEY `fk_chitietchamsoc_tieuchichamsoc1_idx` (`tieuchichamsoc`),
  CONSTRAINT `FKbxn9l7qhun1r0ukf0t0gbu7t4` FOREIGN KEY (`idchamsoc`) REFERENCES `chamsoc` (`id`),
  CONSTRAINT `FKfnstwmdv4dei4ca188ssy12ki` FOREIGN KEY (`tieuchichamsoc`) REFERENCES `tieuchichamsoc` (`id`),
  CONSTRAINT `fk_chitietchamsoc_chamsoc1` FOREIGN KEY (`idchamsoc`) REFERENCES `chamsoc` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chitietchamsoc_tieuchichamsoc1` FOREIGN KEY (`tieuchichamsoc`) REFERENCES `tieuchichamsoc` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietchamsoc`
--

LOCK TABLES `chitietchamsoc` WRITE;
/*!40000 ALTER TABLE `chitietchamsoc` DISABLE KEYS */;
INSERT INTO `chitietchamsoc` VALUES (2,1,1,66,'active',NULL,NULL,''),(3,1,3,88,'active',NULL,NULL,''),(4,2,4,NULL,'active','\0',NULL,NULL),(5,3,4,NULL,'active','\0',NULL,NULL),(6,3,3,23,'active',NULL,NULL,NULL),(7,4,4,NULL,'active',NULL,1111,NULL),(8,4,1,NULL,'active','\0',NULL,NULL),(9,4,3,11111,'active',NULL,NULL,NULL),(10,5,1,NULL,'active','\0',NULL,NULL),(11,6,1,NULL,'active','\0',NULL,NULL),(12,7,1,NULL,'active','\0',NULL,NULL),(13,8,1,NULL,'active','',NULL,NULL),(14,10,5,NULL,'active','\0',5,NULL),(15,10,3,3,'active',NULL,NULL,NULL),(16,10,1,NULL,'active','\0',1,NULL),(17,11,5,NULL,'active','\0',NULL,NULL),(18,11,3,3,'active',NULL,NULL,NULL),(19,11,1,NULL,'active',NULL,1,NULL),(20,12,3,333,'active',NULL,NULL,NULL),(21,12,5,NULL,'active','\0',NULL,NULL),(22,12,1,NULL,'active',NULL,555,NULL),(23,13,1,NULL,'active',NULL,50000,NULL),(24,16,3,23223232,'active',NULL,NULL,NULL),(25,17,5,NULL,'active','',NULL,NULL),(26,23,1,NULL,'active',NULL,10000,NULL),(27,24,1,NULL,'active',NULL,100000,NULL);
/*!40000 ALTER TABLE `chitietchamsoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitiethoadon`
--

DROP TABLE IF EXISTS `chitiethoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chitiethoadon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idhoadon` int(11) NOT NULL,
  `idhanghoa` int(11) NOT NULL,
  `dongia` decimal(10,0) DEFAULT '0',
  `soluong` int(11) DEFAULT '0',
  `giaban` decimal(10,0) DEFAULT '0',
  `thanhtien` decimal(10,0) DEFAULT '0',
  `trangthai` varchar(45) DEFAULT NULL,
  `ghichu` varchar(95) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `fk_chitiethoadon_hanghoa1_idx` (`idhanghoa`),
  KEY `fk_chitiethoadon_hoadon1_idx` (`idhoadon`),
  CONSTRAINT `FK629jyswa5rsfrakpi3ag1l0fp` FOREIGN KEY (`idhanghoa`) REFERENCES `hanghoa` (`id`),
  CONSTRAINT `FKojp5gdoekq1hqflilu2mprs51` FOREIGN KEY (`idhoadon`) REFERENCES `hoadon` (`id`),
  CONSTRAINT `fk_chitiethoadon_hanghoa1` FOREIGN KEY (`idhanghoa`) REFERENCES `hanghoa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chitiethoadon_hoadon1` FOREIGN KEY (`idhoadon`) REFERENCES `hoadon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
INSERT INTO `chitiethoadon` VALUES (2,2,2,15000,6,15000,30000,'active',''),(3,1,1,150000,4,150000,600000,'active',''),(4,3,1,150000,20,150000,3000000,'active',''),(5,4,2,20000,23,20000,460000,'active',''),(6,5,2,20000,22,20000,600000,'active',''),(7,6,2,20000,22,20000,440000,'active',''),(8,7,2,20000,20,20000,400000,'active',''),(9,8,2,20000,8,20000,160000,'active',''),(10,9,2,20000,2,20000,40000,'active',''),(11,10,2,20000,2,20000,40000,'active',''),(12,11,2,20000,3,20000,60000,'active',''),(13,12,2,20000,4,20000,80000,'active',''),(14,13,2,20000,2,20000,40000,'active',''),(15,14,2,20000,3,20000,60000,'active',''),(16,15,2,20000,2,20000,40000,'active',''),(17,16,2,20000,4,20000,80000,'active',''),(18,17,2,20000,4,20000,80000,'active',''),(19,18,2,20000,4,20000,80000,'active',''),(20,19,2,20000,2,20000,40000,'active',''),(21,20,2,20000,2,20000,40000,'active',''),(22,21,2,20000,3,20000,60000,'active',''),(23,22,2,20000,2,20000,40000,'active',''),(24,23,2,20000,40,20000,800000,'active',''),(25,24,2,20000,323,20000,6460000,'active',''),(26,25,2,20000,4343,20000,86860000,'active',''),(27,30,2,20000,3,20000,60000,'active',''),(28,31,2,20000,3,20000,60000,'active',''),(29,32,2,20000,123,20000,2460000,'active',''),(30,33,2,20000,4,20000,80000,'active',''),(31,34,2,20000,4,20000,80000,'active',''),(32,35,2,20000,2,20000,40000,'active',''),(33,36,2,20000,4,20000,80000,'active',''),(34,37,2,20000,3,20000,60000,'active',''),(35,38,2,20000,3,20000,60000,'active',''),(36,39,2,20000,5,20000,100000,'active',''),(37,40,2,20000,4,20000,80000,'active',''),(38,41,2,20000,5,20000,100000,'active',''),(39,42,2,20000,4,20000,80000,'active',''),(40,43,2,20000,60,20000,1200000,'active',''),(41,44,2,20000,60,20000,1200000,'active',''),(42,45,2,20000,60,20000,1200000,'active','');
/*!40000 ALTER TABLE `chitiethoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hanghoa_id` int(11) DEFAULT NULL,
  `nhanvien_id` int(11) DEFAULT NULL,
  `nhavientao_id` int(11) DEFAULT NULL,
  `khachhang_id` int(11) DEFAULT NULL,
  `noidung` longtext,
  `ngaytao` datetime DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `ghichu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,2,6,1,3,'123','2018-01-22 21:16:57','deleted','123'),(2,1,6,1,1,'12','2018-01-23 08:30:11','active','12');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hanghoa`
--

DROP TABLE IF EXISTS `hanghoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hanghoa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mahang` varchar(45) NOT NULL,
  `tenhang` varchar(255) NOT NULL,
  `donvitinh` varchar(45) DEFAULT '',
  `nhomhang` int(11) NOT NULL DEFAULT '0',
  `mota` varchar(255) DEFAULT '',
  `gianhap` decimal(10,0) DEFAULT '0',
  `giaban` decimal(10,0) DEFAULT '0',
  `giakhuyenmai` decimal(10,0) DEFAULT '0',
  `trangthai` varchar(45) DEFAULT NULL,
  `ghichu` varchar(45) DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mahang_UNIQUE` (`mahang`),
  UNIQUE KEY `UK5bsjl9yl4x9nmp4upguw5bqs6` (`mahang`),
  KEY `fk_hanghoa_nhomhang1_idx` (`nhomhang`),
  CONSTRAINT `FK74w5ctlq8n2hpdksjltsg1y42` FOREIGN KEY (`nhomhang`) REFERENCES `nhomhang` (`id`),
  CONSTRAINT `fk_hanghoa_nhomhang1` FOREIGN KEY (`nhomhang`) REFERENCES `nhomhang` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hanghoa`
--

LOCK TABLES `hanghoa` WRITE;
/*!40000 ALTER TABLE `hanghoa` DISABLE KEYS */;
INSERT INTO `hanghoa` VALUES (1,'H01','Hàng 01','chai',1,'Hàng Hóa 1',145000,150000,147000,'active',''),(2,'h02','h02','cái',4,'h02 mô tả',19000,20000,20000,'active',NULL);
/*!40000 ALTER TABLE `hanghoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hoadon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idnhanvienban` int(11) DEFAULT '0',
  `idnhanvienlaphoadon` int(11) DEFAULT '0',
  `idnhanvienchamsoc` int(11) DEFAULT '0',
  `idnhanviengiaohang` int(11) DEFAULT NULL,
  `sohoadon` varchar(45) NOT NULL,
  `hinhthucthanhtoan` varchar(45) DEFAULT '',
  `ngaylap` datetime DEFAULT CURRENT_TIMESTAMP,
  `ngayxuat` datetime DEFAULT '1900-01-01 00:00:00',
  `ngaythanhtoan` datetime DEFAULT '1900-01-01 00:00:00',
  `tongtien` decimal(10,0) DEFAULT '0',
  `congno` decimal(10,0) DEFAULT '0',
  `tiendatra` decimal(10,0) DEFAULT '0',
  `idkhachhang` int(11) DEFAULT '0',
  `diachigiaohang` varchar(95) DEFAULT '',
  `sodienthoai` varchar(15) DEFAULT '',
  `trangthai` varchar(45) DEFAULT '',
  `ghichu` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `fk_hoadon_khachhang1_idx` (`idkhachhang`),
  KEY `fk_hoadon_nhanvien1_idx` (`idnhanvienban`),
  KEY `fk_hoadon_nhanvien1_idx1` (`idnhanvienlaphoadon`),
  KEY `fk_hoadon_nhanvien3_idx` (`idnhanvienchamsoc`),
  KEY `fk_hoadon_nhanvien4_idx` (`idnhanviengiaohang`),
  CONSTRAINT `FK2c83xop7u2m8im3pojpqrc0k9` FOREIGN KEY (`idnhanvienban`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK52evm9frbvd6iwk7wx2v0y9qo` FOREIGN KEY (`idnhanvienchamsoc`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKhe5ol6nl0b09blxihhvmtmk4p` FOREIGN KEY (`idnhanvienlaphoadon`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKlwt6og8a59ih2g8k9le1n7bpy` FOREIGN KEY (`idkhachhang`) REFERENCES `khachhang` (`id`),
  CONSTRAINT `FKn3evbeet1kk0y6uyibdxf7xp8` FOREIGN KEY (`idnhanviengiaohang`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `fk_hoadon_khachhang1` FOREIGN KEY (`idkhachhang`) REFERENCES `khachhang` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hoadon_nhanvien1` FOREIGN KEY (`idnhanvienban`) REFERENCES `nhanvien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hoadon_nhanvien2` FOREIGN KEY (`idnhanvienlaphoadon`) REFERENCES `nhanvien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hoadon_nhanvien3` FOREIGN KEY (`idnhanvienchamsoc`) REFERENCES `nhanvien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hoadon_nhanvien4` FOREIGN KEY (`idnhanviengiaohang`) REFERENCES `nhanvien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (1,1,1,1,NULL,'HD2018110491625','tienmat','2033-10-02 00:00:00','2023-12-03 00:00:00','2013-12-02 00:00:00',23230,333,111,1,'213213 rre qww qweq ','213-122-32321','dagiaohang','23232332'),(2,4,1,4,NULL,'HD2018110491827','tienmat','2018-01-22 00:00:00','2023-12-03 00:00:00','2013-12-02 00:00:00',23230,333,111,1,'213213 rre qww qweq ','213-122-32321','dagiaohang','23232332'),(3,4,1,4,4,'HD20181131724831','tienmat','2018-01-21 00:00:00','2011-11-11 00:00:00','2011-11-11 00:00:00',3000000,0,3000000,1,'123','213-123-21323','dagiaohang','123213'),(4,4,1,4,4,'HD20181132147111','tienmat','2018-01-22 00:00:00','2011-11-11 00:00:00','2011-11-11 00:00:00',460000,0,460000,1,'1232323232','232-133-13323','dagiaohang','b'),(5,4,1,4,4,'HD20181133327661','tienmat','2018-01-22 00:00:00','2023-10-02 00:00:00','2023-10-02 00:00:00',600000,0,600000,1,'xccxcxcq','121-212-12121','dagiaohang','zxcxzczxcxzz'),(6,4,1,4,4,'HD20181134621543','tienmat','2018-01-20 00:00:00','2011-11-11 00:00:00','2011-11-11 00:00:00',600000,0,440000,1,'2312311233213223132','123-232-32332','dagiaohang','123123123123123'),(7,4,1,4,4,'HD2018113572924','tienmat','2018-01-22 00:00:00','2011-11-11 00:00:00','2011-11-11 00:00:00',400000,0,399999,1,'21','213-232-13132','dagiaohang','12321321213'),(8,5,1,5,5,'HD20181225836560','tienmat','2018-01-22 00:00:00','2018-01-22 00:00:00','2018-01-22 00:00:00',160000,0,160000,3,'22/01/2018','232-323-23232','dagiaohang','22/01/2018'),(9,6,1,6,6,'HD2018123318460','tienmat','2018-01-23 00:00:00','2018-01-23 00:00:00','2018-01-23 00:00:00',50000,50000,0,3,'123','123-123-13213','deleted',''),(10,6,1,6,6,'HD20181233448925','tienmat','2018-01-23 00:00:00','2018-01-23 00:00:00','2018-01-23 00:00:00',60000,60000,0,3,'12312312321321','123-123-12321','dagiaohang',''),(11,6,1,6,6,'HD2018123505290','tienmat','2018-01-23 00:00:00','2018-01-23 00:00:00','2018-01-23 00:00:00',80000,80000,0,3,'123123123','123-131-32322','dagiaohang',''),(12,6,1,6,6,'HD20181235241768','tienmat','2018-01-23 00:00:00','2018-01-23 00:00:00','2018-01-23 00:00:00',100000,100000,0,3,'1231212312','123-123-13123','dagiaohang',''),(13,1,1,6,6,'HD2018124337726','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',40000,40000,0,3,'23132','123-123-21323','dagiaohang',''),(14,1,1,6,6,'HD2018124451685','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',60000,60000,0,3,'232323`','232-323-2323','dagiaohang',''),(15,1,1,6,6,'HD20181241124777','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',40000,40000,0,3,'2131231','123-232-3232','deleted',''),(16,1,1,6,6,'HD20181241816989','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',80000,80000,0,3,'21323','123-232-1323','dagiaohang',''),(17,6,1,6,6,'HD20181242045493','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',80000,80000,0,3,'213123213','213-323-23232','dagiaohang',''),(18,1,1,6,6,'HD20181242324948','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',80000,80000,0,3,'3213','323-232-32323','dagiaohang',''),(19,1,1,6,6,'HD20181242518192','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',40000,40000,0,3,'12321321321','232-323-2323','chuagiaohang',''),(20,1,1,6,6,'HD20181242636239','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',40000,40000,0,3,'23232','232-323-2323','dagiaohang',''),(21,1,1,6,6,'HD20181242913149','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',60000,60000,0,3,'232132','232-213-21323','dagiaohang',''),(22,1,1,6,6,'HD20181243053856','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',40000,40000,0,3,'2e23','321-321-31232','dagiaohang',''),(23,6,1,6,6,'HD20181244042989','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',800000,800000,0,3,'2232','232-323-23232','dagiaohang',''),(24,1,1,6,6,'HD20181244113208','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',6460000,6460000,0,3,'qe3qwe','123-232-32323','dagiaohang',''),(25,2,1,6,6,'HD20181244212594','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',86860000,86860000,0,3,'e3243242','232-323-23233','deleted',''),(30,6,1,6,6,'HD20181241532880','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',60000,60000,0,3,'12312313','123-213-13213','dagiaohang','1231231312'),(31,6,1,6,6,'HD20181241654956','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',60000,60000,0,3,'123123','212-312-32132','chuagiaohang',''),(32,6,1,6,6,'HD20181245828440','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',2460000,2460000,0,3,'123131','123-123-13131','chuagiaohang',''),(33,1,1,6,6,'HD2018124137640','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',80000,80000,0,3,'12313123123','112-321-31232','deleted',''),(34,1,1,6,6,'HD2018124418396','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',80000,80000,0,3,'123213123','123-123-32132','chuagiaohang',''),(35,1,1,6,6,'HD2018124645772','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',40000,40000,0,3,'123123','123-313-32131','chuagiaohang',''),(36,1,1,6,6,'HD201812496283','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',80000,80000,0,3,'12313123','112-321-32131','chuagiaohang',''),(37,1,1,6,6,'HD20181241128940','tienmat','2018-01-24 00:00:00','2018-01-24 00:00:00','2018-01-24 00:00:00',60000,60000,0,3,'12321','232-323-22323','deleted',''),(38,1,1,6,6,'HD2018125189456','tienmat','2018-01-25 00:00:00','2018-01-25 00:00:00','2018-01-25 00:00:00',60000,60000,0,5,'12321','123-123-21323','dagiaohang',''),(39,6,1,6,6,'HD20181252329108','tienmat','2018-01-25 00:00:00','2018-01-25 00:00:00','2018-01-25 00:00:00',100000,100000,0,5,'12311231','213-123-21323','chuagiaohang',''),(40,1,1,6,6,'HD20181252512564','tienmat','2018-01-25 00:00:00','2018-01-25 00:00:00','2018-01-25 00:00:00',80000,80000,0,5,'13131','123-123-21312','chuagiaohang',''),(41,1,1,6,6,'HD2018125254811','tienmat','2018-01-25 00:00:00','2018-01-25 00:00:00','2018-01-25 00:00:00',100001,100001,0,5,'abcupdate1','213-132-13232','deleted',''),(42,6,1,6,6,'HD20181255924681','tienmat','2018-01-25 00:00:00','2018-01-25 00:00:00','2018-01-25 00:00:00',80000,80000,0,5,'123213','123-123-21312','deleted',''),(43,6,1,6,6,'HD20181253935866','tienmat','2018-01-25 00:00:00','2018-01-25 00:00:00','2018-01-25 00:00:00',1200000,1200000,0,5,'1212312312','123-213-21213','chuagiaohang',''),(44,6,1,6,6,'HD20181254142761','tienmat','2018-01-25 00:00:00','2018-01-25 00:00:00','2018-01-25 00:00:00',1200000,1200000,0,5,'3123321','213-213-21321','chuagiaohang',''),(45,6,1,6,6,'HD20181254325372','tienmat','2018-01-25 00:00:00','2018-01-25 00:00:00','2018-01-25 00:00:00',1200000,1200000,0,5,'1232131231231','123-123-12312','chuagiaohang','');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `khachhang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `makh` varchar(45) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `manganhnghe` int(11) DEFAULT NULL,
  `sotaikhoan` varchar(20) DEFAULT '',
  `diachi` varchar(255) DEFAULT '',
  `sodienthoai` varchar(15) DEFAULT '',
  `tendaidien` varchar(45) DEFAULT '',
  `dienthoaidaidien` varchar(15) DEFAULT '',
  `ngaysinhnhatndd` datetime DEFAULT '1900-01-01 00:00:00',
  `tenphutrach` varchar(45) DEFAULT '',
  `dienthoaiphutrach` varchar(15) DEFAULT '0',
  `ngaysinhphutrach` datetime DEFAULT '1900-01-01 00:00:00',
  `maloaikhachhang` int(11) DEFAULT '1',
  `sogpkd` varchar(50) DEFAULT '',
  `ngaycap` datetime DEFAULT '1900-01-01 00:00:00',
  `noicap` varchar(45) DEFAULT '',
  `trangthai` varchar(45) DEFAULT NULL,
  `sotienchamsoc` decimal(10,0) DEFAULT '0',
  `sotiendachamsoc` decimal(10,0) DEFAULT '0',
  `diem` int(11) DEFAULT '0',
  `solanchamsoc` int(11) DEFAULT '0',
  `solandamphan` int(11) DEFAULT '0',
  `trangthainhac` varchar(45) DEFAULT NULL,
  `diemtiemnang` int(11) DEFAULT '0',
  `uutien` varchar(10) DEFAULT NULL,
  `ghichu` varchar(255) DEFAULT '',
  `nhomkhachhang_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `makh_UNIQUE` (`makh`),
  UNIQUE KEY `UKoerfb7x5lj5rbyrdpkqrxiki6` (`makh`),
  KEY `fk_khachhang_loaikhachhang_idx` (`maloaikhachhang`),
  KEY `fk_khachhang_nhomkhachhang1_idx` (`nhomkhachhang_id`),
  CONSTRAINT `FK43v854gj0cu7liecwt3ijtnee` FOREIGN KEY (`nhomkhachhang_id`) REFERENCES `nhomkhachhang` (`id`),
  CONSTRAINT `FKb8m7ngrfeegpo68i3y6tad79e` FOREIGN KEY (`maloaikhachhang`) REFERENCES `loaikhachhang` (`id`),
  CONSTRAINT `fk_khachhang_loaikhachhang` FOREIGN KEY (`maloaikhachhang`) REFERENCES `loaikhachhang` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_khachhang_nhomkhachhang1` FOREIGN KEY (`nhomkhachhang_id`) REFERENCES `nhomkhachhang` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (1,'kh01','khách hàng 01',1,'23929293','123 qua ','098374837','kahchs hang 1','09883938','2004-06-16 00:00:00','','0','2004-06-16 00:00:00',1,'82828','2004-06-16 00:00:00','bmt','active',89555,-555,7,2,4,'chosinhnhat',27,'co','2',1),(2,'kh02','khach hang 2 đã sửa',232313,'23123123','123 qua ','232-323-23232','nguyen a','232-323-23213','2033-01-21 00:00:00','nguyen b','123-213-21321','2033-02-11 00:00:00',39,'2323232','2033-02-11 00:00:00','bmt','active',150000,50000,223,6,6,'chosinhnhat',22,'khong','232',1),(3,'kh03','khach hang 2 đã sửa',232313,'23123123','123 qua','232-323-23232','nguyen a','232-323-23213','2033-01-20 00:00:00','nguyen b','123-213-21321','2033-02-11 00:00:00',39,'2323232','2033-02-11 00:00:00','bmt','active',9938000,50000,1184,6,7,'chosinhnhat',22,'co','232',1),(4,'k0','k0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,40,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,'co',NULL,8),(5,'k1','k1',2,'123','123','123-123-12313','123123','123-131-31312','2012-03-12 00:00:00',NULL,NULL,NULL,40,NULL,NULL,NULL,'active',36000,0,654543,0,0,'chosinhnhat',0,'khong',NULL,8);
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kpi`
--

DROP TABLE IF EXISTS `kpi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kpi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ten` varchar(45) DEFAULT NULL,
  `so` int(11) DEFAULT NULL,
  `phantram` double DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `mota` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kpi`
--

LOCK TABLES `kpi` WRITE;
/*!40000 ALTER TABLE `kpi` DISABLE KEYS */;
INSERT INTO `kpi` VALUES (1,'Doanh thu',100,NULL,'active',NULL),(2,'',NULL,NULL,'deleted',''),(3,'abc',2,3,'active','abc'),(4,'abc1',2,3,'active','1');
/*!40000 ALTER TABLE `kpi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaikhachhang`
--

DROP TABLE IF EXISTS `loaikhachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loaikhachhang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenloai` varchar(45) DEFAULT '',
  `trangthai` varchar(45) DEFAULT NULL,
  `mota` varchar(95) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaikhachhang`
--

LOCK TABLES `loaikhachhang` WRITE;
/*!40000 ALTER TABLE `loaikhachhang` DISABLE KEYS */;
INSERT INTO `loaikhachhang` VALUES (1,'VIP','active','Khách Hàng VIP'),(31,'VIP2','active','4'),(32,'','deleted',''),(33,'12','active','22222222222'),(34,'VIP23','active','2333'),(35,'','deleted',''),(36,'3','active',''),(37,'sb','active',''),(38,'df','active',''),(39,'vip222222','active',''),(40,'vip4','active','3333');
/*!40000 ALTER TABLE `loaikhachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `luong`
--

DROP TABLE IF EXISTS `luong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `luong` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nhanvien_id` int(11) DEFAULT NULL,
  `luong` decimal(10,0) DEFAULT NULL,
  `thuong` decimal(10,0) DEFAULT NULL,
  `thang` varchar(2) DEFAULT NULL,
  `nam` varchar(5) DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `ghichu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_nhanvien_luong_idx` (`nhanvien_id`),
  CONSTRAINT `FK13uvechwuw0ch6b07jykctx6j` FOREIGN KEY (`nhanvien_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `fk_nhanvien_luong` FOREIGN KEY (`nhanvien_id`) REFERENCES `nhanvien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `luong`
--

LOCK TABLES `luong` WRITE;
/*!40000 ALTER TABLE `luong` DISABLE KEYS */;
INSERT INTO `luong` VALUES (10,6,12,12,'01','1990','chuatraluong',''),(11,6,3000000,619000,'01','2018','chuathanhtoan',''),(12,1,300000,13998,'01','2018','chuatraluong','');
/*!40000 ALTER TABLE `luong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhanvien` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manhanvien` varchar(45) NOT NULL,
  `tennhanvien` varchar(255) DEFAULT '',
  `socmnd` varchar(15) DEFAULT '',
  `ngaycap` datetime DEFAULT '1900-01-01 00:00:00',
  `noicap` varchar(45) DEFAULT '',
  `sodienthoai` varchar(45) DEFAULT '',
  `diachi` varchar(155) DEFAULT '',
  `ngayvaolam` datetime DEFAULT '1900-01-01 00:00:00',
  `luong` decimal(10,0) DEFAULT NULL,
  `chietkhau` double DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `loainhanvien` int(11) DEFAULT '0',
  `thongtinkhac` varchar(255) DEFAULT '',
  `ghichu` varchar(255) DEFAULT '',
  `bophan_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `manhanvien_UNIQUE` (`manhanvien`),
  UNIQUE KEY `UK3xlxfe14vfowvpflhtexay5em` (`manhanvien`),
  KEY `fk_nhanvien_bophan1_idx` (`bophan_id`),
  CONSTRAINT `FK659m81wi1c5wn5h9vgnqar4qa` FOREIGN KEY (`bophan_id`) REFERENCES `bophan` (`id`),
  CONSTRAINT `fk_nhanvien_bophan1` FOREIGN KEY (`bophan_id`) REFERENCES `bophan` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'QT001','Cuong','240949216','2004-06-16 00:00:00','Daklak','0939888737','BMT','1900-01-01 00:00:00',3000,10,'active',0,'','',1),(2,'nv1','nv1','230-929-384','2010-09-02 00:00:00','gia lai','098-728-37321','112','2082-09-03 00:00:00',3000,10,'active',2,'abc','abc',1),(4,'nv12','nv2','282-828-282','2023-10-02 00:00:00','123','222-222-22222','2222','2023-10-02 00:00:00',3000,10,'active',NULL,'','',3),(5,'nv11','nv1','232-323-233','2012-12-12 00:00:00','gia lao','212-121-21221','1212','2012-12-12 00:00:00',2,12,'active',1212,'12','12',3),(6,'23322','23232323','232-323-232','2019-10-01 00:00:00','232323','223-232-32323','213123','2019-10-01 00:00:00',3000000,10,'active',2,'123','123123123123123',3);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvienkpi`
--

DROP TABLE IF EXISTS `nhanvienkpi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhanvienkpi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nhanvien` int(11) DEFAULT NULL,
  `kpi` int(11) DEFAULT NULL,
  `so` int(11) DEFAULT NULL,
  `ngaydangky` datetime DEFAULT CURRENT_TIMESTAMP,
  `ngayhoanthanh` datetime DEFAULT CURRENT_TIMESTAMP,
  `trangthai` varchar(45) DEFAULT NULL,
  `mucdohoanthanh` double DEFAULT '0',
  `mota` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_nhanvienkpi_nhanvien1_idx` (`nhanvien`),
  KEY `fk_nhanvienkpi_kpi1_idx` (`kpi`),
  CONSTRAINT `FKosdbj8eq6r2gkwq4klvpwigvg` FOREIGN KEY (`kpi`) REFERENCES `kpi` (`id`),
  CONSTRAINT `FKvd200e71xhgdpvcrv1tw0jno` FOREIGN KEY (`nhanvien`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `fk_nhanvienkpi_kpi1` FOREIGN KEY (`kpi`) REFERENCES `kpi` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_nhanvienkpi_nhanvien1` FOREIGN KEY (`nhanvien`) REFERENCES `nhanvien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvienkpi`
--

LOCK TABLES `nhanvienkpi` WRITE;
/*!40000 ALTER TABLE `nhanvienkpi` DISABLE KEYS */;
INSERT INTO `nhanvienkpi` VALUES (1,1,1,2,'2004-06-16 00:00:00','2004-06-16 00:00:00','active',55.6,NULL),(2,4,4,23,'2018-01-08 17:12:31','2033-09-09 00:00:00','inactive',3,'abcabcabc'),(3,4,4,33,'2018-01-08 17:15:12','2073-07-03 00:00:00','inactive',3,'3'),(4,4,4,2,'2018-01-08 17:17:02','2022-02-02 00:00:00','inactive',2,'12'),(5,4,4,3,'2018-01-08 17:20:05','2030-03-03 00:00:00','active',2,'4'),(6,4,4,22,'2018-01-08 18:26:54','2082-02-08 00:00:00','deleted',32,'1');
/*!40000 ALTER TABLE `nhanvienkpi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhomhang`
--

DROP TABLE IF EXISTS `nhomhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhomhang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manhom` varchar(45) DEFAULT NULL,
  `manhomcha` int(11) DEFAULT NULL,
  `tennhom` varchar(255) DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `mota` varchar(95) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `manhom_UNIQUE` (`manhom`),
  UNIQUE KEY `UKj5gksk4nu89n997x1im2aw6vt` (`manhom`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhomhang`
--

LOCK TABLES `nhomhang` WRITE;
/*!40000 ALTER TABLE `nhomhang` DISABLE KEYS */;
INSERT INTO `nhomhang` VALUES (1,'D01',7,'Dầu 01','active','Dầu Nhớt 01'),(2,'D001',1,'Dầu 001','active','Dầu Nhớt 001'),(3,'D02',0,'Dầu 02','active','Dầu Nhớt 02'),(4,'D002',3,'Dầu 002','active','Dầu Nhớt 002'),(6,'',0,'','deleted',''),(7,'D011',3,'3','active','123231'),(8,'D012',0,'12','active','21');
/*!40000 ALTER TABLE `nhomhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhomkhachhang`
--

DROP TABLE IF EXISTS `nhomkhachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhomkhachhang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tennhom` varchar(45) DEFAULT '',
  `diem` int(11) DEFAULT '0',
  `phantram` double DEFAULT '0',
  `trangthai` varchar(45) DEFAULT NULL,
  `sodiemtrentien` int(11) DEFAULT NULL,
  `sotientrendiem` double DEFAULT NULL,
  `phantramtien` double DEFAULT NULL,
  `mota` varchar(95) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhomkhachhang`
--

LOCK TABLES `nhomkhachhang` WRITE;
/*!40000 ALTER TABLE `nhomkhachhang` DISABLE KEYS */;
INSERT INTO `nhomkhachhang` VALUES (1,'Tiềm Năng',2,3.6,'active',1,100000,10,'Nhóm Tiềm Năng'),(2,'',NULL,NULL,'deleted',NULL,NULL,NULL,''),(3,'tư',NULL,NULL,'deleted',NULL,NULL,NULL,''),(4,'tiềm năng2',NULL,NULL,'deleted',NULL,NULL,NULL,''),(5,'tiềm năng1',3,22,'deleted',NULL,NULL,NULL,'3'),(6,'tiềm năng 2',2,23,'active',NULL,NULL,NULL,'22'),(7,'tiềm năng12',23,2,'active',1,NULL,23,'123'),(8,'thu tien',1,1,'active',2,11,1,'1');
/*!40000 ALTER TABLE `nhomkhachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quyen`
--

DROP TABLE IF EXISTS `quyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quyen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `maquyen` varchar(45) NOT NULL,
  `tenquyen` varchar(45) DEFAULT '',
  `mota` varchar(45) DEFAULT '',
  `trangthai` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `maquyen_UNIQUE` (`maquyen`),
  UNIQUE KEY `UK79hh334rg067o8jax8b6fkste` (`maquyen`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quyen`
--

LOCK TABLES `quyen` WRITE;
/*!40000 ALTER TABLE `quyen` DISABLE KEYS */;
INSERT INTO `quyen` VALUES (1,'ROLE_ADMIN','Quan tri','Quanr tri','active'),(2,'ROLE_USER','Bán hàng','Nhân viên bán hàng','draft'),(3,'HOTRO_BANHANG','Hỗ trợ bán hàng','Nhân viên hỗ trợ bán hàng','active'),(4,'ROLE_TEL','ROLE_TELESALE','Bán hàng qua điện thoại, CTV','active'),(5,'SHIPPER','Giao hàng','Nhân viên giao hàng','deleted'),(6,'123','ROLE_SDFASDF',NULL,'inactive'),(7,'ROLE_THUQUYEN','thử quyền','thu quyên7','active'),(8,'ROLE_ADMIN1','qưe','3','deleted');
/*!40000 ALTER TABLE `quyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taikhoan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `matkhau` varchar(505) NOT NULL,
  `email` varchar(45) DEFAULT '',
  `thongtinkhac` varchar(45) DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `nhanvien_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `UK9rrdg0nicwf847tn9cx6dg2cv` (`username`),
  KEY `fk_nhanvien_taikhoan_idx` (`nhanvien_id`),
  CONSTRAINT `FKq4eyfcui3dyor85xgqxxebycq` FOREIGN KEY (`nhanvien_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `fk_nhanvien_taikhoan` FOREIGN KEY (`nhanvien_id`) REFERENCES `nhanvien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES (2,'cuongnp','123456','cuongnp@gmail.com','123','active',1),(3,'admin','123','admin@admin.com','123','active',1),(4,'admin1','$2a$10$i8RAfRxcngmMNkLGOPHG..8gjXCv/7Zs/UgGrnTfWtBda7ziM5/GC','admin1@gmail.com','123','active',1),(5,'ab','123','ab@gmail.com','abc','active',1),(6,'a1','$2a$10$VkrljB3gqv4SsGjEGWcliO6EcB0OjUpuc0vOGvw5C0S6uASAS5oiC','a@gmail.com','abc','deleted',1),(7,'admin2','$2a$10$QPNdd6m1puRKc31fPW/SKeQeVkQlJlQEdndI7YmIIm9RRQSjrCCSy','admin2@gmail.com','1231','active',4);
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan_quyen`
--

DROP TABLE IF EXISTS `taikhoan_quyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taikhoan_quyen` (
  `taikhoan_id` int(11) NOT NULL,
  `quyen_id` int(11) NOT NULL,
  PRIMARY KEY (`taikhoan_id`,`quyen_id`),
  KEY `fk_quyen_taikhoan_quyen_idx` (`quyen_id`),
  CONSTRAINT `FK51f3qwfympqqqwor4f28oml1l` FOREIGN KEY (`taikhoan_id`) REFERENCES `taikhoan` (`id`),
  CONSTRAINT `FKo1u207k6eyq551ypc64rqu3ln` FOREIGN KEY (`quyen_id`) REFERENCES `quyen` (`id`),
  CONSTRAINT `fk_quyen_taikhoan_quyen` FOREIGN KEY (`quyen_id`) REFERENCES `quyen` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_taikhoan_taikhoan_quyen` FOREIGN KEY (`taikhoan_id`) REFERENCES `taikhoan` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan_quyen`
--

LOCK TABLES `taikhoan_quyen` WRITE;
/*!40000 ALTER TABLE `taikhoan_quyen` DISABLE KEYS */;
INSERT INTO `taikhoan_quyen` VALUES (4,1),(4,3),(7,7);
/*!40000 ALTER TABLE `taikhoan_quyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tieuchichamsoc`
--

DROP TABLE IF EXISTS `tieuchichamsoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tieuchichamsoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tentieuchi` varchar(45) DEFAULT NULL,
  `mota` varchar(255) DEFAULT NULL,
  `kieutieuchi` varchar(45) DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `khac` varchar(45) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tieuchichamsoc`
--

LOCK TABLES `tieuchichamsoc` WRITE;
/*!40000 ALTER TABLE `tieuchichamsoc` DISABLE KEYS */;
INSERT INTO `tieuchichamsoc` VALUES (1,'abc','23','tien','active','23'),(2,'23232','2323','tien','deleted','2323'),(3,'zzz','zzzz','so','active','zz'),(4,'thu tieu chi','123','phantram','deleted','123'),(5,'123','123213','cokhong','active','123'),(6,'','','so','active','');
/*!40000 ALTER TABLE `tieuchichamsoc` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-25 14:20:58
