CREATE TABLE IF NOT EXISTS `stock` (

    `product` int NOT NULL,
    `location` int NOT NULL,
    `quantity` int,
    CONSTRAINT `FK_product` FOREIGN KEY (`product`)
    REFERENCES `product`(`id`),
    CONSTRAINT `FK_location2` FOREIGN KEY (`location`)
    REFERENCES `location`(`id`)
);