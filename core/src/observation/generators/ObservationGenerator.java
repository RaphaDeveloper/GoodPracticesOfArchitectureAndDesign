package observation.generators;

import invoice.Invoice;
import invoice.InvoiceRepository;
import observation.formatters.Formatter;
import java.util.List;

public class ObservationGenerator {

    private Formatter formatter;
    private InvoiceRepository invoiceRepository;

    public ObservationGenerator(Formatter formatter, InvoiceRepository invoiceRepository) {

        this.formatter = formatter;
        this.invoiceRepository = invoiceRepository;
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
        return invoiceRepository.getByNumbers(invoiceNumbers);
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
