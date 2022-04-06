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
            int x = rand.nextInt(4);
            if (x == 0)
                setBuffer(ImageIO.read(new File("src/Levels/testImgWall.png")));
            else if (x == 1)
                setBuffer(ImageIO.read(new File("src/Levels/testImgWall2.png")));
            else if (x == 2)
                setBuffer(ImageIO.read(new File("src/Levels/testImgWall3.png")));
            else
                setBuffer(ImageIO.read(new File("src/Levels/testImgWall4.png")));
            if(laserSource) {
                setBuffer(ImageIO.read(new File("src/Levels/laserOffImg.png")));
                imgLaser = ImageIO.read(new File("src/Levels/LaserOnImg.png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move() {
        super.move();
    }

}
