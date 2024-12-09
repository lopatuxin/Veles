package core;

import memory.Entity;
import memory.repository.Repository;
import tokenizer.Tokenizer;
import ui.ConsoleHandler;

import java.util.List;

public class AI {

    private final ConsoleHandler consoleHandler;
    private final Tokenizer tokenizer;
    private final Repository<Entity> repository;

    public AI(Tokenizer tokenizer, Repository<Entity> repository) {
        this.consoleHandler = new ConsoleHandler();
        this.tokenizer = tokenizer;
        this.repository = repository;
    }

    public void start() {
        consoleHandler.sayHello();

        while (true) {
            String input = consoleHandler.readInput();
            List<String> tokens = tokenizer.tokenize(input);
            List<Entity> entities = repository.processTokens(tokens);
        }
    }
}
