package com.Oguz.engine.UI;

import com.Oguz.engine.GameContainer;
import com.Oguz.engine.Input.Keycode;
import com.Oguz.engine.Renderer;
import com.Oguz.game.GameManager;

import java.util.ArrayList;

public class Canvas extends AbstractUserInterface{
    private ArrayList<AbstractUserInterface> hoverChildList = new ArrayList<>();
    public Canvas()
    {
        children = new ArrayList<>();
        width = 320;
        height = 240;
    }
    private AbstractUserInterface hoveredObject;
    @Override
    public void OnMousePressed(GameContainer gc) {
        if(hoveredObject != this && hoveredObject != null)
            hoveredObject.OnMousePressed(gc);
    }

    @Override
    public void OnMouseReleased(GameContainer gc) {
        if(hoveredObject != this && hoveredObject != null)
            hoveredObject.OnMouseReleased(gc);
    }

    @Override
    public void OnMouseHold(GameContainer gc) {
        if(hoveredObject != this && hoveredObject != null)
            hoveredObject.OnMouseHold(gc);
    }
    @Override
    public void Update(GameContainer gc, GameManager gm) {
        for (AbstractUserInterface child:children) {
            child.Update(gc,gm);
        }
        if (gc.getInput().isMouseMove()){
            if (GetOverUI(gc.getInput().getMouseX(),gc.getInput().getMouseY()) != hoveredObject){
                SetHoverUI(GetOverUI(gc.getInput().getMouseX(),gc.getInput().getMouseY()));
                //System.out.println("Object Changed");
            }
        }
        if(gc.getInput().isButtonDown(Keycode.LeftButton)) { OnMousePressed(gc); }
        if (gc.getInput().isButton(Keycode.LeftButton)){ OnMouseHold(gc); }
        if(gc.getInput().isButtonUp(Keycode.LeftButton)) { OnMouseReleased(gc); }
    }
    @Override
    public void Render(GameContainer gc, GameManager gm, Renderer renderer) {
        for (AbstractUserInterface child:children) {
            if (child.enable)
                child.Render(gc,gm,renderer);
        }
    }
    @Override
    public void AddChild(AbstractUserInterface child) {
        children.add(child);
    }
    public void AddHoverChild(AbstractUserInterface child) {
        hoverChildList.add(child);
    }
    @Override
    public AbstractUserInterface GetMaster() {
        return null;
    }
    public AbstractUserInterface GetOverUI(float mouseX,float mouseY) { //Change to GetHoverUI
        if (!enable) return this;
        if (hoverChildList.size() == 0) return this;
        AbstractUserInterface ui = null;
            for (int i = hoverChildList.size() - 1; i >= 0; i--)
            {
                if(ui == null){
                    var child = hoverChildList.get(i);
                    if(child.enable && Canvas.isMouseOver(mouseX,mouseY,child)){
                        ui = child;
                    }
                }else break;
            }
        return ui;
    }
    public static boolean isMouseOver(float mousePosX,float mousePosY,AbstractUserInterface object)
    {
        return mousePosX >= (int)object.posX && mousePosX <= (int)(object.posX + object.width) &&
                mousePosY >= (int)object.posY && mousePosY <= (int)(object.posY + object.height);
    }
    public void SetHoverUI(AbstractUserInterface object)
    {
        if (hoveredObject != null && hoveredObject.isClickable){hoveredObject.color = hoveredObject.normalColor;}
        hoveredObject = object;
        if (hoveredObject != null && hoveredObject.isClickable)
            hoveredObject.color = hoveredObject.hoverColor;
    }
}
