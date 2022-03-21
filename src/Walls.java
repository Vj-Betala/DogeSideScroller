import java.awt.*;

public class Walls extends Items{

    public boolean isLaserSource() {
        return laserSource;
    }

    public void setLaserSource(boolean laserSource) {
        this.laserSource = laserSource;
    }

    private boolean laserSource;

    public Walls(Point pos, boolean laserSource) {
        super(0, pos, 0);
        this.laserSource = laserSource;
        setHostile(false);
    }

    @Override
    public void move(int dir) {
        super.move(dir);
    }
}
