package ua.com.javarush.akhrianin.cryptoanalyser;

public class ProgramDialog {
    public static final String GREETING_MESSAGE = "Вас приветствует криптоанализатор, выберите опцию:";
    public static final String WORKING_MODE1_MESSAGE = "шифрование файла по ключу - введите 1";
    public static final String MODE1_DIALOG_MESSAGE = "Введите путь к файлу для шифровки";
    public static final String WORKING_MODE2_MESSAGE = "расшифровка файла по ключу - введите 2";
    public static final String MODE2_DIALOG_MESSAGE = "Введите путь к файлу для расшифровки";
    public static final String WORKING_MODE3_MESSAGE = "взлом файла с brute force - введите 3";
    public static final String MODE3_DIALOG_MESSAGE = "Введите путь к файлу для взлома";
    public static final String WORKING_MODE4_MESSAGE = "закрыть программу - введите 4";
    public static final String KEY_SELECTION_MESSAGE = "Введите ключ от 1 до 39";
    public static final String FILE_WRITE_MESSAGE = "Файл записан:";
    public static final String DIRECTORY_SELECTION_MESSAGE =
            "Укажите универсальный путь к директории для результатов расшифровки";
    public static final String VALIDATE_KEY_ERROR_MESSAGE = "Введённый ключ не соответствует дианазону!";
    public static final String FILE_PROCESSING_ERROR_MESSAGE = "Файл не может быть записан:";
    public static final String ABSOLUTE_PATH_ERROR_MESSAGE = "Неверно указан путь к файлу! Введите абсолютный путь!";
    public static final String PATH_ERROR_MESSAGE = "Неверно указан путь к файлу!";
    public static final String SYSTEM_PATH_ERROR_MESSAGE = "Указан путь к системным файлам!";
    public static final String MODE4_ERROR_MESSAGE = "Введена неверная опция! Выберите от 1 до 4!";
}
