package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.CartFactory;
import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import ca.ulaval.glo4002.mockexercise.do_not_edit.InvoiceLine;
import ca.ulaval.glo4002.mockexercise.do_not_edit.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StartByTestingThis {
    final private CartFactory cartFactory;
    final private ProductRepository productRepository;
    private final InvoiceGenerator invoiceGenerator;
    public StartByTestingThis(CartFactory cartFactory, ProductRepository productRepository, InvoiceGenerator invoiceGenerator) {
        this.cartFactory = cartFactory;
        this.productRepository = productRepository;
        this.invoiceGenerator = invoiceGenerator;
    }

    public Invoice oneClickBuy(String clientEmail, String productSku) throws CustomException {

        if(clientEmail == null || clientEmail.isEmpty()
                || !Pattern.matches("[a-zA-Z0-9\\-_]+@[a-z0-9A-Z]+\\.[a-z]+", clientEmail)){
            throw new CustomException("invalid client email");
        }
        else if(productSku == null || productSku.isEmpty()){
            throw new CustomException("invalid sku (stock keeping unit)");
        }

        // Étape 1 : Créer le cart avec le CartFactory
        Cart cart = cartFactory.create(clientEmail);

        // Étape 2 : Trouver le produit avec le ProductRepository
        Product product = productRepository.findBySku(productSku);

        // Étape 3 : Ajouter le produit au cart
        cart.addProduct(product);

        // Étape 4 : Pour chaque item du cart, ajouter une ligne sur l'invoice
        Invoice invoice = invoiceGenerator.generate(clientEmail, cart.getProducts());

        // Étape 5 : Retourner l'invoice
        return invoice;
    }
}
