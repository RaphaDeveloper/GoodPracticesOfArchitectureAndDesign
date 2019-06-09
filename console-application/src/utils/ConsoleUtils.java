package utils;

import java.util.Scanner;

public class ConsoleUtils {

    public static String readString(Scanner scanner) {
        System.out.print( "> " );
        return scanner.next();
    }

    public static void printMainMenu() {
        System.out.println("CALC : calculate cost os transport");
        System.out.println("INFO : show main menu command");
        System.out.println("EXIT : exit application");
    }
}
