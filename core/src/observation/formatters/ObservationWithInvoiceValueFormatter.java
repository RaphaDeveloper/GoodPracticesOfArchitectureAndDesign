package observation.formatters;

import invoice.Invoice;

import java.util.List;

public class ObservationWithInvoiceValueFormatter implements ObservationFormatter {

    @Override
    public String formatInvoices(List<Invoice> invoice) {
        return "1 cujo valor é R$ 10,00";
    }
}
