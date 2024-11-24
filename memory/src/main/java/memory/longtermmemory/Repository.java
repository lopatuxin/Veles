package memory.longtermmemory;

import java.util.Optional;

interface Repository<T, ID> {

    void save(T entity, String entityType);
    Optional<T> findById(ID id);
    void update(T entity);
    void delete(T entity);
}
