package com.Oguz.game;

import com.Oguz.engine.GameContainer;
import com.Oguz.engine.Renderer;
import com.Oguz.engine.Vector.Vector2D;

public class Portal {
    public Vector2D entry,exit;
    public Portal(Vector2D entry,Vector2D exit)
    {
        this.entry = entry;
        this.exit = exit;
    }
    public void Render(Renderer renderer, GameManager gm, GameContainer gc)
    {
        renderer.DrawImage(gm.imageManager.getImage("portalEntry"),(int)entry.x,(int)entry.y);
        renderer.DrawImage(gm.imageManager.getImage("portalExit"),(int)exit.x,(int)exit.y);
    }
    public void Update(GameContainer gc, GameManager gm, float dt)
    {
        if(entry.x == gm.getSnake().posX && entry.y == gm.getSnake().posY)
        {
            gm.getSnake().posX = exit.x+8*gm.getSnake().getMovDir().x;
            gm.getSnake().posY = exit.y+8*gm.getSnake().getMovDir().y;
        }
    }
}
