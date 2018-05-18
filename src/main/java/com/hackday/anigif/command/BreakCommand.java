package com.hackday.anigif.command;

import com.hackday.anigif.model.ImageModel;
import magick.MagickException;
import magick.MagickImage;


public class BreakCommand extends FileManager {

    public int execute(ImageModel image) {
        MagickImage magickImage = openImg(image.getPath() + image.getImageName());
        MagickImage[] magickImageList = null;
        try {
            magickImageList = magickImage.breakFrames();
        } catch (MagickException e) {
            System.out.println("error while breaking frames");
        }

        for (int i = 0; i < magickImageList.length; i++) {
            try {
                magickImageList[i].setFileName(image.getResultPath() + getFileName(image.getImageName()) + "-" + String.format("%03d", i + 1)+ ".jpg");
                writeImg(magickImageList[i], image.getResultPath() + getFileName(image.getImageName()) + "-" + String.format("%03d", i + 1)+ ".jpg");
            } catch (MagickException e) {
                System.out.println("error while setting & write Image");
            }
        }

        return magickImageList.length;
    }
}
