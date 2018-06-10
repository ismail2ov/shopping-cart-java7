package es.develex.domain.repository;

import es.develex.domain.model.ShoppingCart;
import org.springframework.data.redis.core.RedisTemplate;

public interface ShoppingCartRepository {
    ShoppingCart findById(String id);

    void save(ShoppingCart shoppingCart);
}
