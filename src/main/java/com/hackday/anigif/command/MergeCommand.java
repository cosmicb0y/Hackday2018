package com.hackday.anigif.command;


import com.hackday.anigif.model.ImageModel;
import magick.MagickException;
import magick.MagickImage;

public class MergeCommand extends FileManager implements AniCommand {

    @Override
    public byte[] execute(ImageModel image) {
        int size = image.getImageList().size();
        MagickImage[] magickImageList = new MagickImage[size];
        int width;
        int height;

        for (int i = 0; i < size; i++) {
            magickImageList[i] = openImg(image.getPath() + image.getImageList().get(i));
        }


        MagickImage gifImage = null;
        try {
            width = Math.min(magickImageList[0].getDimension().width, magickImageList[1].getDimension().width);
            height = Math.min(magickImageList[0].getDimension().height, magickImageList[1].getDimension().height);

            size = magickImageList[0].getNumberImages() + magickImageList[1].getNumberImages();

            MagickImage[] gif1 = magickImageList[0].breakFrames();
            MagickImage[] gif2 = magickImageList[1].breakFrames();

            magickImageList = new MagickImage[size];

            System.arraycopy(gif1, 0, magickImageList, 0, gif1.length);
            System.arraycopy(gif2, 0, magickImageList, gif1.length, gif2.length);

            for (int i = 0; i < size; i++) {
                magickImageList[i] = magickImageList[i].scaleImage(width, height);
            }
            gifImage = new MagickImage(magickImageList);
            gifImage.setIterations(0);
            gifImage.setFileName(image.getResultPath() + getFileName(image.getImageList().get(0)) + "+" + getFileName(image.getImageList().get(1)) + "-merged.gif");
        } catch (MagickException e) {
            System.out.println("error while making gif");
        }

        writeImg(gifImage, image.getResultPath() + getFileName(image.getImageList().get(0)) + "+" + getFileName(image.getImageList().get(1)) + "-merged.gif");

        byte[] gifByte = ImageToByte(image.getResultPath() + getFileName(image.getImageList().get(0)) + "+" + getFileName(image.getImageList().get(1)) + "-merged.gif");


        return gifByte;

    }
}
