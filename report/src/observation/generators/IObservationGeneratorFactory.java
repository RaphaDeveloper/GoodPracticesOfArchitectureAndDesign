package observation.generators;

public interface IObservationGeneratorFactory {

    ObservationGenerator createObservationGenerator(ObservationGeneratorType type);
}
