package pw2021.backend.Flatly.repositories;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Repository
public class FileSystemRepository {
    public String save(byte[] content, String imageName) throws Exception {
        String fileName = new Date().getTime() + "-" + imageName;
        Path newFile = Paths.get(
                Paths.get("").toAbsolutePath().toString(),
                "target",
                "classes",
                "static",
                fileName
        );

        Files.createDirectories(newFile.getParent());

        Files.write(newFile, content);

        return fileName;
    }

    public void remove(String imageName) throws IOException {
        Path path = Paths.get(
                Paths.get("").toAbsolutePath().toString(),
                "target",
                "classes",
                "static",
                imageName
        );

        Files.delete(path);
    }
}