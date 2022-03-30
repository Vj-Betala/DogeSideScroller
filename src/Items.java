import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Items {
    public Random rand = new Random();
    private double step;
    private double xPos, yPos;
    private Rectangle rect;
    private BufferedImage buffer;
    private int direction;
    private boolean hostile;
    private Point[] path;

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isHostile() {
        return hostile;
    }

    public void setHostile(boolean hostile) {
        this.hostile = hostile;
    }

    public Point[] getPath() {
        return path;
    }

    public void setPath(Point[] path) {
        this.path = path;
    }

    public final static int PLAYERCONTROLLED = 0, UP = 1, DOWN = 2, RIGHT = 3, LEFT = 4;

    public Items(int dir, Point pos, double step){
        this.direction = dir;
        this.xPos = pos.x;
        this.yPos = pos.y;
        this.step = step;
        rect = new Rectangle(pos.x, pos.y, 40,40);
        buffer = new BufferedImage(40,40,BufferedImage.TYPE_4BYTE_ABGR);
    }

    public void move(){}

    public void setRect() {
        rect.x = (int) xPos;
        rect.y = (int) yPos;
    }

//    public boolean collisionCheck(Point player){
//        return rect.contains(player);
//    }
    public boolean collisionCheck(Rectangle player){return rect.intersects(player);}

    public void update(long nanoStartTime){
        setRect();
    }
}
