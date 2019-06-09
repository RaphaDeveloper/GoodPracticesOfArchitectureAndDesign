package services;

import transport.TransportData;
import transport.calculators.TransportCostCalculator;
import utils.ConsoleUtils;

import java.text.NumberFormat;
import java.util.Scanner;

public class AppService {
    private TransportCostCalculator transportCostCalculator;

    public AppService(TransportCostCalculator transportCostCalculator) {
        this.transportCostCalculator = transportCostCalculator;
    }

    public void calculateTransportCost(Scanner scanner) {
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

        double transportCost = transportCostCalculator.calculate(transportData);

        System.out.println("The cost of the transport is " + NumberFormat.getCurrencyInstance().format(transportCost));
    }

    public void info() {
        ConsoleUtils.printMainMenu();
    }
}
