-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: hotrobanhang
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
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
 SET character_set_client = utf8mb4 ;
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
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chamsoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nhanvienchamsoc` int(11) DEFAULT '0',
  `nhanvienbanhang` int(11) DEFAULT '0',
  `nhanviengiaohang` int(11) DEFAULT '0',
  `ngay` datetime DEFAULT NULL,
  `lan` int(11) DEFAULT '0',
  `noidung` text,
  `ngaycstiep` datetime DEFAULT NULL,
  `ghichu` varchar(255) DEFAULT '',
  `hoadon_id` int(11) DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `trangthainhac` varchar(45) DEFAULT NULL,
  `khachhang_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chamsoc_khachhang1_idx` (`khachhang_id`),
  CONSTRAINT `FKm0l09ssmksxb0t1c05dj7iemu` FOREIGN KEY (`khachhang_id`) REFERENCES `khachhang` (`id`),
  CONSTRAINT `fk_chamsoc_khachhang1` FOREIGN KEY (`khachhang_id`) REFERENCES `khachhang` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chamsoc`
--

LOCK TABLES `chamsoc` WRITE;
/*!40000 ALTER TABLE `chamsoc` DISABLE KEYS */;
INSERT INTO `chamsoc` VALUES (1,22,22,20,'2018-05-18 00:00:00',1,'KHONG HAI LONG','2018-04-25 00:00:00','',9,'chochamsoc','chuanhac',90),(2,26,22,16,'2018-05-18 00:00:00',1,'Mua bia','2018-06-14 00:00:00','',9,'chochamsoc','chuanhac',91),(3,26,22,0,'2018-05-24 00:00:00',1,'mua qua sn','2018-06-20 00:00:00','',0,'chochamsoc','chuanhac',107),(4,26,22,0,'2018-06-13 00:00:00',1,'hẹn đàm phán','2018-06-15 00:00:00','',0,'dachamsoc','danhac',113),(5,26,22,26,'2018-06-15 00:00:00',2,'đàm phán','2018-06-22 00:00:00',NULL,0,'chochamsoc','chuanhac',113);
/*!40000 ALTER TABLE `chamsoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietchamsoc`
--

DROP TABLE IF EXISTS `chitietchamsoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
  CONSTRAINT `fk_chitietchamsoc_chamsoc1` FOREIGN KEY (`idchamsoc`) REFERENCES `chamsoc` (`id`),
  CONSTRAINT `fk_chitietchamsoc_tieuchichamsoc1` FOREIGN KEY (`tieuchichamsoc`) REFERENCES `tieuchichamsoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietchamsoc`
--

LOCK TABLES `chitietchamsoc` WRITE;
/*!40000 ALTER TABLE `chitietchamsoc` DISABLE KEYS */;
INSERT INTO `chitietchamsoc` VALUES (3,3,7,NULL,'active',NULL,500000,NULL);
/*!40000 ALTER TABLE `chitietchamsoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitiethoadon`
--

DROP TABLE IF EXISTS `chitiethoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
  CONSTRAINT `fk_chitiethoadon_hanghoa1` FOREIGN KEY (`idhanghoa`) REFERENCES `hanghoa` (`id`),
  CONSTRAINT `fk_chitiethoadon_hoadon1` FOREIGN KEY (`idhoadon`) REFERENCES `hoadon` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
INSERT INTO `chitiethoadon` VALUES (6,5,7,10995000,1,10995000,10995000,'active',''),(7,6,8,12095000,1,12095000,12095000,'active',''),(8,7,8,12095000,1,12095000,12095000,'active',''),(9,7,16,9320000,1,9320000,9320000,'active',''),(10,8,34,1060000,1,1060000,1060000,'active',''),(11,8,35,1120000,1,1120000,1120000,'active',''),(12,9,54,398000,6,398000,2388000,'active',''),(13,9,58,345000,6,345000,2070000,'active',''),(15,11,52,320000,6,320000,1920000,'active',''),(16,12,23,1145000,1,1145000,1145000,'active',''),(17,12,22,1050000,1,1050000,1050000,'active',''),(21,15,54,398000,6,398000,2388000,'active',''),(22,15,52,320000,6,320000,1920000,'active',''),(23,16,58,345000,6,345000,2070000,'active',''),(24,17,52,320000,6,320000,1920000,'active',''),(25,18,30,895000,1,895000,895000,'active',''),(28,20,23,1145000,1,1145000,1145000,'active',''),(29,21,23,1145000,1,1145000,1145000,'active',''),(31,23,52,320000,8,400000,3200000,'active',''),(34,25,23,1145000,1,1320000,1320000,'active',''),(35,26,23,1145000,5,1145000,5725000,'active',''),(58,38,16,9320000,1,9320000,9320000,'active',''),(59,39,30,895000,3,895000,2685000,'active',''),(60,40,16,9320000,1,9320000,9320000,'active',''),(61,41,52,320000,6,320000,1920000,'active',''),(62,42,23,1145000,5,1145000,5725000,'active',''),(63,43,30,895000,1,895000,895000,'active',''),(64,44,23,1145000,2,1145000,2290000,'active',''),(65,45,22,1050000,1,1050000,1050000,'active',''),(66,46,30,895000,2,895000,1790000,'active',''),(67,47,16,9320000,1,9320000,9320000,'active',''),(68,47,23,1145000,2,1145000,2290000,'active',''),(70,49,30,895000,1,895000,895000,'active',''),(71,50,30,895000,3,895000,2685000,'active',''),(72,50,23,1145000,3,1145000,3435000,'active',''),(73,51,52,320000,6,320000,1920000,'active',''),(74,51,54,398000,6,398000,2388000,'active',''),(75,52,22,1050000,1,1050000,1050000,'active',''),(76,52,54,398000,1,398000,398000,'active',''),(77,53,30,895000,1,895000,895000,'active',''),(78,54,56,303000,1,303000,303000,'active',''),(79,54,35,1120000,1,1120000,1120000,'active',''),(80,55,52,320000,6,320000,1920000,'active',''),(81,55,54,398000,6,398000,2388000,'active',''),(83,57,34,1060000,1,1060000,1060000,'active',''),(84,58,8,12095000,1,12095000,12095000,'active',''),(85,59,52,320000,2,320000,640000,'active',''),(86,60,23,1145000,1,1145000,1145000,'active',''),(87,61,23,1145000,1,1145000,1145000,'active',''),(88,62,52,320000,1,400000,400000,'active',''),(89,63,30,895000,2,895000,1790000,'active',''),(90,64,52,320000,6,320000,1920000,'active',''),(91,65,54,398000,1,398000,398000,'active',''),(92,66,30,895000,1,895000,895000,'active',''),(93,67,23,1145000,2,1145000,2290000,'active',''),(94,68,58,345000,12,345000,4140000,'active',''),(95,68,52,320000,12,320000,3840000,'active',''),(96,68,54,398000,12,398000,4776000,'active',''),(97,69,16,9320000,1,9320000,9320000,'active',''),(98,70,52,320000,1,400000,400000,'active',''),(99,71,42,1740000,1,1740000,1740000,'active',''),(100,72,52,320000,1,400000,400000,'active',''),(101,73,23,1145000,2,1145000,2290000,'active',''),(102,74,55,554000,2,554000,1108000,'active',''),(103,75,23,1145000,1,1145000,1145000,'active',''),(104,76,52,320000,1,400000,400000,'active',''),(105,77,52,320000,6,320000,1920000,'active',''),(106,78,27,1295000,1,1295000,1295000,'active',''),(107,79,54,398000,6,398000,2388000,'active',''),(108,79,56,303000,6,303000,1818000,'active',''),(109,79,23,1145000,2,1145000,2290000,'active',''),(110,79,16,9320000,1,9320000,9320000,'active',''),(111,80,23,1145000,2,1145000,2290000,'active',''),(112,81,56,303000,6,303000,1818000,'active',''),(113,81,52,320000,6,320000,1920000,'active',''),(114,82,30,895000,3,895000,2685000,'active',''),(115,83,29,865000,2,865000,1730000,'active',''),(116,83,28,830000,1,830000,830000,'active',''),(117,84,22,1050000,1,1050000,1050000,'active',''),(118,84,23,1145000,1,1145000,1145000,'active',''),(119,85,52,320000,6,320000,1920000,'active',''),(120,85,54,398000,6,398000,2388000,'active',''),(121,86,23,1145000,1,1145000,1145000,'active',''),(122,87,52,320000,6,320000,1920000,'active',''),(123,88,52,320000,6,320000,1920000,'active',''),(125,90,58,345000,10,345000,3450000,'active','');
/*!40000 ALTER TABLE `chitiethoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chucnang_quyen`
--

DROP TABLE IF EXISTS `chucnang_quyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chucnang_quyen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenchucnang` varchar(255) DEFAULT NULL,
  `duongdan` varchar(255) DEFAULT NULL,
  `idquyen` int(11) DEFAULT NULL,
  `mota` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chucnang_quyen_idx` (`idquyen`),
  CONSTRAINT `FK2osmlp1shw628jtvf2x20uy0f` FOREIGN KEY (`idquyen`) REFERENCES `quyen` (`id`),
  CONSTRAINT `fk_chucnang_quyen` FOREIGN KEY (`idquyen`) REFERENCES `quyen` (`id`)
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
 SET character_set_client = utf8mb4 ;
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
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hanghoa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mahang` varchar(45) NOT NULL,
  `tenhang` varchar(255) NOT NULL,
  `donvitinh` varchar(45) DEFAULT '',
  `nhomhang` int(11) NOT NULL DEFAULT '0',
  `mota` varchar(255) DEFAULT '',
  `gianhap` decimal(10,0) DEFAULT '0',
  `giaban` decimal(10,0) DEFAULT '0',
  `giabanle` decimal(10,0) DEFAULT NULL,
  `giakhuyenmai` decimal(10,0) DEFAULT '0',
  `trangthai` varchar(45) DEFAULT NULL,
  `ghichu` varchar(45) DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mahang_UNIQUE` (`mahang`),
  UNIQUE KEY `UK5bsjl9yl4x9nmp4upguw5bqs6` (`mahang`),
  KEY `fk_hanghoa_nhomhang1_idx` (`nhomhang`),
  CONSTRAINT `FK74w5ctlq8n2hpdksjltsg1y42` FOREIGN KEY (`nhomhang`) REFERENCES `nhomhang` (`id`),
  CONSTRAINT `fk_hanghoa_nhomhang1` FOREIGN KEY (`nhomhang`) REFERENCES `nhomhang` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hanghoa`
--

LOCK TABLES `hanghoa` WRITE;
/*!40000 ALTER TABLE `hanghoa` DISABLE KEYS */;
INSERT INTO `hanghoa` VALUES (7,'1',' JAPAN HDO TURBO 40 SAE 15W40 , API CF-4 200L','200lit',11,'',9365000,10995000,12645000,NULL,'active',NULL),(8,'2',' JAPAN HDO TURBO 50 SAE 20W50 , API CF-4 200L','200lit',11,'',10280000,12095000,13910000,NULL,'active',NULL),(9,'15W40CH',' JAPAN HDO TURBO 40 SAE 15W40 , API CH-4 ','200lit',11,'',10440000,0,0,NULL,'deleted',NULL),(10,'3',' JAPAN HDO TURBO 40 SAE 15W40 , API CH-4 200L','200lit',11,'',10440000,12285000,14130000,NULL,'active',NULL),(11,'4',' JAPAN HDO TURBO 50 SAE 20W50 , API CH-4 200L','200lit',11,'',11355000,13400000,15400000,NULL,'active',NULL),(12,'5',' JAPAN  HDO TURBO 40 SAE 15W40 , API CI4 200L','200lit',11,'',10785000,12705000,14610000,NULL,'active',NULL),(13,'6',' JAPAN HDO TURBO 50 SAE 20W50 , API CI-4 200L','200lit',11,'',11675000,13770000,15835000,NULL,'active',NULL),(14,'7',' JAPAN  HYDRAULIC AW 32 200L','200lit',11,'',7334000,8560000,9845000,NULL,'active',NULL),(15,'8',' JAPAN HYDRAULIC AW 46 200L','200lit',11,'',7655000,8945000,10285000,NULL,'active',NULL),(16,'9',' JAPAN HYDRAULIC AW 68 200L','200lit',11,'',7967000,9320000,10720000,NULL,'active',NULL),(17,'10',' JAPAN  HYDRAULIC HVI 32( Vàng ) 200L','200lit',11,'',9550000,11220000,12905000,NULL,'active',NULL),(18,'11',' JAPAN  HYDRAULIC HVI 46( Vàng ) 200L','200lit',11,'',9868000,11600000,13340000,NULL,'active',NULL),(19,'12',' JAPAN  HYDRAULIC HVI 68 ( Vàng ) 200L','200lit',11,'',10185000,11600000,13780000,NULL,'active',NULL),(20,'13',' JAPAN GEAR OIL SUPER 90 SAE 80W90, API GL -5 200L','200lit',11,'',9487000,11145000,12820000,NULL,'active',NULL),(21,'14',' JAPAN GEAR OIL SUPER 140 SAE 85W-140, API GL -5 200L','200lit',11,'',10026000,11795000,13560000,NULL,'active',NULL),(22,'15',' JAPAN HDO TURBO 40 SAE 15W40 , API CF-4 18L','18lit',11,'',890000,1050000,1205000,NULL,'active',NULL),(23,'16',' JAPAN HDO TURBO 50 SAE 20W50 , API CF-4 18L','18lit',11,'',975000,1145000,1320000,NULL,'active',NULL),(24,'17',' JAPAN HDO TURBO 40 SAE 15W40 , API CH-4 18L','18lit',11,'',987000,1170000,1340000,NULL,'active',NULL),(25,'18',' JAPAN HDO TURBO 50 SAE 20W50 , API CH-4 18L','18lit',11,'',1070000,1265000,1455000,NULL,'active',NULL),(26,'19',' JAPAN  HDO TURBO 40 SAE 15W40 , API CI4 18L','18lit',11,'',1018000,1200000,1380000,NULL,'active',NULL),(27,'20',' JAPAN HDO TURBO 50 SAE 20W50 , API CI-4 18L','18lit',11,'',1097000,1295000,1490000,NULL,'active',NULL),(28,'21',' JAPAN  HYDRAULIC AW 32 18L','18lit',11,'',707000,830000,830000,NULL,'active',NULL),(29,'22',' JAPAN HYDRAULIC AW 46 18L','18lit',11,'',735000,865000,865000,NULL,'active',NULL),(30,'23',' JAPAN HYDRAULIC AW 68 18L','18lit',11,'',765000,895000,895000,NULL,'active',NULL),(31,'24',' JAPAN  HYDRAULIC HVI 32( Vàng ) 18L','18lit',11,'',908000,1070000,1070000,NULL,'active',NULL),(32,'25',' JAPAN  HYDRAULIC HVI 46( Vàng ) 18L','18lit',11,'',935000,1100000,1100000,NULL,'active',NULL),(33,'26',' JAPAN  HYDRAULIC HVI 68 ( Vàng ) 18L','18lit',11,'',965000,1135000,1135000,NULL,'active',NULL),(34,'27',' JAPAN GEAR OIL SUPER 90 SAE 80W90, API GL -5 18L','18lit',11,'',902000,1060000,1060000,NULL,'active',NULL),(35,'28',' JAPAN GEAR OIL SUPER 140 SAE 85W-140, API GL -5 18L','18lit',11,'',950000,1120000,1120000,NULL,'active',NULL),(36,'29',' JAPAN HDO TURBO 40 SAE 15W40 , API CF-4 25L','25lit',12,'',1185000,1395000,1600000,NULL,'active',NULL),(37,'30',' JAPAN HDO TURBO 50 SAE 20W50 , API CF-4 25L','25 lít',11,'',1300000,1530000,1760000,NULL,'active',NULL),(38,'31',' JAPAN HDO TURBO 40 SAE 15W40 , API CH-4 25L','25 lít',11,'',1320000,1555000,1785000,NULL,'active',NULL),(39,'32',' JAPAN HDO TURBO 40 SAE 15W40 , API CH-4 ','25 lít',11,'',1435000,1695000,1945000,NULL,'deleted',NULL),(40,'33',' JAPAN HDO TURBO 50 SAE 20W50 , API CH-4 25L','25 lít ',11,'',1435000,1695000,1945000,NULL,'active',NULL),(41,'34',' JAPAN  HDO TURBO 40 SAE 15W40 , API CI4 25L','25 lít',11,'',1362000,1605000,1845000,NULL,'active',NULL),(42,'35',' JAPAN HDO TURBO 50 SAE 20W50 , API CI-4 25L','25lit',11,'',1474000,1740000,2000000,NULL,'active',NULL),(44,'36',' JAPAN HYDRAULIC AW 46 25L','25lit',11,'',971000,1135000,1305000,NULL,'active',NULL),(45,'37',' JAPAN HYDRAULIC AW 68 25L','25lit',11,'',1010000,1185000,1360000,NULL,'active',NULL),(46,'38',' JAPAN  HYDRAULIC HVI 32( Vàng ) 25L','25lit',11,'',1208000,1420000,1635000,NULL,'active',NULL),(47,'39',' JAPAN  HYDRAULIC HVI 46( Vàng ) 25L','25lit',11,'',1248000,1470000,1690000,NULL,'active',NULL),(48,'40',' JAPAN  HYDRAULIC HVI 68 ( Vàng ) 25L','25lit',11,'',1288000,1515000,1745000,NULL,'active',NULL),(49,'41',' JAPAN GEAR OIL SUPER 90 SAE 80W90, API GL -5 25L','25lit',11,'',1200000,1410000,1625000,NULL,'active',NULL),(50,'42',' JAPAN GEAR OIL SUPER 140 SAE 85W-140, API GL -5 25L','25lit',11,'',1267000,1495000,1715000,NULL,'active',NULL),(51,'50',' JAPAN  HYDRAULIC AW 32 25L','25lit',11,'',931000,1090000,1250000,NULL,'active',NULL),(52,'4L',' JAPAN SUPER PCMO 40 SAE 5W40; API SL, ACEA A3/B3  4L','4 LIT',11,'',266500,320000,400000,NULL,'active',NULL),(53,'4L SN',' JAPAN PCMO EXTRA 40 SAE 5W40; API SN, ACEA A5/B5 ','4LIT',11,'',335400,493000,704000,NULL,'active',NULL),(54,'5L CF',' JAPAN TUBO 50, SAE 20W50; API CF-4; ACEA E2-96 5LIT','5 LIT',11,'',331500,398000,498000,NULL,'active',NULL),(55,'5L CI',' JAPAN TUBO PLUS 40, SAE 5W40; API CI-4; ACEA E7-12 5LIT','5 LIT',11,'',426400,554000,776000,NULL,'active',NULL),(56,'4L 90',' JAPAN SUPER GEAR 90 SAE 80W90; API GL-5 ','4 LIT',11,'',252200,303000,394000,NULL,'active',NULL),(57,'4 LIT 140',' JAPAN SUPER GEAR 140 SAE 85W140; API GL-5 4LIT','4 LIT',11,'',266500,320000,425000,NULL,'active',NULL),(58,'4L SL','JAPAN SUPER PCMO 20W50 SL 4 LIT','4 LIT',11,'',275000,345000,425000,NULL,'active',NULL);
/*!40000 ALTER TABLE `hanghoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
  `hoadondautien` bit(1) DEFAULT NULL,
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
  CONSTRAINT `fk_hoadon_khachhang1` FOREIGN KEY (`idkhachhang`) REFERENCES `khachhang` (`id`),
  CONSTRAINT `fk_hoadon_nhanvien1` FOREIGN KEY (`idnhanvienban`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `fk_hoadon_nhanvien2` FOREIGN KEY (`idnhanvienlaphoadon`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `fk_hoadon_nhanvien3` FOREIGN KEY (`idnhanvienchamsoc`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `fk_hoadon_nhanvien4` FOREIGN KEY (`idnhanviengiaohang`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (5,16,26,22,20,'HD2018517398438','tienmat','2018-05-08 00:00:00','2018-05-08 00:00:00','2018-05-12 00:00:00',10995000,10995000,0,87,'110 Y MOON','097-740-9476',NULL,'dagiaohang',''),(6,22,26,22,16,'HD2018518733813','tienmat','2018-05-12 00:00:00','2018-05-12 00:00:00','2018-05-12 00:00:00',12095000,12095000,0,86,'PHẠM NGŨ LÃO','091-465-7877',NULL,'dagiaohang','khách hẹn gối đầu toa sau, khoảng 1 tháng rưỡi'),(7,16,26,22,20,'HD20185181541549','tienmat','2018-05-07 00:00:00','2018-05-07 00:00:00','2018-05-18 00:00:00',21415000,21415000,0,88,'km38','094-278-8299',NULL,'dagiaohang','khách hẹn chuyển khoảng 1 nửa trong vòng hai tuần'),(8,16,26,16,16,'HD20185183410494','tienmat','2018-05-03 00:00:00','2018-05-03 00:00:00','2018-05-18 00:00:00',2180000,2180000,0,89,'KM3','091-768-7975',NULL,'dagiaohang','HẸN 1 THÁNG SAU THANH TOÁN'),(9,22,26,22,20,'HD20185182538101','tienmat','2018-05-18 00:00:00','2018-05-18 00:00:00','2018-05-18 00:00:00',4458000,3958000,500000,90,'273 HÙNG VƯƠNG','091-465-7877',NULL,'dagiaohang','THANH TOÁN SAU 1 THÁNG'),(11,16,22,22,16,'HD2018519364943','tienmat','2018-05-14 00:00:00','2018-05-14 00:00:00','2018-05-19 00:00:00',1920000,1920000,0,92,'NGUYỄN KHUYẾN','093-256-4155',NULL,'dagiaohang','HẸN CUỐI TUẦN'),(12,16,22,22,22,'HD20185195046457','tienmat','2018-05-16 00:00:00','2018-05-16 00:00:00','2018-05-19 00:00:00',2195000,2195000,0,94,'NGUYỄN TRI PHƯƠNG','098-915-6456',NULL,'dagiaohang',''),(15,20,26,20,20,'HD20185193224178','tienmat','2018-05-03 00:00:00','2018-05-03 00:00:00','2018-05-19 00:00:00',4308000,4308000,0,96,'ĐAK NÔNG','093-456-2785',NULL,'dagiaohang','CÒN NỢ TOA CŨ'),(16,16,26,22,16,'HD20185193750742','tienmat','2018-05-11 00:00:00','2018-05-11 00:00:00','2018-05-19 00:00:00',2070000,2070000,0,97,'HÙNG VƯƠNG','096-589-1235',NULL,'dagiaohang',''),(17,16,26,22,15,'HD20185193422489','tienmat','2018-05-05 00:00:00','2018-05-05 00:00:00','2018-05-05 00:00:00',1920000,1920000,0,98,'426 PHAN BỘI CHÂU','098-298-7779',NULL,'dagiaohang',''),(18,16,26,22,16,'HD20185194427371','tienmat','2018-05-09 00:00:00','2018-05-09 00:00:00','2018-05-19 00:00:00',895000,895000,0,99,'NGUYỄN LƯƠNG BẰNG','098-282-5058',NULL,'dagiaohang',''),(20,16,26,22,16,'HD20185195331369','tienmat','2018-05-12 00:00:00','2018-05-12 00:00:00','2018-06-11 00:00:00',1145000,0,1145000,100,'THỦ KHOA HUÂN','090-565-4895','\0','dagiaohang',''),(21,16,26,22,16,'HD20185195716524','tienmat','2018-05-02 00:00:00','2018-05-02 00:00:00','2018-06-11 00:00:00',1145000,0,1145000,100,'THỦ KHOA HUÂN','012-345-6789',NULL,'dagiaohang',''),(23,16,26,15,16,'HD20185205037107','tienmat','2018-05-20 00:00:00','2018-05-20 00:00:00','2018-06-11 00:00:00',3200000,0,3200000,102,'KHO VIỆT HÂN','111-111-1111','\0','dagiaohang','KHÁCH CỦA ANH MẠNH THAY NHỚT TAI KHO'),(25,16,26,22,22,'HD2018520590222','tienmat','2018-05-15 00:00:00','2018-05-15 00:00:00','2018-06-11 00:00:00',1320000,0,1320000,104,'PHẠM NGŨ LÃO','096-532-4566','','dagiaohang',''),(26,22,26,22,21,'HD20185212828210','tienmat','2018-05-21 00:00:00','2018-05-21 00:00:00','2018-05-21 00:00:00',5725000,2000000,3725000,105,'KM40','096-589-7456',NULL,'dagiaohang','HẸN TRONG TUẦN'),(38,22,26,22,16,'HD20186121559128','tienmat','2018-05-14 00:00:00','2018-05-14 00:00:00','2018-05-14 00:00:00',9320000,0,9320000,91,'Y NGÔNG','016-431-62968','','dagiaohang',''),(39,16,26,22,25,'HD2018612235717','tienmat','2018-05-16 00:00:00','2018-05-16 00:00:00','2018-06-12 00:00:00',2685000,0,2685000,95,'PHƯỚC AN','090-299-9777','\0','dagiaohang',''),(40,16,26,22,25,'HD20186121853784','tienmat','2018-05-17 00:00:00','2018-05-17 00:00:00','2018-06-12 00:00:00',9320000,0,9320000,95,'PHƯỚC AN','090-299-9777',NULL,'dagiaohang',''),(41,16,26,22,22,'HD2018612340420','tienmat','2018-05-17 00:00:00','2018-05-17 00:00:00','2018-06-12 00:00:00',1920000,1920000,0,108,'Y MOON','015-689-74656',NULL,'dagiaohang',''),(42,22,26,22,22,'HD20186124230366','tienmat','2018-05-21 00:00:00','2018-05-21 00:00:00','2018-06-12 00:00:00',5725000,2000000,3725000,109,'BMT','012-658-94522',NULL,'dagiaohang',''),(43,22,26,22,22,'HD20186125323805','tienmat','2018-05-21 00:00:00','2018-05-21 00:00:00','2018-06-12 00:00:00',895000,895000,0,110,'BMT','090-569-8423',NULL,'dagiaohang',''),(44,22,26,22,22,'HD20186125634885','tienmat','2018-05-21 00:00:00','2018-05-21 00:00:00','2018-06-12 00:00:00',2290000,2290000,0,107,'MADRAK','096-548-5215',NULL,'dagiaohang',''),(45,16,26,22,16,'HD2018612293','tienmat','2018-05-22 00:00:00','2018-05-22 00:00:00','2018-05-22 00:00:00',1050000,1050000,0,111,'CƯMGAR','098-756-2315',NULL,'dagiaohang',''),(46,22,26,22,22,'HD20186121910316','tienmat','2018-05-24 00:00:00','2018-05-24 00:00:00','2018-06-12 00:00:00',1790000,1790000,0,110,'BMT','090-569-8423',NULL,'dagiaohang',''),(47,22,26,22,22,'HD20186122110984','tienmat','2018-05-09 00:00:00','2018-05-09 00:00:00','2018-06-12 00:00:00',11610000,11610000,0,110,'BMT','090-569-8423',NULL,'dagiaohang',''),(49,22,26,22,21,'HD20186143341695','tienmat','2018-05-29 00:00:00','2018-06-14 00:00:00','2018-06-14 00:00:00',895000,895000,0,110,'BMT','090-569-8423',NULL,'dagiaohang',''),(50,16,26,22,16,'HD20186143810958','tienmat','2018-05-30 00:00:00','2018-06-14 00:00:00','2018-06-14 00:00:00',6120000,6120000,0,114,'EASUP','094-443-4210',NULL,'dagiaohang',''),(51,16,26,22,16,'HD20186144255218','tienmat','2018-05-30 00:00:00','2018-06-14 00:00:00','2018-06-14 00:00:00',4308000,4308000,0,115,'EASUP','090-562-4389',NULL,'dagiaohang',''),(52,22,26,22,22,'HD20186144642501','tienmat','2018-05-30 00:00:00','2018-05-30 00:00:00','2018-05-30 00:00:00',1448000,0,1448000,116,'BMT','090-564-8952','','dagiaohang',''),(53,16,26,22,16,'HD20186145736919','tienmat','2018-05-30 00:00:00','2018-05-30 00:00:00','2018-06-14 00:00:00',895000,895000,0,117,'BMT','098-246-5855',NULL,'dagiaohang',''),(54,16,26,22,16,'HD20186153917842','tienmat','2018-05-03 00:00:00','2018-05-03 00:00:00','2018-06-15 00:00:00',1423000,0,1423000,100,'THỦ KHOA HUÂN','095-876-5899',NULL,'dagiaohang',''),(55,17,26,17,17,'HD2018615742321','tienmat','2018-05-15 00:00:00','2018-05-15 00:00:00','2018-06-15 00:00:00',4308000,0,4308000,103,'LÊ THÁNH TÔNG','096-587-5662','\0','dagiaohang',''),(57,22,26,22,22,'HD20186152635759','tienmat','2018-05-05 00:00:00','2018-05-05 00:00:00','2018-05-05 00:00:00',1060000,0,1060000,86,'VÀNH ĐAI','090-278-9562','\0','dagiaohang',''),(58,16,26,22,23,'HD20186154114225','tienmat','2018-05-19 00:00:00','2018-05-19 00:00:00','2018-05-19 00:00:00',12095000,0,12095000,95,'PHƯỚC AN','090-299-9777',NULL,'dagiaohang',''),(59,16,26,15,15,'HD20186155310487','tienmat','2018-05-22 00:00:00','2018-05-22 00:00:00','2018-05-22 00:00:00',640000,0,640000,102,'BMT','095-628-7446','\0','dagiaohang',''),(60,16,26,22,16,'HD20186155819500','tienmat','2018-05-24 00:00:00','2018-05-24 00:00:00','2018-05-24 00:00:00',1145000,0,1145000,100,'THỦ KHOA HUÂN','095-876-5899',NULL,'dagiaohang',''),(61,26,26,26,26,'HD2018615256888','tienmat','2018-05-24 00:00:00','2018-05-24 00:00:00','2018-05-24 00:00:00',1145000,0,1145000,120,'BMT','096-584-8996','','dagiaohang',''),(62,16,26,15,16,'HD201861554673','tienmat','2018-05-24 00:00:00','2018-05-24 00:00:00','2018-05-24 00:00:00',400000,0,400000,102,'BMT','090-255-4865',NULL,'dagiaohang',''),(63,22,26,22,22,'HD201861584930','tienmat','2018-05-24 00:00:00','2018-05-24 00:00:00','2018-06-15 00:00:00',1790000,0,1790000,110,'BMT','090-569-8423','','dagiaohang',''),(64,22,26,22,20,'HD20186151020487','tienmat','2018-05-24 00:00:00','2018-05-24 00:00:00','2018-06-15 00:00:00',1920000,1920000,0,121,'BMT','090-589-5462',NULL,'dagiaohang',''),(65,16,26,15,16,'HD20186151342428','tienmat','2018-05-26 00:00:00','2018-05-26 00:00:00','2018-05-26 00:00:00',398000,0,398000,102,'BMT','090-564-2312',NULL,'dagiaohang',''),(66,16,26,22,16,'HD20186151630573','tienmat','2018-05-28 00:00:00','2018-05-28 00:00:00','2018-05-28 00:00:00',895000,0,895000,99,'NGUYỄN LƯƠNG BẰNG','098-282-5058','','dagiaohang',''),(67,16,26,22,16,'HD20186152018182','tienmat','2018-05-28 00:00:00','2018-05-28 00:00:00','2018-05-28 00:00:00',2290000,0,2290000,123,'BMT','098-654-2358','','dagiaohang',''),(68,22,26,22,22,'HD20186152458537','tienmat','2018-05-29 00:00:00','2018-05-29 00:00:00','2018-06-15 00:00:00',12756000,12756000,0,124,'EAKAR','094-562-3855',NULL,'dagiaohang',''),(69,22,26,22,16,'HD201861724380','tienmat','2018-06-02 00:00:00','2018-06-02 00:00:00','2018-06-17 00:00:00',9320000,9320000,0,110,'BMT','090-569-8423',NULL,'dagiaohang',''),(70,20,26,20,20,'HD20186173938373','tienmat','2018-06-03 00:00:00','2018-06-03 00:00:00','2018-06-03 00:00:00',400000,0,400000,126,'BMT','090-562-4855','','dagiaohang',''),(71,20,26,20,20,'HD20186174436858','tienmat','2018-06-07 00:00:00','2018-06-07 00:00:00','2018-06-07 00:00:00',1740000,0,1740000,127,'BMT','090-564-2855','','dagiaohang',''),(72,16,26,16,16,'HD2018617477389','tienmat','2018-06-09 00:00:00','2018-06-09 00:00:00','2018-06-09 00:00:00',400000,0,400000,102,'BMT','098-756-2214',NULL,'dagiaohang',''),(73,16,26,22,16,'HD20186175527167','tienmat','2018-06-10 00:00:00','2018-06-10 00:00:00','2018-06-10 00:00:00',2290000,0,2290000,128,'NGUYỄN THỊ ĐỊNH','090-623-4562','','dagiaohang',''),(74,26,26,26,26,'HD201861706604','tienmat','2018-06-12 00:00:00','2018-06-12 00:00:00','2018-06-12 00:00:00',1108000,0,1108000,130,'GIA LAI','020-202-0202','','dagiaohang',''),(75,16,26,22,16,'HD2018617838677','tienmat','2018-06-11 00:00:00','2018-06-11 00:00:00','2018-06-11 00:00:00',1145000,0,1145000,100,'THỦ KHOA HUÂN','095-876-5899','\0','dagiaohang',''),(76,16,26,16,16,'HD20186171035462','tienmat','2018-06-10 00:00:00','2018-06-10 00:00:00','2018-06-10 00:00:00',400000,0,400000,102,'BMT','095-462-1225',NULL,'dagiaohang',''),(77,22,26,22,22,'HD20186171231111','tienmat','2018-06-13 00:00:00','2018-06-13 00:00:00','2018-06-17 00:00:00',1920000,920000,1000000,90,'237 HÙNG VƯƠNG','091-294-2771',NULL,'dagiaohang',''),(78,26,26,26,26,'HD20186171349550','tienmat','2018-06-13 00:00:00','2018-06-13 00:00:00','2018-06-13 00:00:00',1295000,0,1295000,120,'BMT','096-584-8996','\0','dagiaohang',''),(79,22,26,22,16,'HD20186171819838','tienmat','2018-06-01 00:00:00','2018-06-01 00:00:00','2018-06-01 00:00:00',15816000,6206000,9610000,131,'EAKAR','098-745-2588',NULL,'dagiaohang',''),(80,16,26,22,16,'HD20186172247568','tienmat','2018-06-09 00:00:00','2018-06-09 00:00:00','2018-06-09 00:00:00',2290000,0,2290000,115,'EASUP','090-562-4389','','dagiaohang',''),(81,22,26,22,16,'HD2018617364119','tienmat','2018-06-03 00:00:00','2018-06-03 00:00:00','2018-06-03 00:00:00',3738000,2000000,1738000,132,'KRONGPAK','095-865-2222',NULL,'dagiaohang',''),(82,22,26,22,21,'HD20186174051698','tienmat','2018-06-06 00:00:00','2018-06-06 00:00:00','2018-06-06 00:00:00',2685000,2685000,0,133,'BMT','095-264-8555',NULL,'dagiaohang',''),(83,16,26,22,16,'HD20186174354273','tienmat','2018-06-04 00:00:00','2018-06-04 00:00:00','2018-06-04 00:00:00',2560000,0,2560000,134,'HÀ LAN','098-756-2455','','dagiaohang',''),(84,22,26,22,16,'HD20186174719378','tienmat','2018-06-07 00:00:00','2018-06-07 00:00:00','2018-06-07 00:00:00',2195000,0,2195000,86,'VÀNH ĐAI','098-624-8858','\0','dagiaohang',''),(85,20,26,20,20,'HD20186175412886','tienmat','2018-06-08 00:00:00','2018-06-08 00:00:00','2018-06-17 00:00:00',4308000,4308000,0,96,'ĐAK NÔNG','093-456-2478',NULL,'dagiaohang',''),(86,22,26,22,22,'HD2018617430968','tienmat','2018-06-12 00:00:00','2018-06-12 00:00:00','2018-06-12 00:00:00',1145000,0,1145000,86,'VÀNH ĐAI','092-458-8856',NULL,'dagiaohang',''),(87,22,26,22,22,'HD20186171211550','tienmat','2018-06-13 00:00:00','2018-06-13 00:00:00','2018-06-13 00:00:00',1920000,0,1920000,121,'BMT','090-589-5462','','dagiaohang',''),(88,22,26,22,21,'HD2018617142861','tienmat','2018-06-13 00:00:00','2018-06-13 00:00:00','2018-06-13 00:00:00',1920000,920000,1000000,90,'237 HÙNG VƯƠNG','091-294-2771',NULL,'dagiaohang',''),(90,22,26,22,22,'HD20186201614611','tienmat','2018-06-20 00:00:00','2018-06-20 00:00:00','2018-06-20 00:00:00',3450000,0,3450000,98,'PHAN BỘI CHÂU','098-298-7779','','deleted','');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
  `lanmuahang` int(11) DEFAULT NULL,
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
  CONSTRAINT `fk_khachhang_loaikhachhang` FOREIGN KEY (`maloaikhachhang`) REFERENCES `loaikhachhang` (`id`),
  CONSTRAINT `fk_khachhang_nhomkhachhang1` FOREIGN KEY (`nhomkhachhang_id`) REFERENCES `nhomkhachhang` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (1,'NAM HOÀNG','GARA NAM HOÀNG',1,NULL,'PHAN BỘI CHÂU',NULL,'ANH THỊNH',NULL,NULL,'ANH THỊNH',NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,3,0,'chosinhnhat',0,0,'khong',NULL,9),(3,'ANH BẢO','GARA ANH BẢO',2,NULL,'NGUYỄN THỊ ĐỊNH','097-744-0437','ANH BẢO',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(4,'ANH NHÓC','GARA ANH NHÓC',3,NULL,'VÀNH ĐAI',NULL,'ANH NHÓC',NULL,NULL,'ANH NHÓC',NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(5,'ANH MINH','GARA MINH',4,NULL,'THỦ KHOA HUÂN',NULL,'ANH MINH',NULL,NULL,'ANH MINH',NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(6,'ANH NAM','GARA HOÀNG NAM',5,NULL,'Y MOON',NULL,'ANH NAM',NULL,NULL,'ANH NAM',NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(7,'ANH SÂM','GARA AN TÂM',6,NULL,'Y MOON',NULL,'ANH SÂM',NULL,NULL,'ANH SÂM',NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(8,'ANH KIÊN ','GARA TRUNG KIÊN',7,NULL,'Y MOON',NULL,'ANH KIÊN',NULL,NULL,'ANH KIÊN',NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(9,'ANH SƠN','GARA TÝ PHÁT',8,NULL,'Y MOON',NULL,'ANH SƠN',NULL,NULL,'ANH SƠN',NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(10,'ANH VŨ','GARA TRẦN VŨ',9,NULL,'LÊ THÁNH TÔNG',NULL,'ANH VŨ',NULL,NULL,'ANH VŨ',NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(12,'ANH KỲ','GARA ANH KỲ',9,NULL,NULL,NULL,'ANH KỲ',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,2,0,'chosinhnhat',0,0,'khong',NULL,9),(13,'ANH PHỤNG','GARA ANH PHỤNG',10,NULL,NULL,NULL,'ANH PHỤNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,2,0,'chosinhnhat',0,0,'khong',NULL,9),(14,'ANH THỤ','GARA THỤ',11,NULL,'AMASA',NULL,'ANH THỤ',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(15,'ANH THÔNG','GARA XANH',12,NULL,NULL,NULL,'ANH THÔNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(16,'ANH HUYNH','GARA HUYNH',13,NULL,'HÙNG VƯƠNG',NULL,'ANH HUYNH',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(17,'ANH HẢO','GARA HẢO',13,NULL,'HÙNG VƯƠNG',NULL,'ANH HẢO',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(18,'ANH QUANG','GARA PHÚ QUÝ',15,NULL,'HÙNG VƯƠNG',NULL,'ANH QUANG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(20,'ANH CƯỜNG','GARA CƯỜNG KHÁNH',17,NULL,'NGUYỄN VĂN CỪ',NULL,'ANH CƯỜNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(21,'ANH NHƯỜNG','GARA NHƯỜNG',18,NULL,'LÊ VỤ',NULL,'ANH NHƯỜNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(22,'ANH KIÊN 2','GARA TRUNG KIÊN',19,NULL,'CƯ JUT',NULL,'ANH KIÊN 2',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(23,'ANH BỬU','GARA BỬU',20,NULL,'KM 42 KRONG PAK',NULL,'ANH BỬU',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(24,'ANH ĐÔNG','GARA ĐÔNG',21,NULL,'MADRAK',NULL,'ANH ĐÔNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(25,'ANH TẤN DŨNG ','GARA TẤN DŨNG',22,NULL,'NGUYỄN VĂN CỪ',NULL,'ANH TẤN DŨNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(26,'PHƯỚC TOÀN','GARA PHƯỚC TOÀN',23,NULL,'ĐINH TIÊN HOÀNG',NULL,'PHƯỚC TOÀN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(27,'NHẤT NGUYÊN','GARA NHẤT NGUYÊN',24,NULL,'156 CHU VĂN AN',NULL,'CHỊ VÂN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(28,'ANH PHÚC','GARA PHÚC',25,NULL,'HÀ HUY TẬP',NULL,'ANH PHÚC',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(29,'ANH LẬP','GARA LẬP',26,NULL,'VÀNH ĐAI',NULL,'ANH LẬP',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(30,'THẢO LAN',' NHÀ XE THẢO LAN',26,NULL,'HÀ LAN',NULL,'ANH CHINH',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(31,'ANH THẢO',' NHÀ XE ANH THẢO',27,NULL,'BUÔN HỒ',NULL,'ANH THẢO',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(32,'ANH TOÀN','NHÀ XE ANH THƯ',28,NULL,'HÀ LAN',NULL,'ANH TOÀN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(33,'ANH QUỐC','GARA ANH QUỐC',28,NULL,'MAI XUÂN THƯỞNG',NULL,'ANH QUỐC',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(34,'ANH HIẾU','GARA HIẾU',28,NULL,'130 YMOAN',NULL,'ANH HIẾU',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(35,'ANH LƯỢNG','GARA LƯỢNG',30,NULL,'23 NGUYỄN KHUYẾN',NULL,'ANH TÁM',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(36,'NGỌC HOÀNG','GARA NGỌC HOÀNG',31,NULL,'39 NGUYỄN THỊ ĐỊNH',NULL,'NGỌC HOÀNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(37,'BẢO KHANH','GARA BẢO KHANH',32,NULL,'KM 42',NULL,'BẢO KHANH',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(38,'ANH MẠNH','VLXD MẠNH HOÀI',33,NULL,'KM 38',NULL,'ANH MẠNH',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(39,'ANH TUẤN','ANH TUẤN',34,NULL,'KM 39 JA',NULL,'ANH TUẤN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(40,'ANH KIỆN * LONG PHÁT*','TÔN LONG PHÁT',35,NULL,'BUÔN HỒ',NULL,'ANH KIỆN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(41,'CHÚ THUẬN','CÔNG TY NAM THUẬN',36,NULL,'TÂN AN',NULL,'CHÚ THUẬN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(43,'ANH ĐƯỜNG','ANH ĐƯỜNG',38,NULL,'BUÔN KA NA-CƯMGAR',NULL,'ANH ĐƯỜNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(45,'CHÚ BÌNH','SƯ ĐOÀN 470-28',39,NULL,'NGUYỄN LƯƠNG BẰNG',NULL,'CHÚ BÌNH',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(47,'ANH TIẾN','ANH TIẾN TÀI XẾ',41,NULL,'MAI HẮC ĐÉ',NULL,'ANH TIẾN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(48,'ANH NGỌC','GARA ANH NGỌC',42,NULL,'NGUYỄN CHÍ THANH',NULL,'ANH NGỌC',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(49,'ANH HÀ','ANH HÀ',42,NULL,'20 HÀM NGHI',NULL,'ANH HÀ',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(50,'ANH LONG','ANH LONG',44,NULL,'61 ĐINH TIÊN HOÀNG',NULL,'ANH LONG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(51,'ANH MẪN','ANH MẪN',45,NULL,'48 TÂN TIẾN EANA',NULL,'ANH MẪN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(52,'ANH TRỌNG','TAXI ANH TRỌNG',46,NULL,'BMT',NULL,'ANH TRỌNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(53,'ANH THY','TAXI ANH THY',47,NULL,'BMT',NULL,'ANH THY',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(54,'ANH LIÊM','ĐẠI LÝ SỸ LIÊM',48,NULL,'KM52',NULL,'ANH LIÊM',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(55,'ANH THÁI','NHÀ XE HOÀNG THÁI',49,NULL,'KM62',NULL,'ANH THÁI',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(56,'DN TOÀN PHÁT','DN TOÀN PHÁT',50,NULL,'GIA NGHĨA',NULL,'ANH TOÀN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(57,'HOÀNG LÂN','HOÀNG LÂN ',51,NULL,'NAMDJANG/ ĐAK NÔNG',NULL,'HOÀNG LÂN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(58,'HOÀNG QUYẾN','CTY HOÀNG QUYẾN',NULL,NULL,'PHẠM NGŨ LÃO',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,3,0,'chosinhnhat',0,0,'co',NULL,9),(59,'ANH ÂU','TAXI ÂU',NULL,NULL,'BMT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(60,'ANH TRUNG','TAXI TRUNG',NULL,NULL,'BMT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(61,'ANH VŨ 2','ANH VŨ 2',NULL,NULL,'TRƯƠNG CÔNG ĐỊNH',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(62,'ANH HUY','TAXI HUY',NULL,NULL,'BMT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(63,'ANH BAN','CAFE MỘC PHỦ',NULL,NULL,'VẠN XUÂN',NULL,'ANH BAN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(64,'ANH HẢI','TAXI ANH HẢI',NULL,NULL,'BMT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(65,'ANH DŨNG','ĐẠI LÝ DŨNG HÀ',NULL,NULL,'EASUP',NULL,'ANH DŨNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(66,'ANH KIÊN EASUP','GARA ANH KIÊN',NULL,NULL,'EASUP',NULL,'ANH KIÊN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(67,'ANH QUY','ANH QUY',NULL,NULL,'CƯMLAN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(68,'CÔ HƯỜNG','CÔ HƯỜNG',NULL,NULL,'EASUP',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(69,'ANH THIỆN',' TÀI XẾ ANH THIỆN ',NULL,NULL,'VÀNH ĐAI',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(70,'LIỄU SANH','ĐẠI LÍ LIỄU SANH',NULL,NULL,'MADRAK',NULL,'LIỄU SANH',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(71,'HỒNG HẢI','HỒNG HẢI',NULL,NULL,'CƯMGAR',NULL,NULL,NULL,NULL,NULL,NULL,NULL,42,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(72,'ANH BÔN','ANH BÔN',NULL,NULL,'ĐINH CÔNG TRÁNG',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(73,'ANH VÕ','ANH VÕ',NULL,NULL,'VẠN XUÂN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(74,'ANH DŨNG ĐINH TIÊN HOÀNG','ANH DŨNG',NULL,NULL,'ĐINH TIÊN HOÀNG',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(75,'ANH TÂN ','ANH TÂN',NULL,NULL,'TRUNG HÒA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(76,'NĂM YẾN','NĂM YẾN',NULL,NULL,'EAROK',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(77,'CHÚ TRƯỜNG','CHÚ TRƯỜNG',NULL,NULL,'LÊ VỤ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(78,'VIỆT NHẬT','CX VIỆT NHẬT',NULL,NULL,'CƯKUIN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(79,'ANH MẪN HÒA TRUNG','ANH MẪN',NULL,NULL,'HÒA TRUNG',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(80,'RỬA XE 07','RỬA XE 07',NULL,NULL,'HOÀNG VĂN THỤ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(81,'HÙNG PHÁT','HÙNG PHÁT',NULL,NULL,'KM38 ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(82,'ANH THANH','ANH THANH',NULL,NULL,'EAKAR',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(83,'ĐẠI LÍ THANH','ANH THANH',NULL,NULL,'EAKAR',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(84,'ANH CHÂU','GARA ANH CHÂU',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,42,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(85,'CHÚ PHONG','CHÚ PHONG NHẬT QUANG',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,42,NULL,NULL,NULL,'deleted',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(86,'GR 0001','GARA TUẤN VÀNH ĐAI',NULL,NULL,'VÀNH ĐAI',NULL,'ANH TUẤN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',1365500,0,161,0,0,'chosinhnhat',0,4,'co',NULL,9),(87,'GR 0002','GARA NAM HOÀNG',NULL,NULL,'Y MOON',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',1099500,0,108,0,0,'chosinhnhat',0,1,'co',NULL,14),(88,'CN 0001','MẠNH HOÀI',NULL,NULL,'KM38','094-278-8299',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',2141500,0,214,0,0,'chosinhnhat',0,1,'co',NULL,9),(89,'GR 0003','ANH THANH',NULL,NULL,'KM3','091-768-7975',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',218000,0,21,0,0,'chosinhnhat',0,1,'co',NULL,9),(90,'GR 0004','ANH CHÂU',NULL,NULL,'237 HÙNG VƯƠNG','091-294-2771',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',404400,0,43,0,2,'chosinhnhat',0,1,'co',NULL,14),(91,'CN0002','ANH TUYỂN',NULL,NULL,'Y NGÔNG','016-431-62968',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',796700,0,92,0,0,'chosinhnhat',0,1,'co',NULL,12),(92,'GR 0005','ANH LƯỢNG',NULL,NULL,'NGUYỄN KHUYẾN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',192000,0,19,0,0,'chosinhnhat',0,1,'co',NULL,9),(94,'GR 0006','ANH QUÃNG',NULL,NULL,'NGUYỄN TRI PHƯƠNG',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',219500,0,21,0,0,'chosinhnhat',0,1,'co',NULL,9),(95,'CTY 0001','HOÀNG SA',NULL,NULL,'PHƯỚC AN','090-299-9777',NULL,NULL,NULL,NULL,NULL,NULL,42,NULL,NULL,NULL,'active',1382000,0,239,0,0,'chosinhnhat',0,3,'co',NULL,14),(96,'GR 0007','TOÀN PHÁT ĐAK NÔNG',NULL,NULL,'ĐAK NÔNG','093-456-2478',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',430800,0,43,0,0,'chosinhnhat',0,1,'co',NULL,9),(97,'GR 0008','ANH XANH',NULL,NULL,'HÙNG VƯƠNG','098-756-2356',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',207000,0,20,0,0,'chosinhnhat',0,1,'co',NULL,9),(98,'GR 0009','ANH THỊNH/ HOÀNG NAM',NULL,NULL,'PHAN BỘI CHÂU','098-298-7779',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',122000,0,-15,0,0,'chosinhnhat',0,0,'co',NULL,9),(99,'CN 0003','BÁC BÌNH/ 470',NULL,NULL,'NGUYỄN LƯƠNG BẰNG','098-282-5058',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',102500,0,16,0,0,'chosinhnhat',0,2,'co',NULL,14),(100,'GR 00010','ANH MINH THỦ KHOA HUÂN',NULL,NULL,'THỦ KHOA HUÂN','095-876-5899',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',422964,0,57,0,0,'chosinhnhat',0,5,'co',NULL,14),(101,'CN 0002','ANH MINH/ VÀNH ĐAI',NULL,NULL,'VÀNH ĐAI',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',55000,0,0,0,0,'chosinhnhat',0,0,'co',NULL,14),(102,'KL 0001','KHÁCH LẺ MẠNH',NULL,NULL,'BMT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',372050,0,47,0,0,'chosinhnhat',0,6,'co',NULL,14),(103,'GR 00011','TRẦN VŨ',NULL,NULL,'LÊ THÁNH TÔNG',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',72000,0,43,0,0,'chosinhnhat',0,1,'co',NULL,14),(104,'CTY 0002','HOÀNG QUYẾN',NULL,NULL,'PJAMJ NGŨ LÃO',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',132000,0,13,0,0,'chosinhnhat',0,1,'co',NULL,14),(105,'GR 00012','ANH TÂM / KM40',NULL,NULL,'KM40',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',572500,0,57,0,0,'chosinhnhat',0,1,'co',NULL,14),(106,'CN 0004','CHÚ PHONG NHẬT QUANG',NULL,NULL,'CƯBUR',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,14),(107,'CN 0005','LIỄU SANH',NULL,NULL,'MADRAK',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',1832160,500000,-225,2,0,'chosinhnhat',0,0,'co',NULL,14),(108,'GR 00014','GARA ANH HIẾU',NULL,NULL,'Y MOON','--',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,'ĐAK LAK','active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,14),(109,'GR 00015','GARA ANH TÂM',NULL,NULL,'BMT','--',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,'ĐAK LAK','active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,14),(110,'CTY 0003','CHÚ PHONG NHẬT QUANG',NULL,NULL,'BMT','090-569-8423',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,'ĐAK LAK','active',26000,0,17,0,0,'chosinhnhat',0,1,'co',NULL,14),(111,'CN 0006','HOÀNG HẢI',NULL,NULL,'CƯMGAR','098-756-2315',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,14),(113,'CN 0009','ANH NGỌC THỌ',NULL,NULL,'BMT','090-568-9752',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,'ĐAK LAK','active',0,0,0,0,2,'chosinhnhat',0,0,'co',NULL,10),(114,'GR 00020','GARA ANH KIÊN EASUP',NULL,NULL,'EASUP','094-443-4210',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,14),(115,'GR 00021','ANH 5 YẾN',NULL,NULL,'EASUP','090-562-4389',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',34000,0,22,0,0,'chosinhnhat',0,1,'co',NULL,14),(116,'CN 0007','ANH VINH',NULL,NULL,'BMT','090-564-8952',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',22650,0,14,0,0,'chosinhnhat',0,1,'co',NULL,14),(117,'GR 00027','GARA ANH BẢO',NULL,NULL,'BMT','098-246-5855',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,14),(118,'GR 00025','GARA PHONG ',NULL,NULL,'KRONG PAK','090-562-8956',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,14),(120,'CN 0010','ANH TIẾN TÀI XẾ',NULL,NULL,'BMT','096-584-8996',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',36800,0,23,0,0,'chosinhnhat',0,2,'co',NULL,14),(121,'GR 00028','GARA ANH KỲ',NULL,NULL,'BMT','090-589-5462',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',32100,0,19,0,0,'chosinhnhat',0,1,'co',NULL,14),(122,'CN 00011','HÒA BÌNH MINH',NULL,NULL,'BMT','095-645-6223',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,14),(123,'GR 00026','TẤN DŨNG',NULL,NULL,'BMT','098-654-2358',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',34000,0,22,0,0,'chosinhnhat',0,1,'co',NULL,14),(124,'ĐL 0001','ANH THANH EAKAR',NULL,NULL,'EAKAR','094-562-3855',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,14),(126,'CN 0011','KHÁCH LẺ LỘC',NULL,NULL,'BMT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',13350,0,4,0,0,'chosinhnhat',0,1,'co',NULL,14),(127,'CN0012','ANH THIỆN TÀI XẾ BA LƠN',NULL,NULL,'BMT','090-564-2855',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',26600,0,17,0,0,'chosinhnhat',0,1,'co',NULL,14),(128,'GR 00030','GARA HƯƠNG ',NULL,NULL,'NGUYỄN THỊ ĐỊNH','090-623-4562',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',34000,0,22,0,0,'chosinhnhat',0,1,'co',NULL,14),(130,'CN 00012','ANH THIỆN KHÁCH SẾP TÂM',NULL,NULL,'GIA LAI','020-202-0202',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',25520,0,11,0,0,'chosinhnhat',0,1,'co',NULL,14),(131,'CN 00013','HOÀI GIANG EAKAR',NULL,NULL,'EAKAR','098-745-2588',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,14),(132,'GR 00036','GARA PHONG KRONGPAK',NULL,NULL,'KRONGPAK','095-865-2222',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,14),(133,'GR 00034','GARA ANH NGỌC NCT',NULL,NULL,'BMT','095-264-8555',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,14),(134,'CN 00014','ANH KIỆN HÀ LAN',NULL,NULL,'HÀ LAN','098-756-2455',NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',38300,0,25,0,0,'chosinhnhat',0,1,'co',NULL,14),(135,'RX 0001','RỬA XE HOÀNG VINH',NULL,NULL,'HÀ HUY TẬP',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,14);
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kpi`
--

DROP TABLE IF EXISTS `kpi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `kpi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ten` varchar(45) DEFAULT NULL,
  `kieukpi` varchar(50) DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `mota` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kpi`
--

LOCK TABLES `kpi` WRITE;
/*!40000 ALTER TABLE `kpi` DISABLE KEYS */;
INSERT INTO `kpi` VALUES (1,'Doanh Thu','tien','active','Số tiền nhân viên kinh doanh bán hàng đăng ký'),(2,'Số Khách Hàng Mới','so','active','Khách hàng mua lần đầu tiên'),(3,'Số Khách Hàng Tái Lập','so','active','Khách hàng mua lại từ lần thứ 2'),(4,'Chăm Sóc','so','active','');
/*!40000 ALTER TABLE `kpi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaikhachhang`
--

DROP TABLE IF EXISTS `loaikhachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `loaikhachhang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenloai` varchar(45) DEFAULT '',
  `trangthai` varchar(45) DEFAULT NULL,
  `mota` varchar(95) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaikhachhang`
--

LOCK TABLES `loaikhachhang` WRITE;
/*!40000 ALTER TABLE `loaikhachhang` DISABLE KEYS */;
INSERT INTO `loaikhachhang` VALUES (41,'Cá nhân','active',''),(42,'Doanh nghiệp','active','');
/*!40000 ALTER TABLE `loaikhachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `luong`
--

DROP TABLE IF EXISTS `luong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
  CONSTRAINT `fk_nhanvien_luong` FOREIGN KEY (`nhanvien_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `luong`
--

LOCK TABLES `luong` WRITE;
/*!40000 ALTER TABLE `luong` DISABLE KEYS */;
INSERT INTO `luong` VALUES (69,16,0,0,0,'04','2018','chuatraluong',''),(70,15,3000000,0,0,'04','2018','chuatraluong',''),(71,7,3000000,0,0,'04','2018','chuatraluong',''),(72,19,0,0,0,'04','2018','chuatraluong',''),(73,18,0,0,0,'04','2018','chuatraluong',''),(74,17,0,0,0,'04','2018','chuatraluong',''),(75,25,0,0,0,'04','2018','chuatraluong',''),(76,24,0,0,0,'04','2018','chuatraluong',''),(77,23,0,0,0,'04','2018','chuatraluong',''),(78,22,0,0,242690,'04','2018','chuatraluong',''),(79,21,0,0,0,'04','2018','chuatraluong',''),(80,20,0,0,0,'04','2018','chuatraluong',''),(81,25,0,0,0,'05','2018','chuatraluong',''),(82,24,0,0,0,'05','2018','chuatraluong',''),(83,23,0,0,0,'05','2018','chuatraluong',''),(84,22,3500000,0,338250,'05','2018','chuatraluong',''),(85,21,0,0,0,'05','2018','chuatraluong',''),(86,20,0,0,0,'05','2018','chuatraluong',''),(87,19,0,0,0,'05','2018','chuatraluong',''),(88,18,0,0,0,'05','2018','chuatraluong',''),(89,17,0,0,-602000,'05','2018','chuatraluong',''),(90,16,0,0,520400,'05','2018','chuatraluong',''),(91,15,0,0,0,'05','2018','chuatraluong',''),(92,7,0,0,0,'05','2018','chuatraluong',''),(93,26,0,0,0,'05','2018','chuatraluong',''),(94,26,0,0,0,'06','2018','chuatraluong',''),(95,25,0,0,0,'06','2018','chuatraluong',''),(96,24,0,0,0,'06','2018','chuatraluong',''),(97,23,0,0,0,'06','2018','chuatraluong',''),(98,22,3500000,0,-2244400,'06','2018','chuatraluong',''),(99,21,0,0,0,'06','2018','chuatraluong',''),(100,20,0,0,-112175,'06','2018','chuatraluong',''),(101,19,0,0,0,'06','2018','chuatraluong',''),(102,18,0,0,0,'06','2018','chuatraluong',''),(103,17,3500000,0,252000,'06','2018','chuatraluong',''),(104,16,0,0,629160,'06','2018','chuatraluong',''),(105,15,0,0,0,'06','2018','chuatraluong',''),(106,7,0,0,0,'06','2018','chuatraluong','');
/*!40000 ALTER TABLE `luong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
  CONSTRAINT `fk_nhanvien_bophan1` FOREIGN KEY (`bophan_id`) REFERENCES `bophan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (7,'003','DƯƠNG THỊ THẢO','293-838-272','2005-03-29 00:00:00','Buôn Ma Thuột','098-767-2632','Buôn Ma Thuột','2010-03-20 00:00:00',0,10,'active','hienthi','Nhân Viên Quản Lý','',4,0,0),(15,'002','NGUYỄN VĂN LỢI','111-222-333','1999-09-09 00:00:00','Dak Lak','091-465-7877','Buôn Ma Thuột','2017-09-09 00:00:00',0,10,'active','khonghienthi','','',6,0,0),(16,'001','HUỲNH TẤN MẠNH','223-344-556','2015-01-22 00:00:00','Đawk Lăk','091-768-7975','bmt','2017-10-01 00:00:00',0,20,'active','hienthi','','',4,0,0),(17,'004','ĐOÀN THỊ CA NY','444-555-666','2005-02-12 00:00:00','1/2/2007','091-508-7975','BMT','2000-02-01 00:00:00',0,35,'active','hienthi','','',5,0,0),(18,'005','NGUYỄN ĐỨC KHƯƠNG','222-333-444','2000-02-12 00:00:00','ĐAK LAK','091-417-9139','BMT','1999-02-01 00:00:00',0,35,'active','hienthi','','',5,0,0),(19,'006','VÕ ĐÌNH NGHỊ','221-160-228','2016-04-25 00:00:00','PHÚ YÊN','091-876-7975','BMT','2000-01-01 00:00:00',0,35,'active','hienthi','','',5,0,0),(20,'007','NGUYỄN ĐÌNH LỘC','555-666-444','1999-02-01 00:00:00','ĐAK LAK','093-474-2842','BMT','2000-02-01 00:00:00',0,35,'active','hienthi','','',5,0,0),(21,'008','DƯ PHÚC LINH','444-555-888','2001-03-02 00:00:00','ĐAK LAK','094-210-5252','BMT','2006-03-02 00:00:00',0,35,'active','hienthi','','',5,0,0),(22,'009','TRẦN MINH THƯ','241-243-949','2007-10-19 00:00:00','ĐAK LAK','091-465-7877','BMT','2017-10-08 00:00:00',3500000,25,'active','hienthi','','',8,0,0),(23,'010','DƯƠNG MINH THUẬN','323-456-789','2001-02-01 00:00:00','ĐAK LAK','123-555-666','BMT','2017-02-01 00:00:00',0,0,'active','hienthi','','',6,0,0),(24,'011','NGUYỄN THỊ LIÊN','120-345-621','2010-02-01 00:00:00','ĐAK LAK','123-456-7899','BMT','2012-12-02 00:00:00',0,0,'active','hienthi','','',4,0,0),(25,'012','NGUYỄN THỊ THANH THẢO','123-589-652','2011-11-12 00:00:00','ĐAK LAK','091-405-7975','BMT','1995-01-01 00:00:00',0,0,'active','hienthi','','',4,0,0),(26,'000','DƯƠNG MINH TÂM','123-455-778','2011-02-10 00:00:00','ĐAK LAK','090-500-8755','BMT','2000-01-01 00:00:00',0,0,'active','hienthi','','',8,0,0);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvienkpi`
--

DROP TABLE IF EXISTS `nhanvienkpi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `nhanvienkpi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nhanvien` int(11) DEFAULT NULL,
  `kpi` int(11) DEFAULT NULL,
  `chitieudangky` decimal(10,0) DEFAULT NULL,
  `ngaydangky` datetime DEFAULT NULL,
  `ngayhoanthanh` datetime DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `mota` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_nhanvienkpi_nhanvien1_idx` (`nhanvien`),
  KEY `fk_nhanvienkpi_kpi1_idx` (`kpi`),
  CONSTRAINT `FKosdbj8eq6r2gkwq4klvpwigvg` FOREIGN KEY (`kpi`) REFERENCES `kpi` (`id`),
  CONSTRAINT `FKvd200e71xhgdpvcrv1tw0jno` FOREIGN KEY (`nhanvien`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `fk_nhanvienkpi_kpi1` FOREIGN KEY (`kpi`) REFERENCES `kpi` (`id`),
  CONSTRAINT `fk_nhanvienkpi_nhanvien1` FOREIGN KEY (`nhanvien`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvienkpi`
--

LOCK TABLES `nhanvienkpi` WRITE;
/*!40000 ALTER TABLE `nhanvienkpi` DISABLE KEYS */;
INSERT INTO `nhanvienkpi` VALUES (1,17,3,2,'2018-05-24 00:00:00','2018-05-31 00:00:00','deleted',''),(2,17,2,3,'2018-05-24 00:00:00','2018-05-31 00:00:00','deleted',''),(3,17,1,40000000,'2018-05-24 00:00:00','2018-05-31 00:00:00','deleted',''),(4,22,2,3,'2018-06-13 00:00:00','2018-06-30 00:00:00','active',''),(5,22,3,3,'2018-06-13 00:00:00','2018-06-30 00:00:00','inactive',''),(6,22,1,50000000,'2018-06-13 00:00:00','2018-06-30 00:00:00','inactive',''),(7,16,2,3,'2018-06-15 00:00:00','2018-06-30 00:00:00','inactive',''),(8,16,3,4,'2018-06-15 00:00:00','2018-06-30 00:00:00','active',''),(9,16,1,60000000,'2018-06-15 00:00:00','2018-06-30 00:00:00','inactive','');
/*!40000 ALTER TABLE `nhanvienkpi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhomhang`
--

DROP TABLE IF EXISTS `nhomhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhomkhachhang`
--

LOCK TABLES `nhomkhachhang` WRITE;
/*!40000 ALTER TABLE `nhomkhachhang` DISABLE KEYS */;
INSERT INTO `nhomkhachhang` VALUES (9,'Tiềm Năng',100,10,'active',1,100000,10,''),(10,'Đang Chăm Sóc',200,15,'active',2,100000,15,''),(11,'Chờ',200,20,'active',3,100000,20,''),(12,'Nợ',50,5,'active',1,50000,5,''),(13,'VIP',300,30,'active',3,100000,30,''),(14,'Đã mua hàng',10,10,'active',1,100000,10,'');
/*!40000 ALTER TABLE `nhomkhachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quyen`
--

DROP TABLE IF EXISTS `quyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
 SET character_set_client = utf8mb4 ;
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
  CONSTRAINT `fk_nhanvien_taikhoan` FOREIGN KEY (`nhanvien_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES (12,'admin','$2a$10$4RKmeRiy5awlasne0U9yz.qProMiB4eQZGZTLiQ3jczZ.5R55egoq','admin@gmail.com','123','active',26),(18,'thutm','$2a$10$hd5z7NJqVKLtVghSGQmy6ezknbX4lVuSQ2stwHnoJDXpUk9mSHTZm','thutm@gmail.com','','active',22),(19,'manhht','$2a$10$l7DrjbHXjscUejLdQyTu4u1DB4RV9/cPWB0xZLYhlBP6Q8xV83i7C','manhht@gmail.com','','active',16),(20,'nydtc','$2a$10$12.qPOsunKgR5HAtSHuW7uOXjEmL2mL4wydhuuEKG0LR.vEgkExam','nydtc@gmail.com','','active',17);
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan_quyen`
--

DROP TABLE IF EXISTS `taikhoan_quyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `taikhoan_quyen` (
  `taikhoan_id` int(11) NOT NULL,
  `quyen_id` int(11) NOT NULL,
  PRIMARY KEY (`taikhoan_id`,`quyen_id`),
  KEY `fk_quyen_taikhoan_quyen_idx` (`quyen_id`),
  CONSTRAINT `FK51f3qwfympqqqwor4f28oml1l` FOREIGN KEY (`taikhoan_id`) REFERENCES `taikhoan` (`id`),
  CONSTRAINT `FKo1u207k6eyq551ypc64rqu3ln` FOREIGN KEY (`quyen_id`) REFERENCES `quyen` (`id`),
  CONSTRAINT `fk_quyen_taikhoan_quyen` FOREIGN KEY (`quyen_id`) REFERENCES `quyen` (`id`),
  CONSTRAINT `fk_taikhoan_taikhoan_quyen` FOREIGN KEY (`taikhoan_id`) REFERENCES `taikhoan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan_quyen`
--

LOCK TABLES `taikhoan_quyen` WRITE;
/*!40000 ALTER TABLE `taikhoan_quyen` DISABLE KEYS */;
INSERT INTO `taikhoan_quyen` VALUES (12,1),(18,1),(19,12),(20,12);
/*!40000 ALTER TABLE `taikhoan_quyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tieuchichamsoc`
--

DROP TABLE IF EXISTS `tieuchichamsoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tieuchichamsoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tentieuchi` varchar(45) DEFAULT NULL,
  `mota` varchar(255) DEFAULT NULL,
  `kieutieuchi` varchar(45) DEFAULT NULL,
  `trangthai` varchar(45) DEFAULT NULL,
  `khac` varchar(45) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tieuchichamsoc`
--

LOCK TABLES `tieuchichamsoc` WRITE;
/*!40000 ALTER TABLE `tieuchichamsoc` DISABLE KEYS */;
INSERT INTO `tieuchichamsoc` VALUES (6,'Đánh Giá Nhân VIên','','cokhong','active',''),(7,'Sinh Nhật','','tien','active',''),(8,'Kiểu Số','','so','active',''),(9,'Thái độ nhân viên','','cokhong','active','');
/*!40000 ALTER TABLE `tieuchichamsoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ungluong`
--

DROP TABLE IF EXISTS `ungluong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
  CONSTRAINT `fk_luong_ungluong` FOREIGN KEY (`idluong`) REFERENCES `luong` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ungluong`
--

LOCK TABLES `ungluong` WRITE;
/*!40000 ALTER TABLE `ungluong` DISABLE KEYS */;
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

-- Dump completed on 2018-06-29 21:27:38
