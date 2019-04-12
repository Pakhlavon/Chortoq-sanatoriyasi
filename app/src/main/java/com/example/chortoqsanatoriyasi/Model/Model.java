package com.example.chortoqsanatoriyasi.Model;

import android.widget.ImageView;

public class Model {

    private int Image;
    private String title;
    private String desc;

    public Model(int image, String title, String desc)
    {
        Image = image;
        this.title = title;
        this.desc = desc;
    }

    public int getImage()
    {
        return Image;
    }

    public void setImage(int image)
    {
        Image = image;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }
}
