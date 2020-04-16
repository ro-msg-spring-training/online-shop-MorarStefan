CREATE TABLE IF NOT EXISTS `order_detail` (

    `order` int NOT NULL,
    `product` int NOT NULL,
    `quantity` int NOT NULL,
    PRIMARY KEY(`order`, `product`),
    CONSTRAINT `FK_order` FOREIGN KEY (`order`)
    REFERENCES `order`(`id`),
    CONSTRAINT `FK_product2` FOREIGN KEY (`product`)
    REFERENCES `product`(`id`)
);