package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GamePanel extends JPanel implements Runnable {

    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    private final int _UNIT = 25;
    //    int TICKS_PER_SECOND = 20;
    //    final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    //    final int MAX_FRAMESKIP = 5;
    //    int MILLIS = 50;
    private static final long REFRESH_INTERVAL_MS = 50;

    SnakeHead head;
    char direction = 'R';
    int applePosX;
    int applePosY;
    boolean running = false;
    static int body = 10;
    int[] x;
    int[] y;
    int appleCount = 0;
    private final Object redrawLock = new Object();

    public GamePanel() {

        x = new int[FRAME_WIDTH];
        y = new int[FRAME_HEIGHT];
        head = new SnakeHead(_UNIT);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setFocusable(true);
        addKeyListener(new PLAY());
        new Thread(this).start();
        gameStart();
    }

    public void gameStart() {
        running = true;
        apple();
        System.out.println(" x : " + x[0] + " : " + x[1]);
        System.out.println(" y : " + y[0] + " : " + y[1]);
    }

    @Override
    public void run() {

        while (running) {
            long durationMs = redraw();
            try {
                Thread.sleep(Math.max(0, REFRESH_INTERVAL_MS - durationMs));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private long redraw() {

        long t = System.currentTimeMillis();

        // perform changes to model
        move();
        eatCheck();
        crashCheck();

        repaint();
        waitForPaint();

        // return time taken to do redraw in ms
        return System.currentTimeMillis() - t;
    }

    private void waitForPaint() {
        try {
            synchronized (redrawLock) {
                redrawLock.wait();
            }
        } catch (InterruptedException ignored) {
        }
    }

    private void resume() {
        synchronized (redrawLock) {
            redrawLock.notify();
        }
    }


    private void crashCheck() {
        int head_X = x[0];
        int head_Y = y[0];

        // TODO : Call a game over function
        for (int i = 1; i < body; i++) {
            if (head_X == x[i] && head_Y == y[i]) {
                running = false;
                System.exit(0);
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        resume();
        draw(g);
    }

    public void move() {

        for (int i = body; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
            if (x[0] > FRAME_WIDTH)
                x[0] = 0;
            else if (x[0] < 0)
                x[0] = FRAME_WIDTH;
            if (y[0] > FRAME_HEIGHT)
                y[0] = 0;
            else if (y[0] < 0)
                y[0] = FRAME_HEIGHT;
        }

        if (direction == 'R')
            x[0] += _UNIT;
        if (direction == 'L')
            x[0] -= _UNIT;
        if (direction == 'U')
            y[0] -= _UNIT;
        if (direction == 'D')
            y[0] += _UNIT;
    }

    public void eatCheck() {
        if (x[0] == applePosX && y[0] == applePosY) {
            grow();
            apple();
        }
    }

    private void grow() {
        body++;
    }

//    public void speedUp() {
//        if (appleCount > 5 && decrease > 0) {
//            MILLIS -= decrease;
//            decrease -= 4;
//        }
//    }

    private void apple() {
        applePosX = (int) (Math.random() * FRAME_WIDTH / _UNIT) * _UNIT;
        applePosY = (int) (Math.random() * FRAME_HEIGHT / _UNIT) * _UNIT;
        appleCount++;
    }

    public void draw(Graphics g) {

        g.setColor(Color.red);
        g.fillOval(applePosX, applePosY, _UNIT, _UNIT);


        for (int i = 0; i < body; i++) {
            if (i == 0) {
                if (direction == 'R') {
                    head.drawRight(g, x[i], y[i]);
                }
                if (direction == 'L') {
                    head.drawLeft(g, x[i], y[i]);
                }
                if (direction == 'U') {
                    head.drawUp(g, x[i], y[i]);
                }
                if (direction == 'D') {
                    head.drawDown(g, x[i], y[i]);
                }
            } else {
                g.setColor(Color.WHITE);
                g.fillRect(x[i], y[i], _UNIT, _UNIT);
            }
        }

    }


    public class PLAY extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (direction != 'R')
                    direction = 'L';
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (direction != 'L')
                    direction = 'R';
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (direction != 'D')
                    direction = 'U';
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (direction != 'U')
                    direction = 'D';
            }
        }
    }
}