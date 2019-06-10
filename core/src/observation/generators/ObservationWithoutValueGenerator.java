package observation.generators;

import invoice.*;

public class ObservationWithoutValueGenerator extends ObservationGeneratorBase {

    public ObservationWithoutValueGenerator(InvoiceRepository invoiceRepository) {
        super(invoiceRepository);
    }

    @Override
    protected String getTextOfInvoice(Invoice invoice) {
        return invoice.getNumber().toString();
    }
}
