DROP DATABASE IF EXISTS security_authentication;
CREATE DATABASE security_authentication;

CREATE TABLE IF NOT EXISTS `security_authentication`.`users` (
                                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                                 `username` VARCHAR(45) NULL,
    `password` VARCHAR(45) NULL,
    `enabled` INT NOT NULL,
    PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `security_authentication`.`authorities` (
                                                                       `id` INT NOT NULL AUTO_INCREMENT,
                                                                       `username` VARCHAR(45) NULL,
    `authority` VARCHAR(45) NULL,
    PRIMARY KEY (`id`));

INSERT INTO `security_authentication`.`authorities` VALUES (NULL, 'gugu', 'write');
INSERT INTO `security_authentication`.`users` VALUES (NULL, 'gugu', '1234', '1');

use security_authentication;

show tables;

select * from users;