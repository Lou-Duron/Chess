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
            icon = new ImageIcon(Images.WSQUARE.getImage());
            iconLastMove = new ImageIcon(Images.WLASTMOVE.getImage());
            iconCheck = new ImageIcon(Images.WCHECK.getImage());
        }
        else{
            icon = new ImageIcon(Images.BSQUARE.getImage());
            iconLastMove = new ImageIcon(Images.BLASTMOVE.getImage());
            iconCheck = new ImageIcon(Images.BCHECK.getImage());
        }
        image = new JLabel(icon);
        imageLastMove = new JLabel(iconLastMove);
        imageCheck = new JLabel(iconCheck);
        iconDispoEmpty = new ImageIcon(Images.EMPTY.getImage());
        movesEmpty = new JLabel(iconDispoEmpty);
        iconDispoFilled = new ImageIcon(Images.FILLED.getImage());
        movesFilled = new JLabel(iconDispoFilled);
    }

    public Square(Square s) { //Copy constructor
        this(s.position, s.isWhite);
        this.imageLastMove = null;
        this.image = null;
        this.imageCheck = null;
        this.icon = null;
        this.iconLastMove = null;
        this.iconDispoFilled = null;
        this.iconCheck = null;
        this.movesFilled = null;
        this.iconDispoEmpty = null;
        this.movesEmpty = null;
        if (s.piece != null) {
            if (s.piece instanceof Bishop) {
                this.piece = new Bishop(s.piece.isWhite);
            }
            if (s.piece instanceof Knight) {
                this.piece = new Knight(s.piece.isWhite);
            }
            if (s.piece instanceof King) {
                this.piece = new King(s.piece.isWhite);
            }
            if (s.piece instanceof Pawn) {
                this.piece = new Pawn(s.piece.isWhite);
            }
            if (s.piece instanceof Queen) {
                this.piece = new Queen(s.piece.isWhite);
            }
            if (s.piece instanceof Rook) {
                this.piece = new Rook(s.piece.isWhite);
            }
        }
        else this.piece = null;
   }

    public List<Position> getPossibleMoves (Board b){ //Basics Moves
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


    public ArrayList<Position> getMoves (Board b) {
        Board temporaryBoard;
        ArrayList<Position> definitiveMoves = new ArrayList<Position>();
        for (Position p : getPossibleMoves(b)) {
            temporaryBoard = new Board(b);
            temporaryBoard.movePieceTemp(temporaryBoard.board[this.position.x][this.position.y], temporaryBoard.board[p.x][p.y]);
            Player player = temporaryBoard.currentPlayer;
            temporaryBoard.currentPlayer = temporaryBoard.uncurrentPlayer;
            temporaryBoard.uncurrentPlayer = player;
            if (!temporaryBoard.isCheck(!b.currentPlayer.isWhite)) {
                definitiveMoves.add(p);
            }
        }
        return definitiveMoves;
    }


    public boolean isMoveValid (Board b, Position p) { //Test self check for a move
        if (piece == null) return false;
        if (!piece.canMove(b, b.board[this.position.x][this.position.y], b.board[p.x][p.y])) {
            return false;
        }
        return true;
    }

    public boolean equals(Square s){
        return this.position == s.position;
    }

}

    



