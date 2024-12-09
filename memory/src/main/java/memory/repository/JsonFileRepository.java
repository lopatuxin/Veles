package memory.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import memory.Entity;
import memory.config.PathConfig;
import memory.filememory.FileCreator;
import memory.filememory.JsonFileCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class JsonFileRepository implements Repository<Entity> {

    private final FileCreator<Entity> fileCreator = new JsonFileCreator<>();
    private static final Logger logger = LoggerFactory.getLogger(JsonFileRepository.class);

    @Override
    public List<Entity> processTokens(List<String> tokens) {
        return tokens.stream()
                .map(token -> {
                    Optional<Entity> optEntity = findByName(token);
                    return optEntity.orElse(save(createEntityByToken(token)));
                })
                .toList();
    }

    @Override
    public Optional<Entity> findByName(String name) {
        Path filePath = PathConfig.getPathForEntity(name);
        File file = filePath.toFile();

        if (file.exists()) {
            return mapToEntity(file);
        }

        logger.warn("Файл не найден: {}", filePath);
        return Optional.empty();
    }

    private Optional<Entity> mapToEntity(File file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Entity entity = objectMapper.readValue(file, Entity.class);
            return Optional.of(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Entity save(Entity entity) {
        try {
            Path path = PathConfig.getPathForEntity(entity.getType());
            fileCreator.createFile(path.toString(), entity);
            logger.info("Создан новый файл: {}", path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    private Entity createEntityByToken(String token) {
        return new Entity(token);
    }
}
