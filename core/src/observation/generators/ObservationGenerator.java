package observation.generators;

import invoice.Invoice;
import observation.formatters.ObservationFormatter;
import java.util.ArrayList;
import java.util.List;

public class ObservationGenerator {

    private ObservationFormatter formatter;

    public ObservationGenerator(ObservationFormatter formatter) {

        this.formatter = formatter;
    }

    private final String TEXT_TEMPLATE_FOR_ONE_INVOICE = "Fatura da nota fiscal de simples remessa: %s";
    private final String TEXT_TEMPLATE_FOR_MULTIPLE_INVOICES = "Fatura das notas fiscais de simples remessa: %s";

    public String generateFromInvoiceNumbers(List<Integer> invoiceNumbers) {

        if (isThereAnyInvoiceNumbers(invoiceNumbers)) {
            List<Invoice> invoices = getInvoicesByNumbers(invoiceNumbers);

            return generateObservation(invoices);
        }

        return "";
    }

    private boolean isThereAnyInvoiceNumbers(List<Integer> invoiceNumbers) {
        return invoiceNumbers != null && !invoiceNumbers.isEmpty();
    }

    private List<Invoice> getInvoicesByNumbers(List<Integer> invoiceNumbers) {
        double value = 0;

        List<Invoice> invoices = new ArrayList<>();

        for (int invoiceNumber : invoiceNumbers) {
            value += 10;

            invoices.add(new Invoice(invoiceNumber, value));
        }

        return invoices;
    }

    private String generateObservation(List<Invoice> invoices) {

        String textTemplate = getTextTemplateBasedOnAmountOfInvoiceNumbers(invoices.size());

        String formattedInvoiceNumbers = formatter.formatInvoices(invoices);

        return String.format(textTemplate, formattedInvoiceNumbers);
    }

    private String getTextTemplateBasedOnAmountOfInvoiceNumbers(int amountOfInvoices) {
        if (amountOfInvoices > 1) {
            return TEXT_TEMPLATE_FOR_MULTIPLE_INVOICES;
        }

        return TEXT_TEMPLATE_FOR_ONE_INVOICE;
    }
}
