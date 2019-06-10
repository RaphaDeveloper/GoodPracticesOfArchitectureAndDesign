package observation.generators;

public class ObservationGeneratorFactory implements IObservationGeneratorFactory {

    @Override
    public ObservationGenerator createObservationGenerator(ObservationGeneratorType type) {
        switch (type) {
            case WITH_VALUE:
                return new ObservationWithValueGenerator();
            case WITHOUT_VALUE:
                return new ObservationWithoutValueGenerator();
            default:
                return null;
        }
    }
}
