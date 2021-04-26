import javax.swing.*;

public class Bishop extends Piece {
    
    public Bishop(boolean isWhite) {
        super(isWhite);
        if(isWhite){
            icon = new ImageIcon("Images/wb.png");
        }
        else{
            icon = new ImageIcon("Images/bb.png");
        }
        image = new JLabel(icon);
    }

    public boolean canMove(Board b, Square start, Square end) {

        if (start.equals(end))
            return false;

        // If piece with same color
        if(end.piece != null){
            if (end.piece.getColor() == this.getColor()) {
                return false;
            }
        }
        // Didn't move diagonally
        if (end.position.x-start.position.x != 0){
            if (Math.abs((float)(end.position.y-start.position.y)/(end.position.x-start.position.x)) != 1) {
                return false;
            }
        }

        // Can't move vertically
        if (end.position.x-start.position.x == 0)
            return false;

        return true;
    }
}
