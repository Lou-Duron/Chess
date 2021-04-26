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

        // If piece with same color
        if (end.piece != null){// if square empty or with same color as the piece
            if (end.piece.getColor() == this.getColor()) {
                return false;
            }
        }

        // Pawns can only move forward

        if (end.position.x == start.position.x){
            if (end.position.y - start.position.y == 2*sense){
                if (start.position.y != 1 && start.position.y != 7){
                    return false;
                }
            }
            if (end.position.y - start.position.y == sense){
                if (end.piece != null){
                    return false;
                }
            }
            if (end.position.y - start.position.y > sense && end.position.y - start.position.y < 2*sense){
                return false;
            }
        }

        return true;
    }
}
