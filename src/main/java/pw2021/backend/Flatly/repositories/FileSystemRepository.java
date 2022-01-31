package pw2021.backend.Flatly.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Level;

@Repository
public class FileSystemRepository {
    Logger logger = LoggerFactory.getLogger(FileSystemRepository.class);

    public String save(byte[] content, String imageName) throws Exception {
        String fileName = new Date().getTime() + "-" + imageName;
        Path newFile = Paths.get(
                Paths.get("").toAbsolutePath().toString(),
                "target",
                "classes",
                "static",
                fileName
        );

        this.logger.error(newFile.getParent().toString());

        Files.createDirectories(newFile.getParent());

        Files.write(newFile, content);

        return fileName;
    }

    public void remove(String imageName) {
        try {
            Path path = Paths.get(
                    Paths.get("").toAbsolutePath().toString(),
                    "target",
                    "classes",
                    "static",
                    imageName
            );

            Files.delete(path);
        } catch (Exception ignored) {
            // Ignore exception
        }
    }
}