package validator;

import grammar.Alphabet;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validator {

    private Validator() {

    }

    public static void validateKey(int key) {
        if (key < 1 || key >= Alphabet.length()) {
            //замени RuntimeException на что-то более конкретное
            throw new RuntimeException(String.format("Ключ должен быть не меньше 1 и не больше %d", Alphabet.length() - 1));
        }
    }

    public static boolean isFileExists(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }

    public static boolean isFileTXT(String filePath) {
        return filePath.endsWith(".txt");
    }
}