package by.zverugo.samsolutions.instagram.util.enums;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public enum PicturesByteArrayEnum {
    //TODO photo reference
    DEFAULT_AVATAR("/photo/default_avatar.png");

    String path;

    PicturesByteArrayEnum(String path) {
        this.path = path;
    }

    public byte[] getByte() {
        File image = new File(path);
        byte[] pictureByte = new byte[0];
        try {
            pictureByte = Files.readAllBytes(image.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pictureByte;
    }
}
