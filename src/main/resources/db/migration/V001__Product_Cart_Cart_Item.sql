-- shopping.products definition
CREATE TABLE `products` (
  `sku` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `average_value` decimal(19,2) NOT NULL,
  PRIMARY KEY (`sku`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- shopping.carts definition
CREATE TABLE `carts` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- shopping.cart_items definition
CREATE TABLE `cart_items` (
  `cart_id` varchar(255),
  `product_sku` varchar(255),
  `amount` int NOT NULL,
  PRIMARY KEY (`product_sku`,`cart_id`),
  KEY `FKpcttvuq4mxppo8sxggjtn5i2c` (`cart_id`),
  KEY `FKavged2kej03ekrqqdy0ijiof6` (`product_sku`),
  CONSTRAINT `FKavged2kej03ekrqqdy0ijiof6` FOREIGN KEY (`product_sku`) REFERENCES `products` (`sku`),
  CONSTRAINT `FKpcttvuq4mxppo8sxggjtn5i2c` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
