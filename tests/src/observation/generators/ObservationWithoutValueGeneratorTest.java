package observation.generators;

import invoice.Invoice;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ObservationWithoutValueGeneratorTest {

    private ObservationWithoutValueGenerator generator;

    @Before
    public void setup() {
        generator = new ObservationWithoutValueGenerator();
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
    public void should_be_possible_to_generate_observation_for_one_invoice_number_provided() {
        Invoice invoice = new Invoice(1, 0d);

        String observation = generator.generateFromInvoices(Arrays.asList(invoice));

        assertThat(observation, is("Fatura da nota fiscal de simples remessa: 1."));
    }

    @Test
    public void should_be_possible_to_generate_observation_for_more_than_one_invoice_number_provided() {
        Invoice invoice1 = new Invoice(1, 0d);
        Invoice invoice2 = new Invoice(2, 0d);
        Invoice invoice3 = new Invoice(3, 0d);
        Invoice invoice4 = new Invoice(4, 0d);
        Invoice invoice5 = new Invoice(5, 0d);

        List<Invoice> invoices = Arrays.asList(invoice1, invoice2, invoice3, invoice4, invoice5);

        String observation = generator.generateFromInvoices(invoices);

        assertThat(observation, is("Fatura das notas fiscais de simples remessa: 1, 2, 3, 4 e 5."));
    }
}
