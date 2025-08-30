package com.grocery.org.service;

import net.devh.boot.grpc.server.service.GrpcService;


import org.springframework.beans.factory.annotation.Autowired;

import com.grocery.grpc.User;
import com.grocery.grpc.UserId;
import com.grocery.grpc.UserResponse;
import com.grocery.grpc.UserServiceGrpc;
import com.grocery.org.entity.UserE;
import com.grocery.org.repo.UserRepository;
import com.mongodb.client.result.UpdateResult;

import io.grpc.stub.StreamObserver;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {
    @Autowired UserRepository userRepository;

    @Override
    public void addUser(User request, StreamObserver<UserResponse> responseObserver) {
        UserE user = userRepository.save(new UserE(request));
        UserResponse response = null;
        if(user.getId() == null) {
            response = UserResponse.newBuilder().setMessage("User NOT ADDED: " + user.getUserName()).build();
        }else{
            response = UserResponse.newBuilder().setMessage("User ADDED: " + user.getUserName()).setSuccess(true).build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteUser(UserId request, StreamObserver<UserResponse> responseObserver) {
        String userName = userRepository.findById(request.getUserId()).get().getUserName();
        userRepository.deleteById(request.getUserId());
        UserResponse response = UserResponse.newBuilder()
        .setMessage("User deleted : " + userName).setSuccess(true).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Override
    public void updateUser(User request, StreamObserver<UserResponse> responseObserver) {
        UpdateResult res = userRepository.updateUserById(new UserE(request));
        UserResponse response = null;
        if(res.getModifiedCount() > 0 ){
            response = UserResponse.newBuilder().setMessage("User updated: " + request.getUserName()).setSuccess(true).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }else{
            response = UserResponse.newBuilder().setMessage("User NOT updated: " + request.getUserName()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getUser(UserId request, StreamObserver<User> responseObserver) {
        String userId = request.getUserId();
        UserE user = userRepository.findById(userId).get();
        User response = User.newBuilder()
        .setUserId(user.getUserId())
        .setFirstName(user.getFirstName())
        .setLastName(user.getLastName())
        .setUserName(user.getUserName())
        .setEmail(user.getEmail())
        .setPhoneNo(user.getPhoneNo())
        .setStatus(user.getStatus())
        .addAllOrderIds(user.getOrderIdsList())
        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    

}
