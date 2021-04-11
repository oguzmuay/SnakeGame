package com.Oguz.engine.gfx;

import java.util.ArrayList;
import java.util.Arrays;

public class ImageTile extends Image {
    private int tileW, tileH;
    public ImageTile(String path,int tileW,int tileH) {
        super(path);
        this.tileW = tileW;
        this.tileH = tileH;
    }

    public int getTileW() {
        return tileW;
    }

    public void setTileW(int tileW) {
        this.tileW = tileW;
    }

    public int getTileH() {
        return tileH;
    }

    public void setTileH(int tileH) {
        this.tileH = tileH;
    }

    public static ArrayList<Image> AnimationParser(ImageTile image,int space)
    {
        var frames = new ArrayList<Image>();
        for (int i = 0; i < image.getW()/image.getTileW(); i++) {
            Image tempImage = new Image(image.getTileW(),image.getTileH());
            int[] p = new int[image.getTileH() * image.getTileW()];
            for (int y = 0; y < image.getTileH(); y++) {
                for (int x = 0; x < image.getTileW(); x++) {
                    p[x+(y*image.getTileW())] = image.getP(x+(image.getTileW()*i)+y* image.getW() + space*i);
                }
            }
            System.out.println(15+(image.getTileW()*i)+0* image.getW() + space*i);
            tempImage.setP(p);
            frames.add(tempImage);
        }
        return frames;
    }
}
