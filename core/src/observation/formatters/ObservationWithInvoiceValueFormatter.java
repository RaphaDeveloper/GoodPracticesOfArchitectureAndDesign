package observation.formatters;

import invoice.Invoice;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;

public class ObservationWithInvoiceValueFormatter implements ObservationFormatter {

    private StringBuilder observationBuilder;

    @Override
    public String formatInvoices(List<Invoice> invoices) {
        observationBuilder = new StringBuilder();

        for (Iterator<Invoice> invoiceIterator = invoices.iterator(); invoiceIterator.hasNext();) {
            formatInvoice(invoiceIterator.next(), !invoiceIterator.hasNext());
        }

        String formattedInvoices = String.format("%s.", observationBuilder);

        if (invoices.size() > 1) {
            formattedInvoices += String.format(" Total = %s.", formatValueToCurrency(getTotalValueOfInvoices(invoices)));
        }

        return formattedInvoices;
    }

    private void formatInvoice(Invoice invoice, boolean isTheLastInvoice) {
        String formattedInvoice = String.format("%s cujo valor é %s", invoice.getNumber(), formatValueToCurrency(invoice.getValue()));

        String separator = "";

        if (someInvoiceHasAlreadyBeenFormatted()) {
            if(isTheLastInvoice) {
                separator = " e ";
            } else {
                separator = ", ";
            }
        }

        observationBuilder.append(separator + formattedInvoice);
    }

    private boolean someInvoiceHasAlreadyBeenFormatted() {
        return observationBuilder.length() > 0;
    }

    private double getTotalValueOfInvoices(List<Invoice> invoices) {
        double value = 0;

        for (Invoice invoice : invoices) {
            value += invoice.getValue();
        }

        return value;
    }

    public String formatValueToCurrency(double value) {
        return NumberFormat.getCurrencyInstance().format(value);
    }
}
