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
        player = new Player(0.4);
        status = PLAYING;
        level = 0;
        board = new Board();
    }

    public void update(long startNanoTime){

    }

    public int winCheck() {
        return PLAYING;
    }

    public boolean gameChecks() {
        return false;
    }

    public void movePlayer(int dir){

    }
}
