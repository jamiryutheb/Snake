import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    public Game(){

        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);

    }
}