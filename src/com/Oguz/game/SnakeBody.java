package com.Oguz.game;

import com.Oguz.engine.Renderer;
import com.Oguz.engine.Vector.Vector2D;

public class SnakeBody {

    private Vector2D pos;
    private Vector2D lastDir;

    static {
        new Vector2D(1, 0);
    }

    public SnakeBody(Vector2D pos,Vector2D lastDir)
    {
        this.pos = pos;
        this.lastDir = lastDir;
    }
    public void Render(Renderer renderer,GameManager gm)
    {
        renderer.DrawImage(gm.imageManager.getImage("body"),(int)pos.x,(int)pos.y);
    }

    public Vector2D getPos() {
        return pos;
    }

    public Vector2D getLastDir() {
        return lastDir;
    }

    public void setPos(Vector2D pos) {
        this.pos = pos;
    }

    public void setLastDir(Vector2D lastDir) {
        this.lastDir = lastDir;
    }
}
