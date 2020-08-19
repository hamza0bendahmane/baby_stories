package com.example.baby_stories;

public class GridClass {
    private String tx;
    private int imgId;

    public GridClass(String tx, int imgId) {
        this.tx = tx;
        this.imgId = imgId;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
