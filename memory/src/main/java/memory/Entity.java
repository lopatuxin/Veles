package memory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity {

    private final String type;
    private final Map<String, List<String>> connections;

    @JsonCreator
    public Entity(@JsonProperty("type") String type) {
        this.type = type;
        this.connections = new HashMap<>();
    }

    public String getType() {
        return type;
    }

    public void addConnection(String connectionType, String targetType) {
        connections.computeIfAbsent(connectionType, k -> new ArrayList<>()).add(targetType);
    }

    public List<String> getConnections(String connectionType) {
        return connections.getOrDefault(connectionType, new ArrayList<>());
    }

    public Map<String, List<String>> getAllConnections() {
        return connections;
    }
}
