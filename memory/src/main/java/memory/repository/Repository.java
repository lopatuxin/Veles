package memory.repository;

import memory.Entity;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    List<T> processTokens(List<String> tokens);
    Optional<T> findByName(String name);
    Entity save(Entity entity);
}
