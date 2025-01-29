package file_manager;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    public static String readFile(String filePath) {
        //добавить проверку файла для чтения на существование в каждом месте где мы получаем аргументы или только в Мэйне?
        //добавить проверку файла для записи на существование, создать если отсутствует
        Path inputPath = Paths.get(filePath);
        if (!Files.exists(inputPath)) {
            throw new RuntimeException("Файл для чтения отсутствует!");
        }
        try {
            List<String> lines = Files.readAllLines(inputPath);
            StringBuilder stringBuilder = new StringBuilder();
            for (String line : lines) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*try (
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
        }*/
    }

    public static void writeFile(String content, String filePath) {
        //Добавил тут проверку существования и создание файла, с помощью NIO
        if (!Files.exists(Paths.get(filePath))) {
            try {
                Files.createFile(Paths.get(filePath));
            } catch (FileAlreadyExistsException e) {
                throw new RuntimeException("File is already exists!");
            } catch (IOException e) {
                throw new RuntimeException("Файл записи отсутствует и его не получилось создать.");
            }
        }

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