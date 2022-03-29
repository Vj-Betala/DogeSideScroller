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

    public final static char NOTHING = '0', WALL = '1', GOAL = '3', OBS = '4', OBSPOINT = '5', LASER = '6', LASERORIGIN = '7';
//    public final static char newLine = ',';

    public Board() {
        items = new ArrayList<>();
        levelString = new String[17];
        visibleScreen = new Rectangle(0,0,dogePanel.SCREENSIZE[0], dogePanel.SCREENSIZE[1]);
        visibleItems = new ArrayList<>();

        levels = new File[5];

        levels[0] = new File("src/Levels/Level1.txt");
        levels[1] = new File("src/Levels/Level2.txt");

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
                        break;
                    case LASERORIGIN:
                        items.add(new Walls(new Point(j*40, i*40), true));
                        ArrayList<Point> tmpPointLaser = PointHelper(i,j,LASER);
                        // TODO: 3/28/22 Move in Straight Line and find Laser
                        items.add(new Laser(tmpPointLaser.toArray(new Point[0]), 1));
                        break;
                    case OBS:
                        ArrayList<Point> tmpPointOBS = PointHelper(i,j,OBSPOINT);
                        System.out.println(tmpPointOBS.toString());
                        items.add(new Obstacle(tmpPointOBS.toArray(new Point[0]), 0.5));
                        break;
                }
            }
        }

        updateVisibleScreen(new Rectangle(40,40,35,35));
    }

    public ArrayList<Point> PointHelper(int i, int j,int finder) {
        ArrayList<Point> tmpPoint = new ArrayList<>();
        Point start = new Point(j*40,i*40);
        tmpPoint.add(start);
        boolean run = true;
        while(run){
            if(j < levelString[i].length() && levelString[i].charAt(j+1) == finder){
                tmpPoint.add(new Point((++j)*40, i*40));
            } else if(i < levelString.length && levelString[i+1].charAt(j) == finder){
                tmpPoint.add(new Point(j*40, (++i)*40));
            } else {
                return tmpPoint;
            }
        }

        tmpPoint.add(new Point(j*40,i*40));
        return tmpPoint;
    }

    public void newLevel(int currLevel) {
        if(currLevel < 5){
            levelString = new String[32];
            items = new ArrayList<>();
            try{
                if(levels[1].isFile()){
                    keyboard = new Scanner(levels[1]);
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
                            break;
                        case LASERORIGIN:
                            items.add(new Walls(new Point(j*40, i*40), true));
                            System.out.println("LaserSource");
                            break;
//                        case LASER:
//                            items.add(new Laser(new Point(j * 40, i * 40)));
//                            break;
                    }
                }
            }
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
