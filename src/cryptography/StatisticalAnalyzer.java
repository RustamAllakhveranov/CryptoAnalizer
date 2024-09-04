package cryptography;

import entity.Result;
import entity.ResultCode;
import file_manager.FileManager;
import grammar.Alphabet;
import grammar.Languages;

import java.io.File;
import java.util.HashSet;

public class StatisticalAnalyzer extends AbstractCommand {

    public Result execute(String[] arguments) {
        String language = arguments[0];
        String inputPath = arguments[1];
        String outputPath = arguments[2];
        String sampleFilePath = arguments[3];
        String encryptedText = FileManager.readFile(inputPath);

        int key = findMostLikelyKey(language, encryptedText, sampleFilePath, 3);
        String content = shift(language, encryptedText, key);
        FileManager.writeFile(content, outputPath);
        return new Result("Расшифровка файла методом статистического анализа завершена!", ResultCode.SUCCESS);
    }

    public int findMostLikelyKey(String language, String encryptedText, String sampleFilePath, int combinationLength) {
        String sample = FileManager.readFile(sampleFilePath);
        HashSet<String> rightCombinations = getRightCombinations(sample, combinationLength);
        Alphabet alphabet = Languages.getGrammar(language).getAlphabet();
        int matchesForKey = 0;
        int maxMatches = -1;
        String decryptedTest;
        int mostLikelyKey = 0;
        for (int key = 1; key < alphabet.asCharArray().length; key++) {
            decryptedTest = shift(language, encryptedText, key);
            matchesForKey = countCombinationMatches(decryptedTest, rightCombinations, combinationLength);
            if (matchesForKey > maxMatches) {
                maxMatches = matchesForKey;
                mostLikelyKey = key;
            }
        }
        return mostLikelyKey;
    }

    public HashSet<String> getRightCombinations(String sample, int combinationLength) {

        HashSet<String> rightCombinations = new HashSet<>();
        String combination;
        for (int i = 0; i < sample.length() - combinationLength; i++) {
            combination = sample.substring(i, i + combinationLength);
            rightCombinations.add(combination);
        }
        return rightCombinations;
    }

    public int countCombinationMatches(String text, HashSet<String> rightCombinations, int combinationLength) {
        int combinationMatches = 0;
        String combination;
        for (int i = 0; i < text.length() - combinationLength; i++) {
            combination = text.substring(i, i + combinationLength);
            if (rightCombinations.contains(combination)) {
                combinationMatches++;
            }
        }
        return combinationMatches;
    }
}