/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godoro.inventory.faces;

import com.godoro.inventory.entity.Category;
import com.godoro.inventory.repository.CategoryRepository;
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
public class CategoryDetailBean {

    private Category category;

    public CategoryDetailBean() {
        long categoryId = getCategoryId();
        if (categoryId == 0) {
            category = new Category();
        } else {
            CategoryRepository repository = new CategoryRepository();
            category = repository.find(categoryId);
        }

    }

    private long getCategoryId() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        long categoryId = 0;
        if (request.getParameter("categoryId") != null) {
            categoryId = Long.parseLong(request.getParameter("categoryId"));
        }
        return categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String save() {
        System.out.println("Saklanıyor " + category.getCategoryId() + " " + category.getCategoryName());
        long categoryId = getCategoryId();
        System.out.println("Kategori No " + categoryId);
        CategoryRepository repository = new CategoryRepository();
        if (categoryId == 0) {
            repository.insert(category);
        } else {
            repository.update(category);
        }
        return "CategorySummaryPage?faces-redirect=true";
    }

}
