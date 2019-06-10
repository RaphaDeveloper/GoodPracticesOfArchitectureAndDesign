package observation.generators;

import invoice.Invoice;

public class ObservationWithoutValueGenerator extends ObservationGeneratorBase {

    @Override
    protected String getTextOfInvoice(Invoice invoice) {
        return invoice.getNumber().toString();
    }
}
