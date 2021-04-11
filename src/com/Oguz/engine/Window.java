package com.Oguz.engine;

import com.Oguz.game.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {

    private JFrame frame;
    private BufferedImage image;

    public Canvas getCanvas() {
        return canvas;
    }

    public BufferedImage getImage() {
        return image;
    }

    public JFrame getFrame() {
        return frame;
    }

    private Canvas canvas;
    private BufferStrategy bStrategy;
    private Graphics g;
    private GameContainer gc;
    public Window(GameContainer gc)
    {
        this.gc = gc;
        image = new BufferedImage(gc.getWidth(),gc.getHeight(),BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();
        Dimension size = new Dimension((int)(gc.getWidth()*gc.getScale()), (int)(gc.getHeight()*gc.getScale()));
        canvas.setPreferredSize(size);
        canvas.setMaximumSize(size);
        canvas.setMinimumSize(size);

        frame = new JFrame(gc.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas,BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bStrategy = canvas.getBufferStrategy();
        g = bStrategy.getDrawGraphics();

    }
    public void Update()
    {
        g.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight(),null);
        bStrategy.show();
        frame.setTitle(gc.getTitle()+" Fps: "+gc.getFps());
    }
}
