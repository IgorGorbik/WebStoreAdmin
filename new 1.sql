CREATE TABLE CATEGORY (
   id int(11) NOT NULL AUTO_INCREMENT,
   name varchar(45) NOT NULL,
   description varchar(45) NOT NULL,
   PRIMARY KEY (`id`)
);

CREATE TABLE ADMIN (
   id int(11) NOT NULL AUTO_INCREMENT,
   name varchar(45) NOT NULL,
   password varchar(45) NOT NULL,
   PRIMARY KEY (`id`)
);

CREATE TABLE PRODUCT (
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(45) NOT NULL,
    description varchar(45) NOT NULL,
	category_id int(11) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `abc` (`category_id`),
    CONSTRAINT `abc` FOREIGN KEY (`category_id`) 
    REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
