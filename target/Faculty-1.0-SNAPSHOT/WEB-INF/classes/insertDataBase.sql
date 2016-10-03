-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: faculty1
-- ------------------------------------------------------
-- Server version	5.6.25-log

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
-- Table structure for table `discipline`
--

DROP TABLE IF EXISTS `discipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discipline` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(70) NOT NULL,
  `teacher_human_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `title_UNIQUE` (`title`),
  KEY `fk_discipline_teacher1_idx` (`teacher_human_id`),
  CONSTRAINT `fk_discipline_teacher1` FOREIGN KEY (`teacher_human_id`) REFERENCES `teacher` (`human_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discipline`
--

LOCK TABLES `discipline` WRITE;
/*!40000 ALTER TABLE `discipline` DISABLE KEYS */;
INSERT INTO `discipline` VALUES (1,'reading',52),(2,'mathematics',32),(3,'writing',52),(4,'chemistry',52);
/*!40000 ALTER TABLE `discipline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discipline_id` int(11) NOT NULL,
  `student_human_id` int(11) NOT NULL,
  `statusInDiscipline` enum('DECLARED','REVOKED','CONFIRMED','DELETED') NOT NULL,
  `mark` enum('A','B','C','D','E','Fx','F') DEFAULT NULL,
  `feedback` mediumtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_evaluation_student1_idx` (`student_human_id`),
  KEY `fk_evaluation_discipline1_idx` (`discipline_id`),
  CONSTRAINT `fk_evaluation_discipline1` FOREIGN KEY (`discipline_id`) REFERENCES `discipline` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_evaluation_student1` FOREIGN KEY (`student_human_id`) REFERENCES `student` (`human_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation`
--

LOCK TABLES `evaluation` WRITE;
/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
INSERT INTO `evaluation` VALUES (26,1,29,'CONFIRMED','C','good'),(27,2,29,'DECLARED',NULL,NULL),
(28,2,31,'DECLARED',NULL,NULL),(29,3,31,'DECLARED',NULL,NULL),(30,4,31,'DECLARED',NULL,NULL),(31,4,34,'DECLARED',NULL,NULL),
(32,3,34,'DECLARED',NULL,NULL),(33,2,38,'DECLARED',NULL,NULL),(34,3,38,'CONFIRMED','E','bed'),(35,3,37,'CONFIRMED','B','good'),
(36,4,37,'DECLARED',NULL,NULL),(37,4,39,'DECLARED',NULL,NULL),(38,3,39,'CONFIRMED',NULL,NULL),(39,2,39,'DECLARED',NULL,NULL),
(40,1,39,'CONFIRMED','A','exllent'),(41,1,40,'DECLARED',NULL,NULL),(42,2,40,'DECLARED',NULL,NULL),(43,3,40,'DECLARED',NULL,NULL),
(44,4,40,'DECLARED',NULL,NULL);
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `human`
--

DROP TABLE IF EXISTS `human`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `human` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `patronymic` varchar(25) NOT NULL,
  `role` enum('STUDENT','ADMIN','TEACHER') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `human`
--

LOCK TABLES `human` WRITE;
/*!40000 ALTER TABLE `human` DISABLE KEYS */;
INSERT INTO `human` VALUES (28,'stud1','40bd001563085fc35165329ea1ff5c5ecbdbbeef','petr','Ivanov','viktorovich','STUDENT'),
(29,'stud2','40bd001563085fc35165329ea1ff5c5ecbdbbeef','vasya','koval','stepanovich','STUDENT'),
(30,'admin','8cb2237d0679ca88db6464eac60da96345513964','god','god','god','ADMIN'),
(31,'stud3','40bd001563085fc35165329ea1ff5c5ecbdbbeef','petr','koval','stepanovich','STUDENT'),
(32,'teacher1','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','vasya','Ivanov','petrovich','TEACHER'),
(33,'stud5','40bd001563085fc35165329ea1ff5c5ecbdbbeef','misha','koval','stepanovich','STUDENT'),
(34,'stud4','40bd001563085fc35165329ea1ff5c5ecbdbbeef','monya','koval','stepanovich','STUDENT'),
(35,'stud11','40bd001563085fc35165329ea1ff5c5ecbdbbeef','monya','koval','mixovich','STUDENT'),
(36,'stud7','40bd001563085fc35165329ea1ff5c5ecbdbbeef','ivan','Ivanov','mixovich','STUDENT'),
(37,'stud8','40bd001563085fc35165329ea1ff5c5ecbdbbeef','monya','koval','stepanovich','STUDENT'),
(38,'stud6','40bd001563085fc35165329ea1ff5c5ecbdbbeef','misha','shulo','viktorovich','STUDENT'),
(39,'stud9','40bd001563085fc35165329ea1ff5c5ecbdbbeef','petr','shulo','petrovich','STUDENT'),
(40,'stud10','40bd001563085fc35165329ea1ff5c5ecbdbbeef','ivan','pupkin','petrovich','STUDENT'),
(41,'stud12','40bd001563085fc35165329ea1ff5c5ecbdbbeef','oleg','voxs','petrovich','STUDENT'),
(42,'stud13','40bd001563085fc35165329ea1ff5c5ecbdbbeef','misha','lipko','petrovich','STUDENT'),
(43,'stud14','40bd001563085fc35165329ea1ff5c5ecbdbbeef','igor','Sovan','stepanovich','STUDENT'),
(44,'stud15','40bd001563085fc35165329ea1ff5c5ecbdbbeef','vasya','shulo','stepanovich','STUDENT'),
(45,'stud16','40bd001563085fc35165329ea1ff5c5ecbdbbeef','vasya','koval','viktorovich','STUDENT'),
(46,'stud17','40bd001563085fc35165329ea1ff5c5ecbdbbeef','misha','pupkin','ivanovich','STUDENT'),
(47,'stud18','40bd001563085fc35165329ea1ff5c5ecbdbbeef','ivan','pupkin','stepanovich','STUDENT'),
(48,'stud19','40bd001563085fc35165329ea1ff5c5ecbdbbeef','misha','pupkin','stepanovich','STUDENT'),
(49,'stud20','40bd001563085fc35165329ea1ff5c5ecbdbbeef','misha','pupkin','mixovich','STUDENT'),
(50,'stud21','40bd001563085fc35165329ea1ff5c5ecbdbbeef','ivan','koval','viktorovich','STUDENT'),
(51,'stud22','40bd001563085fc35165329ea1ff5c5ecbdbbeef','alex','goter','vladimirovich','STUDENT'),
(52,'teacher2','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','petr','Sonlic','stepanovich','TEACHER'),
(53,'stud23','40bd001563085fc35165329ea1ff5c5ecbdbbeef','senya','pupkin','ivanovich','STUDENT');
/*!40000 ALTER TABLE `human` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `contract` tinyint(1) NOT NULL,
  `human_id` int(11) NOT NULL,
  PRIMARY KEY (`human_id`),
  CONSTRAINT `fk_student_human` FOREIGN KEY (`human_id`) REFERENCES `human` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (0,28),(0,29),(0,31),(0,33),(0,34),(0,35),(0,36),(0,37),(0,38),(1,39),(0,40),(1,41),(0,42),
(0,43),(0,44),(0,45),(0,46),(0,47),(0,48),(0,49),(0,50),(0,51),(1,52),(1,53);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `position` enum('ASSISTANT_PROFESSOR','ASSOCIATE_PROFESSOR','PROFESSOR') NOT NULL,
  `human_id` int(11) NOT NULL,
  PRIMARY KEY (`human_id`),
  CONSTRAINT `fk_teacher_human1` FOREIGN KEY (`human_id`) REFERENCES `human` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('ASSISTANT_PROFESSOR',32),('PROFESSOR',52);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-02 22:02:38
