package ua.com.javarush.akhrianin.cryptoanalyser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int count = 0;
        int maxTries = 3;
        while (true) {
            try {
                System.out.println(ProgramDialog.GREETING_MESSAGE.toUpperCase(Locale.ROOT));
                System.out.println(ProgramDialog.WORKING_MODE1_MESSAGE);
                System.out.println(ProgramDialog.WORKING_MODE2_MESSAGE);
                System.out.println(ProgramDialog.WORKING_MODE3_MESSAGE);
                System.out.println(ProgramDialog.WORKING_MODE4_MESSAGE);

                String in = input.nextLine();

                if (in.equals("1")) {
                    String fileName = readFilePath(input, System.out, "Введите путь к файлу для шифровки");
                    encode(fileName);

                } else if (in.equals("2")) {
                    String fileName = readFilePath(input, System.out, "Введите путь к файлу для расшифровки");
                    decode(fileName);

                } else if (in.equals("3")) {
                    String fileName = readFilePath(input, System.out, "Введите путь к файлу для взлома");
                    decodeBruteForce(fileName);

                } else if (in.equals("4")) {
                    System.exit(0);
                } else {
                    throw new IllegalArgumentException("Введена неверная опция! Выберите от 1 до 4!");
                }

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                if (++count == maxTries) throw ex;//this block should be improved
            }

        }
    }
    private static String readFilePath (Scanner input, PrintStream output, String message){
        output.println(message);
        String fileInput = input.nextLine();
        try {
            Path filePath = Path.of(fileInput);
            if (!filePath.isAbsolute()){
                throw new IllegalArgumentException("Неверно указан путь к файлу! Введите абсолютный путь!");
            }
            if (!Files.isReadable(filePath)){
                throw new IllegalArgumentException("Файл не может быть прочитан!");
            }
            if (!Files.isWritable(filePath)){
                throw new IllegalArgumentException("В файл невозможно произвести запись!");
            }

        } catch (InvalidPathException e){
            throw new IllegalArgumentException("Неверно указан путь к файлу", e);
        }
        return fileInput;
    }

    private static void encode(String fileName){

        }

    private static void decode(String fileName){

        }

    private static void decodeBruteForce(String fileName){

    }









}



