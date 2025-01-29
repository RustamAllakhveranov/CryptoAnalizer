package cryptography;

import validator.Validator;
import grammar.Alphabet;

import java.util.Scanner;

public abstract class AbstractCommand implements Command {
    String shift(String text, int key) {
        Validator.validateKey(key);
        Scanner scanner = new Scanner(text);
        StringBuilder shiftedTextBuilder = new StringBuilder();
        String line;
        int indexOfShiftedChar;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            for (char symbol : line.toCharArray()) {
                indexOfShiftedChar = (Alphabet.getIndexOfChar(symbol) + key) % Alphabet.length();
                shiftedTextBuilder.append(Alphabet.getCharByIndex(indexOfShiftedChar));
            }
        }
        return shiftedTextBuilder.toString();
    }
}