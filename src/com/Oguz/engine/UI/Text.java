package com.Oguz.engine.UI;

import com.Oguz.engine.GameContainer;
import com.Oguz.engine.Renderer;
import com.Oguz.engine.gfx.Font;
import com.Oguz.game.GameManager;

import java.util.ArrayList;

public class Text extends AbstractUserInterface{
    private String text;
    private final AbstractUserInterface master;
    private final Canvas canvas;
    public Text(AbstractUserInterface master,Canvas canvas,String text,float posX,float posY,int color,VerticalPositions verPos,HorizontalPositions horPos)
    {
        this.master = master;
        this.canvas = canvas;
        this.text = text;
        this.width = CalculateWidth(text);
        this.height = 6;
        switch (verPos) {
            case None -> this.posY = master.posY + posY;
            case Top -> this.posY = master.posY;
            case Middle -> this.posY = master.posY + (master.height - height) / 2.0f;
            case Bottom -> this.posY = master.posY + (master.height - height);
        }
        switch (horPos) {
            case None -> this.posX = master.posX + posX;
            case Left -> this.posX = master.posX;
            case Middle -> this.posX = master.posX + (master.width - width) / 2.0f;
            case Right -> this.posX = master.posX + (master.width - width);
        }
        this.color = color;
        this.children = new ArrayList<>();
        this.master.AddChild(this);
        this.canvas.AddHoverChild(this);
    }

    int CalculateWidth(String text)
    {
        int[] widths = Font.STANDART.getWidths();
        int width = 0;
        for (int i = 0; i < text.length(); i++) {
            width += widths[text.toUpperCase().codePointAt(i)-32];
        }
        return width;
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

    }

    @Override
    public void Render(GameContainer gc, GameManager gm, Renderer renderer) {
        renderer.DrawText(text,(int)posX,(int)posY,color);
    }

    @Override
    public void AddChild(AbstractUserInterface child) {

    }

    @Override
    public AbstractUserInterface GetMaster() {
        return null;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AbstractUserInterface getMaster() {
        return master;
    }
}
