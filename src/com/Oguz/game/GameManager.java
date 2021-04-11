package com.Oguz.game;

import com.Oguz.engine.*;
import com.Oguz.engine.Input.Keycode;
import com.Oguz.engine.Renderer;
import com.Oguz.engine.UI.*;
import com.Oguz.engine.Vector.Vector2D;

import java.util.ArrayList;
import java.util.Arrays;

public class GameManager extends AbstractGame {
    public ArrayList<GameObject> objects = new ArrayList<>();
    //private final ArrayList<Canvas> canvasObjects = new ArrayList<>();
    public ImageManager imageManager = new ImageManager();
    private final Portal portal;
    private final SnakeHead snake;
    private float elapsedTime;
    private int[][] level = new int[40][30];
    private boolean theEnd = false;
    public GameManager()
    {
        snake = new SnakeHead(3);
        objects.add(new Food(snake));
        portal = new Portal(new Vector2D(16,48),new Vector2D(48,48));
        LoadLevel();
    }
    @Override
    public void Update(GameContainer gc, float dt) {
        if(!theEnd) {
            if(gc.getInput().isKey(Keycode.Space)) elapsedTime +=dt*2;
            else elapsedTime+=dt;
            float updateTime = .5f;
            if(elapsedTime >= updateTime)
            {
                elapsedTime = 0;
                snake.UpdateSnake(this);
            }
            for (GameObject object:objects) {
                object.Update(gc,this,dt);
            }
            snake.Update(gc,this,dt);
            portal.Update(gc,this,dt);
        }
        /*
        for (Canvas canvas:canvasObjects) {
            if(canvas.enable)
            {
                canvas.Update(gc,this);
            }
        }*/
    }

    @Override
    public void Render(GameContainer gc, Renderer renderer)
    {
        portal.Render(renderer,this,gc);
        snake.Render(gc,this,renderer);
        for (int i = objects.size()-1; i >= 0; i--) {
            objects.get(i).Render(gc,this,renderer);
        }
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 40; j++) {
                if(level[j][i] == 1)
                    renderer.DrawImage(imageManager.getImage("brick"),j*8,i*8);
            }
        }
    }
    public static void main(String[] args)
    {
        GameContainer gameContainer = new GameContainer(new GameManager());
        gameContainer.start();
    }
    void LoadLevel()
    {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 40; j++) {
                if(j == 0 || j == 39 || i == 0 || i == 29       )
                    level[j][i] = 1;
            }
        }
    }
    public SnakeHead getSnake() {
        return snake;
    }
    public int[][] getLevel() {
        return level;
    }
    /*public ArrayList<Canvas> getCanvasObjects() {
        return canvasObjects;
    }*/
    public void setTheEnd(boolean theEnd) {
        this.theEnd = theEnd;
    }
}
