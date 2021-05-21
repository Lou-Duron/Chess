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

    public Bishop(Bishop b){
        super(b);
    }

    public boolean canMove(Board b, Square start, Square end) {
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

        int dirX = end.position.x > start.position.x ? 1 : -1;
        int dirY = end.position.y > start.position.y ? 1 : -1;
        for (int i = 1; i < Math.abs(end.position.x - start.position.x)+1; i++){
            int x = start.position.x+i*dirX;
            int y = start.position.y+i*dirY;
            if (b.board[x][y].piece != null) {
                if (b.board[x][y].piece.getColor() == start.piece.getColor()){
                    return false;
                }
                else if (!b.board[x][y].equals(end)){
                    return false;
                }
            }
        }
        return true;
    }
}
