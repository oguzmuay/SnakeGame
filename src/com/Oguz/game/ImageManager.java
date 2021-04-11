package com.Oguz.game;

import com.Oguz.engine.gfx.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageManager {
    private Map<String, Image> images = new HashMap<>();
    public ImageManager()
    {
        images.put("headUp",new Image("/headUp.png"));
        images.put("headDown",new Image("/headDown.png"));
        images.put("headLeft",new Image("/headLeft.png"));
        images.put("headRight",new Image("/headRight.png"));
        images.put("body",new Image("/body.png"));
        images.put("portalEntry",new Image("/levelIndicator.png"));
        images.put("portalExit",new Image("/blankLevelIndicator.png"));
        images.put("brick",new Image("/brick.png"));
    }
    public Image getImage(String key)
    {
        return images.get(key);
    }
}
