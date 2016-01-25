/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godoro.inventory.faces;

import com.godoro.inventory.entity.Category;
import com.godoro.inventory.entity.Product;
import com.godoro.inventory.repository.CategoryRepository;
import com.godoro.inventory.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author ahmet
 */
@ManagedBean
@RequestScoped
public class ProductSummaryBean {
    private List<Product> productList;
    private long selectedCategoryId=0;

    public long getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public void setSelectedCategoryId(long selectedCategoryId) {
        this.selectedCategoryId = selectedCategoryId;
    }
    
    
    public List<SelectItem> getCategoryItems(){
        List<SelectItem> categoryItems=new ArrayList<SelectItem>();
        CategoryRepository repository=new CategoryRepository();
        List<Category> categoryList=repository.list();
        categoryItems.add(new SelectItem(0,"--Seçiniz--"));
        for(Category category : categoryList){
            categoryItems.add(new SelectItem(category.getCategoryId(),category.getCategoryName()));
        }
        return categoryItems;
    }
    
    public  ProductSummaryBean(){
        ProductRepository repository = new ProductRepository();
        productList=repository.list();
    }

    public List<Product> getProductList() {
        return productList;
    }
    private long getProductId() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        long productId = 0;
        if (request.getParameter("productId") != null) {
            productId = Long.parseLong(request.getParameter("productId"));
        }
        return productId;
    }
    public void delete(){
        long productId=getProductId();
        System.out.println("Ürün No"+productId);
         ProductRepository repository = new ProductRepository();
         repository.delete(productId);
         productList=repository.list();
    }
    public void filter(){
        ProductRepository repository=new ProductRepository();
        if(selectedCategoryId!=0){
            productList=repository.listByCategoryId(selectedCategoryId);
        }else{
            productList=repository.list();
        }
    }
    
    
}
