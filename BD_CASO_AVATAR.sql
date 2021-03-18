create database bdEjemAvatar;
use bdEjemAvatar;

DROP TABLE IF EXISTS `producto`;
CREATE TABLE `producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `partNumber` char(10) NOT NULL,
  `tipoProducto` enum('Normal','Paquete') NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `imagen` varchar(100) NOT NULL,
  `cantidadComponentes` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `producto` VALUES (12,'PRD0000001','Normal','TV LG 50 pulgadas','/img/PRD0000001_FULLIMG.jpg',0),(13,'PRD0000005','Normal','TV LG 50 pulgadas','/img/PRD0000001_FULLIMG.jpg',0),(14,'PRD0000002','Normal','TV LG 50 pulgadas','/img/PRD0000001_FULLIMG.jpg',0),(15,'PRD0000003','Paquete','TV LG 50 pulgadas','/img/PRD0000001_FULLIMG.jpg',10);
UNLOCK TABLES;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_findByPartNumber`(v_pnum char(10))
BEGIN
	select*from producto where partNumber=v_pnum;
END ;;
DELIMITER ;