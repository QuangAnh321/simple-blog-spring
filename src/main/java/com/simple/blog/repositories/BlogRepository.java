package com.simple.blog.repositories;

import org.springframework.data.repository.CrudRepository;

import com.simple.blog.repositories.records.BlogRecord;

public interface BlogRepository extends CrudRepository<BlogRecord, Long> {
    
}
