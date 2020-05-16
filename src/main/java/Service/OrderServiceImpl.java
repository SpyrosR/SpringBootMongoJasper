package Service;

import DAO.OrderDAO;
import Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO dao;

    @Override
    public void createOrder(List<Order> ord) { dao.saveAll(ord); }

    @Override
    public Collection<Order> getAllOrders() { return dao.findAll(); }

    @Override
    public Optional<Order> findOrderById(int id) { return dao.findById(id); }

    @Override
    public void deleteOrderById(int id) { dao.deleteById(id); }

    @Override
    public void updateOrder(Order ord) { dao.save(ord); }

    @Override
    public void deleteAllOrders() { dao.deleteAll(); }
}