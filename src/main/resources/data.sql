CREATE SCHEMA `interest` ;

CREATE TABLE `interest`.`credit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `terms` INT NOT NULL,
  `rate` DOUBLE NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `interest`.`payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `credit_id` INT NOT NULL,
  `payment_number` INT NOT NULL,
  `amount` DOUBLE NOT NULL,
  `payment_date` DATE NOT NULL,
  `create_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`));

