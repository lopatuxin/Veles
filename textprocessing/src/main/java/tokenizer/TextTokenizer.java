package tokenizer;

import java.util.Arrays;
import java.util.List;

public class TextTokenizer implements Tokenizer {

    public List<String> tokenize(String input) {
        String cleanedInput = input.toLowerCase().replace("[^a-zа-я0-9 ]", "");


        return Arrays.asList(cleanedInput.split("\\s+"));
    }

}
