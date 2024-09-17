package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import ca.ulaval.glo4002.mockexercise.do_not_edit.InvoiceLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceGeneratorTest {
    private final static String CLIENT_EMAIL = "test@abc.com";
    private final static String PRODUCT_NAME = "PATATE";
    private final static double PRODUCT_PRICE = 10.0;
    private final static Invoice EXPECTED_INVOICE = mock(Invoice.class);
    private final static List<Product> PRODUCTS = List.of(
            // TODO: Considering this is a data object with only setter/getter, we use the object itself, but should we have mocked it instead?
            new Product("0", PRODUCT_NAME, PRODUCT_PRICE)
    );

    private InvoiceGenerator invoiceGenerator;

    @Mock
    private BiFunction<String, Double, InvoiceLine> mockInvoiceLineMaker;

    @Mock
    private BiFunction<String, List<InvoiceLine>, Invoice> mockInvoiceMaker;

    @BeforeEach
    public void setUp() {
        invoiceGenerator = new InvoiceGenerator();
        invoiceGenerator.makeInvoiceLine = mockInvoiceLineMaker;
        invoiceGenerator.makeInvoice = mockInvoiceMaker;
    }

    @Test
    public void givenEmailAndProduct_whenGeneratingInvoice_thenReturnsInvoice() {
        InvoiceLine invoiceLine = makeInvoiceLine();
        when(mockInvoiceLineMaker.apply(PRODUCT_NAME, PRODUCT_PRICE)).thenReturn(invoiceLine);
        when(mockInvoiceMaker.apply(
                eq(CLIENT_EMAIL),
                argThat(list -> list.equals(List.of(invoiceLine))))
        ).thenReturn(EXPECTED_INVOICE);

        Invoice invoice = invoiceGenerator.generate(CLIENT_EMAIL, PRODUCTS);

        assertEquals(EXPECTED_INVOICE, invoice);
    }

    // TODO: Test when two products ?

    private InvoiceLine makeInvoiceLine() {
        return mock(InvoiceLine.class);
    }

}