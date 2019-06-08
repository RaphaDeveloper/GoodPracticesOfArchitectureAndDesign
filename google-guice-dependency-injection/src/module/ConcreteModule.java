package module;

import com.google.inject.AbstractModule;
import transport.vehicle.InMemoryVehicleRepository;
import transport.vehicle.VehicleRepository;

public class ConcreteModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(VehicleRepository.class).to(InMemoryVehicleRepository.class);
    }
}
