package es.develex.domain.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@RedisHash("CartLine")
public class CartLine implements Serializable {
    private static final long serialVersionUID = 1L;
    private Article article;
    private Integer numItems;

    private CartLine() {
    }

    public CartLine(Article article, Integer numItems) {
        this.article = article;
        this.numItems = numItems;
    }

    public Article getArticle() {
        return this.article;
    }

    private void setArticle(Article article) {
        this.article = article;
    }

    public Integer getNumItems() {
        return this.numItems;
    }

    private void setNumItems(Integer numItems) {
        this.numItems = numItems;
    }

    public void increaseNumItems() {
        this.numItems++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CartLine))
            return false;
        CartLine cartLine = (CartLine) o;
        return Objects.equals(this.article, cartLine.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.article);
    }

    public void increaseNumItemsWith(int increaseNum) {
        this.numItems += increaseNum;
    }
}
