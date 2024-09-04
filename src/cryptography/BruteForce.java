package cryptography;

import entity.Result;
import entity.ResultCode;
import file_manager.FileManager;
import grammar.*;


public class BruteForce extends AbstractCommand {

    public Result execute(String[] parameters) {
        String language = parameters[0];
        String inputPath = parameters[1];
        String outputPath = parameters[2];
        String text = FileManager.readFile(inputPath).toLowerCase();
        int key = findMostSuitableKey(language, text);
        String content = shift(language, text, -1 * key);
        FileManager.writeFile(content, outputPath);
        return new Result("All right", ResultCode.SUCCESS);
    }

    public int findMostSuitableKey(String language, String text) {

        char[] alphabet = Languages.getGrammar(language).getAlphabet().asCharArray();
        int mostSuitableKey = 0;
        int minErrors = Integer.MAX_VALUE;
        String shiftedText;
        for (int key = 1; key < alphabet.length - 1; key++) {
            int textErrorsCount;
            shiftedText = shift(language, text, -1 * key);
            if (RussianGrammar.isThereNineConsonantInARow(shiftedText)) {
                continue;
            }
            textErrorsCount = RussianGrammar.countWrongCombinationsInText(shiftedText);
            if (minErrors > textErrorsCount) {
                minErrors = textErrorsCount;
                mostSuitableKey = key;
            }
        }
        System.out.println("Текст был зашифрован с помощью следующего ключа: " + mostSuitableKey);
        return mostSuitableKey;
    }
}