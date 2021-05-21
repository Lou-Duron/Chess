import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import javax.sound.sampled.*;


public class Board {
    
    public Square [][] board;
    Player playerTop, playerBot, currentPlayer, uncurrentPlayer;
    List<Action> history;
    int cursorMoves, nbMoves;
    boolean time;

    public Board(String playerTopName, String playerBotName){
        board = new Square[8][8];
        Random r = new Random();
        boolean randomize = r.nextInt(2) == 1;
        playerTop = new Player(playerTopName, randomize, true); 
        playerBot = new Player(playerBotName, !randomize, false);
        if(playerTop.isWhite){
            currentPlayer = playerTop;
            uncurrentPlayer = playerBot;
        }
        else {
            currentPlayer = playerBot;
            uncurrentPlayer = playerTop;
        }
        cursorMoves = 0;
        nbMoves = 0;
        history = new ArrayList<Action>();
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

    public Board(Board b){
        board = new Square[8][8];
        playerBot = b.playerBot;
        playerTop = b.playerTop;
        currentPlayer = b.currentPlayer;
        uncurrentPlayer = b.uncurrentPlayer;
        cursorMoves = 0;
        nbMoves = 0;
        history = null;
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                this.board[x][y] = new Square(b.board[x][y]);
            }
        }
    }

    // Move a piece on the chessboard
    public void movePiece(Square start, Square end, MoveType m){
        if (m == MoveType.CLASSIC) {
            if (end.piece != null) {
                deletePiece(end);
            }
            end.piece = start.piece;
            start.piece = null;
            end.piece.hasMoved = true;
        } else{
            castleMove(m);
        }
    }

    public void castleMove(MoveType m){
        int sense = currentPlayer == playerTop ? 0 : 7;
        if ( m == MoveType.KINGSIDE) {
            movePieceTemp(board[4][sense], board[2][sense]);
            movePieceTemp(board[0][sense], board[3][sense]);
        }
        else{
            movePieceTemp(board[4][sense], board[6][sense]);
            movePieceTemp(board[7][sense], board[5][sense]);

        }
    }

    public HashMap<Square, MoveType> castling(Square king) {
        HashMap<Square, MoveType> possibleRook = new HashMap<>();
        if (king.piece instanceof King && !king.piece.hasMoved && king.piece.isWhite == currentPlayer.isWhite) {//King moved or wrong color
            System.out.println("king " + king.piece.hasMoved + " "+ king.piece.isWhite);
            if (currentPlayer == playerTop) {
                if (board[0][0].piece != null && board[0][0].piece.isWhite == currentPlayer.isWhite && !board[0][0].piece.hasMoved && board[0][0].piece instanceof Rook) {
                    System.out.println("rook ok");
                    if (!pieceInTheWay(king, -3)) {
                        System.out.println("no piece in the way");
                        possibleRook.put(board[0][0], MoveType.KINGSIDE);
                    }
                }
                if (board[7][0].piece != null && board[7][0].piece.isWhite == currentPlayer.isWhite && !board[7][0].piece.hasMoved && board[0][0].piece instanceof Rook)
                    if (!pieceInTheWay(king, +2)) {
                        possibleRook.put(board[7][0], MoveType.QUEENSIDE);
                    }
            }
            else {
                if (board[7][7].piece != null && board[7][7].piece.isWhite == currentPlayer.isWhite && !board[7][7].piece.hasMoved && board[0][0].piece instanceof Rook) {
                    if (!pieceInTheWay(king, -2)){
                        possibleRook.put(board[7][7], MoveType.QUEENSIDE);
                    }
                }
                if (board[0][7].piece != null && board[0][7].piece.isWhite == currentPlayer.isWhite && !board[0][7].piece.hasMoved && board[0][0].piece instanceof Rook) {
                    if (!pieceInTheWay(king, -2)){
                        possibleRook.put(board[0][7], MoveType.KINGSIDE);
                    }
                }
            }
        }
        return possibleRook;
    }
    public boolean pieceInTheWay(Square king, int x){
        int sense = x < 0 ? -1 : 1;
        for (int i = 0; i < Math.abs(x); i++) {
            if (board[king.position.x + i * sense][king.position.y].piece != null) {
                return true;
            }
        }
        return false;
    }

    public void movePieceTemp(Square start, Square end){
        if(end.piece != null){
            deletePiece(end);
        }
        end.piece = start.piece;
        start.piece = null;
    }

    // Remove a piece from the chessboard
    public void deletePiece(Square s){
        if(s.piece.isWhite == playerBot.isWhite){
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
        if(playerTop.isWhite){
            addPiece(new Queen(true), board[3][0]);
            addPiece(new King(true), board[4][0]);
        }
        else{
            addPiece(new Queen(false), board[3][0]);
            addPiece(new King(false), board[4][0]);
        }
        

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
        if(playerTop.isWhite){
            addPiece(new Queen(false), board[3][7]);
            addPiece(new King(false), board[4][7]);
        }
        else{
            addPiece(new Queen(true), board[3][7]);
            addPiece(new King(true), board[4][7]);
        }
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
        for(Action a: history){
            a.start = board[7-a.start.position.x][7-a.start.position.y];
            a.end = board[7-a.end.position.x][7-a.end.position.y];
        }
    }

    public boolean isCheck(boolean color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].piece != null && board[i][j].piece.isWhite == color) {
                    if(board[i][j].isMoveValid(this, getKing(!color).position)) return true;
                }
            }
        }
        return false;
    }


    public Square getKing(boolean color) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y].piece instanceof King && board[x][y].piece.getColor() == color) {
                    return board[x][y];
                }
            }
        }
        return null;
    }


    public boolean noMovesPossible(Player p){
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y].piece != null && board[x][y].piece.isWhite == p.isWhite && !board[x][y].getMoves(this).isEmpty() ){
                    return false;
                }
            }
        }
        return true;
    }

    // To be done
    public boolean winLose(){
        return currentPlayer.check && noMovesPossible(currentPlayer);
    }
    public boolean equality(){
        int currentKing = 0;
        int uncurrentKing = 0;

        // No check but any moves possibles
        if (!currentPlayer.check && noMovesPossible(currentPlayer))
            return true;

        // King vs King or King alone
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y].piece != null && !(board[x][y].piece instanceof King)){
                    if (board[x][y].piece.isWhite == currentPlayer.isWhite){
                        currentKing ++;
                    }
                    else{
                        uncurrentKing ++;
                    }
                }
            }
        }
        return !(currentKing != 0 && uncurrentKing !=0);
    }


    public void playSound(String s){
        try {
            File f = new File("./Sounds/"+ s +".wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
   
}
