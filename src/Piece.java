import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Piece {
    boolean isWhite; //Status
    JLabel image; // Label
    ImageIcon icon; // Icon
    boolean hasMoved;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public Piece(Piece p){
        this.isWhite = p.isWhite;
        this.image = null;
        this.icon = null;
        hasMoved = false;
    }

    public boolean getColor(){
        return isWhite;
    }
    public abstract boolean canMove(Board b, Square start, Square end);
}
