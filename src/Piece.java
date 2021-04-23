import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Piece {
    private boolean isWhite; //Status
    JLabel image; // Label
    ImageIcon icon; // Icon

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean getColor(){
        return isWhite;
    }
    public abstract boolean canMove(Board b, Square start, Square end);
}
