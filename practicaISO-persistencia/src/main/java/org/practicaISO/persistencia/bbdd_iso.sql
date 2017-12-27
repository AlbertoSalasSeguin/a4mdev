CREATE DATABASE  IF NOT EXISTS `bbdd_iso` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bbdd_iso`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bbdd_iso
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `tb_album`
--

DROP TABLE IF EXISTS `tb_album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_album` (
  `idalbum` int(11) NOT NULL,
  `nombre` char(20) NOT NULL,
  PRIMARY KEY (`idalbum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_album`
--

LOCK TABLES `tb_album` WRITE;
/*!40000 ALTER TABLE `tb_album` DISABLE KEYS */;
INSERT INTO `tb_album` VALUES (1,'One'),(2,'Two'),(3,'Three'),(4,'Four'),(5,'Five'),(6,'Six'),(7,'Seven'),(8,'Eight'),(30,'Album Ejemplo');
/*!40000 ALTER TABLE `tb_album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cancion`
--

DROP TABLE IF EXISTS `tb_cancion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_cancion` (
  `titulo` char(25) NOT NULL,
  `idcancion` int(11) NOT NULL,
  `autor` varchar(24) NOT NULL,
  `album` int(11) NOT NULL,
  `precio` float NOT NULL,
  PRIMARY KEY (`idcancion`),
  KEY `fk_album_idx` (`album`),
  CONSTRAINT `fk_album` FOREIGN KEY (`album`) REFERENCES `tb_album` (`idalbum`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cancion`
--

LOCK TABLES `tb_cancion` WRITE;
/*!40000 ALTER TABLE `tb_cancion` DISABLE KEYS */;
INSERT INTO `tb_cancion` VALUES ('Time of dying',1,'Three days grace',1,10),('Taking you down',2,'Egypt Central',2,20),('Animal i have become',3,'Three days grace',1,30),('Havana',4,'Camila Cabello',3,12),('Menores',5,'Carol G',4,13),('Highway to hell',6,'AC-DC',5,14),('This fire burns',7,'Killswitch Engage',5,16),('Desp',8,'Luis Fonsi',6,19),('Ahora me llama',9,'Bad Bunny',7,10),('Todo cambio',10,'Becky G',8,23);
/*!40000 ALTER TABLE `tb_cancion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cliente`
--

DROP TABLE IF EXISTS `tb_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_cliente` (
  `nick` char(15) NOT NULL,
  `pass` char(20) NOT NULL,
  `email` char(30) NOT NULL,
  `nombre` varchar(16) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `suscripcion` char(8) NOT NULL,
  `rol` char(10) DEFAULT NULL,
  PRIMARY KEY (`nick`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cliente`
--

LOCK TABLES `tb_cliente` WRITE;
/*!40000 ALTER TABLE `tb_cliente` DISABLE KEYS */;
INSERT INTO `tb_cliente` VALUES ('a4m','1234','a4m','A4Mdev','DEVELOPERS','premium','Admin'),('Alberto','123','albertovinaroz@live.com','Alberto','Vinaroz','Normal','Usuario');
/*!40000 ALTER TABLE `tb_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_playlist`
--

DROP TABLE IF EXISTS `tb_playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_playlist` (
  `nick` char(15) NOT NULL,
  `canciones` int(11) NOT NULL,
  PRIMARY KEY (`nick`,`canciones`),
  KEY `fk_3_idx` (`canciones`),
  CONSTRAINT `fk_canciones` FOREIGN KEY (`canciones`) REFERENCES `tb_cancion` (`idcancion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente` FOREIGN KEY (`nick`) REFERENCES `tb_cliente` (`nick`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_playlist`
--

LOCK TABLES `tb_playlist` WRITE;
/*!40000 ALTER TABLE `tb_playlist` DISABLE KEYS */;
INSERT INTO `tb_playlist` VALUES ('a4m',1),('a4m',2),('a4m',3),('a4m',5),('Alberto',5),('Alberto',6);
/*!40000 ALTER TABLE `tb_playlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-27 17:42:52
