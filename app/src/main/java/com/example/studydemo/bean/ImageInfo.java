package com.example.studydemo.bean;

public class ImageInfo {
    private String imagePath;
    private String addDate;
    private String height;
    private String width;
    private String size;
    private String title;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "imagePath='" + imagePath + '\'' +
                ", addDate='" + addDate + '\'' +
                ", height='" + height + '\'' +
                ", width='" + width + '\'' +
                ", size='" + size + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
