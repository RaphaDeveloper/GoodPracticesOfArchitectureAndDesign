package observation.generators;

import observation.formatters.InvoiceNumberFormatter;

import java.util.List;

public class ObservationGenerator {

    private final String TEXT_TEMPLATE_FOR_ONE_INVOICE = "Fatura da nota fiscal de simples remessa: %s.";
    private final String TEXT_TEMPLATE_FOR_MULTIPLE_INVOICES = "Fatura das notas fiscais de simples remessa: %s.";

    public String generateFromInvoiceNumbers(List<Integer> invoiceNumbers) {

        if (isThereAnyInvoiceNumbers(invoiceNumbers))
        {
            return generateObservation(invoiceNumbers);
        }

        return "";
    }

    private boolean isThereAnyInvoiceNumbers(List<Integer> invoiceNumbers) {
        return invoiceNumbers != null && !invoiceNumbers.isEmpty();
    }

    private String generateObservation(List<Integer> invoiceNumbers) {
        String textTemplate = getTextTemplateBasedOnAmountOfInvoiceNumbers(invoiceNumbers.size());

        String formattedInvoiceNumbers = formatInvoiceNumbers(invoiceNumbers);

        return String.format(textTemplate, formattedInvoiceNumbers);
    }

    private String getTextTemplateBasedOnAmountOfInvoiceNumbers(int amountOfInvoiceNumbers) {
        if (amountOfInvoiceNumbers > 1) {
            return TEXT_TEMPLATE_FOR_MULTIPLE_INVOICES;
        }

        return TEXT_TEMPLATE_FOR_ONE_INVOICE;
    }

    private String formatInvoiceNumbers(List<Integer> invoiceNumbers) {
        InvoiceNumberFormatter formatter = new InvoiceNumberFormatter();

        return formatter.formatInvoiceNumbers(invoiceNumbers);
    }
}
