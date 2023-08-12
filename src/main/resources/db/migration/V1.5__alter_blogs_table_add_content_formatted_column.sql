ALTER TABLE `personalBlog`.`blogs` 
ADD COLUMN `content_formatted` TEXT NOT NULL AFTER `content_summary`;