public class King extends Piece {
    public boolean check;

    public King(boolean color){
        super(color);
        check = false;
    }

    public boolean isCheck(){
        return check;
    }

    public boolean canMove(Board b, Square start, Square end) {
        if (start.equals(end))
            return false;
        // If piece with same color
        if (end.piece.getColor() == this.getColor()) {
            return false;
        }
        // Can only move to a neighboring square
        if (Math.abs(end.x-start.x) > 1 || Math.abs(end.y-start.y) > 1)
            return false;

        if (isCheck()){
            return false;
        }
        return true;
    }

}
