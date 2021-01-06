CREATE DATABASE schooljsp;
USE schooljsp;

CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `yearOfFoundation` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `adres` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1

