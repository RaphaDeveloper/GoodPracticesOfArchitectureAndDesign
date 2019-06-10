package observation.generators;

import invoice.InvoiceRepository;

public class ObservationGeneratorFactory implements IObservationGeneratorFactory {

    private InvoiceRepository invoiceRepository;

    public ObservationGeneratorFactory(InvoiceRepository invoiceRepository) {

        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public ObservationGenerator createObservationGenerator(ObservationGeneratorType type) {
        switch (type) {
            case WITH_VALUE:
                return new ObservationWithValueGenerator(invoiceRepository);
            case WITHOUT_VALUE:
                return new ObservationWithoutValueGenerator(invoiceRepository);
            default:
                return null;
        }
    }
}