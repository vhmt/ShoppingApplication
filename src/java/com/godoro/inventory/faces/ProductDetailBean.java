/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godoro.inventory.faces;

import com.godoro.inventory.entity.Product;
import com.godoro.inventory.repository.ProductRepository;
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
public class ProductDetailBean {

    private Product product;

    public ProductDetailBean() {
        long productId = getProductId();
        if (productId == 0) {
            product = new Product();
        } else {
            ProductRepository repository = new ProductRepository();
            product = repository.find(productId);
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

    public String save() {
        System.out.println("Saklanıyor " + product.getProductId() + " " + product.getProductName() + " " + product.getSalesPrice());
        long productId = getProductId();
        System.out.println("Ürün No " + productId);
        ProductRepository repository = new ProductRepository();
        if (productId == 0) {
            repository.insert(product);
        } else {
            repository.update(product);
        }
        return "ProductSummaryPage?faces-redirect=true";
    }

}
