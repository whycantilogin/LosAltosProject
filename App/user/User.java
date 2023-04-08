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
    public int hasRibbons = 0; // how many ribbons the player has

    public User(AppPanel ap, KeyHandler keyH) {
        super(ap);
        // this.ap=ap;
        this.keyH = keyH;

        screenX = ap.screenWidth / 2 - (ap.tileSize / 2);
        screenY = ap.screenHeight / 2 - (ap.tileSize / 2);

        solidArea = new Rectangle(); // should be smaller than character...
        // demo: 8, 16, 32, 32
        solidArea.x = 8;// *ap.tileSize; //18
        solidArea.y = 16;// *ap.tileSize; //21
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;// *ap.tileSize; //39
        solidArea.height = 32;// ; //69

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        // where the player starts as default
        worldX = ap.tileSize * 23;
        worldY = ap.tileSize * 21;
        speed = 4; // default speed
        direction = "down"; // setting default direction

        // player status
        maxLife = 6; // one life = half a heart --> 6 lives = 3 hearts
        life = maxLife;
    }

    public void getPlayerImage() {
        // up1 = setup("/player/Girl1");
        // up2 = setup("/player/Girl2");
        // down1 = setup("/player/Girl3");
        // down2 = setup("/player/Girl4");
        // left1 = setup("/player/Girl5");
        // left2 = setup("/player/Girl6");
        // right1 = setup("/player/Girl7");
        // right2 = setup("/player/Girl8");
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

            // check tile collision
            collisionOn = false;
            // ap.cChecker.checkTile(this);

            // check object collision
            // int objIndex = ap.cChecker.checkObject(this, true);
            // pickUpObject(objIndex);

            // check npc collision
            // int npcIndex = ap.cChecker.checkEntity(this, ap.npc);
            // interactNPC(npcIndex);

            // check monster collision
            // int monsterIndex = ap.cChecker.checkEntity(this, ap.monster);
            // contactMonster(monsterIndex);

            // check event
            // ap.eHandler.checkEvent();
            ap.keyH.enterPressed = false;

            // if collision is false, player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) { // player image changes every 12 frames between the two images for each
                                      // direction
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            // was in the demo?
            // standCounter++;
            // if(standCounter==20) {
            // spriteNum=1;
            // standCounter=0;
            // }
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        // if (i != 999) { // if i==999 then the player didn't touch the object
        // String objectName = ap.obj[i].name;
        // switch (objectName.substring(objectName.lastIndexOf("_") + 1)) {
        // case "Ribbon":
        // ap.playSE(1); // playing sound effect
        // hasRibbons++;
        // ap.obj[i] = null; // deletes the touched object
        // ap.ui.showMessage("You got a "
        // + objectName.substring(objectName.indexOf("_") + 1,
        // objectName.lastIndexOf("_"))
        // + " Ribbon!");
        // break;
        // case "Monster":
        // if (hasRibbons > 0) {
        // ap.obj[i] = null;
        // hasRibbons--;
        // if (objectName.equals("23_Monster")) {
        // ap.ui.gameFinished = true;
        // ap.stopMusic();
        // ap.playSE(1);
        // }
        // } else {
        // ap.ui.showMessage("You need a ribbon!");
        // }
        // break;
        // }
        // switch (objectName) {
        // case "star":
        // speed += 1;
        // ap.obj[i] = null;
        // break;
        // }
        // }
    }

    public void interactNPC(int i) {
        // if (i != 999) {
        // if (ap.keyH.enterPressed) {
        // ap.gameState = ap.dialogueState;
        // ap.npc[i].speak();
        // }
        // }
    }

    public void contactMonster(int i) {
        // // player only receives damage when player is not invincible
        // if (i != 999) {
        // if (!invincible) {
        // life -= 1;
        // invincible = true;
        // }
        // }
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // g2.fillRect(x,y,ap.tileSize,ap.tileSize);
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);

        // debug
        g2.setFont(new Font("Arial", Font.PLAIN, 26));
        g2.setColor(Color.white);
        g2.drawString("Invincible: " + invincibleCounter, 10, 400);
    }
}
