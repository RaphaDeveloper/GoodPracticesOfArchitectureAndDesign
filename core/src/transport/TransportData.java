package transport;

import transport.exceptions.InvalidDistanceException;

public class TransportData {
    private int distanceInPavementRoad;
    private int distanceInUnpavementRoad;
    private int vehicleId;
    private int weightTon;

    public int getTotalDistance() {
        return getDistanceInPavementRoad() + getDistanceInUnpavementRoad();
    }

    public int getDistanceInPavementRoad() {
        return distanceInPavementRoad;
    }

    public void setDistanceInPavementRoad(int distanceInPavementRoad) {
        this.distanceInPavementRoad = distanceInPavementRoad;
    }

    public int getDistanceInUnpavementRoad() {
        return distanceInUnpavementRoad;
    }

    public void setDistanceInUnpavementRoad(int distanceInUnpavementRoad) {
        this.distanceInUnpavementRoad = distanceInUnpavementRoad;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getWeightTon() {
        return weightTon;
    }

    public void setWeightTon(int weightTon) {
        this.weightTon = weightTon;
    }

    public void validateData() {
        if (getDistanceInPavementRoad() < 0) {
            throw  new InvalidDistanceException("The distance of paved road cannot be negative.");
        }

        if (getDistanceInUnpavementRoad() < 0) {
            throw  new InvalidDistanceException("The distance of unpaved road cannot be negative.");
        }
    }
}
