package com.grocery.org.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.grocery.grpc.User;

@Entity
@Document(collection = "user")
public class UserE {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String phoneNo;
    private String status;
    private List<String> orderIds; 

    public List<String> getOrderId() {
        return orderIds;
    }

    public void setOrderId(List<String> orderIds) {
        this.orderIds = orderIds;
    }
    
    public UserE() {
    }

    public UserE(User request) {
        this.userId = request.getUserId();
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.userName = request.getUserName();
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.phoneNo = request.getPhoneNo();
        this.status = request.getStatus();
        this.orderIds = request.getOrderIdsList();       
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getOrderIdsList() {
        return orderIds;
    }

    public void setOrderIdsList(List<String> orderIds) {
        this.orderIds = orderIds;
    }

}
