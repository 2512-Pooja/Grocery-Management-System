package com.grocery.org.service;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

import com.grocery.grpc.Order;
import com.grocery.grpc.OrderResponse;
import com.grocery.grpc.OrderServiceGrpc;
import com.grocery.grpc.OrderServiceGrpc.OrderServiceImplBase;
import com.grocery.org.entity.OrderE;
import com.grocery.org.repo.OrderRepository;
import com.grocery.org.repo.UserRepository;
import com.mongodb.client.result.UpdateResult;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class OrderService extends OrderServiceGrpc.OrderServiceImplBase {

    @Autowired OrderRepository orderRepository;
    @Autowired UserRepository userRepository;

    @Override
    public void addOrder(Order request, StreamObserver<OrderResponse> responseObserver) {
        OrderE order = orderRepository.save(new OrderE(request));
        UpdateResult res = userRepository.updateOrderIds(request.getUserId(), order.getId());
        OrderResponse response = null;
        if(order.getOrderId() == null || res.getModifiedCount() == 0) {
            response = OrderResponse.newBuilder().setMessage("Order NOT ADDED: " + order.getUserId()).build();
        }else{
            response = OrderResponse.newBuilder().setMessage("Order ADDED: " + order.getUserId()).setSuccess(true).build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        
    }
}
