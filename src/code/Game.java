package code;

import javax.swing.*;

public class Game extends JFrame {

    public Game(){

        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }
}