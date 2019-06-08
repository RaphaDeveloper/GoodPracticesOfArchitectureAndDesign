package services;

import valueObjects.TransportData;

public class TransportCostCalculator {

    public double calculate(TransportData transportData) {
        final double pavedRoadCostByKilometer = 0.54;
        final double pavedRoadCost = transportData.getDistanceInPavementRoad() * pavedRoadCostByKilometer;

        final double unpavedRoadCostByKilometer = 0.62;
        final double unpavedRoadCost = transportData.getDistanceInUnpavementRoad() * unpavedRoadCostByKilometer;


        return pavedRoadCost + unpavedRoadCost;
    }
}
