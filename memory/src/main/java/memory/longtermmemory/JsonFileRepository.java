package memory.longtermmemory;

import memory.config.PathConfig;
import memory.exceptions.FileSaveException;

import java.io.IOException;
import java.util.Optional;

public class JsonFileRepository<T, ID> implements Repository<T, ID> {

    private final FileCreator<T> fileCreator = new JsonFileCreator<>();

    @Override
    public void save(T entity, String entityType) {
        try {
            fileCreator.createFile(PathConfig.getPathForEntity(entityType).toString(), entity);
        } catch (IOException e) {
            throw new FileSaveException("Ошибка при сохранении файла для типа: " + entityType, e);
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(T entity) {

    }
}
