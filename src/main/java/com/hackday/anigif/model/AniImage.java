package com.hackday.anigif.model;

import magick.MagickException;
import magick.MagickImage;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AniImage extends CommonImage {
    private MagickImage[] magickImageList;

    public byte[] makeGif(ArrayList<String> imgList, int delay) {
        int size = imgList.size();
        magickImageList = new MagickImage[size];
        InputStream is = null;
        String path = "C:/Users/jangh/Documents/uploads/";
        String gifPath = "C:/Users/jangh/Documents/results/";
        for (int i = 0; i < size; i++) {
            magickImageList[i] = openImg(path + imgList.get(i));
            try {
                magickImageList[i].setDelay(delay);
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

        try {
            gifImage.setFileName(gifPath + imgList.get(0) + "-animated.gif");
        } catch (MagickException e) {
            System.out.println("error while setting file name");
        }

        try {
            gifImage.writeImage(getInfo());
        } catch (MagickException e) {
            System.out.println("error while writing image");
        }

        try {
            is = new BufferedInputStream(new FileInputStream(gifPath + imgList.get(0) + "-animated.gif"));
        } catch (IOException e) {
            System.out.println("error while opening gif image");
        }

        byte[] gifByte = null;
        try {
            gifByte = IOUtils.toByteArray(is);
        } catch (IOException e) {
            System.out.println("error while converting to Array");

        }
        return gifByte;
    }

    public void mergeGif(ArrayList<String> imgList) {

    }

    public void breakGif(String gifImg) {

    }
}
