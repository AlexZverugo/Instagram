package by.zverugo.samsolutions.instagram.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Alex on 03.11.2015.
 */
public class ImageDTO {
    private MultipartFile photo;

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
