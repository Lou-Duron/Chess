import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    boolean check;
    boolean castle;
    boolean isTop;
    boolean isWhite;
    List<Piece> cemetery; // Array of dead pieces

    public Player(String name, boolean isWhite, boolean isTop){
        this.name = name;
        this.isTop = isTop;
        this.isWhite = isWhite ;
        check = false;
        castle = false;

        cemetery = new ArrayList<Piece>();
    }

}
