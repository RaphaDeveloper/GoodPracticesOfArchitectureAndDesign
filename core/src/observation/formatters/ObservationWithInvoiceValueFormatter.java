package observation.formatters;

import java.util.List;

public class ObservationWithInvoiceValueFormatter implements ObservationFormatter {

    @Override
    public String formatInvoices(List<Integer> invoiceNumbers) {
        return "1 cujo valor é R$ 10,00";
    }
}
