import sun.jvm.hotspot.memory.PlaceholderEntry;

import java.awt.*;

public class Game {
    final static int PLAYING = 0, DEAD = -1, WON = 1;

    private int status, level;
    Player player;
    private Board board;


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public Game() {
        player = new Player(3.5);
        status = PLAYING;
        level = 0;
        board = new Board();
    }

    public void update(long startNanoTime){

    }

    public int winCheck() {
        if(level < 5){
            board.newLevel(level);
            if(player.getLives() < 5){
                player.setLives(player.getLives() + 1);
            }

            return PLAYING;
        }

        return WON;
    }

    public boolean gameChecks() {

        if(board.getGoal().getRect().contains(player.getRect())){
            winCheck();
//            System.out.println("On Goal");
            return true;
        }

        for (Items i: board.items) {
            if(i.collisionCheck(player.getRect())){
                if(i.isHostile()){
                    player.setLives(player.getLives() - 1);
                    if(player.getLives() < 0){
                        status = DEAD;
                    } else {
                        player.setxPos(40);
                        player.setyPos(40);
                        player.setRect();
                    }
                    return true;
                } else {
//                    System.out.println("On Wall");
                    return false;
                }
            }
        }

        return true;

    }

    public void movePlayer(int dir){
        player.move(dir);
        if(!gameChecks()){
            switch (dir){
                case Player.UP:
                    player.move(Player.DOWN);
                    break;
                case Player.DOWN:
                    player.move(Player.UP);
                    break;
                case Player.RIGHT:
                    player.move(Player.LEFT);
                    break;
                case Player.LEFT:
                    player.move(Player.RIGHT);
                    break;
            }
        }

        board.updateVisibleScreen(player.getRect());
    }
}
