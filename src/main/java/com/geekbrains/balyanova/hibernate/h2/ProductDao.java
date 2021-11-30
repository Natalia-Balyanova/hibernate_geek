package com.geekbrains.balyanova.hibernate.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao {
    private SessionFactoryUtils sessionFactoryUtils;

    @Autowired
    public void setSessionFactoryCreator(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    public Product findById (Long id){
        try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class,id);
            System.out.println(product);
            session.getTransaction().commit();
            return product;
        }
    }

    public List<Product> findAll() {
        try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Product> productList = session.createQuery("from Product").getResultList();
            System.out.println(productList);
            session.getTransaction().commit();
            return productList;
        }
    }

    public void deleteById (Long id){
        try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Product p where p.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
        System.out.println(findAll());
    }

    public Product saveOrUpdate (Product product){
        if(findById(product.getId())==null){
            try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
                session.beginTransaction();
                session.save(product);
                session.getTransaction().commit();
            }
        }else{
            try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
                session.beginTransaction();
                Product product1 = session.get(Product.class, product.getId());
                product1.setPrice(product.getPrice());
                product1.setTitle(product.getTitle());
                session.getTransaction().commit();
            }
        }
        System.out.println(findAll());
        return product;
    }

    //не работает
//    public Product saveOrUpdate (Product product){
//        try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
//            session.beginTransaction();
//            session.saveOrUpdate(product);
//            session.getTransaction().commit();
//            return product;
//        }
//    }

    public List <Customer> findCustomersByProductId (Long productId){
        try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, productId);
            List <Customer> customerList = product.getCustomerList();
            session.getTransaction().commit();
            return customerList;
        }
    }
}