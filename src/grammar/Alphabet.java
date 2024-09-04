package grammar;

import java.util.Map;

public interface Alphabet {
      char[] asCharArray();
      char getCharByIndex(int index);
      int getIndexOfChar(char c);
      String getLetters();
      String getVowels();
      String getConsonants();
      String getDigits();
      String getSymbols();

}