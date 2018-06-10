package es.develex.domain.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@RedisHash("ShoppingCart")
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private List<CartLine> cartLines;
    private Integer numItems;
    private BigDecimal totalPrice;

    public ShoppingCart() {
    }

    public ShoppingCart(String id, Integer numItems, BigDecimal totalPrice) {
        this.id = id;
        this.numItems = numItems;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    public Integer getNumItems() {
        return numItems;
    }

    public void setNumItems(Integer numItems) {
        this.numItems = numItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" + "id='" + id + '\'' + ", numItems=" + numItems + ", totalPrice=" + totalPrice + '}';
    }
}
