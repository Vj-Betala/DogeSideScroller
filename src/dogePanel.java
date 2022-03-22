import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class dogePanel extends JPanel implements KeyListener, Runnable {
    final static int UPS = 35;
    final static int[] SCREENSIZE = {800,600};
    Game game;
    BufferedImage panelBoard;
    public dogePanel(){
        game = new Game();
        panelBoard = new BufferedImage(SCREENSIZE[0], SCREENSIZE[1], BufferedImage.TYPE_4BYTE_ABGR);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {

    }
}
