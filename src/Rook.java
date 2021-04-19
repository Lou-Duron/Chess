public class Rook extends Piece {

    public Rook(boolean color) {
        super(color);

    }

    public boolean canMove(Board b, Square start, Square end) {
        if (start.equals(end))
            return false;

        // If piece with same color
        if (end.piece.getColor() == this.getColor()) {
            return false;
        }
        // Can only move in a straight line
        if (Math.abs(end.x-start.x) != 0 || Math.abs(end.y-start.y) != 0)
            return false;

        return true;
    }
}
