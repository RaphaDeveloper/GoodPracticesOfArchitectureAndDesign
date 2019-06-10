package services;

import observation.generators.*;
import utils.ConsoleUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ObservationAppService {
    private ObservationGeneratorFactory observationGeneratorFactory;

    public ObservationAppService(ObservationGeneratorFactory observationGeneratorFactory) {
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

        try {
            List<Integer> invoiceNumbers = getInvoiceNumbersFromUserInput(scanner);

            String observation = observationGenerator.generateFromInvoiceNumbers(invoiceNumbers);

            System.out.println("\n" + observation + "\n");
        } catch(RuntimeException exception) {
            System.out.println("\nThe input data is invalid.\n");
        }
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
}
