package com.grocery.org.service;

import net.devh.boot.grpc.server.service.GrpcService;

import org.springframework.beans.factory.annotation.Autowired;

import com.grocery.grpc.User;
import com.grocery.grpc.UserResponse;
import com.grocery.grpc.UserServiceGrpc;
import com.grocery.org.entity.UserE;
import com.grocery.org.repo.UserRepository;

import io.grpc.stub.StreamObserver;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {
    @Autowired UserRepository userRepository;

    @Override
    public void addUser(User request, StreamObserver<UserResponse> responseObserver) {
        UserE user = new UserE();
        System.out.println("User: method invoked ");;
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNo(request.getPhoneNo());
        user.setStatus(request.getStatus());
        userRepository.save(user);
        UserResponse response = UserResponse.newBuilder().setMessage("User ADDED: " + request.getUserName()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    
    
    

}
