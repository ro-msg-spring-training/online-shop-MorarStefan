CREATE TABLE IF NOT EXISTS `orders` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `shipped_from_id` int,
    `customer_id` int NOT NULL,
    `created_at` date,
    `country` varchar(100),
    `city` varchar(100),
    `county` varchar(100),
    `street_address` varchar(100),
    CONSTRAINT `FK_location3` FOREIGN KEY (`shipped_from_id`)
    REFERENCES `location`(`id`),
    CONSTRAINT `FK_customer` FOREIGN KEY (`customer_id`)
    REFERENCES `customer`(`id`)
);