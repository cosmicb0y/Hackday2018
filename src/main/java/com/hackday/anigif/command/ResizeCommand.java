package com.hackday.anigif.command;

import com.hackday.anigif.model.ImageModel;
import magick.MagickException;
import magick.MagickImage;
import org.apache.commons.io.FilenameUtils;

public class ResizeCommand extends FileManager implements AniCommand {
    @Override
    public byte[] execute(ImageModel image) {
        MagickImage magickImage = openImg(image.getPath() + image.getImageName());
        byte[] gifByte = null;
        try {
            if (magickImage.isAnimatedImage()) {
                MagickImage[] magickImagesList = magickImage.breakFrames();
                for (int i = 0; i < magickImagesList.length; i++) {
                    magickImagesList[i] = magickImagesList[i].scaleImage(image.getWidth(), image.getHeight());
                    }
                magickImage = new MagickImage(magickImagesList);
                magickImage.setFileName(image.getResultPath() + getFileName(image.getImageName()) + "-resized.gif");
                writeImg(magickImage, image.getResultPath() + getFileName(image.getImageName()) + "-resized.gif");
                gifByte = ImageToByte(image.getResultPath() + getFileName(image.getImageName()) + "-resized.gif");
            } else {
                magickImage = magickImage.scaleImage(image.getWidth(), image.getHeight());
                magickImage.setFileName(image.getResultPath() + getFileName(image.getImageName()) + "-resized." + FilenameUtils.getExtension(image.getImageName()));
                writeImg(magickImage, image.getResultPath() + getFileName(image.getImageName()) + "-resized." + FilenameUtils.getExtension(image.getImageName()));
                gifByte = ImageToByte(image.getResultPath() + getFileName(image.getImageName()) + "-resized." + FilenameUtils.getExtension(image.getImageName()));
            }
        } catch (MagickException e) {
            System.out.println("error while resizing image");
        }

        return gifByte;
    }
}
