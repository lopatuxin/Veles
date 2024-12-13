package core;

import memory.Entity;
import memory.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tokenizer.Tokenizer;
import ui.UserInterface;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AI {
    private static final Logger logger = LoggerFactory.getLogger(AI.class);
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final UserInterface userInterface;
    private final Tokenizer tokenizer;
    private final Repository<Entity> repository;
    private volatile boolean isRunning = true;

    public AI(Tokenizer tokenizer, Repository<Entity> repository, UserInterface userInterface) {
        this.tokenizer = tokenizer;
        this.repository = repository;
        this.userInterface = userInterface;
    }

    public void start() {
        logger.info("Запуск метода start");
        startUIThread();
        runMainLoop();
    }

    private void startUIThread() {
        executorService.submit(() -> {
            try {
                logger.info("Запуск пользовательского интерфейса");
                userInterface.startUI();
            } catch (Exception e) {
                logger.error("Ошибка при запуске UI: {}", e.getMessage(), e);
            }
        });
    }

    private void runMainLoop() {
        while (isRunning) {
            try {
                logger.debug("Ожидание ввода пользователя");
                String input = userInterface.getInput();
                processInput(input);
            } catch (Exception e) {
                logger.error("Ошибка в основном цикле: {}", e.getMessage(), e);
            }
        }
    }

    private void processInput(String input) {
        logger.info("Обработка ввода пользователя");
        List<String> tokens = tokenizer.tokenize(input);
        logger.debug("Токены: {}", tokens);

        List<Entity> entities = repository.processTokens(tokens);
        entities.forEach(entity -> logger.debug("Обработанная сущность: {}", entity));
    }

    public void stop() {
        logger.info("Завершение работы AI");
        isRunning = false;
        executorService.shutdown();
    }
}
