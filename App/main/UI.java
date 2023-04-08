package App.main;

import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import App.user.Entity;

// import object.heart;
// import object.Ribbons.ribbon1;
public class UI {
    AppPanel ap;
    Graphics2D g2;
    // Font arial_40, arial_80Bold;
    Font purisaBold;
    // BufferedImage heart_full, heart_half, heart_blank;
    // BufferedImage ribbon1Image;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0:first screen, 1:second screen

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00"); // display up to 2 decimals

    public UI(AppPanel ap) {
        this.ap = ap;
        // arial_40=new Font("Arial",Font.PLAIN,40);
        // arial_80Bold=new Font("Arial",Font.BOLD,80);
        try {
            InputStream is = getClass().getResourceAsStream("/res/font/Purisa_Bold.ttf");
            purisaBold = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ribbon1 r1=new ribbon1(ap);
        // ribbon1Image=r1.image;

        // create HUD object
        // Entity heart=new heart(ap);
        // heart_full=heart.image;
        // heart_half=heart.image2;
        // heart_blank=heart.image3;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        // g2.setFont(arial_40);
        g2.setFont(purisaBold);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        // title state
        if (ap.gameState == ap.titleState) {
            drawTitleScreen();
        }

        // play state
        if (ap.gameState == ap.playState) {
            // drawPlayerLife();
        }
        // pause state
        if (ap.gameState == ap.pauseState) {
            // drawPlayerLife();
            drawPauseScreen();
        }
        // dialogue state
        if (ap.gameState == ap.dialogueState) {
            // drawPlayerLife();
            drawDialogueScreen();
        }
        if (gameFinished) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.setColor(Color.pink);
            String text;
            int textLength;
            int x;
            int y;
            text = "You have reached the end!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            // align text to center
            x = ap.screenWidth / 2 - textLength / 2;
            y = ap.screenHeight / 2 - (ap.tileSize * 3);
            g2.drawString(text, x, y);

            text = "You Time is:" + dFormat.format(playTime) + "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            // align text to center
            x = ap.screenWidth / 2 - textLength / 2;
            y = ap.screenHeight / 2 + (ap.tileSize * 4);
            g2.drawString(text, x, y);

            g2.setFont(purisaBold);
            // g2.setFont(arial_80Bold);
            g2.setColor(Color.yellow);
            text = "You have reached the end!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            // align text to center
            x = ap.screenWidth / 2 - textLength / 2;
            y = ap.screenHeight / 2 + (ap.tileSize * 2);
            g2.drawString(text, x, y);

            ap.gameThread = null;
        } else {
            g2.setFont(purisaBold);
            // g2.setFont(arial_40);
            g2.setColor(Color.white);
            // g2.drawImage(ribbon1Image, 5, 5,ap.tileSize,ap.tileSize,null);
            // g2.drawString("x " + ap.player.hasRibbons,74,54); // the y is the baseline

            // time
            playTime += (double) 1 / 60;
            g2.drawString("Time:" + dFormat.format(playTime), ap.tileSize * 11, 54);

            // message
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.setColor(Color.pink);
                g2.drawString(message, ap.tileSize / 2, ap.tileSize * 10);
                messageCounter++;
                if (messageCounter > 120) { // after 120 frames the messsage disappears (60 frames in a second)
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

    }

    // public void drawPlayerLife() {
    // //ap.player.life=5; //player has 2.5 hearts
    // int x=ap.tileSize/2;
    // int y=ap.tileSize/2;
    // int i=0;

    // //draw max life
    // while(i<ap.player.maxLife/2) {
    // g2.drawImage(heart_blank,x,y,null);
    // i++;
    // x+=ap.tileSize;
    // }

    // //reset
    // x=ap.tileSize/2;
    // y=ap.tileSize/2;
    // i=0;

    // //draw current life
    // while(i<ap.player.life) {
    // i++;
    // if(i<ap.player.life) {
    // g2.drawImage(heart_full,x,y,null);
    // i++;
    // }
    // else {
    // g2.drawImage(heart_half,x,y,null);
    // }
    // x+=ap.tileSize;
    // }
    // }

    public void drawTitleScreen() {
        if (titleScreenState == 0) {
            // title screen background color
            g2.setColor(new Color(70, 120, 60));
            g2.fillRect(0, 0, ap.screenWidth, ap.screenHeight);
            // title name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "My app!";
            int x = getXforCenteredText(text);
            int y = ap.tileSize * 2;

            // shadow for text
            g2.setColor(Color.black);
            g2.drawString(text, x + 5, y + 5);

            // main color
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // display image
            x = ap.screenWidth / 2 - (ap.tileSize * 2) / 2;
            y += ap.tileSize * 2;
            // g2.drawImage(ap.player.down1,x,y,ap.tileSize*2,ap.tileSize*2,null);

            // menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            text = "NEW ENTRY";
            x = getXforCenteredText(text);
            y += ap.tileSize * 4;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - ap.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += ap.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - ap.tileSize, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += ap.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - ap.tileSize, y);
            }
        } else if (titleScreenState == 1) {
            // class selection screen
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your class!";
            int x = getXforCenteredText(text);
            int y = ap.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXforCenteredText(text);
            y += ap.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - ap.tileSize, y);
            }

            text = "Thief";
            x = getXforCenteredText(text);
            y += ap.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - ap.tileSize, y);
            }

            text = "Sorcerer";
            x = getXforCenteredText(text);
            y += ap.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - ap.tileSize, y);
            }

            text = "Back";
            x = getXforCenteredText(text);
            y += ap.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - ap.tileSize, y);
            }
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int y = ap.screenHeight / 2;
        int x = getXforCenteredText(text);
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // dialogue window
        int x = ap.tileSize * 2;
        int y = ap.tileSize / 2;
        int width = ap.screenWidth - (ap.tileSize * 4);
        int height = ap.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += ap.tileSize;
        y += ap.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        // black dialgoue window
        Color c = new Color(0, 0, 0, 220); // last number is opacity (255 is no transparency)
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 200); // white outline
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5)); // width of outline
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = ap.screenWidth / 2 - length / 2;
        return x;
    }
}
