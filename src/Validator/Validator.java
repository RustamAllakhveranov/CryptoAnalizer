package Validator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validator {
    public void isValidKey(int key, String[] alphabet){
        //добавить проверку ключа на валидность во всех классах в самом начале!
        if (key < 1 || key >= alphabet.length) {
            //замени RuntimeException на что-то более конкретное
            throw new RuntimeException(String.format("Ключ должен быть не меньше 1 и не больше %d", alphabet.length));
        }
    }

    public boolean isFileExists(String filePath){
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }

    public boolean isFileTXT(String filePath){
        return filePath.endsWith(".txt");
    }
}
