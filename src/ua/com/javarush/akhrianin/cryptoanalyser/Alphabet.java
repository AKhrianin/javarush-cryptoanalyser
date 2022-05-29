package ua.com.javarush.akhrianin.cryptoanalyser;



import java.util.Arrays;
import java.util.List;


public final class Alphabet {

    private static final List<Character> ALPHABET = Arrays.asList('а', 'б', 'в',
            'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»',
            ':', '!', '?', ' ');

    public static List<Character> getAlphabet(){
        return ALPHABET;
    }
}







