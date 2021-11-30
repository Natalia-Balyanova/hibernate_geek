package com.geekbrains.balyanova.hibernate.h2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.geekbrains.balyanova.hibernate.h2")
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainApp.class);
        CustomerProductService service = context.getBean(CustomerProductService.class);

        System.out.println("shopping list " + service.findCustomerById(1L) + ": "
                + service.findProductsByCustomerId(1L));
        System.out.println("############################################");
        System.out.println("shopping list of product " + service.findProductById(1L) + ": " +
                service.findCustomersByProductId(1L));

        service.getPriceByCustomerAndProductId(1L, 1L);

        ProductDao productDao = context.getBean(ProductDao.class);
        CustomerDao customerDao = context.getBean(CustomerDao.class);

        Product newProduct = new Product(6L, "Chocolate", 100);
        Product newProductUpdate = new Product(1L, "Hamburger", 150);
        productDao.saveOrUpdate(newProduct);
        productDao.saveOrUpdate(newProductUpdate);

        Customer newCustomer = new Customer(4L, "Mike");
        Customer newCustomerUpdate = new Customer(1L, "George");
        customerDao.saveOrUpdate(newCustomer);
        customerDao.saveOrUpdate(newCustomerUpdate);
    }
}
