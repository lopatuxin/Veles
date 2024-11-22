package memory;

import java.util.*;

public class KnowledgeBase {

    private final Map<String, Entity> entities;

    public KnowledgeBase() {
        this.entities = new HashMap<>();
    }

    public Entity addEntity(String type) {
        return entities.computeIfAbsent(type, Entity::new);
    }

    public void addConnection(String fromType, String connectionType, String toType) {
        Entity fromEntity = addEntity(fromType);
        fromEntity.addConnection(connectionType, toType);
    }

    public List<String> getConnections(String type, String connectionType) {
        Set<String> result = new HashSet<>();
        findConnectionsRecursively(type, connectionType, result);
        return new ArrayList<>(result);
    }

    private void findConnectionsRecursively(String type, String connectionType, Set<String> result) {
        Entity entity = entities.get(type);
        if (entity == null) return;

        List<String> directConnections = entity.getConnections(connectionType);
        for (String connection : directConnections) {
            if (!result.contains(connection)) {
                result.add(connection);
                findConnectionsRecursively(connection, connectionType, result);
            }
        }
    }
}
