package services;

import valueObjects.TransportData;

public class TransportCostCalculator {

    public double calculate(TransportData transportData) {
        double additionalCostOfExcessBurden = 0;

        final double pavedRoadCostByKilometer = 0.54;
        final double pavedRoadCost = transportData.getDistanceInPavementRoad() * pavedRoadCostByKilometer;

        final double unpavedRoadCostByKilometer = 0.62;
        final double unpavedRoadCost = transportData.getDistanceInUnpavementRoad() * unpavedRoadCostByKilometer;

        if (transportData.getWeightTon() > 5) {
            final int totalDistance = transportData.getDistanceInPavementRoad() + transportData.getDistanceInUnpavementRoad();
            final double costOfExcessBurden = 0.02d;
            final int excessWeight = transportData.getWeightTon() - 5;

            additionalCostOfExcessBurden = costOfExcessBurden * excessWeight * totalDistance;
        }

        return pavedRoadCost + unpavedRoadCost + additionalCostOfExcessBurden;
    }
}
