package memory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Entity {

    private String type;
    private String name;
    private Map<String, String> properties;

    @JsonCreator
    public Entity(@JsonProperty("type") String type,
                  @JsonProperty("name") String name,
                  @JsonProperty("properties") Map<String, String> properties) {
        this.type = type;
        this.name = name;
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
