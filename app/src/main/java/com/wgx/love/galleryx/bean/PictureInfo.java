package com.wgx.love.galleryx.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by wugx on 17-9-4.
 */

public class PictureInfo {
    private String id;
    private Drawable Img;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Drawable getImg() {
        return Img;
    }

    public void setImg(Drawable img) {
        Img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
