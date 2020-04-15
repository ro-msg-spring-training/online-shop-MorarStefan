CREATE TABLE IF NOT EXISTS `order` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `shipped_from` int,
    `customer` int,
    `created_at` date,
    `country` varchar(100),
    `city` varchar(100),
    `county` varchar(100),
    `street_address` varchar(100),
    CONSTRAINT `FK_location3` FOREIGN KEY (`shipped_from`)
    REFERENCES `location`(`id`),
    CONSTRAINT `FK_customer` FOREIGN KEY (`customer`)
    REFERENCES `customer`(`id`)
);