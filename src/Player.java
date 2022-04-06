import java.awt.*;

public class Player extends Items{
    private int lives;

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean left = false, right = false, up = false, down = false;

    public Player(double step) {
        super(0, new Point(40,40), step);
        this.setRect(new Rectangle(40,40,25,25));
        this.lives = 5;
        setHostile(false);
    }

    public void move() {
//        switch(getDirection()) {
//            case UP:
//                this.setyPos(getyPos() - getStep());
//                break;
//            case DOWN:
//                this.setyPos(getyPos() + getStep());
//                break;
//            case RIGHT:
//                this.setxPos(getxPos() + getStep());
//                break;
//            case LEFT:
//                this.setxPos(getxPos() - getStep());
//                break;
//        }

        if(up) {
            this.setyPos(getyPos() - getStep());
        }
        if(down) {
            this.setyPos(getyPos() + getStep());
        }
        if(left) {
            this.setxPos(getxPos() - getStep());
        }
        if(right) {
            this.setxPos(getxPos() + getStep());
        }

        setRect();
    }
}
