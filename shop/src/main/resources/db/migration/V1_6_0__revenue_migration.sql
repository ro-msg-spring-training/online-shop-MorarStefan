CREATE TABLE IF NOT EXISTS `revenue` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `location_id` int,
    `date` date,
    `sum` numeric NOT NULL,
    CONSTRAINT `FK_location` FOREIGN KEY (`location_id`)
    REFERENCES `location`(`id`)
);