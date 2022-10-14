package ru.pb.hibernate.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pb.hibernate.SessionFactoryUtils;
import ru.pb.hibernate.entity.Order;

import java.util.List;

@Component
public class OrderDao {

    @Autowired
    private SessionFactoryUtils sessionFactoryUtils;

    public OrderDao(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }


    public Order findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Order order = session.get(Order.class, id);
            session.getTransaction().commit();
            return order;
        }
    }


  
    public List<Order> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Order> orders = session.createQuery("select u from Order u").getResultList();
            session.getTransaction().commit();
            return orders;
        }
    }

  
    public List<Order> findByPersonId(Long personId) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Order> orders = session.createQuery("select u from Order u where u.person.id = :id", Order.class)
                    .setParameter("id", personId)
                    .getResultList();

            session.getTransaction().commit();
            return orders;
        }
    }



    public void saveOrUpdate(Order order) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(order);
            session.getTransaction().commit();
        }
    }





    public void delete(long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Order p = session.get(Order.class, id);
            if (p != null)
                session.delete(p);

            session.getTransaction().commit();
        }
    }



}
