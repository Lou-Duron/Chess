import javax.swing.*;

public class Rook extends Piece {
        boolean hasMoved;

    public Rook(boolean isWhite) {
        super(isWhite);
        hasMoved = false;
        if(isWhite){
            icon = new ImageIcon(Images.WROOK.getImage());
            value = 50;
        }
        else{
            icon = new ImageIcon(Images.BROOK.getImage());
            value = 50;
        }
        image = new JLabel(icon);
    }

    public Rook(Rook r){
        super(r);
        hasMoved = r.hasMoved;
    }

    public boolean canMove(Board b, Square start, Square end) {
        int distanceX = Math.abs(end.position.x - start.position.x);
        int distanceY = Math.abs(end.position.y - start.position.y);

        // If piece with same color
        if(end.piece != null){
            if (end.piece.getColor() == this.getColor()) {
                return false;
            }
        }
        // if move not in a straight line
        if (distanceX != 0 && distanceY != 0)
            return false;


        //  Collision
        int dirX = end.position.x >= start.position.x ? 1 : -1;
        int dirY = end.position.y >= start.position.y ? 1 : -1;
        int maxDistance = Math.max(distanceX, distanceY );
        int x;
        int y;

        for (int i = 1; i <= maxDistance; i++){
            if (end.position.x == start.position.x){
                x = start.position.x;
                y = start.position.y + i * dirY;
            }
            else{
                x = start.position.x + i * dirX;
                y = start.position.y;
            }

            if (b.board[x][y].piece != null) {
                if (b.board[x][y].piece.getColor() == start.piece.getColor()) {
                    return false;

                } else if (!b.board[x][y].equals(end)) {
                    return false;
                }
            }
        }
        return true;
    }
}
