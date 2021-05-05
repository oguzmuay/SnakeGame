package com.Oguz.engine.gfx;

import java.util.HashMap;
import java.util.Map;

public class ImageManager {
    private Map<String, Image> images = new HashMap<>();
    private Map<Integer,Image> tiles = new HashMap<>();
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
    public Image getImage(String key,String palette)
    {
        return switch (palette) {
            case "tile" -> tiles.get(Integer.parseInt(key));
            case "image" -> images.get(key);
            default -> null;
        };
    }
}
