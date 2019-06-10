package services;

import observation.generators.*;
import transport.calculators.TransportCostCalculator;
import utils.*;
import transport.TransportData;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppService {
    private TransportCostCalculator transportCostCalculator;
    private ObservationGeneratorFactory observationGeneratorFactory;

    public AppService(TransportCostCalculator transportCostCalculator, ObservationGeneratorFactory observationGeneratorFactory) {
        this.transportCostCalculator = transportCostCalculator;
        this.observationGeneratorFactory = observationGeneratorFactory;
    }

    public void generateOldObservation(Scanner scanner) {
        generateObservation(scanner, ObservationGeneratorType.WITHOUT_VALUE);
    }

    public void generateNewObservation(Scanner scanner) {
        generateObservation(scanner, ObservationGeneratorType.WITH_VALUE);
    }

    private void generateObservation(Scanner scanner, ObservationGeneratorType observationGeneratorType) {
        ObservationGenerator observationGenerator = observationGeneratorFactory.createObservationGenerator(observationGeneratorType);

        List<Integer> invoiceNumbers = getInvoiceNumbersFromUserInput(scanner);

        String observation = observationGenerator.generateFromInvoiceNumbers(invoiceNumbers);

        System.out.println("\n" + observation + "\n");
    }

    private List<Integer> getInvoiceNumbersFromUserInput(Scanner scanner) {
        System.out.println("Provide the invoice numbers separated by comma:");

        String userInput = ConsoleUtils.readString(scanner);

        String[] numbersAsString = userInput.trim().split(",");

        List<Integer> invoiceNumbers = new ArrayList<>();
        for (String numberAsString : numbersAsString) {
            invoiceNumbers.add(Integer.parseInt(numberAsString));
        }

        return invoiceNumbers;
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

    public void info() {
        ConsoleUtils.printMainMenu();
    }
}
