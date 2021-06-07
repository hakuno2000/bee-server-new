package com.example.bee.service;

import com.example.bee.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findOrdersBySenderPhone(String phone);
    List<Order> findOrdersBySenderName(String senderName);
    List<Order> findAll();
    List<Order> findAllByUserId(long userId);
    List<Order> findOrdersNotCompleted();
    List<Order> findOrdersByStatus(long status);
    List<Order> findOrdersByAccount_IdAndStatus(long account_id, long status);
    Order findOrderById(long id);
    void save(Order order);
    void forwardToShipper(long orderId, long shipperId);
    void doneOrder(long orderId, long shipperId);
}
