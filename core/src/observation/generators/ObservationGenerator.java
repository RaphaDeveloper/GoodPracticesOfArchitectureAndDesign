package observation.generators;

import java.util.List;

public interface ObservationGenerator {

    String generateFromInvoiceNumbers(List<Integer> invoiceNumbers);
}
