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

    private ShoppingCart() {
    }

    public ShoppingCart(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public List<CartLine> getCartLines() {
        return this.cartLines;
    }

    private void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    public Integer getNumItems() {
        return this.numItems;
    }

    private void setNumItems(Integer numItems) {
        this.numItems = numItems;
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice.setScale(2, BigDecimal.ROUND_UP);
    }

    private void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" + "id='" + this.id + '\'' + ", numItems=" + this.numItems + ", totalPrice=" + this.totalPrice + '}';
    }

    public void addArticle(Article article) {
        int index = this.cartLines.indexOf(article);
        if (index > -1) {
            this.cartLines.get(index).increaseNumItems();
        } else {
            this.cartLines.add(new CartLine(article, 1));
        }

        this.numItems++;
        this.totalPrice = this.totalPrice.add(article.getPrice());

    }
}
