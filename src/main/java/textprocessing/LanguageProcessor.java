package textprocessing;

import memory.Entity;
import memory.KnowledgeBase;

import java.util.List;
import java.util.stream.Collectors;

public class LanguageProcessor {

    public String process(String input, KnowledgeBase knowledgeBase) {

        if (input.contains("привет")) {
            return "Привет! Как я могу помочь?";
        }

        List<Entity> foundEntities = knowledgeBase.findEntitiesByQuery(input);
        if (!foundEntities.isEmpty()) {
            return formatEntitiesResponse(foundEntities);
        }

        return "Я не знаю ответа на этот вопрос. Хотите добавить информацию об этом в базу знаний?";
    }

    private String formatEntitiesResponse(List<Entity> entities) {
        return entities.stream()
                .map(entity -> "Тип: " + entity.getType() + "\n" +
                        "Название: " + entity.getName() + "\n" +
                        "Свойства:\n" +
                        entity.getProperties().entrySet().stream()
                                .map(entry -> entry.getKey() + ": " + entry.getValue())
                                .collect(Collectors.joining("\n")))
                .collect(Collectors.joining("\n\n"));
    }
}
