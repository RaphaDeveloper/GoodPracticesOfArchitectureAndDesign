package observation.generators;

import java.util.Iterator;
import java.util.List;

public class ObservationGenerator {
    private final String INITIAL_TEXT_FOR_ONE_INVOICE = "Fatura da nota fiscal de simples remessa: ";
    private final String INITIAL_TEXT_FOR_MULTIPLE_INVOICES = "Fatura das notas fiscais de simples remessa: ";

    //Gera observa��es, com texto pre-definido, incluindo os n�meros, das notas fiscais, recebidos no par�metro
    public String geraObservacao(List lista)
    {
        if (lista != null && !lista.isEmpty())
        {
            return retornaCodigos(lista) + ".";
        }
        return "";
    }

    //Cria observa��o
    private String retornaCodigos(List lista) {
        String observation = "";

        if (lista.size() >= 2) {
            observation = INITIAL_TEXT_FOR_MULTIPLE_INVOICES;
        } else {
            observation = INITIAL_TEXT_FOR_ONE_INVOICE;
        }

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

        return observation + cod;
    }
}
