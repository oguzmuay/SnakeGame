package com.Oguz.engine.Component;

import com.Oguz.game.GameManager;

import java.util.ArrayList;

public class Collider extends Component
{
    public boolean isTrigger;
    private float width,height;
    private float leftX,leftY;
    private ArrayList<Collider> currentCollisions = new ArrayList<>();
    private ArrayList<Collider> lastCollisions = new ArrayList<>();
    @Override
    public void Update(GameManager gm) {

    }
}
