
public class Queen extends Piece {

    public Queen(boolean color) {
        super(color);
    }

    public boolean canMove(Board b, Square start, Square end) {
        if (start.equals(end))
            return false;

        // If piece with same color
        if (end.piece.getColor() == this.getColor()) {
            return false;
        }

        if (end.x == start.x || end.y == start.y){ //didn't move diagonally
            if (Math.abs(end.x-start.x) != 0 || Math.abs(end.y-start.y) != 0){  //didn't move vertically
                return false;
            }
        }
        return true;
    }
}
