package com.example.DAO;

import com.example.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends MongoRepository<Order, Integer> {

}