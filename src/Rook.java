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
        if(end.piece != null){
            if (end.piece.getColor() == this.getColor()) {
                return false;
            }
        }
        // Can only move in a straight line
        if (Math.abs(end.position.x-start.position.x) != 0 && Math.abs(end.position.y-start.position.y) != 0)
            return false;

/*        int dirX = end.position.x > start.position.x ? 1 : -1;
        int dirY = end.position.y > start.position.y ? 1 : -1;

        for (int i = 1; i < stop condition; i++){
            if (start.position.y + i * dirY >= 0) {
                int x = start.position.x;
                int y = start.position.y + i * dirY;
                System.out.println(x +" "+ y);

                if (b.board[x][y].piece != null) {
                    if (b.board[x][y].piece.getColor() == start.piece.getColor()) {
                        return false;
                    } else if (!b.board[x][y].equals(end)) {
                        return false;
                    }
                }
            }
            if (start.position.y + i * dirX >= 0) {
                int x = start.position.x+i*dirX;
                int y = start.position.y;
                System.out.println(x + " "+ y);
                if (b.board[x][y].piece != null) {
                    if (b.board[x][y].piece.getColor() == start.piece.getColor()) {
                        return false;
                    } else if (!b.board[x][y].equals(end)) {
                        return false;
                    }
                }
            }
        }*/

        return true;
    }
}
