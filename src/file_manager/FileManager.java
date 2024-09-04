package file_manager;

import java.io.*;
import java.util.Scanner;

public class FileManager {
    public static String readFile(String filePath) {
        //добавить проверку файла для чтения на существование в каждом месте где мы получаем аргументы или только в Мэйне?
        //добавить проверку файла для записи на существование, создать если отсутствует

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))
        ) {
            StringBuilder line = new StringBuilder();
            while (bufferedReader.ready()) {
                line.append(bufferedReader.readLine());
            }
            return line.toString().toLowerCase();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(String content, String filePath) {
        Scanner scanner = new Scanner(content);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                bufferedWriter.write(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}