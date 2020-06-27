-- MySQL dump 10.13  Distrib 5.7.29, for macos10.14 (x86_64)
--
-- Host: localhost    Database: funcommitteebackend
-- ------------------------------------------------------
-- Server version	5.7.29-log

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
-- Dumping data for table `answerAttempts`
--

LOCK TABLES `answerAttempts` WRITE;
/*!40000 ALTER TABLE `answerAttempts` DISABLE KEYS */;
/*!40000 ALTER TABLE `answerAttempts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `configKeyValue`
--

LOCK TABLES `configKeyValue` WRITE;
/*!40000 ALTER TABLE `configKeyValue` DISABLE KEYS */;
/*!40000 ALTER TABLE `configKeyValue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `descriptive`
--

LOCK TABLES `descriptive` WRITE;
/*!40000 ALTER TABLE `descriptive` DISABLE KEYS */;
INSERT INTO `descriptive` VALUES (NULL,1),(NULL,5),(NULL,8),(NULL,10),(NULL,12);
/*!40000 ALTER TABLE `descriptive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `gameCompletionStatus`
--

LOCK TABLES `gameCompletionStatus` WRITE;
/*!40000 ALTER TABLE `gameCompletionStatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `gameCompletionStatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `has_answered_entity`
--

LOCK TABLES `has_answered_entity` WRITE;
/*!40000 ALTER TABLE `has_answered_entity` DISABLE KEYS */;
INSERT INTO `has_answered_entity` VALUES (1,'answer1',1,3),(2,'answer2',2,3),(3,'answer3',3,3),(4,'answer4',4,3),(5,'answer5',5,3),(6,'answer6',6,3),(7,'answer7',7,3),(8,'answer8',8,3),(9,'answer9',9,3),(10,'answer10',10,3),(11,'answer11',11,3),(12,'answer12',12,3),(13,'answer1',1,4),(14,'answer2',2,4),(15,'answer3',3,4),(16,'answer4',4,4),(17,'answer5',5,4),(18,'answer6',6,4),(19,'answer7',7,4),(20,'answer8',8,4),(21,'answer9',9,4),(22,'answer10',10,4),(23,'answer11',11,4),(24,'answer12',12,4),(25,'answer1',1,5),(26,'answer2',2,5),(27,'answer3',3,5),(28,'answer4',4,5),(29,'answer5',5,5),(30,'answer6',6,5),(31,'answer7',7,5),(32,'answer8',8,5),(33,'answer9',9,5),(34,'answer10',10,5),(35,'answer11',11,5),(36,'answer12',12,5),(37,'answer1',1,6),(38,'answer2',2,6),(39,'answer3',3,6),(40,'answer4',4,6),(41,'answer5',5,6),(42,'answer6',6,6),(43,'answer7',7,6),(44,'answer8',8,6),(45,'answer9',9,6),(46,'answer10',10,6),(47,'answer11',11,6),(48,'answer12',12,6);
/*!40000 ALTER TABLE `has_answered_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `has_options`
--

LOCK TABLES `has_options` WRITE;
/*!40000 ALTER TABLE `has_options` DISABLE KEYS */;
INSERT INTO `has_options` VALUES (2,1),(2,2),(2,3),(2,4),(3,5),(3,6),(3,7),(3,8),(3,9),(4,10),(4,11),(4,12),(4,13),(6,14),(6,15),(6,16),(6,17),(7,18),(7,19),(7,20),(7,21),(7,22),(9,23),(9,24),(9,25),(9,26),(9,27),(11,28),(11,29),(11,30),(11,31);
/*!40000 ALTER TABLE `has_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `mcq`
--

LOCK TABLES `mcq` WRITE;
/*!40000 ALTER TABLE `mcq` DISABLE KEYS */;
INSERT INTO `mcq` VALUES (0,2),(0,3),(0,4),(1,6),(0,7),(1,9),(1,11);
/*!40000 ALTER TABLE `mcq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,'Call the police'),(2,'Try to escape from the back door'),(3,'Try to find a weapon'),(4,'Hide somewhere'),(5,'Go out for a walk with colleagues'),(6,'Play table tennis'),(7,'Rest at lounge(read newspaper, have coffee)'),(8,'Read news,articles from workplace itself'),(9,'General discussions near pantry with colleagues'),(10,'Mango Mist Resort'),(11,'Trekking to Kumaraparvata'),(12,'Murudeshwara Beach'),(13,'Rather stay at home'),(14,'Cricket'),(15,'Football'),(16,'Table Tennis'),(17,'Badminton'),(18,'Coffee'),(19,'Tea'),(20,'Boost'),(21,'Green Tea'),(22,'Nothing in pantry, rather have something outside'),(23,'Sing'),(24,'Dance'),(25,'Enact a skit with a group'),(26,'Play a music instrument'),(27,'Apply for a leave that day '),(28,'Teacher'),(29,'Doctor'),(30,'Sports'),(31,'Own Business');
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES ('DESCRIPTIVE',1,'Favourite fictional character ','Fictional Character'),('MCQ',2,'It\'s a rainy night, there\'s no electricity and you\'re home all by yourself, suddenly you remember the morning news telling you about a serial killer roaming around.You hear the door knock you see through the eye piece and you see a masked man, what would you do?','Thief Scenario'),('MCQ',3,'Favourite pastime at office','Favourite Pastime'),('MCQ',4,'GigSky has decided to organize a trip, which one of these would you prefer','Trip'),('DESCRIPTIVE',5,'Most memorable childhood achievement','Childhood Achievement'),('MCQ',6,'Favourite game','Favourite Game'),('MCQ',7,'What do you prefer in the pantry?','Pantry'),('DESCRIPTIVE',8,'If you were a castaway on a desert island, what one ‘disc’ (or song), one book, and one luxury would you take with you? (Enter the answers separated by a comma)','Desert Scenario'),('MCQ',9,'GigSky is coming up with a talent show, you are informed just before 2 days, what would you perform? ','Talent Show'),('DESCRIPTIVE',10,'Describe yourself in one line','One liner'),('MCQ',11,'If not in the current profession, what would you have been?','Profession'),('DESCRIPTIVE',12,'Imagine you are quarantined(oh wait a min, all of us are!), and then a genie appears in front of you and you are allowed to ask one wish, what would it be?(other then going out of course )','Genie');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'Admin','$2a$10$4m6owwx/ZJ9EdOFtCb2cQeXE2HmmSaQLhq3Rvkpf4w4OPNqiEQLbS','ADMIN',NULL,NULL,'admin@gigsky.com'),(3,'Abc ','$2a$10$piFJNlST5YBzVfwV2DiayOnT8wsF21lfuDrzTXdtpLjPKQwhtGNFq','USER',NULL,NULL,'abc@gmail.com'),(4,'Bcd','$2a$10$FgaUx76sVyTIzu5/DTRcJuoUlf/DIDA9ixE4/ubp9uXiDtpFoqPV6','USER',NULL,NULL,'bcd@gmail.com'),(5,'Cde','$2a$10$IdOqxL7w5R8J5IH.a2TsPefC5L7BBXwh.2xEAjCI1WTWep3rw0QoK','USER',NULL,NULL,'cde@gmail.com'),(6,'Def','$2a$10$OrSNes3msugFjrNnEsud4uwdWCY6xZcBkH5J7KgfWeHzjE2J9TsOy','USER',NULL,NULL,'def@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-27 20:53:57
