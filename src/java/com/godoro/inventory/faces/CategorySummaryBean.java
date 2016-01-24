/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godoro.inventory.faces;

import com.godoro.inventory.entity.Category;
import com.godoro.inventory.repository.CategoryRepository;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author ahmet
 */
@ManagedBean
@RequestScoped
public class CategorySummaryBean {
    private List<Category> categoryList;
    
    public  CategorySummaryBean(){
        CategoryRepository repository = new CategoryRepository();
        categoryList=repository.list();
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
    private long getCategoryId() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        long categoryId = 0;
        if (request.getParameter("categoryId") != null) {
            categoryId = Long.parseLong(request.getParameter("categoryId"));
        }
        return categoryId;
    }
    public void delete(){
        long categoryId=getCategoryId();
        System.out.println("Kategori No"+categoryId);
         CategoryRepository repository = new CategoryRepository();
         repository.delete(categoryId);
    }
    
    
}
