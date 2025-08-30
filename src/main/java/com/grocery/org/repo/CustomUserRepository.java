package com.grocery.org.repo;

import com.grocery.org.entity.UserE;
import com.mongodb.client.result.UpdateResult;

public interface CustomUserRepository {
    UpdateResult updateUserById(UserE user);
    UpdateResult updateOrderIds(String userId, String orderId);

}
