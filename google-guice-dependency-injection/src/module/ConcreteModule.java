package module;

import com.google.inject.AbstractModule;
import transport.calculators.ITransportCostCalculator;
import transport.calculators.TransportCostCalculator;
import invoice.*;
import observation.generators.*;
import transport.vehicle.*;

public class ConcreteModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(VehicleRepository.class).to(InMemoryVehicleRepository.class);
        bind(InvoiceRepository.class).to(RandomInvoiceRepository.class);
        bind(IObservationGeneratorFactory.class).to(ObservationGeneratorFactory.class);
        configureBindsToConstructors();
    }

    private void configureBindsToConstructors() {
        try {

            bind(ITransportCostCalculator.class).toConstructor(TransportCostCalculator.class.getConstructor(VehicleRepository.class));

        } catch (NoSuchMethodException e) {
            addError(e);
        }
    }
}
