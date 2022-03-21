import java.awt.*;

public class Player extends Items{
    private int lives;

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Player(double step) {
        super(0, new Point(0,0), 1);
        setHostile(false);
    }

    @Override
    public void move(int dir) {
        switch(dir) {
            // TODO: 3/12/22 Check Collision in game 
            case UP:
                this.setyPos(getyPos() + getStep());
                break;
            case DOWN:
                this.setyPos(getyPos() - getStep());
                break;
            case RIGHT:
                this.setxPos(getxPos() + getStep());
                break;
            case LEFT:
                this.setxPos(getxPos() - getStep());
                break;
        }

        setRect();
    }
}
