package utils;

import java.util.Scanner;

public class ConsoleUtils {

    public static String readString(Scanner scanner) {
        System.out.print("> ");
        return scanner.next();
    }

    public static void printMainMenu() {
        System.out.println("CALC : calculate the cost of transport");
        System.out.println("OBS1 : generate observation (deprecated)");
        System.out.println("OBS2 : generate observation");
        System.out.println("EXIT : exit application");
    }
}
