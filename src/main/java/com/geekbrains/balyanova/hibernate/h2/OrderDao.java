package com.geekbrains.balyanova.hibernate.h2;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDao {
    private SessionFactoryUtils sessionFactoryUtils;

    @Autowired
    public void setSessionFactoryUtils(SessionFactoryUtils sessionFactoryCreator) {
        this.sessionFactoryUtils = sessionFactoryCreator;
    }

    public List<Order> findAll() {
        try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Order> orderList = session.createQuery("from Order").getResultList();
            System.out.println(orderList); //  для проверки
            session.getTransaction().commit();
            return orderList;
        }
    }
    public List<Long> findOrderByCustomerAndOrderId(Long customerId, Long productId) {
        try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Long> ordersIdList = session.createQuery(
                            "select o.orderId from Order o where o.customerId= :customerId and o.productId= :productId")
                    .setParameter("customerId", customerId)
                    .setParameter("productId", productId)
                    .getResultList();
            System.out.println(ordersIdList);
            session.getTransaction().commit();
            return ordersIdList;
        }
    }

    public List<Integer> findPriceByCustomerAndOrderId(Long customerId, Long productId) {
        try (Session session = sessionFactoryUtils.getFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Integer> priceList = session.createQuery(
                            "select o.price from Order o where o.customerId= :customerId and o.productId= :productId")
                    .setParameter("customerId", customerId)
                    .setParameter("productId", productId)
                    .getResultList();
            System.out.println(priceList);
            session.getTransaction().commit();
            return priceList;
        }
    }
}