import com.sun.tools.doclets.formats.html.PackageIndexFrameWriter;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    private File[] levels;
    public Scanner keyboard;
    private String[] levelString;
    Goal goal;
    ArrayList<Items> items;

    public ArrayList<Items> getVisibleItems() {
        return visibleItems;
    }

    public void setVisibleItems(ArrayList<Items> visibleItems) {
        this.visibleItems = visibleItems;
    }

    ArrayList<Items> visibleItems;
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

    public String[] getLevelString() {
        return levelString;
    }

    public void setLevelString(String[] levelString) {
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

    public final static char NOTHING = '0', WALL = '1', GOAL = '3', OBS = '4', LASER = '6', LASERORIGIN = '7';
//    public final static char newLine = ',';

    public Board() {
        items = new ArrayList<>();
        levelString = new String[17];
        visibleScreen = new Rectangle(0,0,dogePanel.SCREENSIZE[0], dogePanel.SCREENSIZE[1]);
        visibleItems = new ArrayList<>();

        levels = new File[5];

        levels[0] = new File("src/Levels/Level1.txt");

        try{
            if(levels[0].isFile()){
                keyboard = new Scanner(levels[0]);
                int x = 0;
                while(keyboard.hasNextLine() && x < levelString.length) {
                    levelString[x] = keyboard.nextLine();
                    x++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        for (int i = 0; i < levelString.length; i++) {
            for (int j = 0; j < levelString[i].length(); j++) {
                switch (levelString[i].charAt(j)){
                    case WALL:
                        items.add(new Walls(new Point(j*40, i*40), false));
                        break;
                    case GOAL:
                        this.goal = new Goal(new Point(j*40, i*40));
                }
            }
        }

        updateVisibleScreen(new Rectangle(40,40,35,35));
    }

    public void newLevel(int currLevel) {
        if(currLevel < 5){
            // TODO: 3/25/22 Gen String from CurrLevel
        }
    }

    public void updateVisibleScreen(Rectangle player){
        visibleItems = new ArrayList<>();
        if(player.x < visibleScreen.width/2){
            visibleScreen.x = 0;
        } else if(visibleScreen.x >= levelString[0].length() * 40 - (visibleScreen.width) && player.x - visibleScreen.x >= visibleScreen.width/2){
            visibleScreen.x = levelString[0].length()*40 - (visibleScreen.width);
        } else {
            visibleScreen.x = player.x - visibleScreen.width/2;
        }
        if(player.y < visibleScreen.height/2){
            visibleScreen.y = 0;
        } else if(visibleScreen.y >= levelString.length * 40 - (visibleScreen.height) && player.y - visibleScreen.y >= visibleScreen.height/2){
            visibleScreen.y = levelString.length * 40 - (visibleScreen.height);
        } else {
            visibleScreen.y = player.y - visibleScreen.height/2;
        }


        for (Items i: items) {
            if(visibleScreen.intersects(i.getRect())){
                visibleItems.add(i);
            }
        }
    }
}
