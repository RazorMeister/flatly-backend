package pw2021.backend.Flatly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pw2021.backend.Flatly.enities.Image;
import pw2021.backend.Flatly.services.ImageService;

@RestController
@RequestMapping(path = "images")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping()
    public Image uploadImage(@RequestParam MultipartFile image) throws Exception {
        return this.imageService.upload(image.getBytes(), image.getOriginalFilename());
    }
}
