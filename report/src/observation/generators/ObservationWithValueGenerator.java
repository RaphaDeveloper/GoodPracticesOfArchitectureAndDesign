package observation.generators;

import currency.CurrencyFormatter;
import invoice.Invoice;

import java.util.List;

public class ObservationWithValueGenerator extends ObservationGeneratorBase {

    @Override
    protected String getTextOfInvoice(Invoice invoice) {
        return String.format("%s cujo valor é %s", invoice.getNumber(), CurrencyFormatter.format(invoice.getValue()));
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

        return String.format(" Total = %s.", CurrencyFormatter.format(totalValue));
    }

    private double getTheTotalValueOfInvoices(List<Invoice> invoices) {
        double value = 0;

        for (Invoice invoice : invoices) {
            value += invoice.getValue();
        }

        return value;
    }
}
