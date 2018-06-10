package es.develex.infrastructure.adapter;

import es.develex.domain.model.ShoppingCart;
import es.develex.domain.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public ShoppingCartRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ShoppingCart findById(String id) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        return (ShoppingCart) values.get(getRedisKey(id));
    }

    @Override
    public void save(ShoppingCart shoppingCart) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(getRedisKey(shoppingCart.getId()), shoppingCart);
    }

    private String getRedisKey(String id) {
        return id + "@shoppincart";
    }
}
