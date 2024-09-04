package cryptography;

import file_manager.FileManager;
import grammar.Alphabet;
import grammar.Languages;

import java.util.Scanner;

abstract class AbstractCommand implements Command{
    String shift(String language, String text, int key) {
        Alphabet alphabet = Languages.getGrammar(language).getAlphabet();
        Scanner scanner = new Scanner(text);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        int indexOfChar;
        int alphabetLength = alphabet.asCharArray().length;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            for (char symbol : line.toCharArray()) {
                indexOfChar = alphabet.getIndexOfChar(symbol) + key;
                while(indexOfChar < 0){
                    indexOfChar += alphabetLength;
                }
                if(indexOfChar >= alphabetLength) {
                    indexOfChar = Math.abs(indexOfChar % alphabetLength);
                }
                stringBuilder.append(alphabet.getCharByIndex(indexOfChar));
            }
        }
        return stringBuilder.toString();
    }
}