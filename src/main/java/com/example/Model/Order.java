package com.example.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "order")
public class Order {

    @Id
    private int orderId;
    private String firstName;
    private String lastName;

    public Order() { }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    @Override
    public String toString() {
        return "Order [id=" + orderId + ", " +
                "First Name=" + firstName + ", " +
                "Last Name=" + lastName + "]";
    }
}