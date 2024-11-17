package textprocessing;

import memory.KnowledgeBase;

import java.util.List;

public class LanguageProcessor {

    private final TextTokenizer textTokenizer;

    public LanguageProcessor() {
        this.textTokenizer = new TextTokenizer();
    }

    public String process(String input, KnowledgeBase knowledgeBase) {

        List<String> tokens = textTokenizer.tokenize(input);

        if (input.contains("привет")) {
            return "Привет! Как я могу помочь?";
        }

        return knowledgeBase.findAnswer(String.join(" ", tokens));
    }
}
