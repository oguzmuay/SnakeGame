package com.Oguz.engine.Component;

import com.Oguz.game.GameManager;
import com.Oguz.game.GameObject;

import java.util.ArrayList;

public class Collider extends Component
{
    public boolean isTrigger;
    private float width,height;
    private float leftX,leftY;
    private ArrayList<GameObject> currentCollisions = new ArrayList<>();
    private ArrayList<GameObject> lastCollisions = new ArrayList<>();
    @Override
    public void Update(GameManager gm) {

    }
}
