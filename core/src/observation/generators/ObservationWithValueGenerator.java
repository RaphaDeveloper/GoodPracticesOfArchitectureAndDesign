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

        String observantion = super.generateObservation(invoices);

        if (isThereMoreThanOneInvoice(invoices)) {
            observantion += getTheTextWithTotalValueOfInvoices(invoices);
        }

        return observantion;
    }

    private boolean isThereMoreThanOneInvoice(List<Invoice> invoices) {
        return invoices.size() > 1;
    }

    private String getTheTextWithTotalValueOfInvoices(List<Invoice> invoices) {
        return String.format(" Total = %s.", formatValueToCurrency(getTotalValueOfInvoices(invoices)));
    }

    private double getTotalValueOfInvoices(List<Invoice> invoices) {
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
