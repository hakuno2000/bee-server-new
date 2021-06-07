package com.example.bee.service;

import com.example.bee.entity.Account;
import com.example.bee.entity.Order;
import com.example.bee.repo.AccountRepo;
import com.example.bee.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public List<Order> findOrdersBySenderPhone(String phone) {
        return orderRepo.findOrdersBySenderPhone(phone);
    }

    @Override
    public List<Order> findOrdersBySenderName(String senderName) {
        return orderRepo.findOrdersBySenderName(senderName);
    }

    @Override
    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    @Override
    public List<Order> findAllByUserId(long userId) {
        return orderRepo.findOrderByCreatorUserId(userId);
    }

    @Override
    public List<Order> findOrdersNotCompleted() {
        return orderRepo.findOrdersByStatus(0);
    }

    @Override
    public List<Order> findOrdersByStatus(long status) {
        List<Order> orders = orderRepo.findOrdersByStatus(status);
        for (Order order : orders) {
            order.setUser(accountRepo.findAccountById(order.getCreatorUserId()));
        }

        return orders.stream()
                .sorted(Comparator.comparing(Order::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrdersByAccount_IdAndStatus(long account_id, long status) {
        List<Order> orders = orderRepo.findOrdersByAccount_IdAndStatus(account_id, status);
        for (Order order : orders) {
            order.setUser(accountRepo.findAccountById(order.getCreatorUserId()));
        }
        return orders;
    }

    @Override
    public Order findOrderById(long id) {
        return orderRepo.findOrderById(id);
    }

    @Override
    public void save(Order order) {
        orderRepo.save(order);
    }
    @Override
    public void forwardToShipper(long orderId, long shipperId) {
        Order order = orderRepo.findOrderById(orderId);
        Account shipper = accountRepo.findAccountById(shipperId);
        order.setAccount(shipper);
        order.setStatus(1);
        order.setSentDateReal(LocalDate.now());
        orderRepo.save(order);
    }
    @Override
    public void doneOrder(long orderId, long shipperId) {
        Order order = orderRepo.findOrderById(orderId);
        order.setStatus(2);
        order.setReceivedDateReal(LocalDate.now());
        orderRepo.save(order);
    }
}
