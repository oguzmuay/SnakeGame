package com.Oguz.engine.Input;

import com.Oguz.engine.GameContainer;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener , MouseMotionListener, MouseWheelListener{

    private GameContainer gc;

    private final Keycode KeyCode = new Keycode();

    private final int NUM_KEYS = 256;
    private boolean[] keys = new boolean[NUM_KEYS];
    private boolean[] keysLast = new boolean[NUM_KEYS];

    private final int BUTTON_KEYS = 256;
    private boolean[] buttons = new boolean[BUTTON_KEYS];
    private boolean[] buttonsLast = new boolean[BUTTON_KEYS];

    private int mouseX , mouseY;
    private int scroll;
    public Input(GameContainer gc)
    {
        this.gc = gc;
        mouseX = 0;
        mouseY = 0;
        scroll = 0;
        gc.getWindow().getCanvas().addKeyListener(this);
        gc.getWindow().getCanvas().addMouseListener(this);
        gc.getWindow().getCanvas().addMouseMotionListener(this);
        gc.getWindow().getCanvas().addMouseMotionListener(this);
    }

    public void Update()
    {
        scroll = 0;
        System.arraycopy(keys, 0, keysLast, 0, NUM_KEYS);
        System.arraycopy(buttons, 0, buttonsLast, 0, BUTTON_KEYS);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = (int)(e.getX()/gc.getScale());
        mouseY = (int)(e.getY()/gc.getScale());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (int)(e.getX()/gc.getScale());
        mouseY = (int)(e.getY()/gc.getScale());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll = e.getWheelRotation();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getScroll() {
        return scroll;
    }

    public boolean isKey(int keyCode)
    {
        return keys[keyCode];
    }
    public boolean isKeyUp(int keyCode)
    {
        return !keys[keyCode] && keysLast[keyCode];
    }
    public boolean isKeyDown(int keyCode)
    {
        return keys[keyCode] && !keysLast[keyCode];
    }
    public boolean isButton(int keyCode)
    {
        return buttons[keyCode];
    }
    public boolean isButtonUp(int keyCode)
    {
        return !buttons[keyCode] && buttonsLast[keyCode];
    }
    public boolean isButtonDown(int keyCode)
    {
        return buttons[keyCode] && !buttonsLast[keyCode];
    }
}
