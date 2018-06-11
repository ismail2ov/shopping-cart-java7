package es.develex.domain.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RedisHash("ShoppingCart")
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private List<CartLine> cartLines = new ArrayList<>();
    private Integer numItems = 0;
    private BigDecimal totalPrice = BigDecimal.ZERO;

    public ShoppingCart() {
    }

    public ShoppingCart(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartLine> getCartLines() {
        return this.cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;

        this.numItems = 0;
        this.totalPrice = BigDecimal.ZERO;

        for (CartLine line : cartLines) {
            this.numItems += line.getNumItems();
            this.totalPrice = this.totalPrice.add(line.getPrice().multiply(new BigDecimal(line.getNumItems())));
        }
    }

    public Integer getNumItems() {
        return this.numItems;
    }

    public void setNumItems(Integer numItems) {
        this.numItems = numItems;
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice.setScale(2, BigDecimal.ROUND_UP);
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" + "id='" + this.id + '\'' + ", numItems=" + this.numItems + ", totalPrice=" + this.totalPrice + '}';
    }
}
