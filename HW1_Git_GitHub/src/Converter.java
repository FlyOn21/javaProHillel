import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Converter {
    final static double KILOMETERS_TO_MILES = 0.621371;
    final static double MILES_TO_KILOMETERS = 1.60934;
    static Scanner scanner = new Scanner(System.in);
    static Hashtable<Double, Double> historyMilesToKilometers = new Hashtable<>();
    static Hashtable<Double, Double> historyKilometersToMiles = new Hashtable<>();
    static String title = """
            ###################################################
                
            Convertor mile to kilometers or kilometers to miles
                
            ###################################################
                
            """;
    static String optionStr = """
            choice 1 => Convert Kilometers to Miles
            choice 2 => Convert Kilometers to Miles
            choice 3 => Get History conversion
            choice 4 => Help
            choice 5 => Close program
            """;

    static String help = title + optionStr;


    static void kilometersToMiles() {
        System.out.println();
        System.out.print("Input distance in kilometers: ");
        double kilometers = scanner.nextDouble();
        boolean ifHistoryValueExist = historyKilometersToMiles.contains(kilometers);
        if (!ifHistoryValueExist) {
            double miles;
            miles = KILOMETERS_TO_MILES * kilometers;
            historyKilometersToMiles.put(kilometers, miles);
            System.out.println(kilometers + " Kilometers = " + miles + " Mails");
            System.out.println();
        } else {
            System.out.println(kilometers + " Kilometers = " + historyKilometersToMiles.get(kilometers) + " Mails");
            System.out.println();
        }

    }

    static void milesToKilometers() {
        System.out.println();
        System.out.print("Input distance in miles: ");
        double miles = scanner.nextDouble();
        boolean ifHistoryValueExist = historyKilometersToMiles.contains(miles);
        if (!ifHistoryValueExist) {
            double kilometers;
            kilometers = MILES_TO_KILOMETERS * miles;
            historyMilesToKilometers.put(miles, kilometers);
            System.out.println(miles + " Miles = " + kilometers + " Kilometers");
            System.out.println();
        } else {
            System.out.println(miles + " Miles = " + historyMilesToKilometers.get(miles) + " Kilometers");
            System.out.println();
        }
    }

    static void printHistory() {
        System.out.print("""
                ___________ History _____________
                                
                1) Miles to Kilometers
                """);
        historyMilesToKilometers.forEach((key, value) -> System.out.println(key + " => " + value));
        System.out.print("""
                                
                2) Kilometers to Miles
                """);
        historyKilometersToMiles.forEach((key, value) -> System.out.println(key + " => " + value));
        System.out.println();
    }

    static void conversionProcessing() {
        System.out.println(help);
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        kilometersToMiles();
                        break;
                    case 2:
                        milesToKilometers();
                        break;
                    case 3:
                        printHistory();
                        break;
                    case 4:
                        System.out.println(optionStr);
                        break;
                    case 5:
                        System.out.println("By-by");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        System.out.println(optionStr);
                        System.out.println();
                        break;
                }
            } catch (InputMismatchException error) {
                System.out.println("Something went wrong, maybe your input is incorrect.");
                scanner.nextLine(); // Clear the scanner
            }
        }
    }


    public static void main(String[] args) {
        conversionProcessing();
        scanner.close();
    }

}