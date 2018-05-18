package com.hackday.anigif.command;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileManager {
    public MagickImage openImg(String name) {
        ImageInfo info;
        MagickImage image;
        try {
            info = new ImageInfo(name);
            image = new MagickImage(info);
        } catch (MagickException e) {
            System.out.println("error while opening file");
            return null;
        }
        return image;
    }

    public void writeImg(MagickImage magickImage, String path) {
        ImageInfo info;

        try {
            info = new ImageInfo(path);
            magickImage.writeImage(info);
        } catch (MagickException e) {
            System.out.println("error while writing file");
        }
    }

    public byte[] ImageToByte(String path) {
        byte[] gifByte = null;
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(path));
        } catch (IOException e) {
            System.out.println("error while opening gif image");
        }

        try {
            gifByte = IOUtils.toByteArray(is);
        } catch (IOException e) {
            System.out.println("error while converting to Array");
        }

        return gifByte;
    }

}
