package com.Oguz.game;

import com.Oguz.engine.*;
import com.Oguz.engine.Input.Keycode;
import com.Oguz.engine.Renderer;
import com.Oguz.engine.UI.*;
import com.Oguz.engine.Vector.Vector2D;
import com.Oguz.engine.gfx.ImageManager;

import java.util.ArrayList;

public class GameManager extends AbstractGame {
    public ArrayList<GameObject> objects = new ArrayList<>();
    private final ArrayList<Canvas> canvasObjects = new ArrayList<>();
    public ImageManager imageManager = new ImageManager();
    private final Portal portal;
    private final SnakeHead snake;
    private float elapsedTime;
    private final int[][] level = new int[40][30];
    private boolean theEnd = false;
    Panel panel;
    public GameManager()
    {
        snake = new SnakeHead(3);
        objects.add(new Food(snake));
        portal = new Portal(new Vector2D(16,48),new Vector2D(48,48));
        canvasObjects.add(new Canvas());
        panel = new Panel(canvasObjects.get(0),canvasObjects.get(0),110,20,100,100,
                0x808080,0xff00ff,0x66ff33,0x444444,-1);
        panel.enable = false;
        ArrayList<ButtonFunction> buttonActions = new ArrayList<>();
        buttonActions.add(() -> System.exit(0));
        Button button = new Button(panel,canvasObjects.get(0),0,10,45,25,
                0x696969,0x696969,0x778899,0x708090,
                VerticalPositions.Middle,HorizontalPositions.Middle,buttonActions);
        Text text = new Text(button, canvasObjects.get(0), "ExIt",0,0,0x000000,VerticalPositions.Middle,HorizontalPositions.Middle);

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
        }else{
            panel.enable = true;
        }
        for (Canvas canvas:canvasObjects) {
            if(canvas.enable)
            {
                canvas.Update(gc,this);
            }
        }
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
        for (Canvas canvas:canvasObjects) {
            canvas.Render(gc,this,renderer);
        }
    }
    public static void main(String[] args)
    {
        GameContainer gameContainer = new GameContainer(new GameManager());
        gameContainer.start();
    }
    void LoadLevel() //TODO: Change with SaveLoad
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
    public void setTheEnd(boolean theEnd) {
        this.theEnd = theEnd;
    }
}
