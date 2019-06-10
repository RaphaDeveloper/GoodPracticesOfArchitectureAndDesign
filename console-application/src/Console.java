import inject.DependencyInjectionFacade;
import observation.generators.ObservationGeneratorFactory;
import services.AppService;
import transport.calculators.TransportCostCalculator;
import utils.ConsoleUtils;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        TransportCostCalculator transportCostCalculator = DependencyInjectionFacade.getInstanceOf(TransportCostCalculator.class);
        ObservationGeneratorFactory observationGeneratorFactory = DependencyInjectionFacade.getInstanceOf(ObservationGeneratorFactory.class);

        AppService appService = new AppService(transportCostCalculator, observationGeneratorFactory);

        start(appService);
    }

    public static void start(AppService appService) {
        Scanner scanner = new Scanner(System.in);

        String cmd = "";

        while (true) {
            if ("CALC".equalsIgnoreCase(cmd)) {
                appService.calculateTransportCost(scanner);
            } else if ("OBS1".equalsIgnoreCase(cmd)) {
                appService.generateOldObservation(scanner);
            } else if ("OBS2".equalsIgnoreCase(cmd)) {
                appService.generateNewObservation(scanner);
            } else if ("EXIT".equalsIgnoreCase(cmd)) {
                break;
            }

            appService.info();

            cmd = ConsoleUtils.readString(scanner);
        }

    }
}
