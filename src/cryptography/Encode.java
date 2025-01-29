package cryptography;

import file_manager.FileManager;

public class Encode extends AbstractCommand {
    @Override
    public void execute(String[] parameters) {
        String inputPath = parameters[0];
        String outputPath = parameters[1];
        int key = Integer.parseInt(parameters[2]);
        String inputText = FileManager.readFile(inputPath).toLowerCase();
        String outputText = shift(inputText, key);
        FileManager.writeFile(outputText, outputPath);
    }
}