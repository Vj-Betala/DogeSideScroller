import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Walls extends Items{

    public boolean isLaserSource() {
        return laserSource;
    }

    public void setLaserSource(boolean laserSource) {
        this.laserSource = laserSource;
    }

    private boolean laserSource;
    BufferedImage imgLaser;

    public Walls(Point pos, boolean laserSource) {
        super(0, pos, 0);
        this.laserSource = laserSource;
        setHostile(false);
        try{
            setBuffer(ImageIO.read(new File("src/Levels/testImgWall.png")));
            if(laserSource) {
                setBuffer(ImageIO.read(new File("src/Levels/laserOffImg.png")));
                imgLaser = ImageIO.read(new File("src/Levels/LaserOnImg.png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move(int dir) {
        super.move(dir);
    }
}
