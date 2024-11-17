package textprocessing;

import memory.Entity;
import memory.KnowledgeBase;

import java.util.List;
import java.util.Optional;

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

        Optional<Entity> foundEntity = knowledgeBase.findEntity(input);
        if (foundEntity.isPresent()) {
            Entity entity = foundEntity.get();

            return "Вот что я знаю о " + entity.getName() + ": " + entity.getProperties();
        }

        return "Я не знаю ответа на этот вопрос. Хотите добавить информацию об этом в базу знаний?";
    }
}
