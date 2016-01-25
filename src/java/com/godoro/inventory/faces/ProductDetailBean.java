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
public class ProductDetailBean {

    private Product product;
    private long selectedCategoryId=0;

    public long getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public void setSelectedCategoryId(long selectedCategoryId) {
        this.selectedCategoryId = selectedCategoryId;
    }
    
    
    public ProductDetailBean() {
        long productId = getProductId();
        if (productId == 0) {
            product = new Product();
        } else {
            ProductRepository repository = new ProductRepository();
            product = repository.find(productId);
            if(product.getCategory()!=null){
                selectedCategoryId=product.getCategory().getCategoryId();
            }
            
        }

    }

    private long getProductId() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        long productId = 0;
        if (request.getParameter("productId") != null) {
            productId = Long.parseLong(request.getParameter("productId"));
        }
        return productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public String save() {
        System.out.println("Saklanıyor " + product.getProductId() + " " + product.getProductName() + " " + product.getSalesPrice());
        long productId = getProductId();
        System.out.println("Ürün No " + productId);
        System.out.println("Seçilen Kategori No " + selectedCategoryId);
        CategoryRepository categoryRepository=new CategoryRepository();
        if(selectedCategoryId!=0){
            Category category=categoryRepository.find(selectedCategoryId);
            product.setCategory(category);
        }else{
            product.setCategory(null);
        }
        ProductRepository productRepository = new ProductRepository();
        if (productId == 0) {
            productRepository.insert(product);
        } else {
            productRepository.update(product);
        }
        return "ProductSummaryPage?faces-redirect=true";
    }

}
