package App.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GraphOfCalories extends JFrame implements ActionListener{
    Graphics2D g2;
    private JTextField caloriesField;
    private JLabel caloriesLabel = new JLabel("Calories: ");
    private int caloriesAmt = 2000;
    private JButton b;

    public GraphOfCalories() {
        super("GraphOfCalories");

        setLayout(new GridLayout(4, 2));

        caloriesField = new JTextField(16);

        add(caloriesLabel);
        add(caloriesField);
        b = new JButton("Submit");
        add(b);
        
        drawCaloriesGraph();

        setSize(300, 200);
        setVisible(true);
    }

    public void drawCaloriesGraph() {
        g2.setColor(Color.LIGHT_GRAY);       // code to draw rectangles goes here...

        g2.drawRect(10, 10, 2000, 2500);


        g2.drawRect(20, 2400- caloriesAmt, 20, caloriesAmt);

        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "Graph of Your Caloric Intake for Today";
        g2.drawString(text, 15, 15);
        
    }

    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("Submit")) {
            caloriesAmt = Integer.parseInt(caloriesField.getText());
        }
    }
}
