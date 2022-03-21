import java.awt.*;

public class Obstacle extends Items{
    private int currentPoint;
    public Obstacle(int dir, Point[] pos, double step) {
        super(0, pos[0], step);
        setPath(pos);
        currentPoint = 0;
    }

    @Override
    public void move(int dir) {
        Point nextp;
        if(currentPoint > getPath().length)
            nextp = getPath()[0];
        else
            nextp = getPath()[currentPoint + 1];

        if(getRect().contains(nextp)){

        } else {
            double angle = Math.toDegrees(Math.atan2(getyPos()-nextp.y, getxPos() - nextp.x));
            setxPos(getxPos() + (getStep() * (Math.cos(angle))));
            setyPos(getyPos() + (getStep() * (Math.sin(angle))));
        }
    }

    @Override
    public void update(long nanoStartTime) {
        move(0);
    }
}
