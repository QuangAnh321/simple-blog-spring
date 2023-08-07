ALTER TABLE `personalBlog`.`blogs` 
ADD INDEX `fk_category_id_idx` (`category_id` ASC) VISIBLE,
ADD INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `personalBlog`.`blogs` 
ADD CONSTRAINT `fk_category_id`
  FOREIGN KEY (`category_id`)
  REFERENCES `personalBlog`.`categories` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `personalBlog`.`users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;