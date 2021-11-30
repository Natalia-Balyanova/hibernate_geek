package com.geekbrains.balyanova.hibernate.h2;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDao {
    private SessionFactoryUtils sessionFactoryUtils;

    @Autowired
    public void setSessionFactoryCreator(SessionFactoryUtils sessionFactoryCreator) {
        this.sessionFactoryUtils = sessionFactoryCreator;
    }

    public Customer findById(Long id) {
        try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            System.out.println(customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    public List<Customer> findAll() {
        try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Customer> customerList = session.createQuery("from Customer ").getResultList();
            System.out.println(customerList);
            session.getTransaction().commit();
            return customerList;
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Customer c where c.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
        System.out.println(findAll());
    }

    public Customer saveOrUpdate(Customer customer) {
        if (findById(customer.getId()) == null) {
            try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
                session.beginTransaction();
                session.save(customer);
                session.getTransaction().commit();
            }
        } else {
            try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
                session.beginTransaction();
                Customer customer1 = session.get(Customer.class, customer.getId());
                customer1.setName(customer.getName());
                session.getTransaction().commit();
            }
        }
        System.out.println(findAll());
        return customer;
    }

//    public Customer saveOrUpdate (Customer customer){
//        try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
//            session.beginTransaction();
//            session.saveOrUpdate(customer);
//            session.getTransaction().commit();
//            return customer;
//        }
//    }

    public List<Product> findProductsByCustomerId(Long customerId) {
        try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, customerId);
            List<Product> productList = customer.getProductList();
            session.getTransaction().commit();
            return productList;
        }
    }
}
