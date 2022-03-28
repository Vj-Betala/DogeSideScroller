import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Obstacle extends Items{
    private int currentPoint;
    public Obstacle(Point[] pos, double step) {
        super(0, pos[0], step);
        setHostile(true);
        setPath(pos);
        currentPoint = 1;

        try{
            setBuffer(ImageIO.read(new File("src/Levels/obsimg.png")));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void move(int dir) {
        Point nextp = getPath()[currentPoint];

        if(getRect().contains(nextp)){
            currentPoint = ++currentPoint > getPath().length ? 0 : currentPoint;
            nextp = getPath()[currentPoint];
        }

        double angle = Math.toDegrees(Math.atan2(getyPos()-nextp.y, getxPos() - nextp.x));
        System.out.println(angle);
        if(getxPos() > nextp.x){
            angle -= 180;
        }
        setxPos(getxPos() + (getStep() * (Math.cos(angle))));
        setyPos(getyPos() + (getStep() * (Math.sin(angle))));
        setRect();

    }

    @Override
    public void update(long nanoStartTime) {
        move(0);
    }
}
