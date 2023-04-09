package App.main;

import javax.swing.*;
import java.awt.*;

public class PersonalInfo extends JFrame {
    // create labels, text fields and button
    private JLabel nameLabel = new JLabel("Name:");
    private JTextField nameField = new JTextField(20);
    private JLabel ageLabel = new JLabel("Age:");
    private JTextField ageField = new JTextField(20);
    private JLabel genderLabel = new JLabel("Gender:");
    private JTextField genderField = new JTextField(20);
    private JLabel weightLabel = new JLabel("Weight:");
    private JTextField weightField = new JTextField(20);
    private JLabel heightLabel = new JLabel("Height:");
    private JTextField heightField = new JTextField(20);
    private JButton saveButton = new JButton("Save");

    public PersonalInfo() {
        super("Personal Information");

        setLayout(new GridLayout(4, 2));

        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(genderLabel);
        add(genderField);
        add(weightLabel);
        add(weightField);
        add(heightLabel);
        add(heightField);
        add(new JLabel());
        add(saveButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(300, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        PersonalInfo app = new PersonalInfo();
    }
}
