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
            choice 2 => Convert Miles to Kilometers
            choice 3 => Get History conversion
            choice 4 => Help
            choice 5 => Close program
            """;

    static String help = title + optionStr;
    static ConvertParams kilometersToMiles = new ConvertParams(
            historyKilometersToMiles, "Input distance in kilometers: ", KILOMETERS_TO_MILES, "Kilometers", "Mails"
    );
    static ConvertParams milesToKilometers = new ConvertParams(
            historyMilesToKilometers, "Input distance in miles: ", MILES_TO_KILOMETERS, "Mails", "Kilometers"
    );


    static void conversion(ConvertParams directionOfConversion) {
        System.out.println();
        System.out.print(directionOfConversion.requested());
        double inputValue = scanner.nextDouble();
        boolean ifHistoryValueExist = directionOfConversion.historyHashTable().contains(inputValue);
        if (!ifHistoryValueExist) {
            double result = directionOfConversion.Constant() * inputValue;
            directionOfConversion.historyHashTable().put(inputValue, result);
            System.out.println(inputValue + " " + directionOfConversion.from() + " = " + result + " " + directionOfConversion.to());
            System.out.println();
        } else {
            System.out.println(inputValue + " " + directionOfConversion.from() + " = " + directionOfConversion.historyHashTable().get(inputValue) + " " + directionOfConversion.to());
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
                        conversion(kilometersToMiles);
                        break;
                    case 2:
                        conversion(milesToKilometers);
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
                scanner.nextLine();
            }
        }
    }


    public static void main(String[] args) {
        conversionProcessing();
        scanner.close();
    }

}