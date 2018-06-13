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
        this.addArticle(article, 1);
    }

    public void addArticle(Article article, int numItems) {
        if (!increaseItemsExistingCartLine(article, numItems)) {
            addNewCartLine(article, numItems);
        }
    }

    private void addNewCartLine(Article article, int numItems) {
        this.cartLines.add(new CartLine(article, numItems));
        increaseTotals(article, numItems);
    }

    private boolean increaseItemsExistingCartLine(Article article, int numItems) {
        for (CartLine line : this.cartLines) {
            if (line.getArticle().equals(article)) {
                line.increaseNumItemsWith(numItems);
                increaseTotals(article, numItems);
                return true;
            }
        }

        return false;
    }

    private void increaseTotals(Article article, int numItems) {
        this.numItems += numItems;
        this.totalPrice = this.totalPrice.add(article.getPrice().multiply(new BigDecimal(numItems)));
    }
}
