package memory.longtermmemory;

import com.fasterxml.jackson.databind.ObjectMapper;
import memory.Entity;

import java.io.File;
import java.io.IOException;

public class JsonFileCreator implements FileCreator<Entity> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void createFile(String path, Entity content) throws IOException {
        File file = new File(path);

        createParentDirectories(file);

        ensureFileExists(file);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, content);
    }

    private void createParentDirectories(File file) throws IOException {
        if (needsToCreateParentDirectories(file)) {
            throw new IOException("Не удалось создать директории: " + file.getParentFile().getAbsolutePath());
        }
    }

    private boolean needsToCreateParentDirectories(File file) {
        File parentFile = file.getParentFile();
        return parentFile != null && !parentFile.exists() && !parentFile.mkdirs();
    }

    private void ensureFileExists(File file) throws IOException {
        if (!createFileIfNotExists(file)) {
            throw new IOException("Не удалось создать файл: " + file.getAbsolutePath());
        }
    }

    private boolean createFileIfNotExists(File file) throws IOException {
        return file.exists() || file.createNewFile();
    }
}
