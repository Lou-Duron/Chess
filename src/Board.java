public class Board {
    
    public Square [][] board;// Chessboard
    Player playerB, playerW; // Players

    public Board(){
        board = new Square[8][8];
        playerW = new Player( "Vic", true);
        playerB = new Player("Lou", false);
        // Init chessboard
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(x%2 == y%2){
                    board[x][y] = new Square(new Position(x,y), false);
                }
                else{
                    board[x][y] = new Square(new Position(x,y), true);
                }
            }
        }
    }

    /* POUR VIC : j'ai enlevé initBoard du constructeur au cas ou on veut un board
    sans les pièces au position de départ (par exemple si on veux ajouter une option
    pour jouer à partir d'une custom position). Du coup j'appelle initBoard dans frame
    quand on lance une nouvelle partie. .) */

    public void initBoard(){
        // Black
        addPiece(new Pawn(false), board[0][1]);
        addPiece(new Pawn(false), board[1][1]);
        addPiece(new Pawn(false), board[2][1]);
        addPiece(new Pawn(false), board[3][1]);
        addPiece(new Pawn(false), board[4][1]);
        addPiece(new Pawn(false), board[5][1]);
        addPiece(new Pawn(false), board[6][1]);
        addPiece(new Pawn(false), board[7][1]);
        addPiece(new Rook(false), board[0][0]);
        addPiece(new Rook(false), board[7][0]);
        addPiece(new Bishop(false), board[2][0]);
        addPiece(new Bishop(false), board[5][0]);
        addPiece(new Knight(false), board[1][0]);
        addPiece(new Knight(false), board[6][0]);
        addPiece(new Queen(false), board[3][0]);
        addPiece(new King(false), board[4][0]);

        //White
        addPiece(new Pawn(true), board[0][6]);
        addPiece(new Pawn(true), board[1][6]);
        addPiece(new Pawn(true), board[2][6]);
        addPiece(new Pawn(true), board[3][6]);
        addPiece(new Pawn(true), board[4][6]);
        addPiece(new Pawn(true), board[5][6]);
        addPiece(new Pawn(true), board[6][6]);
        addPiece(new Pawn(true), board[7][6]);
        addPiece(new Rook(true), board[0][7]);
        addPiece(new Rook(true), board[7][7]);
        addPiece(new Bishop(true), board[2][7]);
        addPiece(new Bishop(true), board[5][7]);
        addPiece(new Knight(true), board[1][7]);
        addPiece(new Knight(true), board[6][7]);
        addPiece(new Queen(true), board[3][7]);
        addPiece(new King(true), board[4][7]);

    }
    // Add a piece on the cheesboard
    public void addPiece(Piece p, Square s){
        s.piece = p;
        p.position = s.position;
    }

    // Move a piece on the chessboard
    public void movePiece(Piece p, Square s){
        s.piece = p;
        board[p.position.x][p.position.y].removePiece();;
        p.position = s.position;
    }

    // Remove a piece from the chessboard
    public void deletePiece(Piece p){
        p.setAlive(false);
        if(p.getColor()){
            playerW.deadPieces ++;
        }
        else {
            playerB.deadPieces ++;
        }
        board[p.position.x][p.position.y].removePiece();
    }
}
