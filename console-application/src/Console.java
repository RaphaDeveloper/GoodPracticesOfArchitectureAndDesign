import facades.DependencyInjectionFacade;
import services.AppService;
import transport.calculators.TransportCostCalculator;
import utils.ConsoleUtils;

import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        TransportCostCalculator transportCostCalculator = DependencyInjectionFacade.getInstanceOf(TransportCostCalculator.class);

        AppService appService = new AppService(transportCostCalculator);

        start(appService);
    }

    public static void start(AppService appService) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String cmd = ConsoleUtils.readString(scanner);

            if ("CALC".equalsIgnoreCase(cmd)) {
                appService.calculateTransportCost(scanner);
            } else if ("EXIT".equalsIgnoreCase(cmd)) {
                break;
            } else {
                appService.info();
            }
        }

    }
}
