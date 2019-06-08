package services;

import valueObjects.TransportData;

public class TransportCostCalculator {
    private final double PAVED_ROAD_COST_BY_KILOMETER = 0.54;
    private final double UNPAVED_ROAD_COST_BY_KILOMETER = 0.62;
    private final double COST_OF_EXCESS_WEIGHT = 0.02;
    private final double LIMIT_OF_WEIGHT_WITHOUT_ADDITIONAL_COST = 5;

    public double calculate(TransportData transportData) {
        double cost = calculateRoadCost(transportData);

        if (weightIsInExcess(transportData.getWeightTon())) {
            cost += calculateCostOfExcessWeight(transportData);
        }

        return cost;
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
}
