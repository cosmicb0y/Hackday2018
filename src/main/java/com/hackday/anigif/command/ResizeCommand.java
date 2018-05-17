package com.hackday.anigif.command;

import com.hackday.anigif.model.ImageModel;
import magick.MagickException;
import magick.MagickImage;

public class ResizeCommand extends FileManager implements AniCommand {
    @Override
    public byte[] execute(ImageModel image) {
        MagickImage magickImage = openImg(image.getPath() + image.getImageName());
        try {
            if (magickImage.isAnimatedImage()) {
                MagickImage[] magickImagesList = magickImage.breakFrames();
                MagickImage resizedImg;
                for (int i = 0; i < magickImagesList.length; i++) {
                    magickImagesList[i] = magickImagesList[i].scaleImage(image.getWidth(), image.getHeight());
                    }
                resizedImg = new MagickImage(magickImagesList);
                resizedImg.setFileName(image.getResultPath() + getFileName(image.getImageName()) + "-resized.gif");
                writeImg(resizedImg, image.getResultPath() + getFileName(image.getImageName()) + "-resized.gif");
            }
        } catch (MagickException e) {

        }

        byte[] gifByte = ImageToByte(image.getResultPath() + getFileName(image.getImageName()) + "-resized.gif");

        return gifByte;
    }
}
