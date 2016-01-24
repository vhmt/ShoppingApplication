/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godoro.inventory.repository;

import com.godoro.inventory.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author ahmet
 */
public class ProductRepository {
    public List<Product> list(){
        EntityManagerFactory factory=Persistence.createEntityManagerFactory("ShoppingApplicationPU");
        EntityManager manager=factory.createEntityManager();
        String string="select product from Product as product";
        Query query = manager.createQuery(string);
        List<Product> productList=query.getResultList();
        manager.close();
        return productList;
    }
    public void insert(Product product){
        EntityManagerFactory factory=Persistence.createEntityManagerFactory("ShoppingApplicationPU");
        EntityManager manager=factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(product);
        manager.getTransaction().commit();
        manager.close();
    }
    public void update(Product product){
        EntityManagerFactory factory=Persistence.createEntityManagerFactory("ShoppingApplicationPU");
        EntityManager manager=factory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(product);
        manager.getTransaction().commit();
        manager.close();
    }
    public Product find(long productId){
        EntityManagerFactory factory=Persistence.createEntityManagerFactory("ShoppingApplicationPU");
        EntityManager manager=factory.createEntityManager();
        Product product=manager.find(Product.class,productId);
        manager.close();
        return product;
    }
    public void delete(long productId){
        EntityManagerFactory factory=Persistence.createEntityManagerFactory("ShoppingApplicationPU");
        EntityManager manager=factory.createEntityManager();
        manager.getTransaction().begin();
        Product product=manager.getReference(Product.class,productId);
        manager.remove(product);
        manager.getTransaction().commit();
        manager.close();
        
    }
}
