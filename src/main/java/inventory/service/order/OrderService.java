package inventory.service.order;

import inventory.data.Repository;
import inventory.data.order.OrderRepository;
import inventory.model.Order;
import inventory.service.Service;

import java.util.List;

public class OrderService implements Service<Order> {
    private final Repository<Order> orderRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    @Override
    public void add(Order order) {
        this.orderRepository.add(order);
    }

    @Override
    public void edit(Order order) {
        this.orderRepository.edit(order);
    }

    @Override
    public void delete(long id) {
        this.orderRepository.delete(id);
    }

    @Override
    public Order getById(long id) {
        return this.orderRepository.getById(id);
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository.getAll();
    }

    @Override
    public List<Order> filter(String str) {
        return this.orderRepository.filter(str);
    }
}
