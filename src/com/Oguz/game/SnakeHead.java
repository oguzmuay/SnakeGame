package com.Oguz.game;

import com.Oguz.engine.GameContainer;
import com.Oguz.engine.Renderer;
import com.Oguz.engine.Vector.Vector2D;

import java.util.ArrayList;

public class SnakeHead extends GameObject{
    private int size;
    private Vector2D movDir = new Vector2D(1,0);
    private Vector2D renderDir = new Vector2D(1,0);
    private ArrayList<SnakeBody> body = new ArrayList<>();
    public SnakeHead(int startSize)
    {
        posX = 32;
        posY = 32;
        size = startSize;
        for (int i = 0; i < size-1; i++) {
            body.add(new SnakeBody(new Vector2D(posX-movDir.x*8*(i+1),posY-movDir.y*8*(i+1)),new Vector2D(1,0)));
        }
    }
    @Override
    public void Update(GameContainer gc, GameManager gm, float dt) {
        CheckCollision(gm.getLevel(),gc,gm);
        Vector2D input = new Vector2D(gc.getInputSystem().GetValue("Horizontal"), gc.getInputSystem().GetValue("Vertical"));
        if (input.magnitude() > 0){
            if(renderDir.x != -input.x && renderDir.y != -input.y)
            {
                movDir.set(input.x,input.y);
            }
        }
    }

    @Override
    public void Render(GameContainer gc,GameManager gm,Renderer renderer) {
        if(renderDir.x == 1)
        {
            if(renderDir.y == 1) //TODO: Sag alta dogru gidiyomus gibi gozukmesi lazim ama onun asseti yok
            {
                renderer.DrawImage(gm.imageManager.getImage("headRight"),(int)posX,(int)posY);
            }else if(renderDir.y == -1) //TODO: Sag yukari dogru gidiyomus gibi gozukmesi lazim ama onun asseti yok
            {
                renderer.DrawImage(gm.imageManager.getImage("headRight"),(int)posX,(int)posY);
            }else //TODO: Saga dogru gidiyor.
                {
                    renderer.DrawImage(gm.imageManager.getImage("headRight"),(int)posX,(int)posY);
                }
        }
        else if(renderDir.x == -1)
        {
            if(renderDir.y == 1) //TODO: Sol alta dogru gidiyomus gibi gozukmesi lazim ama onun asseti yok
            {
                renderer.DrawImage(gm.imageManager.getImage("headLeft"),(int)posX,(int)posY);
            }else if(renderDir.y == -1) //TODO: Sol yukari dogru gidiyomus gibi gozukmesi lazim ama onun asseti yok
            {
                renderer.DrawImage(gm.imageManager.getImage("headLeft"),(int)posX,(int)posY);
            }else
            {
                renderer.DrawImage(gm.imageManager.getImage("headLeft"),(int)posX,(int)posY);
            }
        }else
            {
                if(renderDir.y == 1)
                {
                    renderer.DrawImage(gm.imageManager.getImage("headDown"),(int)posX,(int)posY);
                }else if(renderDir.y == -1)
                {
                    renderer.DrawImage(gm.imageManager.getImage("headUp"),(int)posX,(int)posY);
                }
            }
        for (SnakeBody snakeBody : body) {
            snakeBody.Render(renderer, gm);
        }
    }

    public void UpdateSnake(GameManager gm)
    {
        renderDir.set(movDir.x,movDir.y);
        for (int i = body.size()-1; i >= 0; i--) {
            if(i == 0){
                body.get(i).setLastDir(new Vector2D(movDir.x, movDir.y));
                body.get(i).setPos(new Vector2D(posX,posY));
            }
            else{
                body.get(i).setLastDir(new Vector2D(body.get(i-1).getLastDir().x,body.get(i-1).getLastDir().y));
                body.get(i).setPos(new Vector2D(body.get(i-1).getPos().x, body.get(i-1).getPos().y));
            }
        }
        posX += movDir.x * 8;
        if (posX >= 320)
        {
            posX = 0;
        }else if (posX < 0)
        {
            posX = 312;
        }
        posY += movDir.y * 8;
        if (posY >= 240)
        {
            posY = 0;
        }else if (posY < 0)
        {
            posY = 232;
        }
    }
    public void EatFood()
    {
        if(body.size() > 0)
        {
            SnakeBody tail = body.get(body.size()-1);
            body.add(new SnakeBody(new Vector2D(tail.getPos().x-(8*tail.getLastDir().x),
                    tail.getPos().y-(8*tail.getLastDir().y)),
                    new Vector2D(tail.getLastDir().x,tail.getLastDir().y)));
        }else{
            body.add(new SnakeBody(new Vector2D(posX-(8*movDir.x),
                    posY-(8* movDir.y)), new Vector2D(movDir.x, movDir.y)));
        }
    }
    private void CheckCollision(int[][] level,GameContainer gc, GameManager gm){
        if(level[(int)posX/8][(int)posY/8] != 0)
        {
            gm.setTheEnd(true);
        }
        for (int i = 0; i < body.size(); i++) {
            if(body.get(i).getPos().x == posX && body.get(i).getPos().y == posY)
            {
                if (body.size() > i) {
                    body.subList(i, body.size()).clear();
                }
                break;
            }
        }

    }
    public Vector2D getMovDir() {
        return movDir;
    }
}
