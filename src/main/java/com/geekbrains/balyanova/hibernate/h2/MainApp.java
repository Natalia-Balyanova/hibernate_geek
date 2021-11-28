package com.geekbrains.balyanova.hibernate.h2;

public class MainApp {
    public static void main(String[] args) {
        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
        try {
            ProductDao productDao = new ProductDaoImpl(sessionFactoryUtils);
//найти продукт по id
//            Product product = productDao.findById(1L);
//            product.print();

// удаление
//            System.out.println(productDao.deleteById(1L));

// показать все продукты
//            System.out.println(productDao.findAll().toString());

// сохранить или обновить
            productDao.saveOrUpdate(new Product(1L, "Coffee", 400));
            System.out.println(productDao.findAll().toString());


        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            sessionFactoryUtils.shutdown();
        }
    }
}
