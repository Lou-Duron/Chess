import javax.swing.*;

public class Bishop extends Piece {
    
    public Bishop(boolean isWhite) {
        super(isWhite);
        if(isWhite){
            icon = new ImageIcon(Images.WBISHOP.getImage());
            value = 30;
        }
        else{
            icon = new ImageIcon(Images.BBISHOP.getImage());
            value = -30;
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
        // Can't move diagonally
        if (end.position.x-start.position.x != 0){
            if (Math.abs((float)(end.position.y-start.position.y)/(end.position.x-start.position.x)) != 1) {
                return false;
            }
        }
        // Can't move vertically
        if (end.position.x-start.position.x == 0)
            return false;

        // For diagonal moves
        int dirX = end.position.x > start.position.x ? 1 : -1; //right or left diagonal
        int dirY = end.position.y > start.position.y ? 1 : -1; //up or down diagonal
        for (int i = 1; i < Math.abs(end.position.x - start.position.x)+1; i++){
            int x = start.position.x+i*dirX;
            int y = start.position.y+i*dirY;
            if (b.board[x][y].piece != null) { //Collision
                if (b.board[x][y].piece.getColor() == start.piece.getColor()){ //Can't eat its own pieces
                    return false;
                }
                else if (!b.board[x][y].equals(end)){ //Piece in the way between start and end
                    return false;
                }
            }
        }
        return true;
    }
}
