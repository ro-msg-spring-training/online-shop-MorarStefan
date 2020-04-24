CREATE TABLE IF NOT EXISTS `product` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(100) NOT NULL,
    `description` varchar(100),
    `price` numeric,
    `weight` float(14), 
    `category_id` int,
    `supplier_id` int,
    `image_url` varchar(100),
    CONSTRAINT `FK_category` FOREIGN KEY (`category_id`)
    REFERENCES `product_category`(`id`),
    CONSTRAINT `FK_supplier` FOREIGN KEY (`supplier_id`)
    REFERENCES `supplier`(`id`)
);