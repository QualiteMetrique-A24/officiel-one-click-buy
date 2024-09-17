package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import ca.ulaval.glo4002.mockexercise.do_not_edit.InvoiceLine;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class InvoiceGenerator {
    BiFunction<String,Double,InvoiceLine> makeInvoiceLine = InvoiceLine::new;

    public InvoiceGenerator(){

    }
    public Invoice generate(String clientEmail, List<Product> products){
        List<InvoiceLine> lines = products.stream()
                .map(product_stream -> makeInvoiceLine.apply(product_stream.getName(), product_stream.getPrice()))
                .collect(Collectors.toList());

        // Étape 5 : Retourner l'invoice
        return new Invoice(clientEmail, lines);
    }
}
