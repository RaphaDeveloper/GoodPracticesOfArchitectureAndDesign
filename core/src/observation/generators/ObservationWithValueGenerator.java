package observation.generators;

import invoice.*;
import java.text.NumberFormat;
import java.util.List;

public class ObservationWithValueGenerator extends ObservationGeneratorBase {

    public ObservationWithValueGenerator(InvoiceRepository invoiceRepository) {
        super(invoiceRepository);
    }

    @Override
    protected String getTextOfInvoice(Invoice invoice) {
        return String.format("%s cujo valor é %s", invoice.getNumber(), formatValueToCurrency(invoice.getValue()));
    }

    @Override
    protected String generateObservation(List<Invoice> invoices) {

        String observation = super.generateObservation(invoices);

        if (isThereMoreThanOneInvoice(invoices)) {
            observation += getTheTotalValueTextOfInvoices(invoices);
        }

        return observation;
    }

    private boolean isThereMoreThanOneInvoice(List<Invoice> invoices) {
        return invoices.size() > 1;
    }

    private String getTheTotalValueTextOfInvoices(List<Invoice> invoices) {
        double totalValue = getTheTotalValueOfInvoices(invoices);

        return String.format(" Total = %s.", formatValueToCurrency(totalValue));
    }

    private double getTheTotalValueOfInvoices(List<Invoice> invoices) {
        double value = 0;

        for (Invoice invoice : invoices) {
            value += invoice.getValue();
        }

        return value;
    }

    private String formatValueToCurrency(double value) {
        return NumberFormat.getCurrencyInstance().format(value);
    }
}
