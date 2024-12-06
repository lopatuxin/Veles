package core;

import memory.KnowledgeBase;
import textprocessing.LanguageProcessor;
import ui.ConsoleHandler;

public class AI {

    private final LanguageProcessor languageProcessor;
    private final KnowledgeBase knowledgeBase;
    private final ConsoleHandler consoleHandler;

    public AI() {
        this.languageProcessor = new LanguageProcessor();
        this.knowledgeBase = new KnowledgeBase();
        this.consoleHandler = new ConsoleHandler();
    }

    public void start() {
        consoleHandler.sayHello();

        while (true) {
            String input = consoleHandler.readInput();
            String response = languageProcessor.process(input, knowledgeBase);
            consoleHandler.output(response);
        }
    }
}
