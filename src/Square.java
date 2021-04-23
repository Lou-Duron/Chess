import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Square {
    Position position; // Position
    boolean isWhite; // Color and presence of a piece(boolean)
    Piece piece; // Piece on the square
    JLabel image, moves; // Labels 
    ImageIcon icon, iconDispo;

    public Square(Position position, boolean isWhite){
        this.position = position;
        this.isWhite = isWhite;
        piece = null;
        if(isWhite){
            icon = new ImageIcon("Images/cb.png");
            iconDispo = new ImageIcon("Images/cbo.png");
        }
        else{
            icon = new ImageIcon("Images/cn.png");
            iconDispo = new ImageIcon("Images/cno.png");
        }
        image = new JLabel(icon);
        moves = new JLabel(iconDispo);
    }

    // Remove piece from the square
    public void removePiece(){
        piece = null;
    }
    public List<Position> getMoves (Board b) { //Not working at the moment
        List<Position> possibleMoves = new ArrayList<>();

        if (piece != null) {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (piece.canMove(b, this, b.board[x][y])) { //Problem here, since piece doesn't return boolean (not hidden)
                        possibleMoves.add(new Position(x, y));
                    }
                }
            }
        }
        return possibleMoves;
    }

    public boolean equals(Square s){
        return this.position == s.position;
    }
}

    



