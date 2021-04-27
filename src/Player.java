import java.util.Timer;
import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    boolean isTop;
    boolean isWhite;
    Timer time;
    List<Piece> cemetery; // Array of dead pieces

    public Player(String name, boolean isWhite, boolean isTop){
        this.name = name;
        this.isTop = isTop;
        this.isWhite = isWhite ;
        time = null;
        cemetery = new ArrayList<>();
    }
}
