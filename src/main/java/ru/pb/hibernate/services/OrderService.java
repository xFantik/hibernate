package ru.pb.hibernate.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.pb.hibernate.dao.OrderDao;

import ru.pb.hibernate.entity.Order;

import java.util.List;

@Component
public class OrderService {

    private OrderDao orderDao;


    //Вместо @Autowired
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public String getNameById(long id) {
        return orderDao.findById(id).getName();
    }

    public Order getOrder(long id){
        return orderDao.findById(id);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Заказы:\n");
        Order p;
        List<Order> orders =orderDao.findAll();
        for (int i = 0; i < orders.size(); i++) {
            p = orders.get(i);
            sb.append(p.getId()).append(" ").append(p.getName()).append(", ");
        }
        sb.append("\n");

        return sb.toString();
    }


    public List<Order> findByPersonId(long id) {
        return orderDao.findByPersonId(id);
    }
}
