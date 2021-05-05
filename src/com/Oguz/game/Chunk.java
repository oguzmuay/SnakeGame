package com.Oguz.game;

import java.util.ArrayList;

public class Chunk {
    private int posX,posY;
    private final int width = 16;
    private final int height=16;
    private ArrayList<GameObject> objects;
    private ArrayList<GameObject> colliders; //TODO: Bunlari gameobjcet yerine direkt collider olarak degistirebilir.
    private ArrayList<GameObject> tiles;

    public ArrayList<GameObject> getColliders() { //TODO: Sadece colliderlerle islem yapilicak suan.
        return colliders;
    }

    public void setTile(int index,GameObject object)
    {
        tiles.set(index,object);
    }
    public void SaveChunk()
    {
        // This function called when this chunk's updating period is over.
    }
}

