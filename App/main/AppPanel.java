package App.main;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import App.user.Entity;
import App.user.User;

/*
* upper left corner is x:0, y:0
* x increases to the right
* y increases going down
*/
public class AppPanel extends JPanel implements Runnable {
    final int originalSize = 24; // 16x16 tile is now 24x24 tile :)
    final int scale = 3;
    public final int tileSize = originalSize * scale; // 48x48 tile demo, this is 72x72
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 11;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels not anymore
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels not anymore
    public int gameFreeze=0;

    // WORLD SETTINGS
    public final int maxWorldCol = 80; // original: 50
    public final int maxWorldRow = 80; // original: 50
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    public KeyHandler keyH = new KeyHandler(this);
    public UI ui = new UI(this);
    // public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    // entity and object
    public User player = new User(this, keyH);

    ArrayList<Entity> entityList = new ArrayList<>();

    // game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int selectCancerState=4;
    public final int selectCalories=5;

    public AppPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        // playMusic(0);
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameFreeze==0 && gameThread != null) {
            if(gameState==selectCancerState) { //gameState isn't being changed to selectCancerState???
            System.out.println("in select state");
            repaint();
            gameFreeze=1;
            }
            // System.out.println("Gamestate: "+gameState);
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
        
        // while (gameThread != null) {
        //     currentTime = System.nanoTime();
        //     delta += (currentTime - lastTime) / drawInterval;
        //     lastTime = currentTime;
        //     if (delta >= 1) {
        //         update();
        //         repaint();
        //         delta--;
        //     }
        // }

        // double drawInterval = 1000000000 / FPS;
        // double delta = 0;
        // long lastTime = System.nanoTime();
        // long currentTime;
        // while (gameThread != null) {
        //     currentTime = System.nanoTime();
        //     delta += (currentTime - lastTime) / drawInterval;
        //     lastTime = currentTime;
        //     if (delta >= 1) {
        //         update();
        //         repaint();
        //         delta--;
        //     }
        // }
    }

    public void update() {
        if (gameState == playState) {
            // player
            // player.update();

        }
        if (gameState == pauseState) {
            // nothing -- don't update player info while game is paused
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // debug runTime for draw
        long drawStart = 0;
        if (keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        // title scren
        if (gameState == titleState) {
            ui.draw(g2);
        } 
        // else if(gameState==selectCancerState) {
        //     ui.draw(g2);
        // }
        else {

            // // sort
            // Collections.sort(entityList, new Comparator<Entity>() {
            //     @Override
            //     public int compare(Entity e1, Entity e2) {
            //         return Integer.compare(e1.worldY, e2.worldY);
            //     }
            // });

            // // draw entities
            // for (int i = 0; i < entityList.size(); i++)
            //     entityList.get(i).draw(g2);
            // // clear entity list
            // entityList.clear();



            // UI
            ui.draw(g2);
        }

        if (keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            // System.out.println("Draw Time: "+passed);
        }

        g2.dispose(); // graphics object can't be used after dispose is called
    }

    public void playMusic(int i) {
        // music.setFile(i);
        // music.play();
        // music.loop();
    }

    public void stopMusic() {
        // music.stop();
    }

    // PLAY SOUND EFFECTS
    public void playSE(int i) {
        // se.setFile(i);
        // se.play();
    }
}
