package transport.vehicle;

import java.util.HashMap;
import java.util.Map;

public class InMemoryVehicleRepository implements VehicleRepository {
    private Map<Integer, Vehicle> vehicleById = new HashMap<>();

    public InMemoryVehicleRepository() {
        initializeVehicleById();
    }

    private void initializeVehicleById() {
        Vehicle boxTruck = new Vehicle(1, "Box Truck", 1d);
        Vehicle bucketTruck = new Vehicle(2, "Bucket Truck", 1.05d);
        Vehicle bigTruck = new Vehicle(3, "Big Truck", 1.12d);

        vehicleById.put(1, boxTruck);
        vehicleById.put(2, bucketTruck);
        vehicleById.put(3, bigTruck);
    }

    @Override
    public Vehicle getById(int id) {
        return vehicleById.get(id);
    }
}
