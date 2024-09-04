package cryptography;

import entity.Result;
import entity.ResultCode;
import file_manager.FileManager;
import grammar.*;

import java.io.*;

public class Encode extends AbstractCommand {
    public Result execute(String[] parameters) {
        String language = parameters[0];
        String inputPath = parameters[1];
        String outputPath = parameters[2];
        int key = Integer.parseInt(parameters[3]);
        String text = FileManager.readFile(inputPath);
        FileManager.writeFile(shift(language, text, key), outputPath);
        return new Result("Текст успешно зашифрован, ваш ключ: " + key, ResultCode.SUCCESS);
    }
}