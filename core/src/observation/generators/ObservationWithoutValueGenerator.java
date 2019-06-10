package observation.generators;

import invoice.Invoice;
import invoice.InvoiceRepository;

public class ObservationWithoutValueGenerator extends ObservationGeneratorBase {

    public ObservationWithoutValueGenerator(InvoiceRepository invoiceRepository) {
        super(invoiceRepository);
    }

    @Override
    protected String getTextOfInvoice(Invoice invoice) {
        return invoice.getNumber().toString();
    }
}
