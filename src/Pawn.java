import javax.swing.*;

public class Pawn extends Piece {
    int sense;
    public Pawn(boolean isWhite) {
        super(isWhite);
        if(isWhite){
            icon = new ImageIcon("Images/wp.png");
            sense = -1;
        }
        else{
            icon = new ImageIcon("Images/bp.png");
            sense = 1;
        }
        image = new JLabel(icon);

    }
    public boolean canMove(Board b, Square start, Square end) {
        if (getColor()){ //White
            if (start.position.y == 6){ //Starting position
                if ( end.position.y - start.position.y < -2) {
                    return false;
                }
            }
            else if (end.position.y - start.position.y != -1) {
                return false;
            }
        }
        if (!getColor()){ //Black
            if (start.position.y == 1) { //Starting position
                if (end.position.y - start.position.y > 2) {
                    return false;
                }
            }
            else if (end.position.y - start.position.y != 1) {
                return false;
            }
        }

        // Moves to eat a piece
        boolean diagonal = Math.abs(end.position.x - start.position.x) == 1 && Math.abs(end.position.y - start.position.y) == 1;

        if(end.piece != null && (!diagonal || end.piece.getColor() == this.getColor())){ //If there is a piece on the square
            return false;
        }

        if (end.piece == null && Math.abs(end.position.x - start.position.x) != 0) { //if not
            return false;
        }

        return true;
    }
}
