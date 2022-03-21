import java.awt.*;

public class Laser extends Items{
    private boolean visible;
    final static int toggleUpdate = 100;

    public Laser(Point pos) {
        super(0, pos, 0);
        visible = rand.nextBoolean();
        setHostile(true);
    }

    public void toggleVisibilty(long startNanoTime){
        if(startNanoTime % toggleUpdate == 0){
            visible = !visible;
        }
    }

    @Override
    public void update(long nanoStartTime) {
        toggleVisibilty(nanoStartTime);
    }
}
