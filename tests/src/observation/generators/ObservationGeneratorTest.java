package observation.generators;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ObservationGeneratorTest {

    @Test
    public void should_return_empty_text_for_no_invoice_number_provided() {
        ObservationGenerator generator = new ObservationGenerator();

        String observation = generator.geraObservacao(Arrays.asList());

        assertThat(observation, is(""));
    }

    @Test
    public void should_be_possible_to_generate_observation_to_one_invoice() {
        ObservationGenerator generator = new ObservationGenerator();

        String observation = generator.geraObservacao(Arrays.asList(1));

        assertThat(observation, is("Fatura da nota fiscal de simples remessa: 1."));
    }

    @Test
    public void should_be_possible_to_generate_observation_to_more_than_one_invoice() {
        ObservationGenerator generator = new ObservationGenerator();

        String observation = generator.geraObservacao(Arrays.asList(1,2,3,4,5));

        assertThat(observation, is("Fatura das notas fiscais de simples remessa: 1, 2, 3, 4 e 5."));
    }
}
