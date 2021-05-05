package com.Oguz.game;

import com.Oguz.engine.GameContainer;
import com.Oguz.engine.Renderer;

public abstract class GameObject {
    //public ArrayList<Components> components = new ArrayList<>();
    public String tag;
    private boolean isDead = false;
    private boolean inDestructible = false;
    private boolean isTrigger = false;
    public float posX,posY;
    public int width,height;

    public abstract void Update(GameContainer gc, GameManager gm, float dt);
    public abstract void Render(GameContainer gc, GameManager gm,Renderer renderer);
    //public abstract void UpdateComponets(GameObject gc, GameManager gm, float dt);
    public float getLeft(){return  posX;}

    public float getRight(){return posX+width;}

    public float getTop(){return  posY;}

    public float getBottom(){return posY+height;}

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isInDestructable() {
        return inDestructible;
    }

    public void setInDestructable(boolean inDestructable) {
        this.inDestructible = inDestructable;
    }

    public boolean isTrigger() {
        return isTrigger;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean CollisionRect(GameObject object)//TODO: Collider componentinin icine at
    {
        return posX < object.posX + object.width &&
                posX + width > object.posX &&
                posY < object.posY + object.height &&
                posY + height > object.posY;
    }

    public void setTrigger(boolean trigger) {
        isTrigger = trigger;
    }


    public void setDead(boolean dead) {
        isDead = dead;
    }
}
