package transportCost.repositories;

import transportCost.entities.Vehicle;

public interface VehicleRepository {

    Vehicle getById(int id);
}
