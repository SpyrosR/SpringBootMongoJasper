package com.example.Service;

import com.example.Model.Order;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    public void createOrder(List<Order> ord);

    public Collection<Order> getAllOrders();

    public Optional<Order> findOrderById(int id);

    public void deleteOrderById(int id);

    public void updateOrder(Order ord);

    public void deleteAllOrders();
}
