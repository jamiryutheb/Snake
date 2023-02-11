package code;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SnakeHead {

    BufferedImage head_downImg;
    BufferedImage head_upImg;
    BufferedImage head_rightImg;
    BufferedImage head_leftImg;
    int headSize;

    public SnakeHead(int headSize){
        String head_down = "C:\\Users\\enesg\\IdeaProjects\\Snake\\src\\img_src\\head_down.png";
        String head_up = "C:\\Users\\enesg\\IdeaProjects\\Snake\\src\\img_src\\head_up.png";
        String head_right = "C:\\Users\\enesg\\IdeaProjects\\Snake\\src\\img_src\\head_right.png";
        String head_left = "C:\\Users\\enesg\\IdeaProjects\\Snake\\src\\img_src\\head_left.png";

        try {
            head_downImg = ImageIO.read(new File(head_down));
            head_upImg = ImageIO.read(new File(head_up));
            head_rightImg = ImageIO.read(new File(head_right));
            head_leftImg = ImageIO.read(new File(head_left));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.headSize = headSize;
    }

    public void drawUp(Graphics g, int x, int y){
        g.drawImage(head_upImg, x, y, headSize,headSize,null);
    }
    public void drawDown(Graphics g, int x, int y){
        g.drawImage(head_downImg, x, y, headSize,headSize,null);
    }
    public void drawRight(Graphics g, int x, int y){
        g.drawImage(head_rightImg, x, y, headSize,headSize,null);
    }
    public void drawLeft(Graphics g, int x, int y){
        g.drawImage(head_leftImg, x, y, headSize,headSize,null);
    }
}
