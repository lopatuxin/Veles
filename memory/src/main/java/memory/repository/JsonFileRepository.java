package memory.repository;

import memory.Entity;
import memory.config.PathConfig;
import memory.filememory.FileCreator;
import memory.filememory.JsonFileCreator;

import java.util.List;
import java.util.Optional;

public class JsonFileRepository implements Repository<Entity> {

    private final FileCreator<Entity> fileCreator = new JsonFileCreator<>();

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
        return Optional.empty();
    }

    @Override
    public Entity save(Entity entity) {
        try {
            fileCreator.createFile(PathConfig.getPathForEntity(entity.getType()).toString(), entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    private Entity createEntityByToken(String token) {
        return new Entity(token);
    }
}
