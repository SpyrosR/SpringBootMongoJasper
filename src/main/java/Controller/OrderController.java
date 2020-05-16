package Controller;

import JasperControllers.ApplicationJasperController;
import Model.Order;
import Service.OrderService;
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
        logger.debug("Saving Orders.");
        serv.createOrder(ord);
        return "Employee records created.";
    }

    @GetMapping(value= "/getall")
    public Collection<Order> getAll() {
        logger.debug("Getting all orders.");
        return serv.getAllOrders();
    }

    @GetMapping(value= "/getbyid/{order-id}")
    public Optional<Order> getById(@PathVariable(value= "order-id") int id) {
        logger.debug("Getting order with order-id= {}.", id);
        return serv.findOrderById(id);
    }

    @PutMapping(value= "/update/{order-id}")
    public String update(@PathVariable(value= "order-id") int id,
                         @RequestBody Order e) {
        logger.debug("Updating employee with order-id= {}.", id);
        e.setOrderId(id);
        serv.updateOrder(e);
        return "Order record for order-id= " + id + " updated.";
    }

    @DeleteMapping(value= "/delete/{order-id}")
    public String delete(@PathVariable(value= "order-id") int id) {
        logger.debug("Deleting order with order-id= {}.", id);
        serv.deleteOrderById(id);
        return "Order record for order-id= " + id + " deleted.";
    }

    @DeleteMapping(value= "/deleteall")
    public String deleteAll() {
        logger.debug("Deleting all orders.");
        serv.deleteAllOrders();
        return "All order records deleted.";
    }

    @GetMapping(value="/generateJasper")
    public ResponseEntity<byte[]> generateJasper() throws IOException, JRException {

        logger.info("Generation Jasper Report");
        ApplicationJasperController appController = new ApplicationJasperController();

        byte[] bytes = new byte[0];
        bytes = appController.createPdfReport();
        logger.info("Jasper Report generated successfully");

        return ResponseEntity
                .ok()
                .header("Content-Type", "application/pdf; charset=UTF-8")
                .header("Content-Disposition", "inline; filename=\"" + "test" + ".pdf\"")
                .body(bytes);

    }

}