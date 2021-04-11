package com.Oguz.engine.Vector;

public class Vector2D implements IVector<Vector2D>{
    public float x;
    public float y;
    public Vector2D()
    {
        x = 0;
        y = 0;
    }
    public Vector2D(float x,float y)
    {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    public void normalized()
    {
        float len = (float) Math.sqrt((x*x)+(y*y));
        if(len == 0)return;
        if(len == 1)return;
        this.x = x / Math.abs(len);
        this.y = y / Math.abs(len);
    }
    public float magnitude()
    {
        return (float)Math.sqrt((x*x)+(y*y));
    }
    public static Vector2D normalize(Vector2D vector2D)
    {
        var vector = new Vector2D(0,0);
        float len = (float) Math.sqrt((vector2D.x*vector2D.x)+(vector2D.y*vector2D.y));
        if(len == 0) return vector;
        if(len == 1) return vector2D;
        vector.x = vector2D.x / Math.abs(len);
        vector.y = vector2D.y / Math.abs(len);
        return vector;
    }
    @Override
    public Vector2D add(Vector2D one, Vector2D two) {
        return new Vector2D(one.x+two.x,one.y+two.y);
    }
    @Override
    public Vector2D add(Vector2D one, float two) {
        return new Vector2D(one.x+two,one.y+two);
    }

    @Override
    public Vector2D sum(Vector2D one, Vector2D two) {
        return new Vector2D(one.x-two.x,one.y-two.y);
    }

    @Override
    public Vector2D sum(Vector2D one, float two) {
        return new Vector2D(one.x-two,one.y-two);
    }

    @Override
    public Vector2D multi(Vector2D one, Vector2D two) {
        return new Vector2D(one.x*two.x,one.y*two.y);
    }

    @Override
    public Vector2D multi(Vector2D one, float two) {
        return new Vector2D(one.x*two,one.y*two);
    }

    @Override
    public Vector2D div(Vector2D one, Vector2D two) {
        return new Vector2D(one.x/two.x,one.y/two.y);
    }

    @Override
    public Vector2D div(Vector2D one, float two) {
        return new Vector2D(one.x/two,one.y/two);
    }

    public String toString()
    {
        return String.format("Vector2D <%d:%d>",(int)x,(int)y);
    }
}
