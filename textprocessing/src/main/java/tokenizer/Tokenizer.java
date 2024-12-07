package tokenizer;

import java.util.List;

public interface Tokenizer {

    /**
     * Метод для токенизации текста.
     * @param input текст для токенизации.
     * @return список токенов.
     */
    List<String> tokenize(String input);
}
