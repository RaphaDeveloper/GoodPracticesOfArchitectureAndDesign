package transportCost.services;

import transportCost.valueObjects.TransportData;

public class TransportCostCalculator {
    private final double PAVED_ROAD_COST_BY_KILOMETER = 0.54;
    private final double UNPAVED_ROAD_COST_BY_KILOMETER = 0.62;
    private final double COST_OF_EXCESS_WEIGHT = 0.02;
    private final double LIMIT_OF_WEIGHT_WITHOUT_ADDITIONAL_COST = 5;

    public double calculate(TransportData transportData) {
        double transportCost = calculateRoadCost(transportData);

        if (weightIsInExcess(transportData.getWeightTon())) {
            transportCost += calculateCostOfExcessWeight(transportData);
        }

        return applyVehicleMultiplyingFactorOnTransportCost(transportData.getVehicleId(), transportCost);
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

    private double applyVehicleMultiplyingFactorOnTransportCost(int vehicleId, double transportCost) {
        double multiplyingFactor = 1d;

        if (vehicleId == 2) {
            multiplyingFactor = 1.05d;
        } else if (vehicleId == 3) {
            multiplyingFactor = 1.12d;
        }

        return transportCost * multiplyingFactor;
    }
}
