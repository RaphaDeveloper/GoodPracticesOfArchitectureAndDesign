package services;

import valueObjects.TransportData;

public class TransportCostCalculator {

    public double calculate(TransportData transportData) {
        return transportData.getDistanceInPavementRoad() * 0.54;
    }
}
