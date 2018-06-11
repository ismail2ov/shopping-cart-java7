package es.develex.domain.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@RedisHash("CartLine")
public class CartLine implements Serializable {
    private static final long serialVersionUID = 1L;
    private String productId;
    private String size;
    private String color;
    private BigDecimal price;
    private Integer numItems;

    public CartLine() {
    }

    public CartLine(String productId, String size, String color, BigDecimal price, Integer numItems) {
        this.productId = productId;
        this.size = size;
        this.color = color;
        this.price = price;
        this.numItems = numItems;
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

    public Integer getNumItems() {
        return this.numItems;
    }

    public void setNumItems(Integer numItems) {
        this.numItems = numItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CartLine cartLine = (CartLine) o;
        return Objects.equals(this.productId, cartLine.productId) && Objects.equals(this.size, cartLine.size) && Objects.equals(this.color, cartLine.color);
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.productId, this.size, this.color);
    }

    @Override
    public String toString() {
        return "CartLine{" + "productId='" + this.productId + '\'' + ", size='" + this.size + '\'' + ", color='" + this.color + '\'' + ", price=" + this.price + ", numItems=" + this.numItems + '}';
    }

    public void increaseNumItems() {
        this.numItems++;
    }
}
