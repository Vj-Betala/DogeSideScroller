import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class dogePanel extends JPanel implements KeyListener, Runnable {
    final static int UPS = 35;
    final static int[] SCREENSIZE = {360,360};
    private long startNanoTime = 0;
    Game game;
    BufferedImage panelBoard;
    public dogePanel(){
        addKeyListener(this);
        setSize(SCREENSIZE[0], SCREENSIZE[1]);
        game = new Game();
        panelBoard = new BufferedImage(SCREENSIZE[0], SCREENSIZE[1], BufferedImage.TYPE_4BYTE_ABGR);
    }

    public void paint(Graphics g) {
        Graphics bg = panelBoard.getGraphics();

        Board tmp = game.getBoard();

        bg.setColor(Color.BLACK);
        bg.fillRect(0,0,SCREENSIZE[0], SCREENSIZE[1]);

        for (Items i: tmp.getItems()) {
            bg.drawImage(i.getBuffer(), i.getRect().x, i.getRect().y, null);
        }


        g.drawImage(panelBoard, 0,0,null);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        char x = e.getKeyChar();

        switch(x) {
            case 'w':
                game.movePlayer(Player.UP);
                break;
            case 'a':
                game.movePlayer(Player.LEFT);
                break;
            case 's':
                game.movePlayer(Player.DOWN);
                break;
            case 'd':
                game.movePlayer(Player.RIGHT);
                break;
        }


        game.gameChecks();

        System.out.println("Repainting");
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        game.update(startNanoTime);
    }
}
