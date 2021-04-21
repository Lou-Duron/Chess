import javax.swing.*;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(isWhite);
        if(isWhite){
            icon = new ImageIcon("Images/wr.png");
        }
        else{
            icon = new ImageIcon("Images/br.png");
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
        // Can only move in a straight line
        if (Math.abs(end.x-start.x) != 0 || Math.abs(end.y-start.y) != 0)
            return false;

        return true;
    }
}
