-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: hotrobanhang
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bophan`
--

LOCK TABLES `bophan` WRITE;
/*!40000 ALTER TABLE `bophan` DISABLE KEYS */;
INSERT INTO `bophan` VALUES (4,'Kinh Doanh','Bộ Phận Kinh Doanh','Kinh Doanh','active',NULL),(5,'Bán Hàng','Bộ Phận Bán Hàng','Bán Hàng','active',NULL),(6,'Giao Hàng','Bộ Phận Giao Hàng','Giao Hàng','active',NULL),(7,'Chăm Sóc','Bộ Phận Chăm Sóc','Chăm Sóc','active',NULL),(8,'Quản Lý','Bộ Phận Quản Lý','Quản Lý','active',NULL);
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
  `ngay` datetime DEFAULT NULL,
  `lan` int(11) DEFAULT '0',
  `noidung` varchar(255) DEFAULT '',
  `ngaycstiep` datetime DEFAULT NULL,
  `ghichu` varchar(255) DEFAULT '',
  `hoadon_id` int(11) DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `trangthainhac` varchar(45) DEFAULT NULL,
  `khachhang_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chamsoc_khachhang1_idx` (`khachhang_id`),
  CONSTRAINT `FKm0l09ssmksxb0t1c05dj7iemu` FOREIGN KEY (`khachhang_id`) REFERENCES `khachhang` (`id`),
  CONSTRAINT `fk_chamsoc_khachhang1` FOREIGN KEY (`khachhang_id`) REFERENCES `khachhang` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chamsoc`
--

LOCK TABLES `chamsoc` WRITE;
/*!40000 ALTER TABLE `chamsoc` DISABLE KEYS */;
INSERT INTO `chamsoc` VALUES (33,7,0,0,'2018-02-06 00:00:00',1,'Lần Chăm sóc 1','2018-02-13 00:00:00','',52,'dachamsoc','danhac',9),(34,7,11,11,'2018-03-12 00:00:00',2,'cham soc','2018-03-19 00:00:00','',52,'dachamsoc','danhac',9),(35,7,11,11,'2018-03-12 00:00:00',3,'q312','2018-03-19 00:00:00',NULL,52,'dachamsoc','danhac',9),(36,7,11,11,'2018-03-12 00:00:00',4,'ababa','2018-03-19 00:00:00',NULL,52,'chochamsoc','chuanhac',9),(37,7,0,0,'2018-03-14 00:00:00',5,'chăm sóc sinh nhật','2018-03-21 00:00:00','',0,'chochamsoc','chuanhac',9);
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
  `idchamsoc` int(11) NOT NULL,
  `tieuchichamsoc` int(11) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietchamsoc`
--

LOCK TABLES `chitietchamsoc` WRITE;
/*!40000 ALTER TABLE `chitietchamsoc` DISABLE KEYS */;
INSERT INTO `chitietchamsoc` VALUES (1,35,6,NULL,'active','\0',NULL,NULL),(2,34,6,NULL,'active','',NULL,NULL),(3,37,7,NULL,'active',NULL,100000,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
INSERT INTO `chitiethoadon` VALUES (48,51,4,450000,2,450000,900000,'active',''),(49,52,3,270000,3,270000,810000,'active',''),(50,53,4,450000,11,450000,4950000,'active',''),(51,54,4,450000,3,450000,1350000,'active',''),(52,55,4,450000,3,450000,1350000,'active',''),(53,56,4,450000,2,440000,880000,'active',''),(54,56,3,270000,3,270000,810000,'active',''),(55,57,4,450000,2,44000,88000,'active',''),(56,53,3,270000,3,270000,810000,'active',''),(57,58,6,12000,2,11500,23000,'active',''),(58,59,6,12000,2,15000,30000,'active',''),(59,60,6,12000,2,15000,30000,'active',''),(60,61,6,12000,2,15000,30000,'active',''),(61,62,6,12000,2,15000,30000,'active',''),(62,63,6,12000,2,20000,40000,'active',''),(63,64,6,12000,2,11500,23000,'active',''),(64,65,6,12000,2,11500,23000,'active',''),(65,66,6,12000,3,11500,34500,'active',''),(66,67,6,12000,3,11500,34500,'active',''),(67,68,6,12000,3,11500,34500,'active',''),(68,69,6,12000,3,11500,34500,'active',''),(70,71,6,12000,2,15000,30000,'active',''),(71,72,6,12000,3,20000,60000,'active',''),(72,73,6,12000,3,20000,60000,'active',''),(73,74,6,12000,3,20000,60000,'active',''),(74,75,6,12000,2,15000,30000,'active',''),(75,76,6,12000,2,20000,40000,'active',''),(76,77,6,12000,2,15000,30000,'active',''),(77,78,6,12000,20,11500,230000,'active',''),(78,79,6,12000,32,11500,368000,'active',''),(79,80,6,12000,32,11500,368000,'active',''),(80,81,6,12000,3,11500,34500,'active',''),(81,82,6,12000,3,11500,34500,'active',''),(82,83,6,12000,2,11500,23000,'active',''),(83,84,6,12000,3,20000,60000,'active',''),(84,85,6,12000,3,20000,60000,'active',''),(85,86,6,12000,3,20000,60000,'active',''),(86,87,6,12000,2,30000,60000,'active',''),(87,88,6,12000,3,30000,90000,'active',''),(88,89,6,12000,4,30000,120000,'active','');
/*!40000 ALTER TABLE `chitiethoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chucnang_quyen`
--

DROP TABLE IF EXISTS `chucnang_quyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chucnang_quyen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenchucnang` varchar(255) DEFAULT NULL,
  `duongdan` varchar(255) DEFAULT NULL,
  `idquyen` int(11) DEFAULT NULL,
  `mota` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chucnang_quyen_idx` (`idquyen`),
  CONSTRAINT `FK2osmlp1shw628jtvf2x20uy0f` FOREIGN KEY (`idquyen`) REFERENCES `quyen` (`id`),
  CONSTRAINT `fk_chucnang_quyen` FOREIGN KEY (`idquyen`) REFERENCES `quyen` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chucnang_quyen`
--

LOCK TABLES `chucnang_quyen` WRITE;
/*!40000 ALTER TABLE `chucnang_quyen` DISABLE KEYS */;
INSERT INTO `chucnang_quyen` VALUES (1,'trangchu',NULL,1,NULL);
/*!40000 ALTER TABLE `chucnang_quyen` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hanghoa`
--

LOCK TABLES `hanghoa` WRITE;
/*!40000 ALTER TABLE `hanghoa` DISABLE KEYS */;
INSERT INTO `hanghoa` VALUES (3,'MT001','Motul 7100','chai',11,'',NULL,NULL,NULL,'active',NULL),(4,'MT002','Motul 300V','chai',11,'',430000,450000,440000,'active',NULL),(5,'DN001','Nhớt 01','chai',12,'',130000,150000,140000,'active',NULL),(6,'DN002','Nhớt 02','chai',12,'',11000,12000,11500,'active',NULL);
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
  `ngaylap` datetime DEFAULT NULL,
  `ngayxuat` datetime DEFAULT NULL,
  `ngaythanhtoan` datetime DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (51,8,7,9,11,'HD201826554922','tienmat','2018-02-06 00:00:00','2018-02-06 00:00:00','2018-02-06 00:00:00',900000,900000,0,8,'Buôn Ma Thuột','098-738-3747','chuagiaohang',''),(52,11,7,11,11,'HD201826923180','tienmat','2018-02-06 00:00:00','2018-02-06 00:00:00','2018-02-06 00:00:00',810000,810000,0,9,'Buôn Ma Thuột','097-263-74632','chuagiaohang',''),(53,11,7,11,11,'HD2018262031408','tienmat','2018-02-06 00:00:00','2018-02-06 00:00:00','2018-02-06 00:00:00',5760000,5760000,0,9,'Buôn Ma Thuột','098-728-3722','chuagiaohang',''),(54,11,7,11,11,'HD20183125551965','tienmat','2018-03-12 00:00:00','2018-03-12 00:00:00','2018-03-12 00:00:00',9,0,9,9,'123','323-232-32323','chuagiaohang',''),(55,11,7,11,11,'HD2018312555719','tienmat','2018-03-12 00:00:00','2018-03-12 00:00:00','2018-03-12 00:00:00',1350000111,0,1350000111,9,'123123','123-232-13312','chuagiaohang',''),(56,11,7,11,11,'HD20183132227274','tienmat','2018-03-13 00:00:00','2018-03-13 00:00:00','2018-03-13 00:00:00',1690000,1690000,0,9,'123123','123-123-23213','chuagiaohang',''),(57,11,7,11,11,'HD20183135449733','tienmat','2018-03-13 00:00:00','2018-03-13 00:00:00','2018-03-13 00:00:00',88000,88000,0,9,'12312','123-123-21321','chuagiaohang',''),(58,11,7,12,12,'HD20183154444210','tienmat','2018-03-15 00:00:00','2018-03-15 00:00:00','2018-03-15 00:00:00',23000,23000,0,8,'123','123-123-21321','chuagiaohang',''),(59,11,11,12,12,'HD201831521961','tienmat','2018-03-15 00:00:00','2018-03-15 00:00:00','2018-03-15 00:00:00',30000,30000,0,10,'12313','123-123-12313','chuagiaohang','123123'),(60,11,11,12,12,'HD2018315446705','tienmat','2018-03-15 00:00:00','2018-03-15 00:00:00','2018-03-15 00:00:00',30000,30000,0,10,'123','123-123-12323','chuagiaohang',''),(61,11,11,12,12,'HD2018315851949','tienmat','2018-03-15 00:00:00','2018-03-15 00:00:00','2018-03-15 00:00:00',30000,30000,0,10,'123','123-123-12333','chuagiaohang',''),(62,11,11,12,12,'HD20183151138557','tienmat','2018-03-15 00:00:00','2018-03-15 00:00:00','2018-03-15 00:00:00',30000,30000,0,10,'123','123-123-13212','chuagiaohang',''),(63,11,11,12,12,'HD20183152613162','tienmat','2018-03-15 00:00:00','2018-03-15 00:00:00','2018-03-15 00:00:00',40000,40000,0,10,'123123123','123-123-13213','chuagiaohang',''),(64,12,11,12,12,'HD20183153132742','tienmat','2018-03-15 00:00:00','2018-03-15 00:00:00','2018-03-15 00:00:00',23000,23000,0,10,'123','123-123-12312','chuagiaohang',''),(65,11,11,12,12,'HD20183153222481','tienmat','2018-03-15 00:00:00','2018-03-15 00:00:00','2018-03-15 00:00:00',23000,23000,0,10,'êq','123--','chuagiaohang',''),(66,11,11,12,12,'HD20183153326668','tienmat','2018-03-15 00:00:00','2018-03-15 00:00:00','2018-03-15 00:00:00',34500,34500,0,10,'123','123-123-13123','chuagiaohang',''),(67,11,11,12,12,'HD2018315368197','tienmat','2018-03-15 00:00:00','2018-03-15 00:00:00','2018-03-15 00:00:00',34500,34500,0,10,'23','123-131-23213','chuagiaohang',''),(68,11,11,12,12,'HD20183153817255','tienmat','2018-03-15 00:00:00','2018-03-15 00:00:00','2018-03-15 00:00:00',34500,34500,0,10,'123','123-131-23123','chuagiaohang',''),(69,11,11,12,12,'HD2018315404571','tienmat','2018-03-15 00:00:00','2018-03-15 00:00:00','2018-03-15 00:00:00',34500,0,34500,10,'123','123-123-123','chuagiaohang',''),(71,11,11,12,12,'HD2018315336831','tienmat','2018-03-15 00:00:00','2018-03-15 00:00:00','2018-03-15 00:00:00',30000,0,30000,10,'123','123-123-21321','chuagiaohang',''),(72,11,11,12,12,'HD20183163213405','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',60000,0,60000,10,'123','123-123-12313','chuagiaohang',''),(73,12,11,12,12,'HD20183164637966','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',60000,60000,0,10,'123','123-123-12321','chuagiaohang',''),(74,11,11,12,12,'HD20183164847985','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',60000,0,60000,10,'123','123-123-12312','chuagiaohang',''),(75,11,11,12,12,'HD2018316527512','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',30000,0,30000,10,'213','123-131-23213','chuagiaohang',''),(76,11,11,12,12,'HD20183165347844','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',40000,0,40000,10,'123123','123-123-13213','chuagiaohang',''),(77,11,11,12,12,'HD20183165116895','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',30000,0,30000,10,'123','123-123-21321','chuagiaohang',''),(78,11,11,12,12,'HD20183165643393','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',230000,230000,0,10,'31','123-123-21321','chuagiaohang',''),(79,11,11,12,12,'HD20183165932714','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',368000,368000,0,10,'123`','213-123-13131','chuagiaohang',''),(80,11,11,12,12,'HD201831642736','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',368000,45778,322222,10,'123','123-123-13123','chuagiaohang',''),(81,11,11,12,12,'HD2018316748812','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',34500,0,34500,10,'123','231-232-13213','chuagiaohang',''),(82,11,11,12,12,'HD2018316932483','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',34500,34500,0,10,'231','123-123-12312','chuagiaohang',''),(83,11,11,12,12,'HD20183161659589','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',23000,0,23000,10,'123123','123-123-21321','chuagiaohang',''),(84,11,11,12,12,'HD2018316393431','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',60000,40000,20000,10,'1232131','232-131-23123','chuagiaohang',''),(85,11,11,12,12,'HD20183165131856','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',60000,0,60000,10,'123','123-123-12321','deleted',''),(86,11,11,12,12,'HD20183165255610','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',60000,0,60000,10,'23','123-213-12312','deleted',''),(87,11,11,12,12,'HD2018316240755','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',60000,60000,0,10,'2132','122-132-32133','deleted',''),(88,11,11,12,12,'HD2018316458241','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',90000,0,90000,10,'123213','123-213-12321','chuagiaohang',''),(89,11,11,12,12,'HD2018316842313','tienmat','2018-03-16 00:00:00','2018-03-16 00:00:00','2018-03-16 00:00:00',120000,0,120000,10,'12','121-212-11212','chuagiaohang','');
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
  `ngaysinhphutrach` datetime DEFAULT NULL,
  `maloaikhachhang` int(11) NOT NULL,
  `sogpkd` varchar(50) DEFAULT '',
  `ngaycap` datetime DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (8,'KH001','Nguyễn Ngọc Trung',9827,'23098273626','Buôn Ma Thuột','098-783-7463',NULL,NULL,'1996-03-17 00:00:00',NULL,NULL,NULL,41,NULL,NULL,NULL,'active',2300,0,9,0,0,'chosinhnhat',0,'co',NULL,9),(9,'KH002','Nguyễn Văn Cảnh',NULL,NULL,NULL,NULL,NULL,NULL,'1996-03-16 00:00:00',NULL,NULL,NULL,41,NULL,NULL,NULL,'active',2971300044,100000,445706,2,3,'chosinhnhat',0,'co',NULL,11),(10,'KH003','Khách Hàng 003',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',558600,0,37,0,0,'chosinhnhat',0,'co',NULL,13);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kpi`
--

LOCK TABLES `kpi` WRITE;
/*!40000 ALTER TABLE `kpi` DISABLE KEYS */;
INSERT INTO `kpi` VALUES (5,'Doanh Thu',500000,0,'active','');
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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaikhachhang`
--

LOCK TABLES `loaikhachhang` WRITE;
/*!40000 ALTER TABLE `loaikhachhang` DISABLE KEYS */;
INSERT INTO `loaikhachhang` VALUES (41,'Mới','active','');
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
  `thuongcuahoadon` decimal(10,0) DEFAULT NULL,
  `thang` varchar(2) DEFAULT NULL,
  `nam` varchar(5) DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `ghichu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_nhanvien_luong_idx` (`nhanvien_id`),
  CONSTRAINT `FK13uvechwuw0ch6b07jykctx6j` FOREIGN KEY (`nhanvien_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `fk_nhanvien_luong` FOREIGN KEY (`nhanvien_id`) REFERENCES `nhanvien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `luong`
--

LOCK TABLES `luong` WRITE;
/*!40000 ALTER TABLE `luong` DISABLE KEYS */;
INSERT INTO `luong` VALUES (24,7,3000000,747000,0,'02','2018','chuatraluong',''),(25,11,3000000,2061000,0,'02','2018','chuatraluong',''),(26,10,3000000,0,0,'02','2018','chuatraluong',''),(27,9,3000000,90000,0,'02','2018','chuatraluong',''),(28,8,3000000,90000,0,'02','2018','chuatraluong',''),(29,11,3000000,2700,24100,'03','2018','chuatraluong',''),(30,10,3000000,0,0,'03','2018','chuatraluong',''),(31,9,3000000,0,0,'03','2018','chuatraluong',''),(32,8,3000000,0,0,'03','2018','chuatraluong',''),(33,7,3200000,22300,0,'03','2018','chuatraluong',''),(35,12,0,0,0,'03','2018','chuatraluong','');
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
  `ngaycap` datetime DEFAULT NULL,
  `noicap` varchar(45) DEFAULT '',
  `sodienthoai` varchar(45) DEFAULT '',
  `diachi` varchar(155) DEFAULT '',
  `ngayvaolam` datetime DEFAULT NULL,
  `luong` decimal(10,0) DEFAULT NULL,
  `chietkhau` double DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `hienthiluong` varchar(45) DEFAULT NULL,
  `thongtinkhac` varchar(255) DEFAULT '',
  `ghichu` varchar(255) DEFAULT '',
  `bophan_id` int(11) NOT NULL,
  `idnhanviencaptren` int(11) DEFAULT NULL,
  `chietkhauchonhanviencaptren` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `manhanvien_UNIQUE` (`manhanvien`),
  UNIQUE KEY `UK3xlxfe14vfowvpflhtexay5em` (`manhanvien`),
  KEY `fk_nhanvien_bophan1_idx` (`bophan_id`),
  CONSTRAINT `FK659m81wi1c5wn5h9vgnqar4qa` FOREIGN KEY (`bophan_id`) REFERENCES `bophan` (`id`),
  CONSTRAINT `fk_nhanvien_bophan1` FOREIGN KEY (`bophan_id`) REFERENCES `bophan` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (7,'QT001','Nguyễn Phước Cường','293-838-272','2005-03-29 00:00:00','Buôn Ma Thuột','098-767-2632','Buôn Ma Thuột','2010-03-20 00:00:00',3000000,10,'active','khonghienthi','Nhân Viên Quản Lý','',8,0,0),(8,'BH001','Nguyễn Thị Ánh','239-209-393','2005-03-29 00:00:00','Buôn Ma Thuột','098-928-0298','Buôn Ma Thuột','2010-03-20 00:00:00',3500000,10,'active','hienthi','Nhân Viên Bán Hàng','',5,0,0),(9,'CS001','Nguyễn Thị Ngọc Dung','239-049-228','2005-03-29 00:00:00','Buôn Ma Thuột','098-029-0984','Buôn Ma Thuột','2010-03-20 00:00:00',3000000,10,'active','hienthi','Nhân Viên Chăm Sóc','',7,0,0),(10,'GH001','Trần Minh Tài','287-029-376','2005-03-29 00:00:00','Buôn Ma Thuột','016-098-99282','Buôn Ma Thuột','2010-03-20 00:00:00',3000000,10,'active','hienthi','Nhân Viên Giao Hàng','',6,0,0),(11,'KD001','Nguyễn Thị Tuyết Mai','287-392-293','2005-03-29 00:00:00','Buôn Ma Thuột','098-291-4522','Buôn Ma Thuột','2010-03-20 00:00:00',3000000,10,'active','hienthi','Nhân Viên Kinh Doanh','',4,0,0),(12,'NV003','Nhân Viên 003','230-994-929','2018-02-09 00:00:00','BMT','093-838-38373','ưh','2018-03-12 00:00:00',0,0,'active','khonghienthi','','',8,0,0),(13,'NV004','Nhân Viên 004','230-998-999','2010-12-20 00:00:00','Gia Lai','098-728-27382','An khê - Gia Lai','2018-02-20 00:00:00',3000000,5,'active','hienthi','','',5,11,2.5);
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
  `ngaydangky` datetime DEFAULT NULL,
  `ngayhoanthanh` datetime DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvienkpi`
--

LOCK TABLES `nhanvienkpi` WRITE;
/*!40000 ALTER TABLE `nhanvienkpi` DISABLE KEYS */;
INSERT INTO `nhanvienkpi` VALUES (10,7,5,50000,'2018-02-06 00:00:00','2018-02-28 00:00:00','inactive',0,''),(11,7,5,500000,'2018-02-06 00:00:00','2018-02-28 00:00:00','inactive',0,''),(12,8,5,60000,'2018-02-06 00:00:00','2018-02-28 00:00:00','inactive',200,''),(13,7,5,-50000,'2018-02-06 00:00:00','2018-02-28 00:00:00','inactive',0,'');
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhomhang`
--

LOCK TABLES `nhomhang` WRITE;
/*!40000 ALTER TABLE `nhomhang` DISABLE KEYS */;
INSERT INTO `nhomhang` VALUES (11,'DN001',0,'Dầu Nhớt','active',''),(12,'PG001',0,'Phụ Gia','active','');
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhomkhachhang`
--

LOCK TABLES `nhomkhachhang` WRITE;
/*!40000 ALTER TABLE `nhomkhachhang` DISABLE KEYS */;
INSERT INTO `nhomkhachhang` VALUES (9,'Tiềm Năng',100,10,'active',1,100000,10,''),(10,'Đang Chăm Sóc',200,15,'active',2,100000,15,''),(11,'Chờ',200,20,'active',3,100000,20,''),(12,'Nợ',50,5,'active',1,50000,5,''),(13,'VIP',300,30,'active',3,100000,30,'');
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quyen`
--

LOCK TABLES `quyen` WRITE;
/*!40000 ALTER TABLE `quyen` DISABLE KEYS */;
INSERT INTO `quyen` VALUES (1,'ROLE_ADMIN','Quản Trị','Quanr tri','active'),(11,'ROLE_CHAMSOC','Chăm Sóc','','active'),(12,'ROLE_BANHANG','Bán Hàng','','active'),(13,'ROLE_GIAOHANG','Giao Hàng','','active'),(14,'ROLE_TAICHINH','Tài Chính','','active');
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES (12,'admin','$2a$10$jS9Mpd0tgkj8NghGOZ40v.h3pbq28dPmNXBRTI.siWLgPKILlM06.','admin@gmail.com','123','active',7),(13,'maintt','$2a$10$ZzVYbJAcBDNVOgAcR2oIOeuJNTOHCAK91/X2YmMOHu5yzQbeJ0sKq','maintt@gmail.com','','active',11),(14,'taitm','$2a$10$wB7q2x/w7CPKOtKoKoUb0.MPwcnojejLfou7gT5L7/aQ5ZwjPF9Ia','taitm@gmail.com','','active',10),(15,'dungntn','$2a$10$aVpDI4Dsk7fmLfRQGrO6.udcmTBqXaVgi4Ad9A9y35On1e138FqDC','dungntn@gmail.com','','active',9),(16,'anhnt','$2a$10$aJPGbWxGJCCGytz6SbsBU.3Trh82emv4SO4.zzhPLX.nojFBv12dK','anhnt@gmail.com','','active',8);
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
INSERT INTO `taikhoan_quyen` VALUES (12,1),(12,11),(15,11),(12,12),(13,12),(16,12),(12,13),(14,13),(12,14),(13,14);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tieuchichamsoc`
--

LOCK TABLES `tieuchichamsoc` WRITE;
/*!40000 ALTER TABLE `tieuchichamsoc` DISABLE KEYS */;
INSERT INTO `tieuchichamsoc` VALUES (6,'Đánh Giá Nhân VIên','','cokhong','active',''),(7,'Sinh Nhật','','tien','active',''),(8,'Kiểu Số','','so','active','');
/*!40000 ALTER TABLE `tieuchichamsoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ungluong`
--

DROP TABLE IF EXISTS `ungluong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ungluong` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idluong` int(11) NOT NULL,
  `sotienung` decimal(10,0) DEFAULT NULL,
  `ngayung` datetime DEFAULT NULL,
  `noidung` text,
  `trangthai` varchar(45) DEFAULT NULL,
  `ghichu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_luong_ungluong_idx` (`idluong`),
  CONSTRAINT `FKh6ds6sr3ckqdnfps00km3hnlt` FOREIGN KEY (`idluong`) REFERENCES `luong` (`id`),
  CONSTRAINT `fk_luong_ungluong` FOREIGN KEY (`idluong`) REFERENCES `luong` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ungluong`
--

LOCK TABLES `ungluong` WRITE;
/*!40000 ALTER TABLE `ungluong` DISABLE KEYS */;
INSERT INTO `ungluong` VALUES (1,33,200000,'2018-03-14 00:00:00','ứng lương','active','no');
/*!40000 ALTER TABLE `ungluong` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-16 16:16:51
