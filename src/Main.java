import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LogFilter filter = new SevereLogFilter();

        int choice;

        do {
            System.out.println("\n--- Log Filter Menu ---");
            System.out.println("1. Generate demo log file");
            System.out.println("2. Filter log file");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    generateDemoLogFile("system.log");
                    break;

                case 2:
                    System.out.print("Enter source file path: ");
                    String sourceFile = input.nextLine();

                    System.out.print("Enter target file path: ");
                    String targetFile = input.nextLine();

                    System.out.println("Choose log level:");
                    System.out.println("1. CRITICAL");
                    System.out.println("2. ERROR");
                    System.out.println("3. WARNING");
                    System.out.println("4. INFO");
                    System.out.println("5. DEBUG");
                    System.out.print("Your choice: ");

                    int levelChoice = input.nextInt();
                    input.nextLine();

                    LogLevel level = getLevelByChoice(levelChoice);

                    if (level == null) {
                        System.out.println("Invalid log level");
                    } else {
                        filter.filter(sourceFile, targetFile, level);
                    }
                    break;

                case 0:
                    System.out.println("Program finished");
                    break;

                default:
                    System.out.println("Invalid option");
            }

        } while (choice != 0);

        input.close();
    }

    private static LogLevel getLevelByChoice(int choice) {
        switch (choice) {
            case 1:
                return LogLevel.CRITICAL;
            case 2:
                return LogLevel.ERROR;
            case 3:
                return LogLevel.WARNING;
            case 4:
                return LogLevel.INFO;
            case 5:
                return LogLevel.DEBUG;
            default:
                return null;
        }
    }

    private static void generateDemoLogFile(String fileName) {
        try (
                FileWriter fileWriter = new FileWriter(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter)
        ) {
            printWriter.println("1 " + LocalDateTime.now(ZoneOffset.UTC) + " [INFO] AuthModule User opened site");
            printWriter.println("2 " + LocalDateTime.now(ZoneOffset.UTC) + " [ERROR] PaymentModule Payment failed");
            printWriter.println("3 " + LocalDateTime.now(ZoneOffset.UTC) + " [DEBUG] DebugModule Debug message");
            printWriter.println("4 " + LocalDateTime.now(ZoneOffset.UTC) + " [CRITICAL] ServerModule Server is down");
            printWriter.println("5 " + LocalDateTime.now(ZoneOffset.UTC) + " [WARNING] AuthModule Too many login attempts");
            printWriter.println("6 " + LocalDateTime.now(ZoneOffset.UTC) + " [ERROR] DatabaseModule Cannot connect to database");

            System.out.println("Demo log file created: " + fileName);

        } catch (Exception e) {
            System.out.println("Error while creating demo log file: " + e.getMessage());
        }
    }
}
