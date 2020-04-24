CREATE TABLE IF NOT EXISTS `customer` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` varchar(100) NOT NULL,
    `last_name` varchar(100) NOT NULL,
    `username` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    `email_address` varchar(100) NOT NULL
);