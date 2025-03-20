package com.example.nobsv2.product.model;


import lombok.Getter;


@Getter
public class UpdateProductCommand {
    private Integer id;
    private Product product;

    public UpdateProductCommand(Integer id, Product product) {
        this.id = id;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
