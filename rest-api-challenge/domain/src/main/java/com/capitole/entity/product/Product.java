package com.capitole.entity.product;

public class Product {
    private Long id;
    private String code;
    private String name;
    private String sku;

    public Product(Long id, String code, String name, String sku) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.sku = sku;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                '}';
    }

}
