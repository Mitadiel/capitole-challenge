package com.capitole.entity.price;

import com.capitole.entity.brand.Brand;
import com.capitole.entity.product.Product;
import com.capitole.valueObject.Currency;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Price {

  private Long id;
  private Brand brand;
  private Long brandId;
  private Long ProductId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Long priceList;
  private Product product;
  private Long priority;
  private BigDecimal price;
  private Currency currency;

    public Price(Long id, Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate, Long priceList, Long priority, BigDecimal price, Currency currency) {
        this.id = id;
        this.brandId = brandId;
        ProductId = productId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    public Price() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }
}
