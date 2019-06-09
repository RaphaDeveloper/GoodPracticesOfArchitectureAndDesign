package observation.formatters;

import invoice.Invoice;

import java.util.List;

public interface ObservationFormatter {

    String formatInvoices(List<Invoice> invoices);
}
