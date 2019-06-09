package observation.formatters;

import java.util.Iterator;
import java.util.List;

public class InvoiceNumberFormatter {

    private final StringBuilder formattedInvoiceNumberBuilder = new StringBuilder();

    public String formatInvoiceNumbers(List<Integer> invoiceNumbers) {
        for (Iterator<Integer> invoiceNumbersIterator = invoiceNumbers.iterator(); invoiceNumbersIterator.hasNext();) {
            formatInvoiceNumber(invoiceNumbersIterator.next(), !invoiceNumbersIterator.hasNext());
        }

        return formattedInvoiceNumberBuilder.toString();
    }

    private void formatInvoiceNumber(Integer invoiceNumber, boolean isTheLastNumber) {
        String separator = "";

        if (someNumberHasAlreadyBeenFormatted()) {
            if(isTheLastNumber) {
                separator = " e ";
            } else {
                separator = ", ";
            }
        }

        formattedInvoiceNumberBuilder.append(separator + invoiceNumber.toString());
    }

    private boolean someNumberHasAlreadyBeenFormatted() {
        return formattedInvoiceNumberBuilder.length() > 0;
    }
}
