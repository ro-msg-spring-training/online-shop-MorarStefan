CREATE TABLE IF NOT EXISTS `location` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(100) NOT NULL,
    `country` varchar(100),
    `city` varchar(100),
    `county` varchar(100),
    `street_address` varchar(100)
);