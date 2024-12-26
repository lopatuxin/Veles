package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tokenizer.TextTokenizer;
import tokenizer.Tokenizer;
import util.Decoder;
import util.Encoder;
import util.SpeechPartDeterminer;

import java.util.List;

public class AI {
    private static final Logger logger = LoggerFactory.getLogger(AI.class);
    private final Tokenizer tokenizer;
    private final SpeechPartDeterminer speechPartDeterminer = new SpeechPartDeterminer(33, 16, 12);

    public AI() {
        this.tokenizer = new TextTokenizer();
    }

    public String processInput(String input) {
        logger.info("Обработка ввода пользователя");
        List<String> tokens = tokenizer.tokenize(input);
        logger.debug("Токены: {}", tokens);

        return processSpeechPart(tokens);

//        List<Entity> entities = repository.processTokens(tokens);
//        entities.forEach(entity -> logger.debug("Обработанная сущность: {}", entity));
    }

    private String processSpeechPart(List<String> tokens) {
        String token = tokens.get(0);
        double[] inputVector = Encoder.encodeWord(token);
        double[] output = speechPartDeterminer.forward(inputVector);

        return Decoder.interpretOutput(output);
    }
}
