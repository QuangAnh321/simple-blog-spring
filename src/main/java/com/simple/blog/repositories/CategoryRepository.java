package com.simple.blog.repositories;

import org.springframework.data.repository.CrudRepository;

import com.simple.blog.repositories.records.CategoryRecord;

public interface CategoryRepository extends CrudRepository<CategoryRecord, Long> {
    
}
