package com.fns.warehouse.service.domain.entity;

import com.fns.domain.entity.*;
import com.fns.domain.valueObject.*;
import java.math.BigDecimal;

public class Product extends BaseEntity<ProductId> {

    private ProductId productId;
    private String name;
    private String category;
    private BigDecimal price;

    private Product(Builder builder) {
        super.setId(builder.productId);
        this.name = builder.name;
        this.category = builder.category;
        this.price = builder.price;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ProductId getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public BigDecimal getPrice() { return price; }

    // Builder class
    public static class Builder {
        private ProductId productId;
        private String name;
        private String category;
        private BigDecimal price;

        public Builder productId(ProductId productId) {
            this.productId = productId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
