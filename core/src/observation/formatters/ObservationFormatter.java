package observation.formatters;

import java.util.List;

public interface ObservationFormatter {

    String formatInvoices(List<Integer> invoiceNumbers);
}
