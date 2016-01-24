/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godoro.inventory.repository;

import com.godoro.inventory.entity.Category;
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
public class CategoryRepository {
    public List<Category> list(){
        EntityManagerFactory factory=Persistence.createEntityManagerFactory("ShoppingApplicationPU");
        EntityManager manager=factory.createEntityManager();
        String string="select category from Category as category";
        Query query = manager.createQuery(string);
        List<Category> categoryList=query.getResultList();
        manager.close();
        return categoryList;
    }
    public void insert(Category category){
        EntityManagerFactory factory=Persistence.createEntityManagerFactory("ShoppingApplicationPU");
        EntityManager manager=factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(category);
        manager.getTransaction().commit();
        manager.close();
    }
    public void update(Category category){
        EntityManagerFactory factory=Persistence.createEntityManagerFactory("ShoppingApplicationPU");
        EntityManager manager=factory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(category);
        manager.getTransaction().commit();
        manager.close();
    }
    public Category find(long categoryId){
        EntityManagerFactory factory=Persistence.createEntityManagerFactory("ShoppingApplicationPU");
        EntityManager manager=factory.createEntityManager();
        Category category=manager.find(Category.class,categoryId);
        manager.close();
        return category;
    }
    public void delete(long categoryId){
        EntityManagerFactory factory=Persistence.createEntityManagerFactory("ShoppingApplicationPU");
        EntityManager manager=factory.createEntityManager();
        manager.getTransaction().begin();
        Category category=manager.getReference(Category.class,categoryId);
        manager.remove(category);
        manager.getTransaction().commit();
        manager.close();
        
    }
}
