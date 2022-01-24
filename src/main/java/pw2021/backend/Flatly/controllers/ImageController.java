package pw2021.backend.Flatly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pw2021.backend.Flatly.enities.Image;
import pw2021.backend.Flatly.services.ImageService;
import pw2021.backend.Flatly.services.SecurityProvider;

@RestController
@RequestMapping(path = "images")
public class ImageController {
    private final ImageService imageService;
    private SecurityProvider securityService;

    @Autowired
    public ImageController(ImageService imageService, SecurityProvider securityService) {
        this.imageService = imageService;
        this.securityService = securityService;
    }

    @PostMapping()
    public Image uploadImage(@RequestHeader HttpHeaders headers, @RequestParam MultipartFile image) throws Exception {
        this.securityService.checkAuthenticatedAndMainAdmin(headers);
        return this.imageService.upload(image.getBytes(), image.getOriginalFilename());
    }
}
