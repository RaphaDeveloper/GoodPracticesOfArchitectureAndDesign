package observation.generators;

import invoice.InvoiceRepository;
import observation.invoice.InvoiceRepositoryStub;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ObservationWithoutValueGeneratorTest {

    private ObservationWithoutValueGenerator generator;

    @Before
    public void setup() {
        InvoiceRepository invoiceRepository = new InvoiceRepositoryStub();

        generator = new ObservationWithoutValueGenerator(invoiceRepository);
    }

    @Test
    public void should_return_empty_text_for_no_invoice_number_provided() {
        String observation = generator.generateFromInvoiceNumbers(Arrays.asList());

        assertThat(observation, is(""));
    }

    @Test
    public void should_return_empty_text_for_invoice_numbers_provided_equals_to_null() {
        String observation = generator.generateFromInvoiceNumbers(null);

        assertThat(observation, is(""));
    }

    @Test
    public void should_be_possible_to_generate_observation_for_one_invoice_number_provided() {
        String observation = generator.generateFromInvoiceNumbers(Arrays.asList(1));

        assertThat(observation, is("Fatura da nota fiscal de simples remessa: 1."));
    }

    @Test
    public void should_be_possible_to_generate_observation_for_more_than_one_invoice_number_provided() {
        String observation = generator.generateFromInvoiceNumbers(Arrays.asList(1, 2, 3, 4, 5));

        assertThat(observation, is("Fatura das notas fiscais de simples remessa: 1, 2, 3, 4 e 5."));
    }
}
