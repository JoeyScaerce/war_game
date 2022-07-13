package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setResizable(false);
        window.setTitle("Final War");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // sized to fit preferred rendering size

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }


}
