CREATE TABLE IF NOT EXISTS `order_detail` (

    `order_id` int NOT NULL,
    `product_id` int NOT NULL,
    `quantity` int NOT NULL,
    PRIMARY KEY(`order_id`, `product_id`),
    CONSTRAINT `FK_order` FOREIGN KEY (`order_id`)
    REFERENCES `orders`(`id`),
    CONSTRAINT `FK_product2` FOREIGN KEY (`product_id`)
    REFERENCES `product`(`id`)
);