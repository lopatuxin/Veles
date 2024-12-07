package core;

import memory.KnowledgeBase;
import textprocessing.LanguageProcessor;
import tokenizer.Tokenizer;
import ui.ConsoleHandler;

import java.util.List;

public class AI {

    private final LanguageProcessor languageProcessor;
    private final KnowledgeBase knowledgeBase;
    private final ConsoleHandler consoleHandler;
    private final Tokenizer tokenizer;

    public AI(Tokenizer tokenizer) {
        this.languageProcessor = new LanguageProcessor();
        this.knowledgeBase = new KnowledgeBase();
        this.consoleHandler = new ConsoleHandler();
        this.tokenizer = tokenizer;
    }

    public void start() {
        consoleHandler.sayHello();

        while (true) {
            String input = consoleHandler.readInput();
            List<String> tokens = tokenizer.tokenize(input);

            String response = languageProcessor.process(input, knowledgeBase);
            consoleHandler.output(response);
        }
    }
}
