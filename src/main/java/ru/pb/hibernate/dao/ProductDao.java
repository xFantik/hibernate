package ru.pb.hibernate.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pb.hibernate.SessionFactoryUtils;
import ru.pb.hibernate.entity.Product;

import java.util.List;

@Component
public class ProductDao {

    @Autowired
    private SessionFactoryUtils sessionFactoryUtils;

    public ProductDao(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }


    public Product findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }


    public List<Product> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("select u from Product u").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }


    public Product findByTitle(String name) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product products = session.createQuery("select u from Product u where u.name = :name", Product.class)
                    .setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            return products;
        }
    }

    public void saveOrUpdate(Product product) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    public void testCache() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.get(Product.class, 1L);
            session.get(Product.class, 1L);
            session.get(Product.class, 1L);
            session.getTransaction().commit();
        }
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.get(Product.class, 1L);
            session.get(Product.class, 1L);
            session.get(Product.class, 1L);
            session.getTransaction().commit();
        }
    }

    public void delete(long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            if (p != null)
                session.delete(p);

//            session.createQuery("delete Product where id=:id", Product.class)
//                    .setParameter("id", id)
//                    .executeUpdate();
            //      ___________________ ошибка:
//            java.lang.IllegalArgumentException: Update/delete queries cannot be typed
//            at org.hibernate.internal.AbstractSharedSessionContract.resultClassChecking(AbstractSharedSessionContract.java:849)
//            at org.hibernate.internal.AbstractSharedSessionContract.createQuery(AbstractSharedSessionContract.java:832)

            session.getTransaction().commit();
        }
    }


    public boolean addProduct(long id, String title, int price) {
        //todo: insert
//        if (products.stream().anyMatch(p -> p.getId() == id))
//            return false;
//        else
//            products.add(new Product(id, title, price));
        return true;

    }
}
