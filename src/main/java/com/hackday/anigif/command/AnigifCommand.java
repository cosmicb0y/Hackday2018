package com.hackday.anigif.command;

import com.hackday.anigif.model.ImageModel;
import magick.MagickException;
import magick.MagickImage;

public class AnigifCommand extends FileManager implements AniCommand {

    @Override
    public byte[] execute(ImageModel image){
        int size = image.getImageList().size();
        MagickImage[] magickImageList = new MagickImage[size];


        for (int i = 0; i < size; i++) {
            magickImageList[i] = openImg(image.getPath() + image.getImageList().get(i));
            try {
                magickImageList[i].setDelay(image.getDelay());
            } catch (MagickException e) {
                System.out.println("error while setting delay");
            }
        }

        MagickImage gifImage = null;
        try {
            gifImage = new MagickImage(magickImageList);
            gifImage.setIterations(0);
        } catch (MagickException e) {
            System.out.println("error while making gif");
        }

        writeImg(gifImage, image.getResultPath() +  getFileName(image.getImageName()) + "-animated.gif");

        byte[] gifByte = ImageToByte(image.getResultPath() +  getFileName(image.getImageName()) + "-animated.gif");


        return gifByte;
    }
}
