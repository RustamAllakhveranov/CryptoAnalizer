package cryptography;

import file_manager.FileManager;

public class Decode extends AbstractCommand {
    @Override
    public void execute(String[] parameters) {
        String inputPath = parameters[0];
        String outputPath = parameters[1];
        int key = Integer.parseInt(parameters[2]);
        String text = FileManager.readFile(inputPath).toLowerCase();
        FileManager.writeFile(shift(text, -1 * key), outputPath);
    }
}