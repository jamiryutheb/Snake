package code;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Snake {

    BufferedImage head_downImg;
    BufferedImage head_upImg;
    BufferedImage head_rightImg;
    BufferedImage head_leftImg;

    BufferedImage body_verticalImg;
    BufferedImage body_horizontalImg;
    int snakeSize;
    private int bodySize;

    public Snake(int snakeSize){
        String head_down = "src\\img_src\\head_down.png";
        String head_up = "src\\img_src\\head_up.png";
        String head_right = "src\\img_src\\head_right.png";
        String head_left = "src\\img_src\\head_left.png";

        String body_vertical = "src\\img_src\\b_vertical.png";
        String body_horizontal = "src\\img_src\\b_horizontal.png";

        try {
            head_downImg = ImageIO.read(new File(head_down));
            head_upImg = ImageIO.read(new File(head_up));
            head_rightImg = ImageIO.read(new File(head_right));
            head_leftImg = ImageIO.read(new File(head_left));

            body_verticalImg = ImageIO.read(new File(body_vertical));
            body_horizontalImg = ImageIO.read(new File(body_horizontal));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.snakeSize = snakeSize;
        bodySize = snakeSize;
    }

    public void drawUp(Graphics g, int x, int y){
        g.drawImage(head_upImg, x, y, snakeSize, snakeSize,null);
    }
    public void drawDown(Graphics g, int x, int y){
        g.drawImage(head_downImg, x, y, snakeSize, snakeSize,null);
    }
    public void drawRight(Graphics g, int x, int y){
       g.drawImage(head_rightImg, x, y, snakeSize, snakeSize,null);
    }
    public void drawLeft(Graphics g, int x, int y){
        g.drawImage(head_leftImg, x, y, snakeSize, snakeSize,null);
    }

    public void drawBody(char state, Graphics g, int x, int y){

        if (state == 'H') {
            g.drawImage(body_horizontalImg, x, y, bodySize, bodySize, null);
        } else if (state == 'V') {
            g.drawImage(body_verticalImg, x, y, bodySize, bodySize, null);
        }
    }
}
