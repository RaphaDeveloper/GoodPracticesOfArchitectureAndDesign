import inject.DependencyInjectionFacade;
import observation.generators.ObservationGeneratorFactory;
import services.ObservationAppService;
import services.TransportAppService;
import transport.calculators.TransportCostCalculator;
import utils.ConsoleUtils;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        TransportCostCalculator transportCostCalculator = DependencyInjectionFacade.getInstanceOf(TransportCostCalculator.class);
        ObservationGeneratorFactory observationGeneratorFactory = DependencyInjectionFacade.getInstanceOf(ObservationGeneratorFactory.class);

        TransportAppService transportAppService = new TransportAppService(transportCostCalculator);
        ObservationAppService observationAppService = new ObservationAppService(observationGeneratorFactory);


        start(transportAppService, observationAppService);
    }

    public static void start(TransportAppService transportAppService, ObservationAppService observationAppService) {
        Scanner scanner = new Scanner(System.in);

        String cmd = "";

        while (true) {
            if ("CALC".equalsIgnoreCase(cmd)) {
                transportAppService.calculateTransportCost(scanner);
            } else if ("OBS1".equalsIgnoreCase(cmd)) {
                observationAppService.generateOldObservation(scanner);
            } else if ("OBS2".equalsIgnoreCase(cmd)) {
                observationAppService.generateNewObservation(scanner);
            } else if ("EXIT".equalsIgnoreCase(cmd)) {
                break;
            }

            ConsoleUtils.printMainMenu();

            cmd = ConsoleUtils.readString(scanner);
        }

    }
}
