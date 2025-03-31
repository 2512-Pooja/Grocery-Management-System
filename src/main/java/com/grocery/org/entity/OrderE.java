package com.grocery.org.entity;



import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.google.protobuf.Timestamp;
import com.grocery.grpc.Order;

@Document(collection = "order")
public class OrderE {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String orderId;
    private String userId;
    private Timestamp orderDate;
    private String orderStatus;
    private double totalPrice;

    public OrderE() {
    }

    public OrderE(Order request) {
        this.orderId = request.getOrderId();
        this.userId = request.getUserId();
        this.orderDate = request.getOrderDate();
        this.orderStatus = request.getOrderStatus();
        this.totalPrice = request.getTotalPrice();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
