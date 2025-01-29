package cryptography;

import file_manager.FileManager;
import grammar.Alphabet;

import java.util.HashSet;
import java.util.Set;

public class StatisticalAnalyzer extends AbstractCommand {

    public void execute(String[] arguments) {
        String inputPath = arguments[0];
        String outputPath = arguments[1];
        String sampleFilePath = arguments[2];
        String encryptedText = FileManager.readFile(inputPath);

        int key = findMostLikelyKey(encryptedText, sampleFilePath, 3);
        String content = shift(encryptedText, key);
        FileManager.writeFile(content, outputPath);
    }

    public int findMostLikelyKey(String encryptedText, String sampleFilePath, int combinationLength) {
        String sample = FileManager.readFile(sampleFilePath);
        Set<String> rightCombinations = getRightCombinations(sample, combinationLength);
        int matchesForKey;
        int maxMatches = -1;
        String decryptedTest;
        int mostLikelyKey = 0;
        for (int key = 1; key < Alphabet.asCharArray().length; key++) {
            decryptedTest = shift(encryptedText, key);
            matchesForKey = countCombinationMatches(decryptedTest, rightCombinations, combinationLength);
            if (matchesForKey > maxMatches) {
                maxMatches = matchesForKey;
                mostLikelyKey = key;
            }
        }
        return mostLikelyKey;
    }

    public Set<String> getRightCombinations(String sample, int combinationLength) {

        Set<String> rightCombinations = new HashSet<>();
        String combination;
        for (int i = 0; i < sample.length() - combinationLength; i++) {
            combination = sample.substring(i, i + combinationLength);
            rightCombinations.add(combination);
        }
        return rightCombinations;
    }

    public int countCombinationMatches(String text, Set<String> rightCombinations, int combinationLength) {
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