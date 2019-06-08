package services;

import valueObjects.TransportData;

public class TransportCostCalculator {

    public double calculate(TransportData transportData) {
        final double pavementRoadCostByKilometer = 0.54;

        return transportData.getDistanceInPavementRoad() * pavementRoadCostByKilometer;
    }
}
