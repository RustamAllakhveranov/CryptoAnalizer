package grammar;

import java.util.*;

public class RussianAlphabet implements Alphabet {
    private static final String VOWELS = "аеёиоуыэюя";
    private static final String CONSONANTS = "бвгджзйклмнпрстфхцчшщъь";
    private static final String LETTERS = VOWELS + CONSONANTS;
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "\n.,\"';-!? :";
    private static final char[] ALPHABET = (LETTERS + DIGITS + SYMBOLS).toCharArray();
    private static final Map<Character, Integer> charIndexes = new LinkedHashMap<>();

    public RussianAlphabet() {
        Arrays.sort(ALPHABET);
        for (int index = 0; index < ALPHABET.length; index++) {
            charIndexes.put(ALPHABET[index], index);
        }
    }

    @Override
    public char[] asCharArray() {
        return ALPHABET;
    }

    @Override
    public char getCharByIndex(int index) {
        /*
        Нужно как то обработать NullPointerException, в случае если нет искомого символа.
        При этом нельзя заменять каждый такой символ например на *, иначе будет много звездочек, что будет вводить
        в заблуждение что неизвестные символы были одинаковыми.
        */
        return ALPHABET[index];
    }

    @Override
    public int getIndexOfChar(char symbol) {
        try{
            return charIndexes.get(symbol);
        } catch(NullPointerException e){
            System.err.println("Unknown symbol is : " + symbol);
            throw new RuntimeException();
        }
    }

    @Override
    public String getLetters() {
        return LETTERS;
    }

    @Override
    public String getVowels() {
        return VOWELS;
    }

    @Override
    public String getConsonants() {
        return CONSONANTS;
    }

    @Override
    public String getDigits() {
        return DIGITS;
    }

    @Override
    public String getSymbols() {
        return SYMBOLS;
    }

}