ALTER TABLE `personalBlog`.`categories` 
ADD UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE;

INSERT INTO `categories` (`name`)
SELECT 'Programming'
WHERE NOT EXISTS (SELECT * FROM `categories` WHERE `name` = 'Programming');

INSERT INTO `categories` (`name`)
SELECT 'Music'
WHERE NOT EXISTS (SELECT * FROM `categories` WHERE `name` = 'Music');

INSERT INTO `categories` (`name`)
SELECT 'Studying'
WHERE NOT EXISTS (SELECT * FROM `categories` WHERE `name` = 'Studying');

INSERT INTO `categories` (`name`)
SELECT 'Life style'
WHERE NOT EXISTS (SELECT * FROM `categories` WHERE `name` = 'Life style');

INSERT INTO `categories` (`name`)
SELECT 'Sport'
WHERE NOT EXISTS (SELECT * FROM `categories` WHERE `name` = 'Sport');