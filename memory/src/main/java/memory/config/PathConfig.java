package memory.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathConfig {

    private PathConfig() {
    }

    private static final String BASE_DIRECTORY = "storage";

    public static Path getPathForEntity(String entityType) {
        return Paths.get(BASE_DIRECTORY, entityType + ".json");
    }
}
