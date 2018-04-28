-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: hotrobanhang
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
  CONSTRAINT `fk_chamsoc_khachhang1` FOREIGN KEY (`khachhang_id`) REFERENCES `khachhang` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chamsoc`
--

LOCK TABLES `chamsoc` WRITE;
/*!40000 ALTER TABLE `chamsoc` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietchamsoc`
--

LOCK TABLES `chitietchamsoc` WRITE;
/*!40000 ALTER TABLE `chitietchamsoc` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hanghoa`
--

LOCK TABLES `hanghoa` WRITE;
/*!40000 ALTER TABLE `hanghoa` DISABLE KEYS */;
INSERT INTO `hanghoa` VALUES (3,'MT001','Motul 7100','chai',11,'',430000,430000,NULL,'deleted',NULL),(4,'MT002','Motul 300V','chai',11,'',430000,450000,440000,'deleted',NULL),(5,'DN001','Nhớt 01','chai',12,'',130000,150000,140000,'deleted',NULL),(6,'DN002','Nhớt 02','chai',12,'',11000,12000,11500,'deleted',NULL),(7,'1',' JAPAN HDO TURBO 40 SAE 15W40 , API CF-4 ','200lit',11,'',9365000,10995000,NULL,'active',NULL),(8,'2',' JAPAN HDO TURBO 50 SAE 20W50 , API CF-4 ','200lit',11,'',10280000,12095000,NULL,'active',NULL),(9,'15W40CH',' JAPAN HDO TURBO 40 SAE 15W40 , API CH-4 ','200lit',11,'',10440000,0,NULL,'deleted',NULL),(10,'3',' JAPAN HDO TURBO 40 SAE 15W40 , API CH-4 ','200lit',11,'',10440000,12285000,NULL,'active',NULL),(11,'4',' JAPAN HDO TURBO 50 SAE 20W50 , API CH-4 ','200lit',11,'',11355000,13400000,NULL,'active',NULL),(12,'5',' JAPAN  HDO TURBO 40 SAE 15W40 , API CI4 ','200lit',11,'',10785000,12705000,NULL,'active',NULL),(13,'6',' JAPAN HDO TURBO 50 SAE 20W50 , API CI-4 ','200lit',11,'',11675000,13770000,NULL,'active',NULL),(14,'7',' JAPAN  HYDRAULIC AW 32 ','200lit',11,'',7334000,8560000,NULL,'active',NULL),(15,'8',' JAPAN HYDRAULIC AW 46 ','200lit',11,'',7655000,8945000,NULL,'active',NULL),(16,'9',' JAPAN HYDRAULIC AW 68 ','200lit',11,'',7967000,9320000,NULL,'active',NULL),(17,'10',' JAPAN  HYDRAULIC HVI 32( Vàng ) ','200lit',11,'',9550000,11220000,NULL,'active',NULL),(18,'11',' JAPAN  HYDRAULIC HVI 46( Vàng ) ','200lit',11,'',9868000,11600000,NULL,'active',NULL),(19,'12',' JAPAN  HYDRAULIC HVI 68 ( Vàng ) ','200lit',11,'',10185000,11980000,NULL,'active',NULL),(20,'13',' JAPAN GEAR OIL SUPER 90 SAE 80W90, API GL -5 ','200lit',11,'',9487000,11145000,NULL,'active',NULL),(21,'14',' JAPAN GEAR OIL SUPER 140 SAE 85W-140, API GL -5 ','200lit',11,'',10026000,11795000,NULL,'active',NULL),(22,'15',' JAPAN HDO TURBO 40 SAE 15W40 , API CF-4 ','18lit',11,'',890000,1050000,NULL,'active',NULL),(23,'16',' JAPAN HDO TURBO 50 SAE 20W50 , API CF-4 ','18lit',11,'',975000,1145000,NULL,'active',NULL),(24,'17',' JAPAN HDO TURBO 40 SAE 15W40 , API CH-4 ','18lit',11,'',987000,1170000,NULL,'active',NULL),(25,'18',' JAPAN HDO TURBO 50 SAE 20W50 , API CH-4 ','18lit',11,'',1070000,1265000,NULL,'active',NULL),(26,'19',' JAPAN  HDO TURBO 40 SAE 15W40 , API CI4 ','18lit',11,'',1018000,1200000,NULL,'active',NULL),(27,'20',' JAPAN HDO TURBO 50 SAE 20W50 , API CI-4 ','18lit',11,'',1097000,1295000,NULL,'active',NULL),(28,'21',' JAPAN  HYDRAULIC AW 32 ','18lit',11,'',707000,830000,NULL,'active',NULL),(29,'22',' JAPAN HYDRAULIC AW 46 ','18lit',11,'',735000,865000,NULL,'active',NULL),(30,'23',' JAPAN HYDRAULIC AW 68 ','18lit',11,'',765000,895000,NULL,'active',NULL),(31,'24',' JAPAN  HYDRAULIC HVI 32( Vàng ) ','18lit',11,'',908000,1070000,NULL,'active',NULL),(32,'25',' JAPAN  HYDRAULIC HVI 46( Vàng ) ','18lit',11,'',935000,1100000,NULL,'active',NULL),(33,'26',' JAPAN  HYDRAULIC HVI 68 ( Vàng ) ','18lit',11,'',965000,1135000,NULL,'active',NULL),(34,'27',' JAPAN GEAR OIL SUPER 90 SAE 80W90, API GL -5 ','18lit',11,'',902000,1060000,NULL,'active',NULL),(35,'28',' JAPAN GEAR OIL SUPER 140 SAE 85W-140, API GL -5 ','18lit',11,'',950000,1120000,NULL,'active',NULL),(36,'29',' JAPAN HDO TURBO 40 SAE 15W40 , API CF-4 ','25lit',12,'',1185000,1395000,NULL,'active',NULL),(37,'30',' JAPAN HDO TURBO 50 SAE 20W50 , API CF-4 ','25 lít',11,'',1300000,1530000,NULL,'active',NULL),(38,'31',' JAPAN HDO TURBO 40 SAE 15W40 , API CH-4 ','25 lít',11,'',1300000,1555000,NULL,'active',NULL),(39,'32',' JAPAN HDO TURBO 40 SAE 15W40 , API CH-4 ','25 lít',11,'',1320000,0,NULL,'active',NULL),(40,'33',' JAPAN HDO TURBO 50 SAE 20W50 , API CH-4 ','25 lít ',11,'',1435000,1695000,NULL,'active',NULL),(41,'34',' JAPAN  HDO TURBO 40 SAE 15W40 , API CI4 ','25 lít',11,'',1362000,1605000,NULL,'active',NULL),(42,'35',' JAPAN HDO TURBO 50 SAE 20W50 , API CI-4 ','25lit',11,'',1474000,1740000,NULL,'active',NULL),(44,'36',' JAPAN HYDRAULIC AW 46 ','25lit',11,'',971000,1135000,NULL,'active',NULL),(45,'37',' JAPAN HYDRAULIC AW 68 ','25lit',11,'',1010000,1185000,NULL,'active',NULL),(46,'38',' JAPAN  HYDRAULIC HVI 32( Vàng ) ','25lit',11,'',1208000,1420000,NULL,'active',NULL),(47,'39',' JAPAN  HYDRAULIC HVI 46( Vàng ) ','25lit',11,'',1248000,1470000,NULL,'active',NULL),(48,'40',' JAPAN  HYDRAULIC HVI 68 ( Vàng ) ','25lit',11,'',1288000,0,NULL,'active',NULL),(49,'41',' JAPAN GEAR OIL SUPER 90 SAE 80W90, API GL -5 ','25lit',11,'',1200000,0,NULL,'active',NULL),(50,'42',' JAPAN GEAR OIL SUPER 140 SAE 85W-140, API GL -5 ','25lit',11,'',1267000,0,NULL,'active',NULL);
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
  CONSTRAINT `fk_hoadon_khachhang1` FOREIGN KEY (`idkhachhang`) REFERENCES `khachhang` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hoadon_nhanvien1` FOREIGN KEY (`idnhanvienban`) REFERENCES `nhanvien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hoadon_nhanvien2` FOREIGN KEY (`idnhanvienlaphoadon`) REFERENCES `nhanvien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hoadon_nhanvien3` FOREIGN KEY (`idnhanvienchamsoc`) REFERENCES `nhanvien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hoadon_nhanvien4` FOREIGN KEY (`idnhanviengiaohang`) REFERENCES `nhanvien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
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
  CONSTRAINT `fk_khachhang_loaikhachhang` FOREIGN KEY (`maloaikhachhang`) REFERENCES `loaikhachhang` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_khachhang_nhomkhachhang1` FOREIGN KEY (`nhomkhachhang_id`) REFERENCES `nhomkhachhang` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (1,'NAM HOÀNG','GARA NAM HOÀNG',1,NULL,'PHAN BỘI CHÂU',NULL,'ANH THỊNH',NULL,NULL,'ANH THỊNH',NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,3,0,'chosinhnhat',0,0,'co',NULL,9),(3,'ANH BẢO','GARA ANH BẢO',2,NULL,'NGUYỄN THỊ ĐỊNH',NULL,'ANH BẢO',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(4,'ANH NHÓC','GARA ANH NHÓC',3,NULL,'VÀNH ĐAI',NULL,'ANH NHÓC',NULL,NULL,'ANH NHÓC',NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(5,'ANH MINH','GARA MINH',4,NULL,'THỦ KHOA HUÂN',NULL,'ANH MINH',NULL,NULL,'ANH MINH',NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(6,'ANH NAM','GARA HOÀNG NAM',5,NULL,'Y MOON',NULL,'ANH NAM',NULL,NULL,'ANH NAM',NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(7,'ANH SÂM','GARA AN TÂM',6,NULL,'Y MOON',NULL,'ANH SÂM',NULL,NULL,'ANH SÂM',NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(8,'ANH KIÊN ','GARA TRUNG KIÊN',7,NULL,'Y MOON',NULL,'ANH KIÊN',NULL,NULL,'ANH KIÊN',NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(9,'ANH SƠN','GARA TÝ PHÁT',8,NULL,'Y MOON',NULL,'ANH SƠN',NULL,NULL,'ANH SƠN',NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(10,'ANH VŨ','GARA TRẦN VŨ',9,NULL,'LÊ THÁNH TÔNG',NULL,'ANH VŨ',NULL,NULL,'ANH VŨ',NULL,NULL,41,NULL,NULL,NULL,'deleted',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(12,'ANH KỲ','GARA ANH KỲ',9,NULL,NULL,NULL,'ANH KỲ',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(13,'ANH PHỤNG','GARA ANH PHỤNG',10,NULL,NULL,NULL,'ANH PHỤNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(14,'ANH THỤ','GARA THỤ',11,NULL,'AMASA',NULL,'ANH THỤ',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(15,'ANH THÔNG','GARA XANH',12,NULL,NULL,NULL,'ANH THÔNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(16,'ANH HUYNH','GARA HUYNH',13,NULL,'HÙNG VƯƠNG',NULL,'ANH HUYNH',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(17,'ANH HẢO','GARA HẢO',13,NULL,'HÙNG VƯƠNG',NULL,'ANH HẢO',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(18,'ANH QUANG','GARA PHÚ QUÝ',15,NULL,'HÙNG VƯƠNG',NULL,'ANH QUANG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(20,'ANH CƯỜNG','GARA CƯỜNG KHÁNH',17,NULL,'NGUYỄN VĂN CỪ',NULL,'ANH CƯỜNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(21,'ANH NHƯỜNG','GARA NHƯỜNG',18,NULL,'LÊ VỤ',NULL,'ANH NHƯỜNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(22,'ANH KIÊN 2','GARA TRUNG KIÊN',19,NULL,'CƯ JUT',NULL,'ANH KIÊN 2',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(23,'ANH BỬU','GARA BỬU',20,NULL,'KM 42 KRONG PAK',NULL,'ANH BỬU',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(24,'ANH ĐÔNG','GARA ĐÔNG',21,NULL,'MADRAK',NULL,'ANH ĐÔNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(25,'ANH TẤN DŨNG ','GARA TẤN DŨNG',22,NULL,'NGUYỄN VĂN CỪ',NULL,'ANH TẤN DŨNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(26,'PHƯỚC TOÀN','GARA PHƯỚC TOÀN',23,NULL,'ĐINH TIÊN HOÀNG',NULL,'PHƯỚC TOÀN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,2,0,'chosinhnhat',0,0,'co',NULL,9),(27,'NHẤT NGUYÊN','GARA NHẤT NGUYÊN',24,NULL,'156 CHU VĂN AN',NULL,'CHỊ VÂN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(28,'ANH PHÚC','GARA PHÚC',25,NULL,'HÀ HUY TẬP',NULL,'ANH PHÚC',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(29,'ANH LẬP','GARA LẬP',26,NULL,'VÀNH ĐAI',NULL,'ANH LẬP',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(30,'THẢO LAN',' NHÀ XE THẢO LAN',26,NULL,'HÀ LAN',NULL,'ANH CHINH',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(31,'ANH THẢO',' NHÀ XE ANH THẢO',27,NULL,'BUÔN HỒ',NULL,'ANH THẢO',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(32,'ANH TOÀN','NHÀ XE ANH THƯ',28,NULL,'HÀ LAN',NULL,'ANH TOÀN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(33,'ANH QUỐC','GARA ANH QUỐC',28,NULL,'MAI XUÂN THƯỞNG',NULL,'ANH QUỐC',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(34,'ANH HIẾU','GARA HIẾU',28,NULL,'130 YMOAN',NULL,'ANH HIẾU',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(35,'ANH LƯỢNG','GARA LƯỢNG',30,NULL,'23 NGUYỄN KHUYẾN',NULL,'ANH TÁM',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(36,'NGỌC HOÀNG','GARA NGỌC HOÀNG',31,NULL,'39 NGUYỄN THỊ ĐỊNH',NULL,'NGỌC HOÀNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(37,'BẢO KHANH','GARA BẢO KHANH',32,NULL,'KM 42',NULL,'BẢO KHANH',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(38,'ANH MẠNH','VLXD MẠNH HOÀI',33,NULL,'KM 38',NULL,'ANH MẠNH',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(39,'ANH TUẤN','ANH TUẤN',34,NULL,'KM 39 JA',NULL,'ANH TUẤN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(40,'ANH KIỆN * LONG PHÁT*','TÔN LONG PHÁT',35,NULL,'BUÔN HỒ',NULL,'ANH KIỆN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(41,'CHÚ THUẬN','CÔNG TY NAM THUẬN',36,NULL,'TÂN AN',NULL,'CHÚ THUẬN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(43,'ANH ĐƯỜNG','ANH ĐƯỜNG',38,NULL,'BUÔN KA NA-CƯMGAR',NULL,'ANH ĐƯỜNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(45,'CHÚ BÌNH','SƯ ĐOÀN 470-28',39,NULL,'NGUYỄN LƯƠNG BẰNG',NULL,'CHÚ BÌNH',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(47,'ANH TIẾN','ANH TIẾN TÀI XẾ',41,NULL,'MAI HẮC ĐÉ',NULL,'ANH TIẾN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(48,'ANH NGỌC','GARA ANH NGỌC',42,NULL,'NGUYỄN CHÍ THANH',NULL,'ANH NGỌC',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(49,'ANH HÀ','ANH HÀ',42,NULL,'20 HÀM NGHI',NULL,'ANH HÀ',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(50,'ANH LONG','ANH LONG',44,NULL,'61 ĐINH TIÊN HOÀNG',NULL,'ANH LONG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(51,'ANH MẪN','ANH MẪN',45,NULL,'48 TÂN TIẾN EANA',NULL,'ANH MẪN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(52,'ANH TRỌNG','TAXI ANH TRỌNG',46,NULL,'BMT',NULL,'ANH TRỌNG',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(53,'ANH THY','TAXI ANH THY',47,NULL,'BMT',NULL,'ANH THY',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(54,'ANH LIÊM','ĐẠI LÝ SỸ LIÊM',48,NULL,'KM52',NULL,'ANH LIÊM',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(55,'ANH THÁI','NHÀ XE HOÀNG THÁI',49,NULL,'KM62',NULL,'ANH THÁI',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(56,'DN TOÀN PHÁT','DN TOÀN PHÁT',50,NULL,'GIA NGHĨA',NULL,'ANH TOÀN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(57,'HOÀNG LÂN','HOÀNG LÂN ',51,NULL,'NAMDJANG/ ĐAK NÔNG',NULL,'HOÀNG LÂN',NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(58,'HOÀNG QUYẾN','CTY HOÀNG QUYẾN',NULL,NULL,'PHẠM NGŨ LÃO',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,3,0,'chosinhnhat',0,0,'co',NULL,9),(59,'ANH ÂU','TAXI ÂU',NULL,NULL,'BMT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(60,'ANH TRUNG','TAXI TRUNG',NULL,NULL,'BMT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9),(61,'ANH VŨ 2','ANH VŨ 2',NULL,NULL,'TRƯƠNG CÔNG ĐỊNH',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,1,0,'chosinhnhat',0,0,'co',NULL,9),(62,'ANH HUY','TAXI HUY',NULL,NULL,'BMT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,41,NULL,NULL,NULL,'active',0,0,0,0,0,'chosinhnhat',0,0,'co',NULL,9);
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
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `luong`
--

LOCK TABLES `luong` WRITE;
/*!40000 ALTER TABLE `luong` DISABLE KEYS */;
INSERT INTO `luong` VALUES (69,16,0,0,0,'04','2018','chuatraluong',''),(70,15,3000000,0,0,'04','2018','chuatraluong',''),(71,7,3000000,0,0,'04','2018','chuatraluong','');
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (7,'QT001','Nguyễn Phước Cường','293-838-272','2005-03-29 00:00:00','Buôn Ma Thuột','098-767-2632','Buôn Ma Thuột','2010-03-20 00:00:00',3000000,10,'deleted','hienthi','Nhân Viên Quản Lý','',8,0,0),(15,'CS01','Trần Minh Thư','000-000-000','1999-09-09 00:00:00','Dak Lak','091-465-7877','Buôn Ma Thuột','2017-09-09 00:00:00',3000000,0,'active','khonghienthi','','',7,0,0),(16,'001','Huỳnh Tấn Mạnh','223-344-556','2015-01-22 00:00:00','Đawk Lăk','091-462-67777','bmt','2017-10-01 00:00:00',0,0,'active','hienthi','','',8,0,0);
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
  CONSTRAINT `fk_nhanvienkpi_kpi1` FOREIGN KEY (`kpi`) REFERENCES `kpi` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_nhanvienkpi_nhanvien1` FOREIGN KEY (`nhanvien`) REFERENCES `nhanvien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvienkpi`
--

LOCK TABLES `nhanvienkpi` WRITE;
/*!40000 ALTER TABLE `nhanvienkpi` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES (12,'admin','$2a$10$4RKmeRiy5awlasne0U9yz.qProMiB4eQZGZTLiQ3jczZ.5R55egoq','admin@gmail.com','123','active',7),(18,'thutm','$2a$10$hd5z7NJqVKLtVghSGQmy6ezknbX4lVuSQ2stwHnoJDXpUk9mSHTZm','thutm@gmail.com','','active',15),(19,'manhht','$2a$10$l7DrjbHXjscUejLdQyTu4u1DB4RV9/cPWB0xZLYhlBP6Q8xV83i7C','manhht@gmail.com','','active',16);
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
INSERT INTO `taikhoan_quyen` VALUES (12,1),(18,1),(12,12),(19,12),(12,13),(12,14);
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

-- Dump completed on 2018-04-27 20:30:08
