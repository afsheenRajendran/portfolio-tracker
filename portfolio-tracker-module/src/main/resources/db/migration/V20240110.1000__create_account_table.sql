USE portfolio;

CREATE TABLE `account`
(
    `account_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,

    `name` varchar(255) NOT NULL,

     PRIMARY KEY(`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;