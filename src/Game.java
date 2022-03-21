public class Game {
    final static int PLAYING = 0, DEAD = -1, WON = 1;
    private int status, level;
    Player player;
    public Game() {
        player = new Player(0.4);

    }
}
