package org.example.ecommerce.infraestructure.services.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.example.ecommerce.domain.services.Storage.ImageStoragePort;
import org.example.ecommerce.infraestructure.config.CloudinaryConfig;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
public class CloudinaryStorageService implements ImageStoragePort {

    private final Cloudinary cloudinary;

    public CloudinaryStorageService(
            CloudinaryConfig cloudinaryConfig
    ) {
        this.cloudinary = cloudinaryConfig.getCloudinary();
    }

    @Override
    public String upload(byte[] image, UUID itemId) {

        Map<String, Object> options = ObjectUtils.asMap(
                "folder", "items/" + itemId
        );

        try{
            var uploadResult = cloudinary.uploader().upload(image, options);
            return (String) uploadResult.get("secure_url");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID itemId) {
        try{
            cloudinary.uploader().destroy(
                    "/items" + itemId,
                    ObjectUtils.emptyMap()
            );
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}