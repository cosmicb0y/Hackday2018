package com.hackday.anigif.model;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

import java.util.ArrayList;

public class CommonImage {
    public ImageInfo getInfo() {
        return info;
    }

    public void setInfo(ImageInfo info) {
        this.info = info;
    }

    public MagickImage getImage() {
        return image;
    }

    public void setImage(MagickImage image) {
        this.image = image;
    }

    public ArrayList<String> getExtentionList() {
        return extentionList;
    }

    public void setExtentionList(ArrayList<String> extentionList) {
        this.extentionList = extentionList;
    }

    private ImageInfo info = null;
    private MagickImage image = null;
    ArrayList<String> extentionList;

    public MagickImage openImg(String name) {
        try {
            info = new ImageInfo(name);
            image = new MagickImage(info);
        } catch (MagickException e) {
            System.out.println("error while opening file");
        }
        return image;
    }
}
