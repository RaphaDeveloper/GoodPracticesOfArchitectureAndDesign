package services;

import invoice.Invoice;
import invoice.InvoiceRepository;
import observation.generators.*;
import utils.ConsoleUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ObservationAppService {
    private IObservationGeneratorFactory observationGeneratorFactory;
    private InvoiceRepository invoiceRepository;

    public ObservationAppService(InvoiceRepository invoiceRepository, IObservationGeneratorFactory observationGeneratorFactory) {
        this.invoiceRepository = invoiceRepository;
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

            List<Invoice> invoices = invoiceRepository.getByNumbers(invoiceNumbers);

            String observation = observationGenerator.generateFromInvoiceNumbers(invoices);

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
