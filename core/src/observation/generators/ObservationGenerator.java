package observation.generators;

import java.util.Iterator;
import java.util.List;

public class ObservationGenerator {
    private final String TEXT_TEMPLATE_FOR_ONE_INVOICE = "Fatura da nota fiscal de simples remessa: %s.";
    private final String TEXT_TEMPLATE_FOR_MULTIPLE_INVOICES = "Fatura das notas fiscais de simples remessa: %s.";

    public String generateFromInvoiceNumbers(List<Integer> invoiceNumbers) {

        if (isThereAnyInvoiceNumbers(invoiceNumbers))
        {
            //String templateText = getTextTemplateBasedOnAmountOfInvoiceNumbers(invoiceNumbers.size());

            return retornaCodigos(invoiceNumbers);
        }

        return "";
    }

    private boolean isThereAnyInvoiceNumbers(List<Integer> invoiceNumbers) {
        return invoiceNumbers != null && !invoiceNumbers.isEmpty();
    }

    private String getTextTemplateBasedOnAmountOfInvoiceNumbers(int amountOfInvoiceNumbers) {
        if (amountOfInvoiceNumbers > 1) {
            return TEXT_TEMPLATE_FOR_MULTIPLE_INVOICES;
        }

        return TEXT_TEMPLATE_FOR_ONE_INVOICE;
    }

    //Cria observa��o
    private String retornaCodigos(List lista) {
        String observation = getTextTemplateBasedOnAmountOfInvoiceNumbers(lista.size());

        //Acha separador
        StringBuilder cod = new StringBuilder();
        for (Iterator<Integer> iterator = lista.iterator(); iterator.hasNext();) {
            Integer c = iterator.next();
            String s = "";
            if( cod.toString() == null || cod.toString().length() <= 0 )
                s =  "";
            else if( iterator.hasNext() )
                s =  ", ";
            else
                s =  " e ";

            cod.append(s + c);
        }

        return String.format(observation, cod);
    }
}
