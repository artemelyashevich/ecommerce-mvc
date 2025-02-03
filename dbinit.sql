CREATE SCHEMA IF NOT EXISTS `ecommerce`;

CREATE TABLE IF NOT EXISTS `ecommerce`.`categories`
(
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ecommerce`.`products`
(
    `id`          INT            NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255)   NOT NULL,
    `description` TEXT           NULL DEFAULT NULL,
    `price`       DECIMAL(10, 2) NOT NULL,
    `category_id` INT            NULL DEFAULT NULL,
    `image`       VARCHAR(255)   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `category_id` (`category_id` ASC) VISIBLE,
    CONSTRAINT `products_ibfk_1`
        FOREIGN KEY (`category_id`)
            REFERENCES `ecommerce`.`categories` (`id`)
);

CREATE TABLE IF NOT EXISTS `ecommerce`.`users`
(
    `id`        INT          NOT NULL AUTO_INCREMENT,
    `username`  VARCHAR(50)  NOT NULL,
    `password`  VARCHAR(255) NOT NULL,
    `email`     VARCHAR(100) NOT NULL,
    `full_name` VARCHAR(100) NULL DEFAULT NULL,
    `address`   VARCHAR(255) NULL DEFAULT NULL,
    `image`     VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ecommerce`.`cart`
(
    `id`         INT NOT NULL AUTO_INCREMENT,
    `product_id` INT NULL DEFAULT NULL,
    `quantity`   INT NULL DEFAULT NULL,
    `user_id`    INT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `product_id` (`product_id` ASC) VISIBLE,
    INDEX `user_id` (`user_id` ASC) VISIBLE,
    CONSTRAINT `cart_ibfk_1`
        FOREIGN KEY (`product_id`)
            REFERENCES `ecommerce`.`products` (`id`),
    CONSTRAINT `cart_ibfk_2`
        FOREIGN KEY (`user_id`)
            REFERENCES `ecommerce`.`users` (`id`)
);

CREATE TABLE IF NOT EXISTS `ecommerce`.`orders`
(
    `id`           INT            NOT NULL AUTO_INCREMENT,
    `user_id`      INT            NULL DEFAULT NULL,
    `total_amount` DECIMAL(10, 2) NULL DEFAULT NULL,
    `created_at`   TIMESTAMP      NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`   TIMESTAMP      NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `user_id` (`user_id` ASC) VISIBLE,
    CONSTRAINT `orders_ibfk_1`
        FOREIGN KEY (`user_id`)
            REFERENCES `ecommerce`.`users` (`id`)
);

CREATE TABLE IF NOT EXISTS `ecommerce`.`roles`
(
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name` (`name` ASC) VISIBLE
);

CREATE TABLE IF NOT EXISTS `ecommerce`.`user_roles`
(
    `user_id` INT NOT NULL,
    `role_id` INT NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    INDEX `role_id` (`role_id` ASC) VISIBLE,
    CONSTRAINT `user_roles_ibfk_1`
        FOREIGN KEY (`user_id`)
            REFERENCES `ecommerce`.`users` (`id`),
    CONSTRAINT `user_roles_ibfk_2`
        FOREIGN KEY (`role_id`)
            REFERENCES `ecommerce`.`roles` (`id`)
);

INSERT INTO `ecommerce`.`roles` (name) VALUES ('ADMIN');
INSERT INTO `ecommerce`.`roles` (name) VALUES ('USER');

INSERT INTO `ecommerce`.`users` (username, email, password, full_name, address)
VALUES ('admin', 'admin@example.com', 'vw==', 'Administrator', 'Minsk'); -- Password = 1

INSERT INTO `ecommerce`.`user_roles` (user_id, role_id)
VALUES (1, 1);