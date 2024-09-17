package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.CartFactory;
import ca.ulaval.glo4002.mockexercise.do_not_edit.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

        InvoiceGenerator invoiceGenerator = mock(InvoiceGenerator.class);

        service = new StartByTestingThis(cartFactory, productRepository, invoiceGenerator);
    }

    @Test
    public void givenABC_whenWhat_thenOk() {




    }

    @Test
    public void givenNullClientEmail_whenOneClickBuy_thenThrowsException() {
        String PRODUCT_SKU = "12345678";

        assertThrows(CustomException.class, () -> service.oneClickBuy(null, PRODUCT_SKU));
    }
    @Test
    public void givenEmptyClientEmail_whenOneClickBuy_thenThrowsException() {
        String EMPTY_CLIENT_EMAIL = "";
        String PRODUCT_SKU = "12345678";

        assertThrows(CustomException.class, () -> service.oneClickBuy(EMPTY_CLIENT_EMAIL, PRODUCT_SKU));
    }
    @Test
    public void givenInvalidClientEmail_whenOneClickBuy_thenThrowsException() {
        String INVALID_CLIENT_EMAIL = "invalid email";
        String PRODUCT_SKU = "12345678";

        assertThrows(CustomException.class, () -> service.oneClickBuy(INVALID_CLIENT_EMAIL, PRODUCT_SKU));
    }

    @Test
    public void givenNullProductSku_whenOneClickBuy_thenThrowsException() {
        String CLIENT_EMAIL = "perfectly@valid.email";

        assertThrows(CustomException.class, () -> service.oneClickBuy(CLIENT_EMAIL, null));
    }

    @Test
    public void givenEmptyProductSku_whenOneClickBuy_thenThrowsException() {
        String CLIENT_EMAIL = "perfectly@valid.email";
        String EMPTY_PRODUCT_SKU = "";

        assertThrows(CustomException.class, () -> service.oneClickBuy(CLIENT_EMAIL, EMPTY_PRODUCT_SKU));
    }


}
