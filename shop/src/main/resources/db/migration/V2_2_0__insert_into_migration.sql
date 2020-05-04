INSERT INTO `product_category` VALUES (1, 'Phones', 'Technology');
INSERT INTO `product_category` VALUES (2, 'Computers', 'Technology');
INSERT INTO `product_category` VALUES (3, 'TVs', 'Technology');

INSERT INTO `supplier` VALUES (1, 'EMAG');

INSERT INTO `product` VALUES (1, 'iPhone X', 'Apple', 1000, '0.5', 1, 1, 'URL');
INSERT INTO `product` VALUES (2, 'Samsung J6', 'Samsung', 150, '0.6', 1, 1, 'URL');
INSERT INTO `product` VALUES (3, 'Y7000', 'Lenovo', 1000, '2.5', 2, 1, 'URL');
INSERT INTO `product` VALUES (4, 'ROG Strix', 'Asus', 1200, '2.5', 2, 1, 'URL');
INSERT INTO `product` VALUES (5, 'Pavilion', 'HP', 900, '2.5', 2, 1, 'URL');
INSERT INTO `product` VALUES (6, 'Smart TV', 'Samsung', 300, '4.0', 3, 1, 'URL');

INSERT INTO `location` VALUES(1, 'Location 1', 'Romania', 'Brasov', 'Brasov', 'B-dul Garii');
INSERT INTO `location` VALUES(2, 'Location 2', 'Romania', 'Cluj-Napoca', 'Cluj', 'Str. Observatorului');
INSERT INTO `location` VALUES(3, 'Location 3', 'Romania', 'Brasov', 'Brasov', 'Str. Cibinului');
INSERT INTO `location` VALUES(4, 'Location 4', 'Romania', 'Cluj-Napoca', 'Brasov', 'Str. George Baritiu');

INSERT INTO `customer` VALUES(1, 'John', 'Doe', 'jdoe', '1234', 'john.doe@email.org');
INSERT INTO `customer` VALUES(2, 'Paul', 'Atreides', 'paulatreides', '0000', 'paul.atr@email.org');
INSERT INTO `customer` VALUES(3, 'Michael', 'Hannigan', 'mike', 'password', 'mikeh@email.org');

INSERT INTO `stock` VALUES(1, 1, 10);
INSERT INTO `stock` VALUES(1, 2, 5);
INSERT INTO `stock` VALUES(1, 3, 4);

INSERT INTO `stock` VALUES(2, 1, 1);
INSERT INTO `stock` VALUES(2, 2, 11);
INSERT INTO `stock` VALUES(2, 4, 3);

INSERT INTO `stock` VALUES(3, 1, 4);
INSERT INTO `stock` VALUES(3, 2, 5);
INSERT INTO `stock` VALUES(3, 4, 9);

INSERT INTO `stock` VALUES(4, 1, 1);
INSERT INTO `stock` VALUES(4, 2, 11);

INSERT INTO `stock` VALUES(5, 1, 3);
INSERT INTO `stock` VALUES(5, 4, 30);

INSERT INTO `stock` VALUES(6, 1, 3);