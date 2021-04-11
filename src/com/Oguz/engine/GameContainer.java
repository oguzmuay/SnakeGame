package com.Oguz.engine;

import com.Oguz.engine.Input.Input;
import com.Oguz.engine.Input.InputSystem;

public class GameContainer implements Runnable
{
    private Window window;
    private Thread thread;
    private Renderer renderer;
    private Input input;
    private InputSystem inputSystem;
    private AbstractGame game;

    private boolean running = false;

    private final double UPDATE_CAP = 1/60.0;
    private int width = 320,height = 240;
    private  float scale = 3.0f;
    private double fps = 0;

    private String title = "My Project";

    private int alphaColor = 0xffff00ff;

    public GameContainer(AbstractGame game)
    {
        this.game = game;
    }
    public GameContainer(AbstractGame game, String title)
    {
        this.title = title;
        this.game = game;
    }
    public GameContainer(AbstractGame game, int alphaColor)
    {
        this.game = game;
        this.alphaColor = alphaColor;
    }
    public GameContainer(AbstractGame game, String title , int alphaColor)
    {
        this.title = title;
        this.game = game;
        this.alphaColor = alphaColor;
    }
    public void start()
    {
        window = new Window(this);
        renderer = new Renderer(this);
        thread = new Thread(this);
        input = new Input(this);
        inputSystem = new InputSystem(this);
        thread.start();
    }
    @Override
    public void run() {
        running = true;

        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unProcessedTime = 0;

        boolean render = true;

        double frameTime = 0;
        double frames = 0;

        while (running)
        {
            render = false;
            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;
            unProcessedTime += passedTime;
            frameTime += passedTime;
            while (unProcessedTime >= UPDATE_CAP)
            {
                unProcessedTime -= UPDATE_CAP;
                render = true;
                game.Update(this,(float)UPDATE_CAP);
                input.Update();
                inputSystem.Update();
                if(frameTime >= 1.0)
                {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }
            if(render)
            {
                renderer.Clear();
                game.Render(this,renderer);
                renderer.DrawText("FPS: "+fps,0,0,0x000000);
                window.Update();
                frames++;
            }else{
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        dispose();
    }
    public void stop()
    {
        thread.interrupt();
    }
    public void dispose()
    {
        stop();
        System.exit(0);
    }

    public double getFps() {
        return fps;
    }

    public Input getInput() {
        return input;
    }

    public InputSystem getInputSystem() {
        return inputSystem;
    }

    public Window getWindow() {
        return window;
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

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAlphaColor() {
        return alphaColor;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
