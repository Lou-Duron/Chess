import javax.swing.*;

public class Knight extends Piece {
    boolean castlingDone;
    public Knight(boolean isWhite){
        super(isWhite);
        castlingDone = false;
        if(isWhite){
            icon = new ImageIcon("Images/wn.png");
        }
        else{
            icon = new ImageIcon("Images/bn.png");
        }
        image = new JLabel(icon);
    }
    public boolean canMove(Board b, Square start, Square end) {
        if (start.equals(end))
            return false;

        // If piece with same color
        if (end.piece.getColor() == this.getColor()) {
            return false;
        }

        if (Math.abs(end.y-start.y) != 2 || Math.abs(end.x-start.x) != 1 ){ // up and down L
            if (Math.abs(end.x-start.x) != 2 || Math.abs(end.y-start.y) != 1 ){ // right left L
                return false;
            }
        }
        return true;
    }
}
