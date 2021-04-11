package com.Oguz.engine.UI;

import com.Oguz.engine.GameContainer;
import com.Oguz.engine.Renderer;
import com.Oguz.game.GameManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Button extends AbstractUserInterface{
    private final AbstractUserInterface master;
    private final ArrayList<ButtonFunction> actions = new ArrayList<>();
    public Button(@NotNull AbstractUserInterface master, float posX, float posY, int width, int height, int color,
                  VerticalPositions verPos, HorizontalPositions horPos, ArrayList<ButtonFunction> actions)
    {
        this.master = master;
        this.master.AddChild(this);
        this.width = width;
        this.height = height;
        this.color = color;
        switch (verPos){
            case None -> {
                this.posY = master.posY+posY;
            }
            case Top -> {
                this.posY= master.posY;
            }
            case Middle -> {
                this.posY= master.posY+(master.height-height)/2.0f;
            }
            case Bottom -> {
                this.posY= master.posY+(master.height-height);
            }
        }
        switch (horPos){
            case None -> this.posX = master.posX+posX;
            case Left -> this.posX = master.posX;
            case Middle -> this.posX = master.posX+(master.width-width)/2.0f;
            case Right -> this.posX = master.posX+(master.width-width);
        }
        assert false;
        this.actions.addAll(actions);
    }

    @Override
    public void OnMousePressed(GameContainer gc) {
        //TODO: Actions kismi pressed actions,released actions ve clicked actions diye ayrilabilir,
    }

    @Override
    public void OnMouseReleased(GameContainer gc) {
        //TODO: Actions kismi pressed actions,released actions ve clicked actions diye ayrilabilir,
    }

    @Override
    public void OnMouseClicked(GameContainer gc) {
        for (ButtonFunction action:actions) {
            action.Run();
        }
    }

    @Override
    public void Update(GameContainer gc, GameManager gm) {
        //Update This Object.
        //----------------------------------------------
        //Update Child Objects.
        for (AbstractUserInterface child:children) {
            child.Update(gc,gm);
        }
        //-----------------------------------------------
    }

    @Override
    public void Render(GameContainer gc, GameManager gm, Renderer renderer) {
        //Render This Object.
        //----------------------------------------------
        //Render Child Objects.
        renderer.DrawRect((int)(posX),(int)(posY),width,height,color);
        for (AbstractUserInterface child:children) {
            child.Render(gc,gm,renderer);
        }
        //-----------------------------------------------
    }

    @Override
    public void AddChild(AbstractUserInterface child) {
        //Add Child Object To Children ArrayList.
        children.add(child);
    }

    @Override
    public AbstractUserInterface GetMaster() {
        //Return Master Object.
        return master;
    }
}
