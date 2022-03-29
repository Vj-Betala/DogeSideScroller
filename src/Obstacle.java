import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Obstacle extends Items{
    private double distance;
    private int currentPoint;
    private Point nextp;
    public Obstacle(Point[] pos, double step) {
        super(0, pos[0], step);
        setHostile(true);
        setPath(pos);
        distance = 0;currentPoint = 0;
        nextp = pos[currentPoint];
        try{
            setBuffer(ImageIO.read(new File("src/Levels/obsimg.png")));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void move(int dir) {

        if(distance <= 0){
            if(currentPoint == getPath().length-1)
                currentPoint = 0;
            else
                currentPoint++;
            nextp = getPath()[currentPoint];
            distance = (int) Math.sqrt(Math.pow(nextp.x - getxPos(), 2) + Math.pow(nextp.y - getyPos(), 2));
        } else {
            double tanAng ;
            if(nextp.y - getyPos() == 0){
                if(nextp.x - getxPos() < 0){
                    tanAng = 180;
                } else {
                    tanAng = 0;
                }
            } else {
                if(nextp.y - getyPos() < 0)
                    tanAng = 270;
                else {
                    tanAng = 90;
                }
            }

            tanAng = Math.toRadians(tanAng);

            setxPos(getxPos() + getStep()*Math.cos(tanAng));
            setyPos(getyPos() + getStep()*Math.sin(tanAng));
            setRect();
            distance -= getStep();
        }

    }

    @Override
    public void update(long nanoStartTime) {
        move(0);
    }
}
