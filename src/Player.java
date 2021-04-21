import java.util.Timer;

public class Player {
    String name;
    boolean isWhite;
    Timer time;
    int deadPieces; // Cemetery ??

    public Player(String name, boolean isWhite){
        this.name = name;
        this.isWhite = isWhite ;
        time = null;
        deadPieces = 0;
    }
}
