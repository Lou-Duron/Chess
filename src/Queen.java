import javax.swing.*;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
        if(isWhite){
            icon = new ImageIcon("Images/wq.png");
            value = 90;
        }
        else{
            icon = new ImageIcon("Images/bq.png");
            value = -90;
        }
        image = new JLabel(icon);
    }
    public Queen(Queen q){
        super(q);
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

        if (Math.abs((float)(end.position.y-start.position.y)/(end.position.x-start.position.x)) != 1) { //didn't move diagonally
            if (distanceX != 0 && distanceY != 0) {  //Didn't move vertically
                return false;
            }
        }

        //Diagonal Collisions
        boolean diagonal = Math.abs(end.position.x - start.position.x) == Math.abs(end.position.y - start.position.y);
        if (diagonal) {
            int dirX = end.position.x > start.position.x ? 1 : -1;
            int dirY = end.position.y > start.position.y ? 1 : -1;
            for (int i = 1; i < Math.abs(end.position.x - start.position.x) + 1; i++) {
                int x = start.position.x + i * dirX;
                int y = start.position.y + i * dirY;
                if (b.board[x][y].piece != null) {
                    if (b.board[x][y].piece.getColor() == start.piece.getColor()) {
                        return false;
                    } else if (!b.board[x][y].equals(end)) {
                        return false;
                    }
                }
            }
        }
        else {
            // Vertical Collisions
            int dirX = end.position.x >= start.position.x ? 1 : -1;
            int dirY = end.position.y >= start.position.y ? 1 : -1;
            int maxDistance = Math.max(distanceX, distanceY);
            int x;
            int y;

            for (int i = 1; i <= maxDistance; i++) {
                if (end.position.x == start.position.x) {
                    x = start.position.x;
                    y = start.position.y + i * dirY;
                } else {
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
        }
        return true;
    }
}
