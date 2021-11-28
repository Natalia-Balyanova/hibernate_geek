package com.geekbrains.balyanova.hibernate.h2;

import org.hibernate.Session;

import java.util.List;

//@Service
public class ProductDaoImpl implements ProductDao{
    private SessionFactoryUtils sessionFactoryUtils;

    public ProductDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            List<Product> products = session.createQuery("select p from Product p").getResultList();//hql request
            //List<Product> products = session.createQuery("select p from Product where p.id < 3").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public Product deleteById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public Product saveOrUpdate(Product product) {
        if(findById(product.getId())==null){ //если продукта с таким id не существует, то сохраняем
            try (Session session = sessionFactoryUtils.getSession()) {
                session.beginTransaction();
                session.save(product);
                session.getTransaction().commit();
            }
        }else{ //eсли существует, то апдейтим, затираем старый
            try (Session session = sessionFactoryUtils.getSession()) {
                session.beginTransaction();
                Product productUpdate = session.get(Product.class, product.getId());
                productUpdate.setPrice(product.getPrice());
                productUpdate.setTitle(product.getTitle());
                session.getTransaction().commit();
            }
        }
        return product;
    }

    //реализация метода как на уроке
//    @Override
//    public Product saveOrUpdate(Product product) {
//        try (Session session = sessionFactoryUtils.getSession()) {
//            session.beginTransaction();
//            session.saveOrUpdate(product);
//            session.getTransaction().commit();
//            return product;
//        }
//    }

    @Override
    public Product findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }
}
