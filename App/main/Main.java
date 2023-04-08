package App.main;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Health!");
        AppPanel appPanel=new AppPanel();
        window.add(appPanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        appPanel.setupGame();
        appPanel.startGameThread();
    }
}