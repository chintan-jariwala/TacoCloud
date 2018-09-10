
DROP TABLE IF EXISTS `taco`;
DROP TABLE IF EXISTS `ingredient`;
DROP TABLE IF EXISTS `taco_ingredients`;
DROP TABLE IF EXISTS `taco_order`;
DROP TABLE IF EXISTS `taco_order_tacos`;

CREATE TABLE `taco` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `ingredient` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `taco_ingredients` (
  `taco_id` bigint(20) NOT NULL,
  `ingredients_id` varchar(255) COLLATE utf8_bin NOT NULL,
  KEY `FK7y679y77n5e75s3ss1v7ff14j` (`ingredients_id`),
  KEY `FK27rycuh3mjaepnba0j6m8xl4q` (`taco_id`)
);

CREATE TABLE `taco_order` (
  `id` bigint(20) NOT NULL,
  `cccvv` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cc_expiration` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cc_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `city` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `placed_at` datetime DEFAULT NULL,
  `state` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `street` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `zip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `taco_order_tacos` (
  `order_id` bigint(20) NOT NULL,
  `tacos_id` bigint(20) NOT NULL,
  KEY `FKfwvqtnjfview9e5f7bfqtd1ns` (`tacos_id`),
  KEY `FKcxwvdkndaqmrxcen10vkneexo` (`order_id`)
);

