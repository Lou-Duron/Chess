import javax.swing.*;
import java.util.Objects;

public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite);
        if(isWhite){
            icon = new ImageIcon(Images.WPAWN.getImage());
            value = 10;
        }
        else{
            icon = new ImageIcon(Images.BPAWN.getImage());
            value = -10;
        }
        image = new JLabel(icon);

    }

    public Pawn(Pawn p){
        super(p);
    }

    public boolean canMove(Board b, Square start, Square end) {
        if (b.currentPlayer == b.playerBot){
            if (start.position.y == 6){ //Starting position
                if ( end.position.y - start.position.y < -2 || end.position.y - start.position.y > 0) {
                    return false;
                }
                else {
                    if (end.position.y == start.position.y-2 && end.position.x == start.position.x){
                        if (b.board[start.position.x][start.position.y-1].piece != null){
                            return false;
                        }
                    }
                }
            }
            else if (end.position.y - start.position.y != -1) {
                return false;
            }
        }
        else{
            if (start.position.y == 1) { //Starting position
                if (end.position.y - start.position.y > 2 || end.position.y - start.position.y < 0) {
                    return false;
                }
                else {
                    if (end.position.y == start.position.y+2 && end.position.x == start.position.x){
                        if (b.board[start.position.x][start.position.y+1].piece != null){
                            return false;
                        }
                    }
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
