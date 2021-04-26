import javax.swing.*;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
        if(isWhite){
            icon = new ImageIcon("Images/wq.png");
        }
        else{
            icon = new ImageIcon("Images/bq.png");
        }
        image = new JLabel(icon);
    }

    public boolean canMove(Board b, Square start, Square end) {

        // If piece with same color
        if(end.piece != null){
            if (end.piece.getColor() == this.getColor()) {
                return false;
            }
        }

        if (Math.abs((float)(end.position.y-start.position.y)/(end.position.x-start.position.x)) != 1) {//didn't move diagonally
            if ((Math.abs(end.position.x-start.position.x) != 0 && Math.abs(end.position.y-start.position.y) != 0)) {  //Didn't vertically
                return false;
            }
        }
        return true;
    }
}
