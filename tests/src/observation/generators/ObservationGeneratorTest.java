package observation.generators;

import observation.formatters.*;
import org.junit.Test;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ObservationGeneratorTest {

    @Test
    public void should_return_empty_text_for_no_invoice_number_provided() {
        ObservationGenerator generator = new ObservationGenerator(new SimpleObservationFormatter());

        String observation = generator.generateFromInvoiceNumbers(Arrays.asList());

        assertThat(observation, is(""));
    }

    @Test
    public void should_return_empty_text_for_invoice_numbers_provided_equals_to_null() {
        ObservationGenerator generator = new ObservationGenerator(new SimpleObservationFormatter());

        String observation = generator.generateFromInvoiceNumbers(null);

        assertThat(observation, is(""));
    }

    @Test
    public void should_be_possible_to_generate_observation_for_one_invoice_number_provided() {
        ObservationGenerator generator = new ObservationGenerator(new SimpleObservationFormatter());

        String observation = generator.generateFromInvoiceNumbers(Arrays.asList(1));

        assertThat(observation, is("Fatura da nota fiscal de simples remessa: 1."));
    }

    @Test
    public void should_be_possible_to_generate_observation_for_more_than_one_invoice_number_provided() {
        ObservationGenerator generator = new ObservationGenerator(new SimpleObservationFormatter());

        String observation = generator.generateFromInvoiceNumbers(Arrays.asList(1,2,3,4,5));

        assertThat(observation, is("Fatura das notas fiscais de simples remessa: 1, 2, 3, 4 e 5."));
    }

    //

    @Test
    public void should_be_possible_to_generate_observation_with_the_invoice_value_for_one_invoice_number_provided() {
        ObservationGenerator generator = new ObservationGenerator(new ObservationWithInvoiceValueFormatter());

        String observation = generator.generateFromInvoiceNumbers(Arrays.asList(1));

        assertThat(observation, is("Fatura da nota fiscal de simples remessa: 1 cujo valor é R$ 10,00."));
    }

    @Test
    public void should_be_possible_to_generate_observation_with_the_invoice_value_for_more_than_one_invoice_number_provided() {
        ObservationGenerator generator = new ObservationGenerator(new ObservationWithInvoiceValueFormatter());

        String observation = generator.generateFromInvoiceNumbers(Arrays.asList(1));

        assertThat(observation, is("Fatura da nota fiscal de simples remessa: 1 cujo valor é R$ 10,00, 2 cujo valor é R$ 20,00. Total = R$ 30,00."));
    }
}
