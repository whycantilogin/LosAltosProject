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
    // use BufferedImage to store image files
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    String dialogues[] = new String[30];
    int dialogueIndex = 0;
    public BufferedImage image, image2, image3;
    public String name;
    public int type; // 0=player, 1=npc, 2=monster

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
    }

    public void update() {
        setAction();
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - ap.player.worldX + ap.player.screenX;
        int screenY = worldY - ap.player.worldY + ap.player.screenY;
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
