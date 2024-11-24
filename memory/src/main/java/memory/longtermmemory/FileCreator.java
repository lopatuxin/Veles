package memory.longtermmemory;

import java.io.IOException;

public interface FileCreator<T> {
    void createFile(String path, T content) throws IOException;
}
