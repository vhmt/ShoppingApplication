/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godoro.inventory.faces;

import com.godoro.inventory.entity.Product;
import com.godoro.inventory.repository.ProductRepository;
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
public class ProductSummaryBean {
    private List<Product> productList;
    
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
    }
    
    
}
