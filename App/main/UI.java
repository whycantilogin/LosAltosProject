package App.main;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
// import App.main.BarChart;
// import object.heart;
// import object.Ribbons.ribbon1;
// class drawRectangle extends JPanel{
//     public int rectx;
//     public int recty;
//     public int rectwidth;
//     public int rectheight;
//     public void setRectValues(int rectx,int recty,int rectwidth, int rectheight) {
//         this.rectx=rectx;
//         this.recty=recty;
//         this.rectwidth=rectwidth;
//         this.rectheight=rectheight;
//     }
//     protected void paintComponent(Graphics2D g2) {
//         super.paintComponent(g2);
//         g2.setColor(Color.ORANGE);
//         g2.fillRect(rectx,recty,rectwidth,rectheight);
//     }
// }

public class UI implements Runnable{
    public boolean male=true;
    public JTextField proteinField=new JTextField("10");
    public JTextField vitaminField=new JTextField("10");
    public JTextField sugarField=new JTextField("10");
    public JTextField vegField=new JTextField("10");
    public JTextField caloriesField = new JTextField("10");
    public JTextField weightField= new JTextField("10");
    public JTextField heightField = new JTextField("10");
    public boolean saveIsClicked=false;
    public String cancerType;

    AppPanel ap;
    Disease d;
    Graphics2D g2;
    GraphsOfEverything gEverything;
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

    public UI(AppPanel ap,Disease d) {
        this.ap = ap;
        this.d=d;
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
        // g2.setFont(arial_40Gr);
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

        //entering nutrition values
        if(ap.gameState==ap.enterValuesState) { //have to move the program from selectCancerState to this enterValuesState
            // System.out.println("in the entering state");
            enterValues();
        }
        //getting user's personal info
        if(ap.gameState==ap.personalInfoState) {
            // System.out.println("personal info state");
            getPersonalInfo();
        }
        // play state
        if (ap.gameState == ap.playState) {
            //DISPLAY THE GRAPHS/STATS FOR USER
            //add the recommendations for user based on entered info
        }
        //display graph state
        if(ap.gameState==ap.graphState) {
            drawGraphs();
        }
        // pause state
        if (ap.gameState == ap.pauseState) {
            drawPauseScreen();
        }
        // dialogue state
        if (ap.gameState == ap.dialogueState) {
            drawDialogueScreen();
        }
        //draw calories graph
        /*if(ap.gameState==ap.selectCalories) {
            gEverything.drawCaloriesGraph();
        } */
        if (gameFinished) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.setColor(Color.blue);
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

    public void drawGraphs() {
        // g2.fillRect(10, 10, 1500, 1000);
        // g2.setColor(Color.orange);
        // g2.fillRect(20, 600-Integer.parseInt(caloriesField.getText()), 20, Integer.parseInt(proteinField.getText())); //protien bar
        // g2.fillRect(50, 600-Integer.parseInt(caloriesField.getText()), 20, Integer.parseInt(caloriesField.getText())); //calories bar
        // g2.fillRect(80, 600-Integer.parseInt(vitaminField.getText()), 20, Integer.parseInt(vitaminField.getText())); //vitamins bar
        // g2.fillRect(110, 600-Integer.parseInt(fruitField.getText()), 20, Integer.parseInt(fruitField.getText())); //fruits bar
        // g2.fillRect(140, 600-Integer.parseInt(vegField.getText()), 20, Integer.parseInt(vegField.getText())); //veggies bar

        //values is {suggested, user's data}
        // BarChart barchartProtein=new BarChart({20,}, null, null, currentDialogue)

        // JFrame method
        JFrame frame=new JFrame("Graphs");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(72*20,72*11);
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);   
        JLabel proteinArea=new JLabel();
        double percentage=(Integer.parseInt(proteinField.getText())/(d.suggestedMinProtein+d.suggestedMaxProtein)/2.0)*100.0;
        if(Integer.parseInt(proteinField.getText())>d.suggestedMaxProtein || Integer.parseInt(proteinField.getText())<d.suggestedMinProtein) {
            proteinArea.setForeground(Color.RED);
        }
        else proteinArea.setForeground(Color.GREEN);
        proteinArea.setHorizontalAlignment(JLabel.CENTER);
        proteinArea.setFont(new Font("Marker Felt", Font.PLAIN, 35));
        proteinArea.setText("The suggested amount of protein for you was "+(d.suggestedMinProtein+d.suggestedMaxProtein)/2+"g, and your intake was "+percentage+"% of that.");

        JLabel caloriesArea=new JLabel();
        int cal_min=0;
        int cal_max=0;
        if(male) {
            cal_min=d.suggestedMinCaloriesMen;
            cal_max=d.suggestedMaxCaloriesMen;
        }
        else {
            cal_min=d.suggestedMinCaloriesWomen;
            cal_max=d.suggestedMaxCaloriesWomen;
        }
        percentage=(Integer.parseInt(caloriesField.getText())/((cal_min+cal_max)/2.0))*100.0;

        percentage = ((int)percentage*100)/100.0;
        if(Integer.parseInt(caloriesField.getText())>cal_max || Integer.parseInt(caloriesField.getText())<cal_min) {
            caloriesArea.setForeground(Color.RED);
        }
        else caloriesArea.setForeground(Color.GREEN);
        caloriesArea.setHorizontalAlignment(JLabel.CENTER);
        caloriesArea.setFont(new Font("Marker Felt", Font.PLAIN, 35));
        caloriesArea.setText("The suggested amount of calories for you was "+(cal_min+cal_max)/2.0+" calories, and your intake was "+percentage+"% of that.");

        JLabel vitaminsArea=new JLabel();
        percentage=(Integer.parseInt(vitaminField.getText())/((d.suggestedMinVitaminD+d.suggestedMaxVitaminD)/2.0))*100.0;
        if(Integer.parseInt(vitaminField.getText())>d.suggestedMaxVitaminD || Integer.parseInt(vitaminField.getText())<d.suggestedMinVitaminD) {
            vitaminsArea.setForeground(Color.RED);
        }
        else vitaminsArea.setForeground(Color.GREEN);

        percentage = ((int)percentage*100)/100.0;
        vitaminsArea.setHorizontalAlignment(JLabel.CENTER);
        vitaminsArea.setFont(new Font("Marker Felt", Font.PLAIN, 35));
        vitaminsArea.setText("The suggested amount of vitamins for you was "+(d.suggestedMinVitaminD+d.suggestedMaxVitaminD)/2+"mg, and your intake was "+percentage+"% of that.");

        JLabel sugarArea=new JLabel();
        percentage=(Integer.parseInt(sugarField.getText()))/((double)d.suggestedSugar)*100.0;
        percentage = ((int)percentage*100)/100.0;
        if(Integer.parseInt(sugarField.getText())>d.suggestedSugar) {
            sugarArea.setForeground(Color.RED);
        }
        else sugarArea.setForeground(Color.GREEN);
        sugarArea.setHorizontalAlignment(JLabel.CENTER);
        sugarArea.setFont(new Font("Marker Felt", Font.PLAIN, 35));
        sugarArea.setText("The suggested amount of sugar for you was "+(d.suggestedSugar)+"g, and your intake was "+percentage+"% of that.");

        JLabel vegetablesArea=new JLabel();
        percentage=Integer.parseInt(vegField.getText())/d.suggestedVegetable*100;
        percentage = ((int)percentage*100)/100.0;
        if(Integer.parseInt(vegField.getText())<d.suggestedVegetable-0.05) {
            vegetablesArea.setForeground(Color.RED);
        }
        else vegetablesArea.setForeground(Color.GREEN);
        vegetablesArea.setHorizontalAlignment(JLabel.CENTER);
        vegetablesArea.setFont(new Font("Marker Felt", Font.PLAIN, 35));
        vegetablesArea.setText("The suggested amount of vegetables for you was "+d.suggestedVegetable+" cups, and your intake was "+percentage+"% of that.");

        JLabel BMILabel=new JLabel();
        BMILabel.setFont(new Font("Marker Felt", Font.PLAIN, 35));
        double heightSquared = Math.pow(((double)Integer.parseInt(heightField.getText()))/100.0, 2);
        double bmi = Integer.parseInt(weightField.getText())/heightSquared;
        //double bmi = Integer.parseInt(weightField.getText())/((Integer.parseInt(heightField.getText())*100.0)*(Integer.parseInt(heightField.getText())*100.0));
        BMILabel.setHorizontalAlignment(JLabel.CENTER);
        bmi = ((int)(bmi*10))/10.0;
        BMILabel.setText("Your BMI (body mass index) is: "+bmi);
        // BufferedImage myPicture = ImageIO.read(new File("App/res/bmiChart.png"));
        // JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        panel.add(proteinArea);
        panel.add(caloriesArea);
        panel.add(vitaminsArea);
        panel.add(sugarArea);
        panel.add(vegetablesArea);
        panel.add(BMILabel);
        // panel.add(picLabel);
        // drawRectangle dr=new drawRectangle();
        // dr.setRectValues(30, 200, 20, Integer.parseInt(proteinField.getText()));
        // dr.paintComponent(g2);
       
        // JFrame frame3=new JFrame("Graphs");
        // frame.setVisible(true);
        // frame3.setSize(72*20,72*11);
        // JPanel panel=new JPanel();

        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // frame3.add(panel);

        // // //original:
        // setColor(Color.YELLOW);       // code to draw rectangles goes here...

        // System.out.println("proteinField: "+proteinField.getText());
        // panel.add(Rectangle (30, 200, 20, Integer.parseInt(proteinField.getText()))); //protien bar
        // panel.add(Rectangle(60, 200, 20, Integer.parseInt(caloriesField.getText()))); //calories bar
        // panel.add(Rectangle(90, 200, 20, Integer.parseInt(vitaminField.getText()))); //vitamins bar
        // panel.add(Rectangle(120, 200, 20, Integer.parseInt(fruitField.getText()))); //fruits bar
        // panel.add(Rectangle(150, 200, 20, Integer.parseInt(vegField.getText()))); //veggies bar
        
        // g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 70F));
        // String text = "Your Nutrition Charts for Today";
        // g2.drawString(text, 50, 80);
        // repaint();

        //orignal
        // System.out.println("proteinField: "+proteinField.getText());
        // g2.fillRect(30, 200, 20, Integer.parseInt(proteinField.getText())); //protien bar
        // g2.fillRect(60, 200, 20, Integer.parseInt(caloriesField.getText())); //calories bar
        // g2.fillRect(90, 200, 20, Integer.parseInt(vitaminField.getText())); //vitamins bar
        // g2.fillRect(120, 200, 20, Integer.parseInt(fruitField.getText())); //fruits bar
        // g2.fillRect(150, 200, 20, Integer.parseInt(vegField.getText())); //veggies bar
        
        // g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 70F));
        // String text = "Your Nutrition Charts for Today";
        // g2.drawString(text, 50, 80);
    }

    public void enterValues() {
        // System.out.println("starting this stage"); //not getting to here! //delete??
        JFrame frame=new JFrame("Entering Nutrition Values");//delete?
        frame.setVisible(true);
        frame.setSize(72*20,72*11);
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);
        JLabel proteinLabel = new JLabel("Protein consumed (g):");
        // JTextField proteinField = new JTextField("10");
        JLabel caloriesLabel = new JLabel("Calories consumed (kcal): ");
        // caloriesField = new JTextField("10");
        JLabel vitaminLabel = new JLabel("Vitamin A consumed (mg):");
        // JTextField vitaminField = new JTextField("10");
        JLabel sugarLabel = new JLabel("Sugar consumed (g): ");
        // JTextField fruitField = new JTextField("10");
        JLabel vegLabel = new JLabel("Vegetables consumed (cups): ");
        // JTextField vegField = new JTexdrawTitleScreen

        JButton saveButton = new JButton("Save");
        panel.add(proteinLabel);
        panel.add(proteinField);
        panel.add(caloriesLabel);
        panel.add(caloriesField);
        panel.add(vitaminLabel);
        panel.add(vitaminField);
        panel.add(sugarLabel);
        panel.add(sugarField);
        panel.add(vegLabel);
        panel.add(vegField); 
        panel.add(saveButton);
        double protein = Double.parseDouble(proteinField.getText());
        double weight = Double.parseDouble(weightField.getText());
        double ratio=protein/weight;
        JLabel resultLabel = new JLabel("Protein-to-weight ratio: " + ratio);
        panel.add(resultLabel);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); //exit this frame/scre  
                saveIsClicked=true;
                ap.gameState=7;
                getPersonalInfo(); //newly added - attempt to update from the default of "10"

                // ap.gameState=6;
                // ap.gameFreeze =1;
                // System.out.println("changed to: "+ap.gameState);
         
             }
        });
        // ap.gameState=7; //change?? I am going delirious :D
        // drawGraphs(); //mew
        // frame.setVisible(true);
        //https://fdc.nal.usda.gov/api-guide.html        //api to implement : https://foodapi.calorieking.com/v1

        
    }
    public void getPersonalInfo(){
        JFrame frame = new JFrame("Entering Personal Information");//delete??
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(72*20,72*11);
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);
        JLabel nameLabel = new JLabel("Name (firstName lastName): ");
        JTextField nameField = new JTextField("Type your name");
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField("Type your age");
        // JLabel genderLabel = new JLabel("Gender (male/female/nonbinary):");
        // JTextField genderField = new JTextField("Type your gender");
        JLabel weightLabel = new JLabel("Weight (kg):");
        //JTextField weightField = new JTextField("Type your weight");
        // this.weightField = new JTextField("10");
        JLabel heightLabel = new JLabel("Height (cm):");
        
        JButton saveButton = new JButton("Save");
        

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        // panel.add(genderLabel);
        // panel.add(genderField);
        panel.add(weightLabel);
        panel.add(weightField);
        panel.add(heightLabel);
        panel.add(heightField);
        panel.add(saveButton);
        ap.gameState=8;
        // g2.setColor(Color.GREEN);
        // g2.drawString("testing", 50, 80);
        frame.setVisible(true);
        
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                frame.setVisible(false); //exit this frame/scre 
                ap.gameState=8;
                // ap.gameFreeze =1;
                drawGraphs();
             }
        });
    }

    public void selectMenu() {
        JFrame frame = new JFrame("Selection");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(500, 500);
        frame.setSize(72*20,72*11);
        // frame.setLocation(430, 100);
    
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
        frame.add(panel);
    
        JLabel lbl = new JLabel("Select one of the possible conditions and click OK");
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        panel.add(lbl);
    
        String[] choices = { "Bladder Cancer", "Brain Cancer", "Bone Cancer", "Breast Cancer",
                             "Cervical Cancer", "Colon Cancer", "Kidney Cancer", "Leukemia", "Lung Cancer", 
                            "Mesothelioma", "Ovarian Cancer", "Pancreatic Cancer", "Prostate Cancer", "Thyroid Cancer",
                            "Uterine Cancer"};
    
        final JComboBox<String> cb = new JComboBox<String>(choices);
    
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(cb);
    
        JButton btn = new JButton("OK");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btn);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); //exit this frame/scre  
                cancerType=String.valueOf(cb.getSelectedItem());
                switch(cancerType) {
                    case "Bladder Cancer":
                    d.bladderCancer();
                    break;
                    case "Brain Cancer":
                    d.brainCancer();
                    break;
                    case "Bone Cancer":
                    d.boneCancer();
                    break;
                    case "Breast Cancer":
                    d.breastCancer();
                    break;
                    case "Cervical Cancer":
                    d.cervicalCancer();
                    break;
                    case "Colon Cancer":
                    d.colorectalCancer();
                    break;
                    case "Kidney Cancer":
                    d.kidneyCancer();
                    break;
                    case "Leukemia":
                    d.leukemiaCancer();
                    break;
                    case "Lung Cancer":
                    d.lungCancer();
                    break;
                    case "Mesothelioma":
                    d.mesothelioma();
                    break;
                    case "Ovarian Cancer":
                    d.general();
                    break;
                    case "Pancreatic Cancer":
                    d.pancreaticCancer();
                    break;
                    case "Prostate Cancer":
                    d.general();
                    break;
                    case "Thyroid Cancer":
                    d.general();
                    break;
                    case "Uterine Cancer":
                    d.uterineCancer();
                    break;
                }
                ap.gameState=6;
                //new
                enterValues();
                ap.gameFreeze =1;
                // ap.gameState=7;
             }
        });
        // ap.gameState=6;
        // frame.setVisible(true);

    }
    public void drawTitleScreen() {
        if (titleScreenState == 0) {
            // title screen background color
            g2.setColor(new Color(70, 120, 60));
            g2.fillRect(0, 0, ap.screenWidth, ap.screenHeight);
            // title name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 190F));
            String text = "Nutrify"; //TITLE OF THE PROGRAM
            int x = getXforCenteredText(text);
            int y = ap.tileSize * 2;

            // shadow for text
            g2.setColor(Color.black);
            g2.drawString(text, x + 5, y + 210);

            // main color
            g2.setColor(Color.white);
            g2.drawString(text, x, y+205);

            // display image
            x = ap.screenWidth / 2 - (ap.tileSize * 2) / 2;
            y += ap.tileSize * 2;

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
                male=false;
            }

            text = "Male";
            x = getXforCenteredText(text);
            y += ap.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - ap.tileSize, y);
                male=true;
            }

            // text = "Calorie";
            // x = getXforCenteredText(text);
            // y += ap.tileSize;
            // g2.drawString(text, x, y);
            // if (commandNum == 2) {
            //     g2.drawString(">", x - ap.tileSize, y);
            // }

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

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    
}
