import cryptography.CommandContainer;
import cryptography.Command;
import entity.Result;

import java.util.Arrays;

public class ConsoleRunner {
    public static void main(String[] args) {
        String textPath = "C:\\Users\\rebel\\OneDrive\\Рабочий стол\\Текст.txt";
        String encryptedPath = "C:\\Users\\rebel\\OneDrive\\Рабочий стол\\Шифр.txt";
        String decryptedPath = "C:\\Users\\rebel\\OneDrive\\Рабочий стол\\Расшифровано.txt";
        String samplePath = "C:\\Users\\rebel\\OneDrive\\Рабочий стол\\Образец.txt";
        //добавь приведение к верхнему регистру у текста комманд и языка

//        String[] encode = new String[]{"ENCODE", "RUSSIAN", textPath, encryptedPath, "34"};
//        Command command = CommandContainer.getCommand(encode[0]);
//        Result result = command.execute(Arrays.copyOfRange(encode, 1, encode.length));
//        System.out.println(result);

//        String[] decode = new String[]{"DECODE", "RUSSIAN", encryptedPath, decryptedPath, "34"};
//        Command command2 = CommandContainer.getCommand(decode[0]);
//        Result result2 = command2.execute(Arrays.copyOfRange(decode, 1, decode.length));
//        System.out.println(result2);


//        String[] brute_force = new String[]{"BRUTE_FORCE", "RUSSIAN", encryptedPath, decryptedPath};
//        Command command3 = CommandContainer.getCommand(brute_force[0]);
//        Result result3 = command3.execute(Arrays.copyOfRange(brute_force, 1, brute_force.length));
//        System.out.println(result3);

        String[] analyze = new String[]{"ANALYZE", "RUSSIAN", encryptedPath, decryptedPath, samplePath};
        Command command4 = CommandContainer.getCommand(analyze[0]);
        Result result4 = command4.execute(Arrays.copyOfRange(analyze, 1, analyze.length));
        System.out.println(result4);
    }
}