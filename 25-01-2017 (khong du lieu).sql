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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-25 14:21:23
