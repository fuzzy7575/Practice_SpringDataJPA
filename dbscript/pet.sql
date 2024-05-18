create database pet;
grant all privileges on pet.* to 'ohgiraffers'@'%';
show grants for 'ohgiraffers'@'%';
use pet;


CREATE TABLE `tbl_category` (
  `category_code` int NOT NULL AUTO_INCREMENT COMMENT '카테고리코드',
  `category_name` varchar(30) NOT NULL COMMENT '카테고리명',
  `ref_category_code` int DEFAULT NULL COMMENT '상위카테고리코드',
  PRIMARY KEY (`category_code`),
  KEY `fk_ref_category_code` (`ref_category_code`),
  CONSTRAINT `fk_ref_category_code` FOREIGN KEY (`ref_category_code`) REFERENCES `tbl_category` (`category_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='카테고리';

CREATE TABLE `tbl_pet` (
  `pet_code` int NOT NULL AUTO_INCREMENT COMMENT '펫코드',
  `pet_name` varchar(30) NOT NULL COMMENT '펫이름',
  `pet_price` int NOT NULL COMMENT '펫가격',
  `category_code` int NOT NULL COMMENT '카테고리코드',
  `adoption_status` char(1) NOT NULL COMMENT '입양가능상태',
  PRIMARY KEY (`pet_code`),
  KEY `fk_category_code` (`category_code`),
  CONSTRAINT `fk_category_code` FOREIGN KEY (`category_code`) REFERENCES `tbl_category` (`category_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='펫';


INSERT INTO `tbl_category`
(`category_code`,`category_name`)
VALUES
(1,'포유류'),
(2,'조류'),
(3,'어류');

INSERT INTO `tbl_category`
(`category_name`, `ref_category_code`)
VALUES
('강아지', 1),
('고양이', 1),
('토끼', 1),
('페럿', 1),
('미어캣', 1),
('기니피그', 1),
('앵무새', 2),
('카나리아', 2),
('구관조', 2),
('공작', 2),
('금계', 2),
('십자매', 2),
('금붕어', 3),
('비단잉어', 3),
('구피', 3),
('몰리', 3),
('라스보라', 3),
('시클리드', 3);

INSERT INTO `tbl_pet`
(`pet_name`,`pet_price`,`category_code`,`adoption_status`)
VALUES
('시츄',700000,4,'Y'),
('말티즈',600000,4,'Y'),
('비숑프리제',1000000,4,'Y'),
('푸들',800000,4,'Y'),
('닥스훈트',900000,4,'Y'),
('포메라니안',700000,4,'Y'),
('치와와',800000,4,'Y'),
('페르시안',800000,5,'Y'),
('먼치킨',900000,5,'Y'),
('노르웨이숲',700000,5,'Y'),
('뱅갈',600000,5,'Y'),
('러시안블루',800000,5,'Y'),
('홀랜드롭이어',400000,6,'Y'),
('라이언헤드',300000,6,'Y'),
('렉스',200000,6,'Y'),
('블랙세이블패럿',600000,7,'Y'),
('블랙패럿',400000,7,'Y'),
('미어캣',350000,8,'Y'),
('기니피그',400000,9,'Y'),
('금강앵무새',200000,10,'Y'),
('뉴기니아앵무새',300000,10,'Y'),
('왕관앵무새',400000,10,'Y'),
('카나리아',200000,11,'Y'),
('구관조',200000,12,'Y'),
('인도공작',3000000,13,'Y'),
('녹색공작',2000000,13,'Y'),
('아프리카공작',5000000,13,'Y'),
('금계',200000,14,'Y'),
('십자매',200000,15,'Y'),
('화금',1000,16,'Y'),
('오란다',3000,16,'Y'),
('툭눈금붕어',5000,16,'Y'),
('홍백',20000000,17,'Y'),
('대정삼색',1500000,17,'Y'),
('황금',300000,17,'Y'),
('알비노풀레드',100000,18,'Y'),
('네온블루슈퍼화이트',20000,18,'Y'),
('HB레드로즈하프문테일',45000,18,'Y'),
('셀핀몰리',11000,19,'Y'),
('풍선몰리',3000,19,'Y'),
('라스보라마큘라타',15000,20,'Y'),
('라스보라브리짓데',10000,20,'Y'),
('라스보라블루라인',12000,20,'Y'),
('라스보라갤럭시',13000,20,'Y');