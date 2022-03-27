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

        if (game.getStatus() == Game.PLAYING){
            Board tmp = game.getBoard();

            bg.setColor(Color.BLACK);
            bg.fillRect(0, 0, SCREENSIZE[0], SCREENSIZE[1]);

            Rectangle visibleScreen = tmp.getVisibleScreen();

            bg.drawImage(tmp.getGoal().getBuffer(), tmp.getGoal().getRect().x - visibleScreen.x, tmp.getGoal().getRect().y - visibleScreen.y, tmp.getGoal().getRect().width, tmp.getGoal().getRect().height, null);

            for (Items i : tmp.getVisibleItems()) {
                bg.drawImage(i.getBuffer(), i.getRect().x - visibleScreen.x, i.getRect().y - visibleScreen.y, i.getRect().width, i.getRect().height, null);
            }

            bg.setColor(Color.GREEN);
            bg.fillRect(game.player.getRect().x-visibleScreen.x, game.player.getRect().y- visibleScreen.y, game.player.getRect().width, game.player.getRect().height);

            bg.setColor(Color.WHITE);
            bg.drawString("Lives: ", 10,10);
            bg.setColor(Color.RED);

            for (int i = 0; i < game.getPlayer().getLives(); i++) {
                bg.fillRect(10+ i*(15 + 5), 10, 15,15);
            }

            g.drawImage(panelBoard, 0, 0, null);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

//        System.out.println("Moving");

        if(game.getStatus() == Game.PLAYING){
            char x = e.getKeyChar();

            switch (x) {
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
        }

        game.gameChecks();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        game.update(startNanoTime);
    }

    public void addNotify(){
        super.addNotify();
        requestFocus();
    }
}
