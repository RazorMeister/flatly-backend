package pw2021.backend.Flatly.repositories;

import org.springframework.stereotype.Repository;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Repository
public class FileSystemRepository {
    private Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public String save(byte[] content, String imageName) throws Exception {
        String extension = this.getExtensionByStringHandling(imageName).orElse("png");
        String fileName = Base64.getEncoder().withoutPadding().encodeToString((new Date().getTime() + "-" + imageName).getBytes()) + "." + extension;
        Path newFile = Paths.get(
                FileSystems.getDefault().getPath("").toAbsolutePath().toString(),
                "target",
                "classes",
                "static",
                fileName
        );

        Files.createDirectories(newFile.getParent());

        Files.write(newFile, content);

        return fileName;
    }

    public void remove(String imageName) {
        try {
            Path path = Paths.get(
                    FileSystems.getDefault().getPath("").toAbsolutePath().toString(),
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