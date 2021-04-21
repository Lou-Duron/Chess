import javax.swing.*;

public class Bishop extends Piece {
    
    public Bishop(boolean color, int y) {
        super(color);
    }

    public boolean canMove(Board b, Square start, Square end) {

        if (start.equals(end))
            return false;

        // If piece with same color
        if (end.piece.getColor() == this.getColor()) {
            return false;
        }
        // Didn't move diagonally
        if (end.x == start.x || end.y == start.y)
            return false;

        return true;
    }
}
