package observation.generators;

public interface ObservationGeneratorFactory {

    ObservationGenerator createObservationGenerator(ObservationGeneratorType type);
}
