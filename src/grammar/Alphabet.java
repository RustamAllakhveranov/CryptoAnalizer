package grammar;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Alphabet {
    //сделать класс синглтоном? Нахера нам много русских алфавитов?
    //проблема в том, что сложнее юнит-тестить такую приложуху (или класс?) с синглтоном
    //если класс синглтон, предлагаю запилить ленивую инициализацию. Или зря?
    //сделать класс статическим? мы же вроде не будем менять внутреннее состояние объекта?
    //по сути это класс содержащий инфу, а значит по сути это Entity или Model. Но точно не Contoller или View!
    //вне зависимости от решения по предыдущему пункту, тебе нужно применить к этому приложению паттерн MVC

    private Alphabet() {
    }

    private static final String VOWELS = "аеёиоуыэюя";
    private static final String CONSONANTS = "бвгджзйклмнпрстфхцчшщъь";
    private static final String LETTERS = VOWELS + CONSONANTS;
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "\n.,\"';-!? :";
    private static final char[] ALPHABET = (LETTERS + DIGITS + SYMBOLS).toCharArray();
    private static final Map<Character, Integer> charIndexes = new LinkedHashMap<>();

    static {
        initCharIndexesMap();
    }

    public static int length() {
        return asCharArray().length;
    }

    public static char[] asCharArray() {
        return ALPHABET;
    }

    public static char getCharByIndex(int index) {
        /*
        Нужно как то обработать NullPointerException, в случае если нет искомого символа.
        При этом нельзя заменять каждый такой символ например на *, иначе будет много звездочек, что будет вводить
        в заблуждение что неизвестные символы были одинаковыми.
        */
        return ALPHABET[index];
    }

    public static int getIndexOfChar(char symbol) {
        try {
            return charIndexes.get(symbol);
        } catch (NullPointerException e) {
            System.err.println("Unknown symbol is : " + symbol);
            throw new RuntimeException();
        }
    }

    public static String getLetters() {
        return LETTERS;
    }

    public static String getVowels() {
        return VOWELS;
    }

    public static String getConsonants() {
        return CONSONANTS;
    }

    public static String getDigits() {
        return DIGITS;
    }

    public static String getSymbols() {
        return SYMBOLS;
    }
    private static void initCharIndexesMap(){
        Arrays.sort(ALPHABET);
        for (int index = 0; index < ALPHABET.length; index++) {
            charIndexes.put(ALPHABET[index], index);
        }
    }

}