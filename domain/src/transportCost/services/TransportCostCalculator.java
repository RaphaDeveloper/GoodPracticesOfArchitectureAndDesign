package transportCost.services;

import transportCost.entities.Vehicle;
import transportCost.repositories.VehicleRepository;
import transportCost.valueObjects.TransportData;

public class TransportCostCalculator {
    private final double PAVED_ROAD_COST_BY_KILOMETER = 0.54;
    private final double UNPAVED_ROAD_COST_BY_KILOMETER = 0.62;
    private final double COST_OF_EXCESS_WEIGHT = 0.02;
    private final double LIMIT_OF_WEIGHT_WITHOUT_ADDITIONAL_COST = 5;

    private VehicleRepository vehicleRepository;

    public TransportCostCalculator(VehicleRepository vehicleRepository) {

        this.vehicleRepository = vehicleRepository;
    }

    public TransportCostCalculator() {}

    public double calculate(TransportData transportData) {
        double transportCost = calculateRoadCost(transportData);

        if (weightIsInExcess(transportData.getWeightTon())) {
            transportCost += calculateCostOfExcessWeight(transportData);
        }

        Vehicle vehicle = getVehicleById(transportData.getVehicleId());

        return applyVehicleMultiplyingFactorOnTransportCost(vehicle, transportCost);
    }

    private double calculateRoadCost(TransportData transportData) {
        return calculatePavedRoadCost(transportData) + calculateUnpavedRoadCost(transportData);
    }

    private double calculatePavedRoadCost(TransportData transportData) {
        return transportData.getDistanceInPavementRoad() * PAVED_ROAD_COST_BY_KILOMETER;
    }

    private double calculateUnpavedRoadCost(TransportData transportData) {
        return transportData.getDistanceInUnpavementRoad() * UNPAVED_ROAD_COST_BY_KILOMETER;
    }

    private boolean weightIsInExcess(int weight) {
        return weight > LIMIT_OF_WEIGHT_WITHOUT_ADDITIONAL_COST;
    }

    private double calculateCostOfExcessWeight(TransportData transportData) {
        return COST_OF_EXCESS_WEIGHT * getExcessWeight(transportData.getWeightTon()) * transportData.getTotalDistance();
    }

    private double getExcessWeight(int weight) {
        return weight - LIMIT_OF_WEIGHT_WITHOUT_ADDITIONAL_COST;
    }

    private Vehicle getVehicleById(int vehicleId) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        vehicle.setMultiplyingFactor(1d);

        if (vehicleId == 2) {
            vehicle.setMultiplyingFactor(1.05d);
        } else if (vehicleId == 3) {
            vehicle.setMultiplyingFactor(1.12d);
        }

        return vehicle;
    }

    private double applyVehicleMultiplyingFactorOnTransportCost(Vehicle vehicle, double transportCost) {
        return transportCost * vehicle.getMultiplyingFactor();
    }
}
