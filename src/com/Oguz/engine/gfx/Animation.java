package com.Oguz.engine.gfx;

import java.util.ArrayList;

public class Animation {
    private float currentFrame;
    private float speed;
    private ArrayList<Image> frames;

    public Animation()
    {
        frames = new ArrayList<Image>();
    }

    public Animation(ArrayList<Image> frames)
    {
        this.frames = frames;
    }

    public Animation(ArrayList<Image> frames,int speed)
    {
        this.frames = frames;
    }

    public int getFrameCount()
    {
        return frames.toArray().length;
    }

    public Image getFrame(int index)
    {
        return frames.get(index);
    }

    public float getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(float currentFrame) {
        this.currentFrame = currentFrame;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
