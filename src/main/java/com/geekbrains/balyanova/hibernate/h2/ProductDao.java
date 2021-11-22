package com.geekbrains.balyanova.hibernate.h2;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    Product deleteById(Long id);
    Product saveOrUpdate(Product product);
    Product findById(Long id);

}
