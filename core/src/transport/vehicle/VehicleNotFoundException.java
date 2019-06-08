package transport.vehicle;

public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException(Integer vehicleId) {
        super("There is no vehicle with the code " + vehicleId.toString() + ".");
    }
}
