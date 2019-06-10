package observation.generators;

import invoice.Invoice;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ObservationWithValueGeneratorTest {

    private ObservationWithValueGenerator generator;

    @Before
    public void setup() {
        generator = new ObservationWithValueGenerator();
    }

    @Test
    public void should_return_empty_text_for_no_invoice_number_provided() {
        String observation = generator.generateFromInvoices(Arrays.asList());

        assertThat(observation, is(""));
    }

    @Test
    public void should_return_empty_text_for_invoice_numbers_provided_equals_to_null() {
        String observation = generator.generateFromInvoices(null);

        assertThat(observation, is(""));
    }

    @Test
    public void should_be_possible_to_generate_observation_with_the_invoice_value_for_one_invoice_number_provided() {
        Invoice invoice = new Invoice(1, 10d);

        String observation = generator.generateFromInvoices(Arrays.asList(invoice));

        assertThat(observation, is("Fatura da nota fiscal de simples remessa: 1 cujo valor é R$ 10,00."));
    }

    @Test
    public void should_be_possible_to_generate_observation_with_the_invoice_value_for_more_than_one_invoice_number_provided() {
        Invoice invoice1 = new Invoice(1, 10d);
        Invoice invoice2 = new Invoice(2, 20d);
        Invoice invoice3 = new Invoice(3, 30d);

        String observation = generator.generateFromInvoices(Arrays.asList(invoice1, invoice2, invoice3));

        assertThat(observation, is("Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 10,00, 2 cujo valor é R$ 20,00 e 3 cujo valor é R$ 30,00. Total = R$ 60,00."));
    }
}
