package ru.pb.hibernate;

import org.hibernate.Session;

import java.util.List;

public class ProductDao implements ProductDaoInt {

    private SessionFactoryUtils sessionFactoryUtils;

    public ProductDao(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public Product findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("select u from Product u").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
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

    @Override
    public void save(Product product) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Long id, String title) {
        //            session.createQuery("update Product u set u.title :title where u.id = :id")
        //                    .setParameter("title", title)
        //                    .setParameter("id", id)
        //                    .executeUpdate();
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            product.setTitle(title);
            session.getTransaction().commit();
        }
    }

    @Override
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

    @Override
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


}
