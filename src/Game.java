
public class Game {
    final static int PLAYING = 0, DEAD = -1, WON = 1;
    int currLevel;

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
        player = new Player(1.5);
        status = PLAYING;
        level = 0;
        board = new Board();
    }

    public void update(long startNanoTime){
        if(status == PLAYING) {
            for (Items i : board.items) {
                i.move();
                i.update(startNanoTime);
            }

            if(gameChecks()) {
                player.move();
                board.updateVisibleScreen(player.getRect(), new int[]{0, 0});
            }
            if(!gameChecks()){
                if(player.up) {
                    player.setyPos(player.getyPos() + player.getStep());
                }
                if(player.down) {
                    player.setyPos(player.getyPos() - player.getStep());
                }
                if(player.left) {
                    player.setxPos(player.getxPos() + player.getStep());
                }
                if(player.right) {
                    player.setxPos(player.getxPos() - player.getStep());
                }

                player.setRect();
            }
        }
    }

    public int winCheck() {
        if(level <= 2){
            board.newLevel(level);
            level++;
            player.setxPos(40);
            player.setyPos(40);
            player.setRect();
            player.setLives(player.getLives() + 1);
            return PLAYING;
        }

        System.out.println("Won");
        return WON;
    }

    public boolean gameChecks() {

        if(board.getGoal().getRect().contains(player.getRect())){
            status = winCheck();
            return true;
        }

        for (Items i: board.items) {
            if(i.collisionCheck(player.getRect())){
                if(i.isHostile()){
                    player.setLives(player.getLives() - 1);
                    if(player.getLives() < 1){
                        status = DEAD;
                    } else {
                        player.setxPos(40);
                        player.setyPos(40);
                        player.setRect();
                    }
                    return true;
                }else {
                    return false;
                }
            }
        }

        return true;

    }

//    public void movePlayer(int dir){
//        player.setDirection(dir);
//    }

}
