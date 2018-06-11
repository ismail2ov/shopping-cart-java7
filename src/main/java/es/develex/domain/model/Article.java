package es.develex.domain.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;

@RedisHash("Article")
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    private String productId;
    private String size;
    private String color;
    private BigDecimal price;

    public Article() {
    }

    public Article(String productId, String size, String color, BigDecimal price) {
        this.productId = productId;
        this.size = size;
        this.color = color;
        this.price = price;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return this.price.setScale(2, BigDecimal.ROUND_UP);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Article{" + "productId='" + this.productId + '\'' + ", size='" + this.size + '\'' + ", color='" + this.color + '\'' + ", price=" + this.price + '}';
    }
}
