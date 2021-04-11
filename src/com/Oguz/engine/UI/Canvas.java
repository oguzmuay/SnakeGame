package com.Oguz.engine.UI;

import com.Oguz.engine.GameContainer;
import com.Oguz.engine.Renderer;
import com.Oguz.game.GameManager;

public class Canvas extends AbstractUserInterface{

    public Canvas()
    {

    }

    @Override
    public void OnMousePressed(GameContainer gc) {

    }

    @Override
    public void OnMouseReleased(GameContainer gc) {

    }

    @Override
    public void OnMouseClicked(GameContainer gc) {

    }

    @Override
    public void Update(GameContainer gc, GameManager gm) {
        for (AbstractUserInterface child:children) {
            child.Update(gc,gm);
        }
    }

    @Override
    public void Render(GameContainer gc, GameManager gm, Renderer renderer) {
        for (AbstractUserInterface child:children) {
            child.Render(gc,gm,renderer);
        }
    }

    @Override
    public void AddChild(AbstractUserInterface child) {
        children.add(child);
    }

    @Override
    public AbstractUserInterface GetMaster() {
        return null;
    }
}
