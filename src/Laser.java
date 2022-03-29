import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Laser extends Items{
    private boolean visible;
    final static int toggleUpdate = 100;

    public Laser(Point[] pos, int dir) {
        super(0, pos[0], 0);
        visible = rand.nextBoolean();
        setHostile(true);
        try{
            setBuffer(ImageIO.read(new File("src/Levels/laserImg.png")));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void toggleVisibilty(long startNanoTime){
        if(startNanoTime % toggleUpdate == 0){
            visible = !visible;
        }

        if(visible){
            try{
                setBuffer(ImageIO.read(new File("src/Levels/laserImg.png")));
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            setBuffer(null);
        }
    }

    @Override
    public void update(long nanoStartTime) {
        toggleVisibilty(nanoStartTime);
    }

    @Override
    public boolean collisionCheck(Rectangle player) {
        return super.collisionCheck(player) && visible;
    }
}
