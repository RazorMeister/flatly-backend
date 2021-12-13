package pw2021.backend.Flatly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw2021.backend.Flatly.enities.Image;
import pw2021.backend.Flatly.exceptions.NotFoundException;
import pw2021.backend.Flatly.repositories.FileSystemRepository;
import pw2021.backend.Flatly.repositories.ImageRepository;

import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final FileSystemRepository fileSystemRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, FileSystemRepository fileSystemRepository) {
        this.imageRepository = imageRepository;
        this.fileSystemRepository = fileSystemRepository;
    }

    public Image upload(byte[] bytes, String imageName) throws Exception {
        String location = this.fileSystemRepository.save(bytes, imageName);

        return this.imageRepository.save(new Image(location));
    }

    public Image getImage(Long id) throws NotFoundException {
        Optional<Image> imageOptional = this.imageRepository.findById(id);

        if (imageOptional.isEmpty()) {
            throw new NotFoundException(String.format("Image with id: %d does not exist", id));
        }

        return imageOptional.get();
    }
}
