package observation.generators;

import invoice.Invoice;

import java.util.List;

public interface ObservationGenerator {

    String generateFromInvoices(List<Invoice> invoices);
}
