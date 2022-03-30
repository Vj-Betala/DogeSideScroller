import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class Laser extends Items{
    private boolean visible;
    final static int toggleUpdate = 100;
    private BufferedImage buffer;

    public Laser(Point pos, int dir, int height, int width) {
        super(dir, pos, 0);
        visible = rand.nextBoolean();
        setHostile(true);
        try{
            buffer = ImageIO.read(new File("src/Levels/laserBeam.png"));
            if(getDirection() == 4)
                buffer = rotateImageByDegrees(buffer, 90);

            setBuffer(buffer);
        } catch (Exception e){
            e.printStackTrace();
        }

        setRect(new Rectangle(pos.x, pos.y, width*40, height*40));

    }

    public void toggleVisibilty(long startNanoTime){
        if(startNanoTime % toggleUpdate == 0){
            visible = !visible;
        }

        if(visible){
            try{
                setBuffer(buffer);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            BufferedImage bg = new BufferedImage(getRect().width,getRect().height,BufferedImage.TYPE_4BYTE_ABGR);
            Graphics g = bg.getGraphics();
            int percent;
            g.setColor(new Color(255,0,0, 7));
            if(getDirection() == 2) {
                percent = (int) (startNanoTime % toggleUpdate)*getRect().height/toggleUpdate;
                g.fillRect(0,0,getRect().width, percent);
            } else {
                percent = (int) (startNanoTime % toggleUpdate) * getRect().width / toggleUpdate;
                g.fillRect(0,0,percent, getRect().height);
            }
            setBuffer(bg);
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

    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.setColor(Color.RED);
        g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
        g2d.dispose();

        return rotated;
    }
}
