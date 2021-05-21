import javax.swing.*;

public class Knight extends Piece {
    boolean castlingDone;
    public Knight(boolean isWhite){
        super(isWhite);
        castlingDone = false;
        if(isWhite){
            icon = new ImageIcon("Images/wn.png");
            value = 30;
        }
        else{
            icon = new ImageIcon("Images/bn.png");
            value = -30;
        }
        image = new JLabel(icon);
    }

    public Knight(Knight k){
        super(k);
        castlingDone = k.castlingDone;
    }
    public boolean canMove(Board b,Square start, Square end) {
        if (start.equals(end))
            return false;

        // If piece with same color
        if(end.piece != null){
            if (end.piece.getColor() == this.getColor()) {
                return false;
            }
        }
        if (Math.abs((float)(end.position.y-start.position.y)*(end.position.x-start.position.x)) != 2){ // right left L
                return false;
            }
        return true;
    }
}
