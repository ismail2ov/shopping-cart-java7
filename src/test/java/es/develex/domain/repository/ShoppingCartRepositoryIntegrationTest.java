package es.develex.domain.repository;

import es.develex.domain.model.CartLine;
import es.develex.domain.model.ShoppingCart;
import es.develex.infrastructure.adapter.ShoppingCartRepositoryImpl;
import es.develex.infrastructure.configuration.RedisConfiguration;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisConfiguration.class, ShoppingCartRepositoryImpl.class})
public class ShoppingCartRepositoryIntegrationTest {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Test
    public void when_save_cart_then_available_on_retrieve() {
        ShoppingCart shoppingCart = getShoppingCart();
        this.shoppingCartRepository.save(shoppingCart);

        ShoppingCart retrievedShoppingCart = this.shoppingCartRepository.findById(shoppingCart.getId());

        assertRetrievedData(shoppingCart, retrievedShoppingCart);
    }

    private ShoppingCart getShoppingCart() {
        List<CartLine> cartLines = new ArrayList<>();
        cartLines.add(new CartLine("001", "002", "003", new BigDecimal(12.36), 2));
        cartLines.add(new CartLine("102", "203", "304", new BigDecimal(6.23), 3));

        Integer numItems = 0;
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartLine line : cartLines) {
            numItems += line.getNumItems();
            totalPrice = totalPrice.add(line.getPrice().multiply(new BigDecimal(line.getNumItems())));
        }

        ShoppingCart shoppingCart = new ShoppingCart("001", numItems, totalPrice);
        shoppingCart.setCartLines(cartLines);

        return shoppingCart;
    }

    private void assertRetrievedData(ShoppingCart shoppingCart, ShoppingCart retrievedShoppingCart) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(retrievedShoppingCart.getId()).isEqualTo(shoppingCart.getId());
        softAssertions.assertThat(retrievedShoppingCart.getNumItems()).isEqualTo(shoppingCart.getNumItems());
        softAssertions.assertThat(retrievedShoppingCart.getTotalPrice()).isEqualTo(shoppingCart.getTotalPrice());
        softAssertions.assertThat(retrievedShoppingCart.getCartLines().size()).isEqualTo(shoppingCart.getCartLines().size());
        softAssertions.assertAll();
    }
}