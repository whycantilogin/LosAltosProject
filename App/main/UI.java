package App.main;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import App.user.Entity;
import App.main.GraphOfCalories;

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

    // double playTime;
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
        //selecting type of cancer state
        if(ap.gameState==ap.selectCancerState) {
            selectMenu();
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

            // text = "You Time is:" + dFormat.format(playTime) + "!";
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
            // playTime += (double) 1 / 60;
            // g2.drawString("Time:" + dFormat.format(playTime), ap.tileSize * 11, 54);

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

    public void selectMenu() {
        JFrame frame = new JFrame("Selection");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(500, 500);
        frame.setSize(72*20,72*11);
        frame.setLocation(430, 100);
    
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
        frame.add(panel);
    
        JLabel lbl = new JLabel("Select one of the possible conditions and click OK");
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        panel.add(lbl);
    
        String[] choices = { "Bladder Cancer", "Brain Cancer", "Bone Cancer", "Breast Cancer",
                             "Cervical Cancer", "Colon  Cancer", "Kidney Cancer", "Leukemia Cancer", "Lung Cancer", 
                            "Mesothelioma", "Ovarian Cancer", "Pancreatic Cancer", "Prostate Cancer", "Thyroid Cancer",
                            "Uterine Cancer"};
    
        final JComboBox<String> cb = new JComboBox<String>(choices);
    
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(cb);
    
        JButton btn = new JButton("OK");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btn);
        frame.setVisible(true);
        if(btn.getModel().isPressed()) {
            System.out.println("button is pressed!");
            ap.gameState=ap.playState;
            //exit this screen
            frame.setVisible(false);
        }
    }

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

            text = "LOAD ENTRY";
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

            String text = "Enter some information to get started!";
            int x = getXforCenteredText(text);
            int y = ap.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Female";
            x = getXforCenteredText(text);
            y += ap.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - ap.tileSize, y);
            }

            text = "Male";
            x = getXforCenteredText(text);
            y += ap.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - ap.tileSize, y);
            }

            // text = "Sorcerer";
            // x = getXforCenteredText(text);
            // y += ap.tileSize;
            // g2.drawString(text, x, y);
            // if (commandNum == 2) {
            //     g2.drawString(">", x - ap.tileSize //overall rectangle, y);
            // }
 //the visual rectacngle
            text = "Back";
            x = getXforCenteredText(text);
            y += ap.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
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

    // public void drawCaloriesGraph() {
    //     g2.setColor(Color.LIGHT_GRAY);       // code to draw rectangles goes here...

    //     g2.drawRect(10, 10, 2000, 2500);

    //     double graphHeight = 3000;
    //     double fractionOfHeight = (((double) GraphOfCalories.caloriesAmt)/3000) *2000;

    //     g2.drawRect(20, 2400 - fractionOfHeight, 20, fractionOfHeight);

        
    //     g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
    //     String text = "Graph of Your Caloric Intake for Today";
    //     g2.drawString(text, 15, 15);
        
    // }
    

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
