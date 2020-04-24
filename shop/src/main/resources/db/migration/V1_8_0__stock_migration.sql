CREATE TABLE IF NOT EXISTS `stock` (

    `product_id` int NOT NULL,
    `location_id` int NOT NULL,
    `quantity` int NOT NULL,
    PRIMARY KEY (`product_id`, `location_id`),
    CONSTRAINT `FK_product` FOREIGN KEY (`product_id`)
    REFERENCES `product`(`id`),
    CONSTRAINT `FK_location2` FOREIGN KEY (`location_id`)
    REFERENCES `location`(`id`)
);