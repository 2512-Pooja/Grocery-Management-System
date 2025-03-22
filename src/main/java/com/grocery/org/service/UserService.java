package com.grocery.org.service;

import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.grocery.grpc.User;
import com.grocery.grpc.UserId;
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
    
    

}
