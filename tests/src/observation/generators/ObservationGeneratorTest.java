package observation.generators;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ObservationGeneratorTest {

    @Test
    public void should_be_possible_to_instantiate_the_generator() {
        ObservationGenerator generator = new ObservationGenerator();

        Assert.assertNotNull(generator);
    }

    @Test
    public void should_be_possible_to_generate_observation_to_one_invoice() {
        ObservationGenerator generator = new ObservationGenerator();

        String observation = generator.geraObservacao(Arrays.asList(1));

        assertThat(observation, is("Fatura da nota fiscal de simples remessa: 1."));
    }
}
