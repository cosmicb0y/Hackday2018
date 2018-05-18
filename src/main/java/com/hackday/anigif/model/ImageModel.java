package com.hackday.anigif.model;

import magick.MagickImage;

import java.util.ArrayList;

public class ImageModel {

    private MagickImage image = null;
    String[] extentionList = {"jpg", "png", "gif"};
    private String path = "C:/Users/jangh/Documents/uploads/";
    private String resultPath = "C:/Users/jangh/Documents/results/";
    private ArrayList<String> imageList;
    private int delay;

    public ImageModel(ArrayList<String> imageList, int delay) {
        this.imageList = imageList;
        this.delay = delay;
    }

    public MagickImage getImage() {
        return image;
    }

    public void setImage(MagickImage image) {
        this.image = image;
    }

    public String[] getExtentionList() {
        return extentionList;
    }

    public String getPath() {
        return path;
    }


    public String getResultPath() {
        return resultPath;
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
