package observation.formatters;

import java.util.Iterator;
import java.util.List;

public class SimpleObservationFormatter implements ObservationFormatter {

    private StringBuilder observationBuilder;

    @Override
    public String formatInvoices(List<Integer> invoiceNumbers) {
        observationBuilder = new StringBuilder();

        for (Iterator<Integer> invoiceNumbersIterator = invoiceNumbers.iterator(); invoiceNumbersIterator.hasNext();) {
            formatInvoice(invoiceNumbersIterator.next(), !invoiceNumbersIterator.hasNext());
        }

        return observationBuilder.toString();
    }

    private void formatInvoice(Integer invoiceNumber, boolean isTheLastInvoice) {
        String separator = "";

        if (someInvoiceHasAlreadyBeenFormatted()) {
            if(isTheLastInvoice) {
                separator = " e ";
            } else {
                separator = ", ";
            }
        }

        observationBuilder.append(separator + invoiceNumber.toString());
    }

    private boolean someInvoiceHasAlreadyBeenFormatted() {
        return observationBuilder.length() > 0;
    }
}
