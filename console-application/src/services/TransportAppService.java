package services;

import transport.calculators.TransportCostCalculator;
import transport.TransportData;
import utils.*;

import java.util.Scanner;

public class TransportAppService {
    private TransportCostCalculator transportCostCalculator;

    public TransportAppService(TransportCostCalculator transportCostCalculator) {
        this.transportCostCalculator = transportCostCalculator;
    }

    public void calculateTransportCost(Scanner scanner) {
        TransportData transportData = createTransportDataFromUserInput(scanner);

        tryToCalculateTransportCost(transportData);
    }

    private TransportData createTransportDataFromUserInput(Scanner scanner) {
        System.out.println("What is the distance of the paved road?");
        int pavedRoadDistance = Integer.parseInt(ConsoleUtils.readString(scanner));

        System.out.println("What is the distance of the unpaved road?");
        int unpavedRoadDistance = Integer.parseInt(ConsoleUtils.readString(scanner));

        System.out.println("What is the utilized vehicle?");
        int vehicleId = Integer.parseInt(ConsoleUtils.readString(scanner));

        System.out.println("What is the weight?");
        int weight = Integer.parseInt(ConsoleUtils.readString(scanner));

        TransportData transportData = new TransportData();
        transportData.setDistanceInPavementRoad(pavedRoadDistance);
        transportData.setDistanceInUnpavementRoad(unpavedRoadDistance);
        transportData.setVehicleId(vehicleId);
        transportData.setWeightTon(weight);

        return transportData;
    }

    private void tryToCalculateTransportCost(TransportData transportData) {
        try {
            double transportCost = transportCostCalculator.calculate(transportData);

            printTheTransportCost(transportCost);
        } catch (RuntimeException exception) {
            System.out.println("\nCould not calculate the transport cost for the following reason: " + exception.getMessage() + "\n");
        }
    }

    private void printTheTransportCost(double transportCost) {
        String formattedTransportCost = CurrencyUtils.formatValueToCurrency(transportCost);

        System.out.println("\nThe cost of the transport is " + formattedTransportCost + "\n");
    }
}
