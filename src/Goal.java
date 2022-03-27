import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Goal extends Items{
    public Goal(Point pos) {
        super(0, pos, 0);
        setHostile(false);
        try{
            setBuffer(ImageIO.read(new File("src/Levels/goalImg.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
