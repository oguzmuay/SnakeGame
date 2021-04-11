package com.Oguz.engine.UI;

import com.Oguz.engine.GameContainer;
import com.Oguz.engine.Renderer;
import com.Oguz.game.GameManager;

import java.util.ArrayList;

public abstract class AbstractUserInterface {
    public float posX,posY;
    public int width,height;
    public int color;
    public ArrayList<AbstractUserInterface> children;
    public boolean enable = true;
    public abstract void OnMousePressed(GameContainer gc);
    public abstract void OnMouseReleased(GameContainer gc);
    public abstract void OnMouseClicked(GameContainer gc);
    public abstract void Update(GameContainer gc, GameManager gm);
    public abstract void Render(GameContainer gc, GameManager gm, Renderer renderer);
    public abstract void AddChild(AbstractUserInterface child);
    public abstract AbstractUserInterface GetMaster();
}
