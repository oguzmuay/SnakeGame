package com.Oguz.engine.UI;

import com.Oguz.engine.GameContainer;
import com.Oguz.engine.Renderer;
import com.Oguz.game.GameManager;

import java.util.ArrayList;

public class Panel extends AbstractUserInterface{
    private AbstractUserInterface master;
    private Canvas canvas;
    private int thickness;
    public Panel(AbstractUserInterface master,Canvas canvas,float posX,float posY,int width,int height,int color,int normalColor,
                 int hoverColor,int clickColor,int thickness)
    {
        this.master = master;
        this.canvas = canvas;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.thickness = thickness;
        this.color = color;
        this.normalColor = normalColor;
        this.hoverColor = hoverColor;
        this.clickColor = clickColor;
        this.children = new ArrayList<>();
        this.master.AddChild(this);
        this.canvas.AddHoverChild(this);
    }
    @Override
    public void OnMousePressed(GameContainer gc) {

    }

    @Override
    public void OnMouseReleased(GameContainer gc) {

    }

    @Override
    public void OnMouseHold(GameContainer gc) {

    }

    @Override
    public void Update(GameContainer gc, GameManager gm) {
        for (AbstractUserInterface child:children) {
            child.Update(gc,gm);
        }
    }

    @Override
    public void Render(GameContainer gc, GameManager gm, Renderer renderer) {
        if(thickness < 0)
            renderer.DrawRect((int)posX,(int)posY,width,height,color);
        else
            renderer.DrawRect((int)posX,(int)posY,width,height,color,thickness);
        for (AbstractUserInterface child:children) {
            if (child.enable)
                child.Render(gc,gm,renderer);
        }
    }

    @Override
    public void AddChild(AbstractUserInterface child) {
        children.add(child);
    }

    @Override
    public AbstractUserInterface GetMaster() {
        return master;
    }

}
