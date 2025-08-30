package com.grocery.org.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.grocery.org.entity.OrderE;

public interface OrderRepository extends MongoRepository<OrderE, String> {

}
