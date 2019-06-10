package observation.generators;

import invoice.InvoiceRepository;
import observation.invoice.InvoiceRepositoryStub;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ObservationWithValueGeneratorTest {

    private ObservationWithValueGenerator generator;

    @Before
    public void setup() {
        InvoiceRepository invoiceRepository = new InvoiceRepositoryStub();

        generator = new ObservationWithValueGenerator(invoiceRepository);
    }

    @Test
    public void should_be_possible_to_generate_observation_with_the_invoice_value_for_one_invoice_number_provided() {
        String observation = generator.generateFromInvoiceNumbers(Arrays.asList(1));

        assertThat(observation, is("Fatura da nota fiscal de simples remessa: 1 cujo valor é R$ 10,00."));
    }

    @Test
    public void should_be_possible_to_generate_observation_with_the_invoice_value_for_more_than_one_invoice_number_provided() {
        String observation = generator.generateFromInvoiceNumbers(Arrays.asList(1, 2));

        assertThat(observation, is("Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 10,00 e 2 cujo valor é R$ 20,00. Total = R$ 30,00."));
    }
}
