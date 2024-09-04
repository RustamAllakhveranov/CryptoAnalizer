package cryptography;

import entity.Result;
import entity.ResultCode;
import file_manager.FileManager;
import grammar.Alphabet;
import grammar.Languages;

import java.io.File;

public class Decode extends AbstractCommand {
    Alphabet alphabet;
    StringBuilder builder;

    public Result execute(String[] parameters) {
        String language = parameters[0];
        String inputPath = parameters[1];
        String outputPath = parameters[2];
        int key = Integer.parseInt(parameters[3]);

        String text = FileManager.readFile(inputPath).toLowerCase();

        FileManager.writeFile(shift(language, text, -1 * key), outputPath);

        return new Result("Текст успешно расшифрован, ваш ключ: " + key, ResultCode.SUCCESS);
    }
}