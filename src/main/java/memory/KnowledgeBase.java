package memory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import textprocessing.TextTokenizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class KnowledgeBase {

    private static final String FILE_PATH = "knowledge.json";
    private List<Entity> entities;

    public KnowledgeBase() {
        loadKnowledge();
    }

    private void loadKnowledge() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                entities = objectMapper.readValue(file, new TypeReference<>() {
                });
            } else {
                entities = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            entities = new ArrayList<>();
        }
    }

    public Optional<Entity> findEntity(String name) {
        return entities.stream()
                .filter(entity -> entity.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<Entity> findEntitiesByQuery(String query) {
        List<String> tokens = new TextTokenizer().tokenize(query);

        return entities.stream()
                .filter(entity ->
                        tokens.stream()
                                .anyMatch(token ->
                                        entity.getName().toLowerCase().contains(token) ||
                                                entity.getType().toLowerCase().contains(token) ||
                                                entity.getProperties().values().stream()
                                                        .anyMatch(value -> value.toLowerCase().contains(token))))
                .toList();
    }

    public void addEntity(String type, String name, Map<String, String> properties) {
        Entity newEntity = new Entity(type, name, properties);
        entities.add(newEntity);
        saveKnowledge();
    }

    private void saveKnowledge() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(FILE_PATH), entities);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
