package module;

import com.google.inject.AbstractModule;
import invoice.InvoiceRepository;
import invoice.RandomInvoiceRepository;
import transport.calculators.TransportCostCalculator;
import transport.vehicle.InMemoryVehicleRepository;
import transport.vehicle.VehicleRepository;

public class ConcreteModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(VehicleRepository.class).to(InMemoryVehicleRepository.class);
        bind(InvoiceRepository.class).to(RandomInvoiceRepository.class);
        configureBindsToConstructors();
    }

    private void configureBindsToConstructors() {
        try {

            bind(TransportCostCalculator.class).toConstructor(TransportCostCalculator.class.getConstructor(VehicleRepository.class));

        } catch (NoSuchMethodException e) {
            addError(e);
        }
    }
}
