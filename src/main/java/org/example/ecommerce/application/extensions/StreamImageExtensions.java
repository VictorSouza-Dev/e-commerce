package org.example.ecommerce.application.extensions;

import org.example.ecommerce.application.dto.ImageValidationResult;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

public final class StreamImageExtensions {

    private StreamImageExtensions() {
    }

    public static ImageValidationResult validateAndGetImageExtension(byte[] imageBytes) {
        if (imageBytes == null || imageBytes.length == 0) {
            return new ImageValidationResult(false, null);
        }

        try (var byteArrayStream = new ByteArrayInputStream(imageBytes);
             var input = ImageIO.createImageInputStream(byteArrayStream)) {

            if (input == null) {
                return new ImageValidationResult(false, null);
            }

            Iterator<ImageReader> readers = ImageIO.getImageReaders(input);

            if (!readers.hasNext()) {
                return new ImageValidationResult(false, null);
            }

            ImageReader reader = readers.next();
            String format = reader.getFormatName();

            reader.dispose();

            return new ImageValidationResult(true, format.toLowerCase());

        } catch (IOException ex) {
            return new ImageValidationResult(false, null);
        }
    }
}