package code;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Food {

    BufferedImage food;

    public Food()  {
        String food_path = "src\\img_src\\apple.png";
        try {
            food = ImageIO.read(new File(food_path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void draw(Graphics g, int x, int y, int _UNIT){
        g.drawImage(food, x , y,  _UNIT + 5,_UNIT + 5 , null);
    }


}
