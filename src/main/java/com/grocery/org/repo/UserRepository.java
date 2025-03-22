package com.grocery.org.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.grocery.org.entity.UserE;

public interface UserRepository extends MongoRepository<UserE, String>  {
    
}
