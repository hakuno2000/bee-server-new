package com.example.bee.controller;

import com.example.bee.entity.Account;
import com.example.bee.entity.Order;
import com.example.bee.service.AccountService;
import com.example.bee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/get-by-user-id/{userid}")
    public Map<String, Object> getAllOrders(@PathVariable("userid") long userid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orders", orderService.findAllByUserId(userid));
        return map;
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Object> getOrderDetail(@PathVariable("id") long id) {
        return ResponseEntity.ok(orderService.findOrderById(id));
    }

    @GetMapping("/status/{status}")
    public Map<String, Object> getOrderByStatus(@PathVariable("status") long status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orders", orderService.findOrdersByStatus(status));
        return map;
    }

    @GetMapping("/{phone}")
    public ResponseEntity<Object> getUserOrders(@PathVariable("phone") String phone) {
        return ResponseEntity.ok(orderService.findOrdersBySenderPhone(phone));
    }

    @GetMapping("/notcompleted")
    public ResponseEntity<Object> getNotCompletedOrders() {
        return ResponseEntity.ok(orderService.findOrdersNotCompleted());
    }

    @GetMapping("/my/shipping")
    public ResponseEntity<Object> getMyShippingOrders(@RequestBody Account account) {
        return ResponseEntity.ok(orderService.findShippingOrdersByAccount_Phone(account.getPhone()));
    }

    @GetMapping("/my/completed")
    public ResponseEntity<Object> getMyCompletedOrders(@RequestBody Account account) {
        return ResponseEntity.ok(orderService.findCompletedOrdersByAccount_Phone(account.getPhone()));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
//        Account matchedAccount = accountService.findAccountByPhone(order.getAccount().getPhone());
//        if (matchedAccount == null) return ResponseEntity.badRequest().build();
//        order.setAccount(matchedAccount);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        order.setOrderNo("Od" + LocalDateTime.now().format(formatter) + "No" + orderService.findAll().size());
        orderService.save(order);
        return ResponseEntity.ok(order);
    }
}
