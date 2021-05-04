import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Square {
    Position position; // Position
    boolean isWhite; // Color and presence of a piece(boolean)
    Piece piece; // Piece on the square
    JLabel image, imageLastMove, imageCheck, movesEmpty, movesFilled; // Labels 
    ImageIcon icon, iconDispoEmpty, iconDispoFilled, iconLastMove, iconCheck;


    public Square(Position position, boolean isWhite){
        this.position = position;
        this.isWhite = isWhite;
        piece = null;
        if(isWhite){
            icon = new ImageIcon("Images/cb.png");
            iconLastMove = new ImageIcon("Images/cby.png");
            iconCheck = new ImageIcon("Images/cbr.png");
        }
        else{
            icon = new ImageIcon("Images/cn.png");
            iconLastMove = new ImageIcon("Images/cny.png");
            iconCheck = new ImageIcon("Images/cnr.png");
        }
        image = new JLabel(icon);
        imageLastMove = new JLabel(iconLastMove);
        imageCheck = new JLabel(iconCheck);
        iconDispoEmpty = new ImageIcon("Images/empty.png");
        movesEmpty = new JLabel(iconDispoEmpty);
        iconDispoFilled = new ImageIcon("Images/filled.png");
        movesFilled = new JLabel(iconDispoFilled);
    }

    public List<Position> getMoves (Board b) {
        List<Position> possibleMoves = new ArrayList<>();
        if (piece != null) {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (piece.canMove(b, this, b.board[x][y])) {
                        possibleMoves.add(new Position(x, y));
                    }
                }
            }
        }
        return possibleMoves;
    }

    public List<Position> getMovesInCheck (Board b){
        List<Position> restrictedMoves = new ArrayList<>();
        Position currentSquarePosition = new Position(this.position);
        for (Position p : getMoves(b)){
            b.movePiece(this, b.board[p.x][p.y]);
            if (!b.isCheck(b.currentPlayer.isWhite)){
                restrictedMoves.add(p);
            }
            b.movePiece(this, b.board[currentSquarePosition.x][currentSquarePosition.y]);
        }
        return restrictedMoves;
    }

    public Square(Square s){
        new Square(s.position, s.isWhite);
        this.piece = s.piece;
    }

    public void hypothesisMove(Square s){
        s.piece = this.piece;
        this.piece = null;
    }

    public boolean equals(Square s){
        return this.position == s.position;
    }
}

    



