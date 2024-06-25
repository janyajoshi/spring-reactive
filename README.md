Start my-sql in docker

GraphQl doc http://localhost:5000/graphiql?path=/graphql

refer [here](https://gatheca-george.medium.com/spring-webflux-using-relational-database-mysql-postgresql-fcc5e487f57f)

```sh
docker run -p 3306:3306 --name some-mysql -e MYSQL_ROOT_PASSWORD=password -d mysql
```

Initialize course schema

```sql
CREATE DATABASE studentsDb;
USE studentsDb;

CREATE TABLE `courses` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `coursename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `coursename` (`coursename`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `students` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `registered_on` bigint NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE `coursework` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `student_id` int unsigned NOT NULL,
  `course_id` int unsigned NOT NULL,
  `marks` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `coursework_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `coursework_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



INSERT INTO `students`(`id`,`name`,`registered_on`,`status`) VALUES
(1,'George Gatheca Updated',1622515678,1),
(2,'Jonh Doe',1622515678,1),
(3,'Alice Smith',1622515678,1),
(4,'Bob Johnson',1622602078,1),
(5,'Charlie Brown',1622688478,0),
(6,'David Wilson',1622774878,1),
(7,'Eva Davis',1622861278,0),
(8,'Frank Miller',1622947678,1),
(9,'Grace Lee',1623034078,1),
(10,'Henry Walker',1623120478,1),
(11,'Ivy Robinson',1623206878,0),
(12,'Jack Martinez',1623293278,1),
(13,'Kelly Clark',1623379678,1),
(14,'Liam Lewis',1623466078,0),
(15,'Mia Young',1623552478,1),
(16,'Noah Hill',1623638878,1),
(17,'Olivia Scott',1623725278,0),
(18,'Paul Green',1623811678,1),
(19,'Quincy Adams',1623898078,1),
(20,'Rachel Baker',1623984478,0),
(21,'Steve Harris',1624070878,1),
(22,'Tina Wright',1624157278,1),
(23,'Umar Gonzalez',1624243678,0),
(24,'Vera Nelson',1624330078,1),
(25,'Will Turner',1624416478,1),
(26,'Xena King',1624502878,0),
(27,'Yara Collins',1624589278,1),
(28,'Zane Rivera',1624675678,1),
(29,'Abby Perez',1624762078,0),
(30,'Ben Murphy',1624848478,1),
(31,'Cara Rogers',1624934878,1),
(32,'Danielle Reed',1625021278,0),
(33,'Eli Cooper',1625107678,1),
(34,'Fiona Cook',1625194078,1),
(35,'Gabe Howard',1625280478,0),
(36,'Holly Ward',1625366878,1),
(37,'Ian Bell',1625453278,1),
(38,'Jill Torres',1625539678,0),
(39,'Kyle Brooks',1625626078,1),
(40,'Lara Sanders',1625712478,1),
(41,'Mason Price',1625798878,0),
(42,'Nina Bennett',1625885278,1),
(43,'Omar Wood',1625971678,1),
(44,'Paula Fisher',1626058078,0),
(45,'Quinn Hunter',1626144478,1),
(46,'Ruby Hayes',1626230878,1),
(47,'Sam Gray',1626317278,0),
(48,'Tara Ross',1626403678,1),
(49,'Uma Ortiz',1626490078,1),
(50,'Victor Jenkins',1626576478,0),
(51,'Wendy Perry',1626662878,1),
(52,'Xander Russell',1626749278,1),
(53,'Yvonne Sullivan',1626835678,0),
(54,'Zachary Peterson',1626922078,1),
(55,'Amy Patterson',1627008478,1),
(56,'Blake Foster',1627094878,0),
(57,'Cara Jenkins',1627181278,1),
(58,'Dean Simmons',1627267678,1),
(59,'Ella Patterson',1627354078,0),
(60,'Finn Coleman',1627440478,1),
(61,'Gina Harris',1627526878,1),
(62,'Hank Wallace',1627613278,0),
(63,'Irene Stone',1627699678,1),
(64,'Jackie Rhodes',1627786078,1),
(65,'Karl Duncan',1627872478,0),
(66,'Lila Wade',1627958878,1),
(67,'Mike Austin',1628045278,1),
(68,'Nina Morris',1628131678,0),
(69,'Oscar Crawford',1628218078,1),
(70,'Paula Burns',1628304478,1),
(71,'Quinn Hart',1628390878,0),
(72,'Rita Warren',1628477278,1),
(73,'Stanley Chavez',1628563678,1),
(74,'Tina Black',1628650078,0),
(75,'Uma Bishop',1628736478,1),
(76,'Vince Keller',1628822878,1),
(77,'Wendy Rogers',1628909278,0),
(78,'Xander Fuller',1628995678,1),
(79,'Yasmine Bryant',1629082078,1),
(80,'Zane Foster',1629168478,0),
(81,'Abby Porter',1629254878,1),
(82,'Brian Parker',1629341278,1),
(83,'Cara Edwards',1629427678,0),
(84,'Derek Barnes',1629514078,1),
(85,'Ella Howard',1629600478,1),
(86,'Frank Young',1629686878,0),
(87,'Gina Bryant',1629773278,1),
(88,'Hank Reed',1629859678,1),
(89,'Irene Cook',1629946078,0),
(90,'Jackie Morgan',1630032478,1),
(91,'Karl Jenkins',1630118878,1),
(92,'Lila Bell',1630205278,0),
(93,'Mike Cooper',1630291678,1),
(94,'Nina Perry',1630378078,1),
(95,'Oscar Butler',1630464478,0),
(96,'Paula Jenkins',1630550878,1),
(97,'Quinn Morris',1630637278,1),
(98,'Rita Ward',1630723678,0),
(99,'Stanley Brooks',1630810078,1),
(100,'Tina Scott',1630896478,1);
```
