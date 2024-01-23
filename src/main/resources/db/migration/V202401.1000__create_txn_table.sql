USE portfolio;

CREATE TABLE `txn` 
(
    `txn_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  
    `type` varchar(255) NOT NULL,

    `amount` DECIMAL(8,2) NOT NULL,

    `description` varchar(1024) DEFAULT NULL,

    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY(`txn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
