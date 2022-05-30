package ua.com.javarush.akhrianin.cryptoanalyser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {

                System.out.println(ProgramDialog.GREETING_MESSAGE.toUpperCase(Locale.ROOT));
                System.out.println(ProgramDialog.WORKING_MODE1_MESSAGE);
                System.out.println(ProgramDialog.WORKING_MODE2_MESSAGE);
                System.out.println(ProgramDialog.WORKING_MODE3_MESSAGE);
                System.out.println(ProgramDialog.WORKING_MODE4_MESSAGE);

                String in = input.nextLine();

                switch (in) {
                    case "1" -> {
                        Path filePath = readFilePath(input, System.out, "Введите путь к файлу для шифровки");
                        encode(filePath, input);

                    }
                    case "2" -> {
                        Path filePath = readFilePath(input, System.out, "Введите путь к файлу для расшифровки");
                        decode(filePath, input);

                    }
                    case "3" -> {
                        Path filePath = readFilePath(input, System.out, "Введите путь к файлу для взлома");
                        decodeBruteForce(filePath, input);

                    }
                    case "4" -> System.exit(0);
                    default -> throw new IllegalArgumentException("Введена неверная опция! Выберите от 1 до 4!");
                }
        }
    }



    private static Path readFilePath (Scanner input, PrintStream output, String message) {
        output.println(message);
        String fileInput = input.nextLine();
        Path filePath;
        try {
            filePath = Path.of(fileInput);
            if (!filePath.isAbsolute()) {
                throw new IllegalArgumentException("Неверно указан путь к файлу! Введите абсолютный путь!");
            }

        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Неверно указан путь к файлу", e);
        }
        return filePath;
    }

    private static void encode(Path filePath, Scanner input){
        System.out.println("Введите ключ от 1 до 39");
        int key = validateKey(input);
        try {List<String> fileText = Files.readAllLines(filePath);//reading from file

            List<Character> alphabetForEncryption = Alphabet.getAlphabet();//create copy of alphabet
            Collections.rotate(alphabetForEncryption, key);

            StringBuilder outgoStringBuilder = new StringBuilder();

            for (int i = 0; i < fileText.size(); i++) {
                String incomeString = fileText.get(i);
                for (int j = 0; j < incomeString.length(); j++) {
                    int index = Alphabet.getAlphabet().indexOf(incomeString.charAt(j));
                    if (index > -1){
                        outgoStringBuilder.append(alphabetForEncryption.get(index));
                    }
                }
                fileText.set(i,outgoStringBuilder.toString());
                outgoStringBuilder.delete(0,outgoStringBuilder.length());
            }
            System.out.println("encrypted text:" + fileText);
            //List<String> lines = Collections.singletonList(Arrays.toString(fileToChar));//при переводе в лист раделяет запятой

/*            try {
                Files.write(filePath,lines);
                System.out.println("файл зашифрован!");
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("Что-то пошло не так");//очень плохо
            }
*/


        } catch (IOException e){
            throw new IllegalArgumentException("Файл не найден:" + filePath + e.getMessage());
        }



        }

    private static void decode(Path filePath, Scanner input){

        }

    private static void decodeBruteForce(Path filePath, Scanner input){

    }
    private static int validateKey(Scanner input){
        Integer key = null;
        try {
            key = Integer.valueOf(input.nextLine());

            if (key < 1 || key > 39) {
                throw new IllegalArgumentException("Введённый ключ не соответствует дианазону!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ключа, используйте число!");
        }
        return key;
    }








}



