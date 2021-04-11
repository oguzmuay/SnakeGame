package com.Oguz.engine.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Image {
    private int w,h;
    private int[] p;
    public Image(String path)
    {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Image.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        w = image.getWidth();
        h = image.getHeight();
        p = image.getRGB(0,0, w, h,null,0,w);
        image.flush();
    }
    public Image(int w,int h,int[] p)
    {
        this.w = w;
        this.h = h;
        this.p = p;
    }
    public Image(int w,int h)
    {
        this.w = w;
        this.h = h;
        this.p = null;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int[] getP() {
        return p;
    }

    public int getP(int index)
    {
        return p[index];
    }

    public void setP(int[] p) {
        this.p = p;
    }
}
