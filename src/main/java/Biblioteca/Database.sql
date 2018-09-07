-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: library
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
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articles` (
  `idArticle` int(11) NOT NULL AUTO_INCREMENT,
  `ArticleName` varchar(100) NOT NULL,
  `Author` varchar(45) NOT NULL,
  `Description` text NOT NULL,
  `NumberOfWords` bigint(4) NOT NULL,
  `Date` date NOT NULL,
  `AddedBy` int(11) NOT NULL,
  `idCategory` int(11) NOT NULL,
  `ItemType` int(11) NOT NULL,
  `Status` varchar(45) NOT NULL DEFAULT 'AVAILABLE',
  PRIMARY KEY (`idArticle`),
  KEY `fk_ar_idCategory` (`idCategory`),
  KEY `fk_ar_idUser_idx` (`AddedBy`),
  KEY `fk_ar_idType_idx` (`ItemType`),
  CONSTRAINT `fk_ar_Category` FOREIGN KEY (`idCategory`) REFERENCES `categories` (`idcategory`),
  CONSTRAINT `fk_ar_idType` FOREIGN KEY (`ItemType`) REFERENCES `item_type` (`idtype`),
  CONSTRAINT `fk_ar_idUser` FOREIGN KEY (`AddedBy`) REFERENCES `users` (`idusername`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles`
--

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `books` (
  `idBook` int(11) NOT NULL AUTO_INCREMENT,
  `BookName` varchar(100) NOT NULL,
  `Author` varchar(45) NOT NULL,
  `Description` text NOT NULL,
  `NoPages` int(11) NOT NULL,
  `VolumeNumber` int(11) NOT NULL,
  `Date` date NOT NULL,
  `idCategory` int(11) NOT NULL,
  `AddedBy` int(11) NOT NULL,
  `ItemType` int(11) NOT NULL,
  `Status` varchar(45) NOT NULL DEFAULT 'AVAILABLE',
  `LastUpdateDate` date DEFAULT NULL,
  `LastUpdateBy` int(11) DEFAULT NULL,
  `bookscol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idBook`),
  KEY `idCategory_idx` (`idCategory`),
  KEY `fk_bk_idUser_idx` (`AddedBy`),
  KEY `fk_bk_itemType_idx` (`ItemType`),
  KEY `fk_update_user_idx` (`LastUpdateBy`),
  CONSTRAINT `fk_bk_idUser` FOREIGN KEY (`AddedBy`) REFERENCES `users` (`idusername`),
  CONSTRAINT `fk_item_Type` FOREIGN KEY (`ItemType`) REFERENCES `item_type` (`idtype`),
  CONSTRAINT `fk_item_category` FOREIGN KEY (`idCategory`) REFERENCES `categories` (`idcategory`),
  CONSTRAINT `fk_update_user` FOREIGN KEY (`LastUpdateBy`) REFERENCES `users` (`idusername`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Baltagul','Mihail  Sadoveanu','Acesta este un roman scris de Mihail Sadoveanu.',180,1,'2000-12-14',1,2,1,'AVAILABLE',NULL,NULL,NULL),(2,'Poezii','Mihai Eminescu','Aceasta este o care de poezii de Mihai Eminescu.',220,1,'2000-12-12',1,2,1,'LENT',NULL,NULL,NULL),(22,'Java','Ioan Irasoc','Aceasta este o carte java.',221,1,'2015-12-12',3,2,1,'AVAILABLE','2018-08-30',2,NULL),(23,'C#','Fotache Maria','Aceasta este o carte despre C#',230,1,'2009-09-18',3,2,1,'AVAILABLE','2018-09-06',2,NULL),(24,'Amintiri din copilarie','Ion Creanga','Aceasta este o carte scrisa de Creanga.',180,1,'2009-12-18',1,2,1,'AVAILABLE',NULL,NULL,NULL),(44,'Python','Criastian Popescu','O carte dedicata programatorilor in Python.',245,1,'2018-08-28',3,2,1,'AVAILABLE','2018-09-03',2,NULL),(45,'Java - Curs Practic','Cristian Cris','Acesta este un curs practic despre cum poti programa in Java.',768,1,'2018-09-01',3,2,1,'AVAILABLE',NULL,NULL,NULL),(46,'Java pentru incepatori','Antonescu Vasile','Carte pentru programatori juniori.',1450,1,'2018-09-01',3,2,1,'AVAILABLE',NULL,NULL,NULL),(47,'Curs Java','Nestian Andrei','Aceasta este un curs pentru incepatori.',121,1,'2018-09-03',3,2,1,'AVAILABLE',NULL,NULL,NULL),(48,'Baze de date','Popescu Vasile','Aceasta este o carte despre baze de date.',212,1,'2018-09-03',3,2,1,'AVAILABLE',NULL,NULL,NULL),(51,'Logistica Transporturilor','Andrei Ionescu','O carte despre logistica transporturilor rutiere, feroviare, navale si prin conducta.',456,1,'2018-09-05',2,2,1,'AVAILABLE',NULL,NULL,NULL),(52,'Macroeconomie','Teodora Roman','Cum functioneaza principiile macroeconomiei in contextul unei tari democratice.',553,1,'2018-09-05',2,2,1,'LENT',NULL,NULL,NULL),(53,'Microeconomie','Teodora Roman','Despre micronomie si micile afaceri.',345,1,'2018-09-05',2,2,1,'RETIRED',NULL,NULL,NULL),(54,'Avutia Natiunilor','Adam Smith','Cartea scrisa de Adam Smith sta la baza teoriei economiei moderne.',455,1,'2018-09-05',2,2,1,'LENT',NULL,NULL,NULL),(55,'Fiscalitate','Vasile Antonescu','O carte destinata tuturor antreprenorilor si nu numai',544,1,'2018-09-03',5,2,1,'AVAILABLE',NULL,NULL,NULL),(56,'Teoria sentimentelor morale','Adam Smith','O carte despre teoria sentimentelor morale.',455,1,'2018-09-03',6,2,1,'AVAILABLE',NULL,NULL,NULL);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categories` (
  `idCategory` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategory`),
  UNIQUE KEY `CategoryName_UNIQUE` (`CategoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (5,'Drept'),(2,'Economie'),(3,'Informatica'),(1,'Literatura'),(6,'Psihologie');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_type`
--

DROP TABLE IF EXISTS `item_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `item_type` (
  `idType` int(11) NOT NULL AUTO_INCREMENT,
  `TypeName` varchar(45) NOT NULL,
  PRIMARY KEY (`idType`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_type`
--

LOCK TABLES `item_type` WRITE;
/*!40000 ALTER TABLE `item_type` DISABLE KEYS */;
INSERT INTO `item_type` VALUES (1,'BOOK'),(2,'MEDIA');
/*!40000 ALTER TABLE `item_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lent_items`
--

DROP TABLE IF EXISTS `lent_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lent_items` (
  `idLending` int(11) NOT NULL AUTO_INCREMENT,
  `idItem` int(11) NOT NULL,
  `lending_date` date NOT NULL,
  `lending_period` int(11) NOT NULL DEFAULT '14' COMMENT 'Days',
  `restitution_date` date DEFAULT NULL,
  `toUser` int(11) NOT NULL,
  `byLibrarian` int(11) NOT NULL,
  `ItemType` int(11) NOT NULL,
  PRIMARY KEY (`idLending`),
  KEY `fk_idUser_idx` (`toUser`),
  KEY `fk_lt_byLibrarian_idx` (`byLibrarian`),
  KEY `fk_itemType_idx` (`ItemType`),
  CONSTRAINT `fk_itemType` FOREIGN KEY (`ItemType`) REFERENCES `item_type` (`idtype`),
  CONSTRAINT `fk_lt_toUser` FOREIGN KEY (`toUser`) REFERENCES `users` (`idusername`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lent_items`
--

LOCK TABLES `lent_items` WRITE;
/*!40000 ALTER TABLE `lent_items` DISABLE KEYS */;
INSERT INTO `lent_items` VALUES (42,56,'2018-09-07',14,'2018-09-07',4,2,1),(43,2,'2018-09-07',14,NULL,3,2,1),(44,52,'2018-09-07',14,NULL,3,2,1),(45,54,'2018-09-07',14,NULL,3,2,1),(46,22,'2018-09-07',14,'2018-09-07',14,2,1),(47,23,'2018-09-07',14,'2018-09-07',14,2,1);
/*!40000 ALTER TABLE `lent_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `magazines`
--

DROP TABLE IF EXISTS `magazines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `magazines` (
  `idMagazine` int(11) NOT NULL AUTO_INCREMENT,
  `MagazineName` varchar(45) NOT NULL,
  `Author` varchar(45) DEFAULT NULL,
  `Description` text NOT NULL,
  `MagazinePublisher` varchar(45) NOT NULL,
  `PublicationDate` date NOT NULL,
  `DateAdded` varchar(45) NOT NULL,
  `idCategory` int(11) NOT NULL,
  `AddedBy` int(11) NOT NULL,
  `ItemType` int(11) NOT NULL,
  `Status` varchar(45) NOT NULL,
  PRIMARY KEY (`idMagazine`),
  KEY `fk_mg_idUser_idx` (`AddedBy`),
  KEY `fk_mg_Category_idx` (`idCategory`),
  KEY `fk_mg_idType_idx` (`ItemType`),
  CONSTRAINT `fk_mg_Category` FOREIGN KEY (`idCategory`) REFERENCES `categories` (`idcategory`),
  CONSTRAINT `fk_mg_idType` FOREIGN KEY (`ItemType`) REFERENCES `item_type` (`idtype`),
  CONSTRAINT `fk_mg_idUser` FOREIGN KEY (`AddedBy`) REFERENCES `users` (`idusername`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `magazines`
--

LOCK TABLES `magazines` WRITE;
/*!40000 ALTER TABLE `magazines` DISABLE KEYS */;
/*!40000 ALTER TABLE `magazines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `media` (
  `idmedia` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Author` varchar(45) NOT NULL,
  `MediaType` varchar(30) NOT NULL,
  `MediaContent` varchar(30) NOT NULL,
  `Description` text NOT NULL,
  `Date` date NOT NULL,
  `idCategory` int(11) NOT NULL,
  `AddedBy` int(11) NOT NULL,
  `ItemType` int(11) NOT NULL,
  `Status` varchar(45) NOT NULL DEFAULT 'AVAILABLE',
  PRIMARY KEY (`idmedia`),
  KEY `fk_md_AddedBy_idx` (`AddedBy`),
  KEY `fk_md_Category_idx` (`idCategory`),
  KEY `fk_md_idType_idx` (`ItemType`),
  CONSTRAINT `fk_md_AddedBy` FOREIGN KEY (`AddedBy`) REFERENCES `users` (`idusername`),
  CONSTRAINT `fk_md_Category` FOREIGN KEY (`idCategory`) REFERENCES `categories` (`idcategory`),
  CONSTRAINT `fk_md_idType` FOREIGN KEY (`ItemType`) REFERENCES `item_type` (`idtype`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ranks`
--

DROP TABLE IF EXISTS `ranks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ranks` (
  `idRank` int(11) NOT NULL AUTO_INCREMENT,
  `RankName` varchar(45) NOT NULL,
  PRIMARY KEY (`idRank`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranks`
--

LOCK TABLES `ranks` WRITE;
/*!40000 ALTER TABLE `ranks` DISABLE KEYS */;
INSERT INTO `ranks` VALUES (1,'Administrator'),(2,'Librarian'),(3,'Student');
/*!40000 ALTER TABLE `ranks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retired_items`
--

DROP TABLE IF EXISTS `retired_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `retired_items` (
  `idRetiredItem` int(11) NOT NULL AUTO_INCREMENT,
  `idItem` int(11) NOT NULL,
  `ItemType` int(11) NOT NULL,
  `RetiredBy` int(11) NOT NULL,
  `Reason` varchar(45) NOT NULL,
  `RetireDate` date NOT NULL,
  PRIMARY KEY (`idRetiredItem`),
  KEY `fk_id_type_idx` (`ItemType`),
  KEY `fk_retiredBy_idx` (`RetiredBy`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_idType` FOREIGN KEY (`ItemType`) REFERENCES `item_type` (`idtype`),
  CONSTRAINT `fk_retiredBy` FOREIGN KEY (`RetiredBy`) REFERENCES `users` (`idusername`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci PACK_KEYS=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retired_items`
--

LOCK TABLES `retired_items` WRITE;
/*!40000 ALTER TABLE `retired_items` DISABLE KEYS */;
INSERT INTO `retired_items` VALUES (60,53,1,2,'STOLEN','2018-09-07');
/*!40000 ALTER TABLE `retired_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `idUsername` int(11) NOT NULL AUTO_INCREMENT,
  `idRank` int(11) NOT NULL,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `SecondName` varchar(45) NOT NULL,
  `Joined` date NOT NULL,
  `Status` varchar(45) NOT NULL DEFAULT 'Active',
  `userscol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUsername`),
  KEY `idRank_idx` (`idRank`),
  CONSTRAINT `idRank` FOREIGN KEY (`idRank`) REFERENCES `ranks` (`idrank`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'Administrator','myPassword','rusu.raducu@yahoo.com','Raducu','Rusu','2007-12-12','Active',NULL),(2,2,'Librarian','myPassword','marius.asdf@gmail.com','Octavian','Grigorescu','2018-12-12','Active',NULL),(3,3,'Gratian','myPassword','antonescuG@yahoo.com','Gratian','Antonescu','2010-12-12','Active',NULL),(4,1,'Marius','myPassword','popescu_19@yahoo.com','Marius','Popescu','2007-12-12','Active',NULL),(14,3,'Bogdan','$password28','student@yahoo.com','Bogdan','Paler','2018-09-03','Active',NULL),(17,3,'Dumescu','$dumescu17','dumescu@yahoo.com','Octavian','Dumescu','2018-09-06','Inactive/Deleted',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-07 10:59:18
