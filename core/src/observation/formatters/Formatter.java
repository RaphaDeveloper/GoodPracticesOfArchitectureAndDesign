package observation.formatters;

import invoice.Invoice;

import java.util.List;

public interface Formatter {

    String formatInvoices(List<Invoice> invoices);
}
