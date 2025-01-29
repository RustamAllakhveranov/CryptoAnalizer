package cryptography;

import file_manager.FileManager;
import grammar.Alphabet;
import grammar.Grammar;

public class BruteForce extends AbstractCommand {
    @Override
    public void execute(String[] parameters) {
        String inputPath = parameters[0];
        String outputPath = parameters[1];
        String text = FileManager.readFile(inputPath).toLowerCase();
        int key = findMostSuitableKey(text);
        String content = shift(text, -1 * key);
        FileManager.writeFile(content, outputPath);
    }

    public int findMostSuitableKey(String text) {
        char[] alphabet = Alphabet.asCharArray();
        int mostSuitableKey = 0;
        int minErrors = Integer.MAX_VALUE;
        String shiftedText;
        for (int key = 1; key < alphabet.length - 1; key++) {
            int textErrorsCount;
            shiftedText = shift(text, -1 * key);
            if (Grammar.isThereNineConsonantInARow(shiftedText)) {
                continue;
            }
            textErrorsCount = Grammar.countWrongCombinationsInText(shiftedText);
            if (minErrors > textErrorsCount) {
                minErrors = textErrorsCount;
                mostSuitableKey = key;
            }
        }
        System.out.println("Текст был зашифрован с помощью следующего ключа: " + mostSuitableKey);
        return mostSuitableKey;
    }
}