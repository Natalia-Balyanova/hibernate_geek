package com.geekbrains.balyanova.hibernate.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerProductService {
    private CustomerDao customerDao;
    private ProductDao productDao;
    private OrderDao orderDao;

    @Autowired
    public CustomerProductService(CustomerDao customerDao, ProductDao productDao, OrderDao orderDao) {
        this.customerDao = customerDao;
        this.productDao = productDao;
        this.orderDao = orderDao;
    }

    public List<Product> findProductsByCustomerId(Long customerId) {
        return customerDao.findProductsByCustomerId(customerId);
    }

    public List<Customer> findCustomersByProductId(Long productId) {
        return productDao.findCustomersByProductId(productId);
    }

    public Customer findCustomerById(Long id) {
        return customerDao.findById(id);
    }

    public Product findProductById(Long id) {
        return productDao.findById(id);
    }

    public void findOrders(){
        System.out.println("Order list: " + orderDao.findAll());
    }

    public void getPriceByCustomerAndProductId (Long customerId, Long productId){
        System.out.println("Customer (" + customerDao.findById(customerId).toString() +
                ") buy product (" + productDao.findById(productId).toString() + ") in order #" +
                orderDao.findOrderByCustomerAndOrderId(customerId,productId) + " by price: " +
                orderDao.findPriceByCustomerAndOrderId(customerId,productId));
    }
}