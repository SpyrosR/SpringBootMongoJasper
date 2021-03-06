package com.example.Controller;

import com.example.JasperControllers.ApplicationJasperController;
import com.example.Model.Order;
import com.example.Service.OrderService;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= "/api/mongo/order")
public class OrderController {

    @Autowired
    OrderService serv;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value= "/create")
    public String create(@RequestBody List<Order> ord) {
        logger.debug("OrderController ::: create() ::: saving orders");
        serv.createOrder(ord);
        return "Employee records created";
    }

    @GetMapping(value= "/getall")
    public Collection<Order> getAll() {
        logger.debug("OrderController ::: getAll() ::: getting all orders");
        return serv.getAllOrders();
    }

    @GetMapping(value= "/getbyid/{order-id}")
    public Optional<Order> getById(@PathVariable(value= "order-id") int id) {
        logger.debug("OrderController ::: getById() ::: getting order with order-id= {}", id);
        return serv.findOrderById(id);
    }

    @PutMapping(value= "/update/{order-id}")
    public String update(@PathVariable(value= "order-id") int id,
                         @RequestBody Order e) {
        logger.debug("OrderController ::: update() ::: updating employee with order-id= {}", id);
        e.setOrderId(id);
        serv.updateOrder(e);
        return "Order record for order-id= " + id + " updated.";
    }

    @DeleteMapping(value= "/delete/{order-id}")
    public String delete(@PathVariable(value= "order-id") int id) {
        logger.debug("Deleting order with order-id= {}", id);
        serv.deleteOrderById(id);
        return "Order record for order-id= " + id + " deleted";
    }

    @DeleteMapping(value= "/deleteall")
    public String deleteAll() {
        logger.debug("OrderController ::: deleteAll() ::: deleting all orders");
        serv.deleteAllOrders();
        return "All order records deleted";
    }

    @GetMapping(value="/generateJasper")
    public ResponseEntity<byte[]> generateJasper() throws IOException, JRException {

        logger.info("OrderController ::: generateJasper() ::: jasper report generation started");

        logger.info("OrderController ::: generateJasper() ::: fetching all orders");
        Collection<Order> allOrders = serv.getAllOrders();
        logger.info("OrderController ::: generateJasper() ::: fetched " + allOrders.size() + " orders");

        ApplicationJasperController appController = new ApplicationJasperController();
        byte[] bytes = appController.createPdfReport(allOrders);

        logger.info("OrderController ::: generateJasper() ::: jasper report generated successfully");

        return ResponseEntity
                .ok()
                .header("Content-Type", "application/pdf; charset=UTF-8")
                .header("Content-Disposition", "inline; filename=\"" + "application" + ".pdf\"")
                .body(bytes);

    }

}