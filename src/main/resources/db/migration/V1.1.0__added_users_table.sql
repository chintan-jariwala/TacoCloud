CREATE TABLE `user` (
  `id`           bigint(20) NOT NULL,
  `city`         varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `fullname`     varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password`     varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `state`        varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `street`       varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `username`     varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `zip`          varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
);
