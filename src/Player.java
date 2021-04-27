import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    boolean isTop;
    boolean isWhite;
    List<Piece> cemetery; // Array of dead pieces

    public Player(String name, boolean isWhite, boolean isTop){
        this.name = name;
        this.isTop = isTop;
        this.isWhite = isWhite ;
        cemetery = new ArrayList<Piece>();
    }
}
