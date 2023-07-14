package com.simple.blog.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.simple.blog.repositories.records.UserRecord;

public interface UserRepository extends CrudRepository<UserRecord, Long> {
    
    Optional<UserRecord> findByUsername(String username);
}
