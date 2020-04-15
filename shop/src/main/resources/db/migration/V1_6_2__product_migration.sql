CREATE TABLE IF NOT EXISTS `product` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(100),
    `description` varchar(100),
    `price` numeric,
    `weight` float(14), 
    `category` int,
    `supplier` int,
    `image_url` varchar(100),
    CONSTRAINT `FK_category` FOREIGN KEY (`category`)
    REFERENCES `product_category`(`id`),
    CONSTRAINT `FK_supplier` FOREIGN KEY (`supplier`)
    REFERENCES `supplier`(`id`)
);