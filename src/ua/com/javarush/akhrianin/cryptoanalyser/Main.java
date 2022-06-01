package ua.com.javarush.akhrianin.cryptoanalyser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            System.out.println();

            workingModeSelector(input);
        }
    }

    private static void encode(Path filePath, Scanner input) {
        System.out.println(ProgramDialog.KEY_SELECTION_MESSAGE);

        int key = validateKey(input);

        List<String> fileText = readFile(filePath);

        codingProcessor(key, fileText);

        writeFile(filePath, fileText);
        System.out.println(ProgramDialog.FILE_WRITE_MESSAGE + filePath);
    }

    private static void decode(Path filePath, Scanner input) {
        System.out.println(ProgramDialog.KEY_SELECTION_MESSAGE);

        int key = validateKey(input);

        List<String> fileText = readFile(filePath);

        codingProcessor(-key, fileText);

        writeFile(filePath, fileText);
        System.out.println(ProgramDialog.FILE_WRITE_MESSAGE + filePath);
    }

    private static void decodeBruteForce(Path filePath, Scanner input) {
        List<String> fileText = readFile(filePath);

        List<Character> alphabet = Alphabet.getAlphabet();
        List<Character> alphabetForDecryption = Alphabet.getAlphabet();

        System.out.println(ProgramDialog.DIRECTORY_SELECTION_MESSAGE);
        String dirForResults = input.nextLine();

        for (int i = 0; i < alphabet.size(); i++) {
            List<String> checkList = new ArrayList<>();
            Collections.rotate(alphabetForDecryption, 1);

            for (int j = 0; j < fileText.size(); j++) {
                String incomeString = fileText.get(j);
                StringBuilder buffer = new StringBuilder();

                for (int k = 0; k < incomeString.length(); k++) {
                    int index = alphabet.indexOf(incomeString.charAt(k));
                    if (index > -1) {
                        buffer.append(alphabetForDecryption.get(index));
                    }
                }
                checkList.add(j, buffer.toString());

                String resultFilePath = dirForResults + "/" + i + ".txt";
                try {
                    Path path = Paths.get(resultFilePath);
                    writeFile(path, checkList);
                    System.out.println(ProgramDialog.FILE_WRITE_MESSAGE + path);
                    if (Files.isExecutable(path)){
                        throw new IllegalArgumentException(ProgramDialog.SYSTEM_PATH_ERROR_MESSAGE);
                    }
                    if (!path.isAbsolute()){
                        throw new IllegalArgumentException(ProgramDialog.ABSOLUTE_PATH_ERROR_MESSAGE);
                    }

                } catch (InvalidPathException e) {
                    throw new RuntimeException(ProgramDialog.PATH_ERROR_MESSAGE + e);
                }
            }
        }
    }

    private static int validateKey(Scanner input) {
        int key = 0;
        try {
            key = Integer.parseInt(input.nextLine());

            if (key < 1 || key > 39) {
                throw new IllegalArgumentException(ProgramDialog.VALIDATE_KEY_ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            System.out.println(ProgramDialog.VALIDATE_KEY_ERROR_MESSAGE);
        }
        return key;
    }

    private static void workingModeSelector(Scanner input) {
        String mode = input.nextLine();

        switch (mode) {
            case "1" -> {
                Path filePath = readFilePath(input, ProgramDialog.MODE1_DIALOG_MESSAGE);
                encode(filePath, input);

            }
            case "2" -> {
                Path filePath = readFilePath(input, ProgramDialog.MODE2_DIALOG_MESSAGE);
                decode(filePath, input);

            }
            case "3" -> {
                Path filePath = readFilePath(input, ProgramDialog.MODE3_DIALOG_MESSAGE);
                decodeBruteForce(filePath, input);

            }
            case "4" -> System.exit(0);
            default -> throw new IllegalArgumentException(ProgramDialog.MODE4_ERROR_MESSAGE);
        }
    }

    private static Path readFilePath(Scanner input, String message) {
        System.out.println(message);
        String fileInput = input.nextLine();
        Path filePath;
        try {
            filePath = Path.of(fileInput);
            if (!filePath.isAbsolute()) {
                throw new IllegalArgumentException(ProgramDialog.ABSOLUTE_PATH_ERROR_MESSAGE);
            }

        } catch (InvalidPathException e) {
            throw new RuntimeException(ProgramDialog.PATH_ERROR_MESSAGE + e);
        }
        return filePath;
    }

    private static void writeFile(Path filePath, List<String> fileText) {
        try {
            Files.write(filePath, fileText);
        } catch (IOException e) {
            throw new RuntimeException(ProgramDialog.FILE_PROCESSING_ERROR_MESSAGE + filePath + e);
        }
    }

    private static List<String> readFile(Path filePath) {
        List<String> fileText;
        try {
            fileText = Files.readAllLines(filePath);

        } catch (IOException e) {
            throw new RuntimeException(ProgramDialog.FILE_PROCESSING_ERROR_MESSAGE + filePath + e.getMessage());
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



