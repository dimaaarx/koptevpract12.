import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class SevereLogFilter implements LogFilter {

    @Override
    public void filter(String sourceFile, String targetFile, LogLevel level) {
        int count = 0;

        try (
                FileReader fileReader = new FileReader(sourceFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                Scanner scanner = new Scanner(bufferedReader);

                FileWriter fileWriter = new FileWriter(targetFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter)
        ) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains(level.getKeyword())) {
                    printWriter.println(line);
                    count++;
                }
            }

            System.out.println("Фільтрацію завершено");
            System.out.println("Знайдено рядків: " + count);
            System.out.println("Результат записано у файл: " + targetFile);

        } catch (Exception e) {
            System.out.println("Помилка під час обробки файлу: " + e.getMessage());
        }
    }
}
