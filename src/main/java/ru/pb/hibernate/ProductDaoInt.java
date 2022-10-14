package ru.pb.hibernate;

import java.util.List;

public interface ProductDaoInt {

    Product findById(Long id);

    List<Product> findAll();

    Product findByTitle(String title);

    void saveOrUpdate(Product product);

    void testCache();

    void delete(long id);
}
