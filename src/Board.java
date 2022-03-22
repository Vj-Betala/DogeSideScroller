import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    private File[] levels;
    public Scanner keyboard;
    private String[][] levelString;
    Goal goal;
    ArrayList<Items> items;
    Rectangle visibleScreen;

    public File[] getLevels() {
        return levels;
    }

    public void setLevels(File[] levels) {
        this.levels = levels;
    }

    public Scanner getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Scanner keyboard) {
        this.keyboard = keyboard;
    }

    public String[][] getLevelString() {
        return levelString;
    }

    public void setLevelString(String[][] levelString) {
        this.levelString = levelString;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    public Rectangle getVisibleScreen() {
        return visibleScreen;
    }

    public void setVisibleScreen(Rectangle visibleScreen) {
        this.visibleScreen = visibleScreen;
    }

    public final static int NOTHING = 0, WALL = 1, GOAL = 3, OBS = 4, LASER = 6, LASERORIGIN = 7;
    public final static char newLine = ',';

    public Board() {
        items = new ArrayList<>();
        visibleScreen = new Rectangle(0,0,dogePanel.SCREENSIZE[0], dogePanel.SCREENSIZE[1]);

    }

    public void newLevel(int currLevel) {

    }

    public void updateVisibleScreen(Rectangle player){
        if(player.x < dogePanel.SCREENSIZE[0]/2){
            visibleScreen.x = 0;
        } else {
            
        }
        if(player.y < dogePanel.SCREENSIZE[1]/2){
            visibleScreen.y = 0;
        } else {

        }
    }
}
