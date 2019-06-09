package observation.formatters;

import invoice.Invoice;

import java.util.Iterator;
import java.util.List;

public class SimpleObservationFormatter implements ObservationFormatter {

    private StringBuilder observationBuilder;

    @Override
    public String formatInvoices(List<Invoice> invoice) {
        observationBuilder = new StringBuilder();

        for (Iterator<Invoice> invoiceIterator = invoice.iterator(); invoiceIterator.hasNext();) {
            formatInvoice(invoiceIterator.next(), !invoiceIterator.hasNext());
        }

        return String.format("%s.", observationBuilder);
    }

    private void formatInvoice(Invoice invoice, boolean isTheLastInvoice) {
        String formattedInvoice = invoice.getNumber().toString();
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
}
