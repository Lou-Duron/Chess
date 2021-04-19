public class Pawn extends Piece {

    public Pawn(boolean color) {
        super(color);
    }
    public boolean canMove(Board b, Square start, Square end) {
        if (start.equals(end))
            return false;

        // If piece with same color
        if (end.piece.getColor() == this.getColor()) {
            return false;
        }
        // Pawns can only move forward
        if (end.x < start.x) {
            return false;
        }

        // Diagonal move to eat a piece
        if (Math.abs(end.x-start.x) != 1 || Math.abs(end.y-start.y) != 1) {
            return false;
        }
        return true;
    }
}
