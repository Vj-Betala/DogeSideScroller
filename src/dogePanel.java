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
        bg.setColor(Color.BLACK);
        bg.fillRect(0, 0, SCREENSIZE[0], SCREENSIZE[1]);

        if (game.getStatus() == Game.PLAYING){
            Board tmp = game.getBoard();


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

        } else if(game.getStatus() == Game.WON){
            bg.setColor(Color.WHITE);
            bg.drawString("You have won, press 'r' to reset game from level 1", 50,50);
        } else if(game.getStatus() == Game.DEAD) {
            bg.setColor(Color.WHITE);
            bg.drawString("You have lost, press 'r' to reset game from level 1", 50, 50);
        }
        g.drawImage(panelBoard, 0, 0, null);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char x = e.getKeyChar();
        if(game.getStatus() == Game.PLAYING){

            switch (x) {
                case 'w':
                    game.getPlayer().up = true;
                    break;
                case 'a':
                    game.getPlayer().left = true;
                    break;
                case 's':
                    game.getPlayer().down = true;
                    break;
                case 'd':
                    game.getPlayer().right = true;
                    break;
            }
            game.gameChecks();
            repaint();
        }

        if(x == 'r' && (game.getStatus() == Game.DEAD || game.getStatus() == Game.WON)){
            System.out.println("Reset");
            game = new Game();
            repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        char x = e.getKeyChar();

        if(game.getStatus() == Game.PLAYING){

            switch (x) {
                case 'w':
                    game.getPlayer().up = false;
                    break;
                case 'a':
                    game.getPlayer().left = false;
                    break;
                case 's':
                    game.getPlayer().down = false;
                    break;
                case 'd':
                    game.getPlayer().right = false;
                    break;
            }
            game.gameChecks();
            repaint();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                game.update(startNanoTime++);
                Thread.sleep(1000 / UPS);
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addNotify(){
        super.addNotify();
        requestFocus();
    }
}
