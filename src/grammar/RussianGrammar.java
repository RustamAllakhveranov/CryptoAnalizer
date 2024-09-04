package grammar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RussianGrammar implements Grammar {
    private static final RussianAlphabet alphabet = new RussianAlphabet();
    private static final Set<String> WRONG_COMBINATIONS = new HashSet<>(Arrays.asList(
            //согласные, которые не могут идти перед твердым знаком:
            "гъ", "йъ", "къ", "лъ", "мъ", "пъ", "ръ", "фъ", "цъ", "чъ", "шъ", "ъъ",
            //буквы, которые не могут идти после "ъ":
            "ъа", "ъи", "ъо", "ъы", "ъ ",
            //все буквы, которые не могут стоять перед мягким знаком:
            "йь", "кь", "хь", "ць",
            //все буквы, на которые нельзя начинать слово:
            " ы", " ъ", " ь",
            //несуществующие сочетания с буквой "ё":
            "ёф",
            //буквы которые не удваиваются:
            "ёё", "йй", "шш", "щщ", "ъъ", "ыы", "ьь", "ээ",
            //несуществующие сочетания:
            "аы", "аь", "еэ", "ёы", "ёэ", "ёя", "йж", "жф", "жч",
            "жш", "жщ", "зп", "зщ", "йь", "оы", "уы", "уь", "фц", "хщ",
            "цщ", "цэ", "чщ", "чэ", "шщ", "ьы", "ыэ",
            "цс", "щс"
    ));

    public RussianGrammar() {

        char[] digits = alphabet.getDigits().toCharArray();
        char[] letters = alphabet.getLetters().toCharArray();
        char[] vowels = alphabet.getVowels().toCharArray();
        char[] consonants = alphabet.getConsonants().toCharArray();
        char[] punctuation = alphabet.getSymbols().toCharArray();
        //неверные комбинации одних знаков препинаний с другими, включая пробел и перенос строки
        addWrongCombination('\n', getArrayWithoutExtra(punctuation, "\n "));
        addWrongCombination('.', getArrayWithoutExtra(punctuation, "\n.\"'- "));
        addWrongCombination(',', getArrayWithoutExtra(punctuation, "\n- "));
        addWrongCombination('"', getArrayWithoutExtra(punctuation, "\n.,';-!? "));
        addWrongCombination('\'', getArrayWithoutExtra(punctuation, "\n.,\";-!? "));
        addWrongCombination(';', getArrayWithoutExtra(punctuation, "\n- "));
        addWrongCombination('-', getArrayWithoutExtra(punctuation, "\n\"'- "));
        addWrongCombination('!', getArrayWithoutExtra(punctuation, "\n\"'-! "));
        addWrongCombination('?', getArrayWithoutExtra(punctuation, "\n\"'-!? "));
        addWrongCombination(' ', getArrayWithoutExtra(punctuation, "\n\"'-"));
        addWrongCombination(':', getArrayWithoutExtra(punctuation, "\n\"' "));
        //знаки после которых не могут идти буквы(без пробела)
        addWrongCombination(".,:;!?".toCharArray(), letters);
        //неверные комбинации букв и цифр
        addWrongCombination(digits, letters);
        addWrongCombination(letters, digits);
        //неверные комбинации букв с другими буквами
        addWrongCombination(vowels, "ъыь".toCharArray());
        addWrongCombination('ё', vowels);
        addWrongCombination('ъ', consonants);
    }

    private void addWrongCombination(char firstElement, char[] secondElements) {
        for (char secondElement : secondElements) {
            WRONG_COMBINATIONS.add(firstElement + secondElement + "");
        }
    }

    private void addWrongCombination(char[] firstElements, char secondElement) {
        for (char firstElement : firstElements) {
            WRONG_COMBINATIONS.add(firstElement + secondElement + "");
        }
    }

    private void addWrongCombination(char[] firstElements, char[] secondElements) {
        for (char firstElement : firstElements) {
            for (char secondElement : secondElements) {
                WRONG_COMBINATIONS.add(firstElement + secondElement + "");
            }
        }
    }

    private char[] getArrayWithoutExtra(char[] originalElements, String extraElements) {
        char[] result = new char[originalElements.length - extraElements.length()];
        int i = 0;
        for (char firstElement : originalElements) {
            if (extraElements.indexOf(firstElement) < 0) {
                result[i] = firstElement;
            }
        }
        return result;
    }

    public static Set<String> getWrongCombinations() {
        return WRONG_COMBINATIONS;
    }

    public static int countWrongCombinationsInText(String text) {
        Scanner scanner = new Scanner(text);
        Set<String> wrongCombinations = getWrongCombinations();
        int grammarErrorsCount = 0;
        String combination;
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            for (int i = 0; i < line.length() - 2; i++) {
                combination = line.substring(i, i + 2);
                if (wrongCombinations.contains(combination)) {
                    grammarErrorsCount++;
                }
            }
        }
        return grammarErrorsCount;
    }

    public static boolean isThereNineConsonantInARow(String text) {
        Scanner scanner = new Scanner(text);
        String consonants = alphabet.getConsonants();
        String line;
        int consonantsInARowCount;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            for (int i = 0; i < line.length(); i++) {
                consonantsInARowCount = 0;
                if (consonants.indexOf(line.charAt(i)) >= 0) {
                    consonantsInARowCount++;
                    if (consonantsInARowCount > 8) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Alphabet getAlphabet() {
        return alphabet;
    }
}