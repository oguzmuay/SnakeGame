package com.Oguz.game;

import com.Oguz.engine.GameContainer;
import com.Oguz.engine.Renderer;
import com.Oguz.engine.gfx.Image;

import java.util.Random;

public class Food extends GameObject{
    private SnakeHead snakeHead;
    private Image image;
    public Food(SnakeHead snakeHead)
    {
        image = new Image("/apple.png");
        posX = 16;
        posY = 16;
        this.snakeHead = snakeHead;
    }

    @Override
    public void Update(GameContainer gc, GameManager gm, float dt) {
        if(snakeHead.posX == posX && snakeHead.posY == posY)
        {
            //int tekrar = 0;
            posX = new Random().nextInt(20)*8;
            posY = new Random().nextInt(15)*8;
            //tekrar++;
            while(gm.getLevel()[(int) (posY/8)][(int) (posX/8)] == 1)
            {
                posX = new Random().nextInt(20)*8;
                posY = new Random().nextInt(15)*8;
            //    tekrar++;
            }
            //System.out.println("Yemek "+tekrar+" kere yer degistirdi.");
            snakeHead.EatFood();
        }
    }

    @Override
    public void Render(GameContainer gc,GameManager gm,Renderer renderer) {
        renderer.DrawImage(image,(int)posX,(int)posY);
    }
}
