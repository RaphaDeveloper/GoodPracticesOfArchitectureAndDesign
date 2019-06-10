package observation.generators;

import invoice.*;

public class ObservationWithoutValueGenerator extends ObservationGeneratorBase {

    @Override
    protected String getTextOfInvoice(Invoice invoice) {
        return invoice.getNumber().toString();
    }
}
