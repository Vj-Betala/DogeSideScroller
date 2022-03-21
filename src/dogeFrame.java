import javax.swing.*;
import java.awt.*;

public class dogeFrame extends JFrame {

    public dogePanel p;

    public dogeFrame(String x){
        super(x);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        pack();

        p = new dogePanel();

        Insets inset = getInsets();

        int width = p.getWidth() + inset.left + inset.right;
        int height = p.getHeight() + inset.top + inset.bottom;

        setPreferredSize(new Dimension(width, height));
        setLayout(null);
        add(p);
        pack();

        setVisible(true);
    }
}
