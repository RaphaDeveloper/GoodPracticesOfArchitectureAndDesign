package observation.generators;

import invoice.Invoice;
import invoice.InvoiceRepository;
import java.util.Iterator;
import java.util.List;

public abstract class ObservationGeneratorBase implements ObservationGenerator {

    private InvoiceRepository invoiceRepository;

    public ObservationGeneratorBase(InvoiceRepository invoiceRepository) {

        this.invoiceRepository = invoiceRepository;
    }

    private final String TEXT_TEMPLATE_FOR_ONE_INVOICE = "Fatura da nota fiscal de simples remessa: %s";
    private final String TEXT_TEMPLATE_FOR_MULTIPLE_INVOICES = "Fatura das notas fiscais de simples remessa: %s";

    @Override
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

    protected String generateObservation(List<Invoice> invoices) {

        String textTemplate = getTextTemplateBasedOnAmountOfInvoiceNumbers(invoices.size());

        String textOfInvoices = getFormattedTextOfInvoices(invoices);

        return String.format(textTemplate, textOfInvoices);
    }

    private String getTextTemplateBasedOnAmountOfInvoiceNumbers(int amountOfInvoices) {
        if (amountOfInvoices > 1) {
            return TEXT_TEMPLATE_FOR_MULTIPLE_INVOICES;
        }

        return TEXT_TEMPLATE_FOR_ONE_INVOICE;
    }

    private String getFormattedTextOfInvoices(List<Invoice> invoices) {
        StringBuilder observationBuilder = new StringBuilder();

        for (Iterator<Invoice> invoiceIterator = invoices.iterator(); invoiceIterator.hasNext();) {
            Invoice invoice = invoiceIterator.next();
            boolean isTheLastInvoice = !invoiceIterator.hasNext();
            boolean someInvoiceHasAlreadyBeenFormatted = observationBuilder.length() > 0;

            String separator = getSeparator(isTheLastInvoice, someInvoiceHasAlreadyBeenFormatted);

            observationBuilder.append(separator + getTextOfInvoice(invoice));
        }

        return String.format("%s.", observationBuilder);
    }

    private String getSeparator(boolean isTheLastInvoice, boolean someInvoiceHasAlreadyBeenFormatted) {
        String separator = "";

        if (someInvoiceHasAlreadyBeenFormatted) {
            if(isTheLastInvoice) {
                separator = " e ";
            } else {
                separator = ", ";
            }
        }

        return separator;
    }

    protected abstract String getTextOfInvoice(Invoice invoice);
}
