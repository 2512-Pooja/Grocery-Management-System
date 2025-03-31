package com.grocery.org.repo.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.grocery.org.entity.UserE;
import com.grocery.org.repo.CustomUserRepository;
import com.mongodb.client.result.UpdateResult;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired MongoTemplate mongoTemplate;

    @Override
    public UpdateResult updateUserById(UserE user) {
        // TODO Auto-generated method stub
        Query query = new Query(Criteria.where("_id").is(user.getUserId()));
        Update update = new Update().set("firstName", user.getFirstName())
                                    .set("lastName", user.getLastName())
                                    .set("userName", user.getUserName())
                                    .set("email", user.getEmail())
                                    .set("password", user.getPassword())
                                    .set("phoneNo", user.getPhoneNo())
                                    .set("status", user.getStatus());
        UpdateResult res = mongoTemplate.updateFirst(query, update,UserE.class);
        return res;
    }

    @Override
    public UpdateResult updateOrderIds(String userId, String orderId) {
        // TODO Auto-generated method stub
        Query query = new Query(Criteria.where("_id").is(userId));
        Update update = new Update().push("orderIds", orderId);
        UpdateResult res = mongoTemplate.updateFirst(query, update, UserE.class);
        return res;
    }

}
