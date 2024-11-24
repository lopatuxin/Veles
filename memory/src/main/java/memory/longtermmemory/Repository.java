package memory.longtermmemory;

import java.util.Optional;

interface Repository<T, ID> {

    void save(ID id, T entity);
    Optional<T> findById(ID id);
    void update(T entity);
    void delete(T entity);
}
