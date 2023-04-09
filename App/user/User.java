//player
package App.user;

import App.main.AppPanel;
import App.main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class User extends Entity {
    // GamePanel ap;
    KeyHandler keyH;

    // where player is drawn on the screen
    // draw player at the center of the screen, scroll the background as player
    // moves
    public final int screenX;
    public final int screenY;
    //SICKNUM indicates which sickness/disease the user has
    public int sickNum;
    public int hasRibbons = 0; // how many ribbons the player has

    public User(AppPanel ap, KeyHandler keyH) {
        super(ap);
        // this.ap=ap;
        this.keyH = keyH;

        screenX = ap.screenWidth / 2 - (ap.tileSize / 2);
        screenY = ap.screenHeight / 2 - (ap.tileSize / 2);
    }

    public void askForUserBasicInfo() {
        //height, weight, age...
        //amount of exercise...
    }

    public void enterFoodsEaten() {
        //then based on datasets calulate nutrition info
    }

    public void displayResults() {
        //show the chart (red if too much of something... etc)
        
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
                // worldY-=speed;
            } else if (keyH.downPressed) {
                direction = "down";
                // worldY+=speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                // worldX-=speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                // worldX+=speed;
            }
            ap.keyH.enterPressed = false;
        }
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        g2.drawImage(image, screenX, screenY, null);

        // debug
        g2.setFont(new Font("Arial", Font.PLAIN, 26));
        g2.setColor(Color.white);
    }
}
