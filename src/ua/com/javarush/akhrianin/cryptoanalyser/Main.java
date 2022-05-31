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
            System.out.println(ProgramDialog.GREETING_MESSAGE);
            System.out.println(ProgramDialog.WORKING_MODE1_MESSAGE);
            System.out.println(ProgramDialog.WORKING_MODE2_MESSAGE);
            System.out.println(ProgramDialog.WORKING_MODE3_MESSAGE);
            System.out.println(ProgramDialog.WORKING_MODE4_MESSAGE);

            workingModeSelector(input);
        }
    }

    private static void encode(Path filePath, Scanner input) {
        System.out.println("Введите ключ от 1 до 39");

        int key = validateKey(input);

        List<String> fileText = readFile(filePath);

        codingProcessor(key, fileText);

        writeFile(filePath, fileText);
        System.out.println("Файл записан:" + filePath);
    }

    private static void decode(Path filePath, Scanner input) {
        System.out.println("Введите ключ от 1 до 39");

        int key = validateKey(input);

        List<String> fileText = readFile(filePath);

        codingProcessor(-key, fileText);

        writeFile(filePath, fileText);
        System.out.println("Файл записан:" + filePath);
    }

    private static void decodeBruteForce(Path filePath) {
        List<String> fileText = readFile(filePath);

        List<Character> alphabet = Alphabet.getAlphabet();
        List<Character> alphabetForDecryption = Alphabet.getAlphabet();

        for (int i = 0; i < alphabet.size(); i++) {
            List<String> checkList = new ArrayList<>();//новый лист, куда пишем попытки для проверки
            Collections.rotate(alphabetForDecryption, i);

            for (int j = 0; j < fileText.size(); j++) {
                String incomeString = fileText.get(j);//получили субстроку
                StringBuilder buffer = new StringBuilder();//буфер для чтения

                for (int k = 0; k < incomeString.length(); k++) {//проходим по субстроке
                    int index = alphabet.indexOf(incomeString.charAt(k));//проверяем есть ли буква в алфавите
                    if (index > -1) {//если есть
                        buffer.append(alphabetForDecryption.get(index));//заполняем буфер
                    }
                }
                checkList.set(j, buffer.toString());//пишем в проверочный лист
            }
            //тут должна быть логика сверки полученного листа с эталонным списком


        }

        writeFile(filePath, checkList);
        System.out.println("Файл расшифрован:" + filePath);
    }

    private static int validateKey(Scanner input) {
        int key = 0;
        try {
            key = Integer.parseInt(input.nextLine());

            if (key < 1 || key > 39) {
                throw new IllegalArgumentException("Введённый ключ не соответствует дианазону!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Введённый ключ не соответствует дианазону!");
        }
        return key;
    }

    private static void workingModeSelector(Scanner input) {
        String mode = input.nextLine();

        switch (mode) {
            case "1" -> {
                Path filePath = readFilePath(input, "Введите путь к файлу для шифровки");
                encode(filePath, input);

            }
            case "2" -> {
                Path filePath = readFilePath(input, "Введите путь к файлу для расшифровки");
                decode(filePath, input);

            }
            case "3" -> {
                Path filePath = readFilePath(input, "Введите путь к файлу для взлома");
                decodeBruteForce(filePath);

            }
            case "4" -> System.exit(0);
            default -> throw new IllegalArgumentException("Введена неверная опция! Выберите от 1 до 4!");
        }
    }

    private static Path readFilePath(Scanner input, String message) {
        System.out.println(message);
        String fileInput = input.nextLine();
        Path filePath;
        try {
            filePath = Path.of(fileInput);
            if (!filePath.isAbsolute()) {
                throw new IllegalArgumentException("Неверно указан путь к файлу! Введите абсолютный путь!");
            }

        } catch (InvalidPathException e) {
            throw new RuntimeException("Неверно указан путь к файлу:" + e);
        }
        return filePath;
    }

    private static void writeFile(Path filePath, List<String> fileText) {
        try {
            Files.write(filePath, fileText);
        } catch (IOException e) {
            throw new RuntimeException("Файл не может быть записан:" + filePath + e);
        }
    }

    private static List<String> readFile(Path filePath) {
        List<String> fileText;
        try {
            fileText = Files.readAllLines(filePath);

        } catch (IOException e) {
            throw new RuntimeException("Файл не может быть записан:" + filePath + e.getMessage());
        }
        return fileText;
    }

    private static void codingProcessor(int key, List<String> fileText) {
        List<Character> alphabetForEncryption = Alphabet.getAlphabet();
        Collections.rotate(alphabetForEncryption, key);

        for (int i = 0; i < fileText.size(); i++) {
            String incomeString = fileText.get(i);
            StringBuilder outgoStringBuilder = new StringBuilder();
            for (int j = 0; j < incomeString.length(); j++) {
                int index = Alphabet.getAlphabet().indexOf(incomeString.charAt(j));
                if (index > -1) {
                    outgoStringBuilder.append(alphabetForEncryption.get(index));
                }
            }
            fileText.set(i, outgoStringBuilder.toString());
        }
    }
}



