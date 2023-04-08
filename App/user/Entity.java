package App.user;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;

import App.main.AppPanel;
// import App.main.UtilityTool;

public class Entity {
    AppPanel ap;
    public int worldX, worldY; // player's position on the world map
    public int speed;
    // use BufferedImage to store image files
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // solid portion of character
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    String dialogues[] = new String[30];
    int dialogueIndex = 0;
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int type; // 0=player, 1=npc, 2=monster

    // character status
    public int maxLife;
    public int life;

    public Entity(AppPanel ap) {
        this.ap = ap;
    }

    public void setAction() {
    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        ap.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        // get NPC to face the player
        switch (ap.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update() {
        setAction();
        collisionOn = false;
        // ap.cChecker.checkTile(this);
        // ap.cChecker.checkObject(this, false);
        // ap.cChecker.checkEntity(this, ap.npc);
        // ap.cChecker.checkEntity(this, ap.monster);
        // boolean contactPlyaer = ap.cChecker.checkPlayer(this);

        // if (this.type == 2 && contactPlyaer) {
        // if (!ap.player.invincible) {
        // // give damage to player
        // ap.player.life -= 1;
        // ap.player.invincible = true;
        // }
        // }

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
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - ap.player.worldX + ap.player.screenX;
        int screenY = worldY - ap.player.worldY + ap.player.screenY;

        if (worldX + ap.tileSize > ap.player.worldX - ap.player.screenX
                && worldX - ap.tileSize < ap.player.worldX + ap.player.screenX
                && worldY + ap.tileSize > ap.player.worldY - ap.player.screenY
                && worldY - ap.tileSize < ap.player.worldY + ap.player.screenY) {
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
            g2.drawImage(image, screenX, screenY, ap.tileSize, ap.tileSize, null);
        }

    }

    public BufferedImage setup(String imagePath) {
        // UtilityTool uTool=new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res" + imagePath + ".png"));
            // image = uTool.scaleImage(image, ap.tileSize, ap.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
            ;
        }
        return image;
    }
}
