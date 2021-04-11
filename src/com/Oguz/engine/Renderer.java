package com.Oguz.engine;

import com.Oguz.engine.UI.AbstractUserInterface;
import com.Oguz.engine.gfx.Font;
import com.Oguz.engine.gfx.Image;
import com.Oguz.engine.gfx.ImageTile;

import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Renderer {
    private int pW,pH;
    private int[] p;
    private int alphaColor;
    private final Font font = Font.STANDART;
    public Renderer(GameContainer gc)
    {
        pW = gc.getWidth();
        pH = gc.getHeight();
        p = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
        alphaColor = gc.getAlphaColor();
    }
    public void Clear()
    {
        Arrays.fill(p, 0);
    }

    public void SetPixel(int x ,int y, int value)
    {
        if (x < 0 || x >= pW || y < 0 || y >= pH || value == alphaColor){return;}
        p[x+y*pW] = value;
    }

    public void DrawImage(Image image, int offX, int offY)
    {
        if(offX < -image.getW())return;
        if(offY < -image.getH())return;
        if(offX >= pW)return;
        if(offY >= pH)return;

        int newX = 0;
        int newY = 0;
        int newW = image.getW();
        int newH = image.getH();

        if(offX < 0) { newX -= offX; }
        if(offY < 0) { newY -= offY; }
        if(newW+offX > pW) { newW -= newW+offX - pW; }
        if(newH+offY > pH) { newH -= newH+offY - pH; }

        for (int y = newY; y < newH; y++) {
            for (int x = newX; x < newW; x++) {
                SetPixel(x+offX,y+offY,image.getP()[x+y* image.getW()]);
            }
        }
    }
    public void DrawImageTile(ImageTile image, int offX, int offY,int tileX,int tileY)
    {
        if(offX < -image.getTileW())return;
        if(offY < -image.getTileH())return;
        if(offX >= pW)return;
        if(offY >= pH)return;

        int newX = 0;
        int newY = 0;
        int newW = image.getTileW();
        int newH = image.getTileH();

        if(offX < 0) { newX -= offX; }
        if(offY < 0) { newY -= offY; }
        if(newW+offX > pW) { newW -= newW+offX - pW; }
        if(newH+offY > pH) { newH -= newH+offY - pH; }

        for (int y = newY; y < newH; y++) {
            for (int x = newX; x < newW; x++) {
                SetPixel(x+offX,y+offY,image.getP()[(x+tileX*image.getTileW())+(y+tileY*image.getTileH())* image.getW()]);
            }
        }
    }
    public void DrawRect(int offX, int offY,int width,int height,int color) {

        if(offX < -width)return;
        if(offY < -height)return;
        if(offX >= pW)return;
        if(offY >= pH)return;

        int newX = 0;
        int newY = 0;
        int newW = width;
        int newH = height;

        if(offX < 0) { newX -= offX; }
        if(offY < 0) { newY -= offY; }
        if(newW+offX > pW) { newW -= newW+offX - pW; }
        if(newH+offY > pH) { newH -= newH+offY - pH; }

        for (int y = newY; y < newH; y++) {
            for (int x = newX; x < newW; x++) {
                SetPixel(x + offX, y + offY, color);
            }
        }
    }
    public void DrawRect(int offX, int offY,int width,int height,int color,int thickness) {
        if(thickness <= 0)return;
        if(offX < -width)return;
        if(offY < -height)return;
        if(offX >= pW)return;
        if(offY >= pH)return;

        int newX = 0;
        int newY = 0;
        int newW = width;
        int newH = height;

        if(offX < 0) { newX -= offX; }
        if(offY < 0) { newY -= offY; }
        if(newW+offX > pW) { newW -= newW+offX - pW; }
        if(newH+offY > pH) { newH -= newH+offY - pH; }

        for (int y = newY; y < newH; y++) {
            for (int x = newX; x < newW; x++) {
                if((x >= offX && x <= offX+thickness) || (x >= offX+width-1-thickness && x <= offX+width) ||
                        (y >= offY && y <= offY+thickness) || (y >= offY+height-1-thickness && y <= offY+height))
                SetPixel(x + offX, y + offY, color);
            }
        }
    }
    public void DrawRect(int offX, int offY,int width,int height,int color,int thickness,int radius) {
        //TODO:Pixelin merkezden uzakligina bakicam ve ona gore renderlayacagim.
        if(thickness <= 0)return;
        if(offX < -width)return;
        if(offY < -height)return;
        if(offX >= pW)return;
        if(offY >= pH)return;

        int newX = 0;
        int newY = 0;
        int newW = width;
        int newH = height;

        if(offX < 0) { newX -= offX; }
        if(offY < 0) { newY -= offY; }
        if(newW+offX > pW) { newW -= newW+offX - pW; }
        if(newH+offY > pH) { newH -= newH+offY - pH; }

        for (int y = newY; y < newH; y++) {
            for (int x = newX; x < newW; x++) {
                if((x >= offX && x <= offX+thickness) || (x >= offX+width-1-thickness && x <= offX+width) ||
                        (y >= offY && y <= offY+thickness) || (y >= offY+height-1-thickness && y <= offY+height))
                    SetPixel(x + offX, y + offY, color);
            }
        }
    }
    public void DrawToCanvasRect(AbstractUserInterface Canvas, int offX, int offY, int width, int height, int color)
    {
        //TODO: Canvas'a gore poziyonunu belirleyip canvas'in icerisinde ise
    }
    public void DrawText(String text, int offsetX, int offsetY,int color)
    {
        text = text.toUpperCase();

        int offSet = 0;

        for (int i = 0; i < text.length(); i++) {
            int unicode = text.codePointAt(i) - 32;
            for (int y = 0; y < font.getFontImage().getH(); y++) {
                for (int x = 0; x < font.getWidths()[unicode]; x++) {
                    if(font.getFontImage().getP()[(x+font.getOffsets()[unicode])+y*font.getFontImage().getW()] == 0xffffffff){
                        SetPixel(x+offsetX+offSet,y+offsetY,color);
                    }
                }
            }
            offSet += font.getWidths()[unicode];
        }
    }
}
