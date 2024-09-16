package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.CartFactory;
import ca.ulaval.glo4002.mockexercise.do_not_edit.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StartByTestingThisTest {

    private StartByTestingThis service;

    final String EMAIL = "Email";
    final String PRODUCT_SKU = "sku_data";

    @BeforeEach
    public void setUp() {

        Product mockProduct = mock(Product.class);

        Cart cart = mock(Cart.class);
        // GetProduct
        // addProduct
        CartFactory cartFactory = mock(CartFactory.class);
//        when(cartFactory.create(EMAIL)).thenReturn(cart);

        ProductRepository productRepository = mock(ProductRepository.class);
//        when(productRepository.findBySku(PRODUCT_SKU)).thenReturn(mockProduct);
        service = new StartByTestingThis(cartFactory, productRepository);
    }

    @Test
    public void givenABC_whenWhat_thenOk() {

    }

}
