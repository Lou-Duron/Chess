import java.util.HashMap;

public class Board {
    
    public Square [][] board;// Chessboard
    Player playerTop, playerBot, currentPlayer;

    public Board(){
        board = new Square[8][8];
        playerTop = new Player("Lou", false, true); // Player(name, isWhite, isTop)
        playerBot = new Player( "Vic", true, false);
        if(playerTop.isWhite){
            currentPlayer = playerTop; 
        }
        else {
            currentPlayer = playerBot;
        }
                
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(x%2 != y%2){
                    board[x][y] = new Square(new Position(x,y), false);
                }
                else{
                    board[x][y] = new Square(new Position(x,y), true);
                }
            }
        }
    }

    // Move a piece on the chessboard
    public void movePiece(Square start, Square end){
        if(end.piece != null){
            deletePiece(end);
        }
        end.piece = start.piece;
        start.piece = null;
    }

    // Remove a piece from the chessboard
    public void deletePiece(Square s){
        if(s.piece.getColor()){
            playerTop.cemetery.add(s.piece);
        }
        else{
            playerBot.cemetery.add(s.piece);
        }
        s.piece = null;
    }

    // Add a piece on the cheesboard
    public void addPiece(Piece p, Square s){
        s.piece = p;
    }

    public void initBoard(){
        //Initialize default setup (white down) 
        // Top
        addPiece(new Pawn(playerTop.isWhite), board[0][1]);
        addPiece(new Pawn(playerTop.isWhite), board[1][1]);
        addPiece(new Pawn(playerTop.isWhite), board[2][1]);
        addPiece(new Pawn(playerTop.isWhite), board[3][1]);
        addPiece(new Pawn(playerTop.isWhite), board[4][1]);
        addPiece(new Pawn(playerTop.isWhite), board[5][1]);
        addPiece(new Pawn(playerTop.isWhite), board[6][1]);
        addPiece(new Pawn(playerTop.isWhite), board[7][1]);
        addPiece(new Rook(playerTop.isWhite), board[0][0]);
        addPiece(new Rook(playerTop.isWhite), board[7][0]);
        addPiece(new Bishop(playerTop.isWhite), board[2][0]);
        addPiece(new Bishop(playerTop.isWhite), board[5][0]);
        addPiece(new Knight(playerTop.isWhite), board[1][0]);
        addPiece(new Knight(playerTop.isWhite), board[6][0]);
        addPiece(new Queen(playerTop.isWhite), board[3][0]);
        addPiece(new King(playerTop.isWhite), board[4][0]);

        // Bot
        addPiece(new Pawn(playerBot.isWhite), board[0][6]);
        addPiece(new Pawn(playerBot.isWhite), board[1][6]);
        addPiece(new Pawn(playerBot.isWhite), board[2][6]);
        addPiece(new Pawn(playerBot.isWhite), board[3][6]);
        addPiece(new Pawn(playerBot.isWhite), board[4][6]);
        addPiece(new Pawn(playerBot.isWhite), board[5][6]);
        addPiece(new Pawn(playerBot.isWhite), board[6][6]);
        addPiece(new Pawn(playerBot.isWhite), board[7][6]);
        addPiece(new Rook(playerBot.isWhite), board[0][7]);
        addPiece(new Rook(playerBot.isWhite), board[7][7]);
        addPiece(new Bishop(playerBot.isWhite), board[2][7]);
        addPiece(new Bishop(playerBot.isWhite), board[5][7]);
        addPiece(new Knight(playerBot.isWhite), board[1][7]);
        addPiece(new Knight(playerBot.isWhite), board[6][7]);
        addPiece(new Queen(playerBot.isWhite), board[3][7]);
        addPiece(new King(playerBot.isWhite), board[4][7]);
    }

    public void reverseBoard(){
        HashMap<Piece, Position>  state = new HashMap<Piece, Position>();
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(board[x][y].piece != null){
                    state.put(board[x][y].piece, board[x][y].position);
                }
            }
        }
        board = new Square[8][8];
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(x%2 != y%2){
                    board[x][y] = new Square(new Position(x,y), false);
                }
                else{
                    board[x][y] = new Square(new Position(x,y), true);
                }
            }
        }
        for(Piece p:state.keySet()){
            addPiece(p,board[7-state.get(p).x][7-state.get(p).y]);
        }
    }
}
