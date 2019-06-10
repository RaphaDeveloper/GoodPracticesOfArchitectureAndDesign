package module;

import com.google.inject.AbstractModule;
import transport.calculators.TransportCostCalculator;
import transport.calculators.TransportCostCalculatorImpl;
import invoice.*;
import observation.generators.*;
import transport.vehicle.*;

public class ConcreteModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(VehicleRepository.class).to(InMemoryVehicleRepository.class);
        bind(InvoiceRepository.class).to(RandomInvoiceRepository.class);
        configureBindsToConstructors();
    }

    private void configureBindsToConstructors() {
        try {

            bind(TransportCostCalculator.class).toConstructor(TransportCostCalculatorImpl.class.getConstructor(VehicleRepository.class));
            bind(ObservationGeneratorFactory.class).toConstructor(ObservationGeneratorFactoryImpl.class.getConstructor(InvoiceRepository.class));

        } catch (NoSuchMethodException e) {
            addError(e);
        }
    }
}
