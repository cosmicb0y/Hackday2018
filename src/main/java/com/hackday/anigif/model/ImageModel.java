package com.hackday.anigif.model;

import magick.MagickImage;

import java.util.ArrayList;

public class ImageModel {

    private String imageName;
    String[] extentionList = {"jpg", "png", "gif"};
    private String path = "C:/Users/jangh/Documents/uploads/";
    private String resultPath = "C:/Users/jangh/Documents/results/";
    private ArrayList<String> imageList;
    private int delay;
    private int width;
    private int height;


    public ImageModel(ArrayList<String> imageList) {
        this.imageList = imageList;
    }
    public ImageModel(ArrayList<String> imageList, int delay) {
        this.imageList = imageList;
        this.delay = delay;
    }

    public ImageModel(String image, int width, int height) {
        this.imageName = image;
        this.width = width;
        this.height = height;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
