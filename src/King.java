import javax.swing.*;

public class King extends Piece {
    public boolean check;

    public King(boolean isWhite){
        super(isWhite);
        check = false;
        if(isWhite){
            icon = new ImageIcon("Images/wk.png");
        }
        else{
            icon = new ImageIcon("Images/bk.png");
        }
        image = new JLabel(icon);
    }

    public boolean isCheck(){
        return check;
    }

    public boolean canMove(Board b, Square start, Square end) {

        // If piece with same color
        if(end.piece != null){
            if (end.piece.getColor() == this.getColor()) {
                return false;
            }
        }
        // Can only move to a neighboring square
        if (Math.abs(end.position.x-start.position.x) > 1 || Math.abs(end.position.y-start.position.y) > 1)
            return false;

        if (isCheck()){
            return false;
        }
        return true;
    }

}
