package es.develex.domain.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@RedisHash("Article")
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    private String productId;
    private String size;
    private String color;
    private BigDecimal price;

    private Article() {
    }

    public Article(String productId, String size, String color, BigDecimal price) {
        this.productId = productId;
        this.size = size;
        this.color = color;
        this.price = price.setScale(2, BigDecimal.ROUND_UP);
    }

    public String getProductId() {
        return this.productId;
    }

    private void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSize() {
        return this.size;
    }

    private void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return this.color;
    }

    private void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    private void setPrice(BigDecimal price) {
        this.price = price.setScale(2, BigDecimal.ROUND_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Article article = (Article) o;
        return Objects.equals(this.productId, article.productId) && Objects.equals(this.size, article.size) && Objects.equals(this.color, article.color);
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.productId, this.size, this.color);
    }

    @Override
    public String toString() {
        return "Article{" + "productId='" + this.productId + '\'' + ", size='" + this.size + '\'' + ", color='" + this.color + '\'' + ", price=" + this.price + '}';
    }
}
