import java.util.*;

public class IA {
    Player p;
    Frame f;

    public IA(Player p, Frame f){
        this.p = p;
        this.f = f;
    }

    public List<Square[][]> getMoves(Square[][] board){
        List<Square[][]> possibleMoves = new ArrayList<Square[][]>();
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(board[x][y].piece != null && board[x][y].piece.isWhite == p.isWhite){
                    if(board[x][y].getMoves(f.b).size() > 0){
                        for(Position p:board[x][y].getMoves(f.b)){
                            Square[][] temp = new Square[8][8];
                            for(int i=0; i<8; i++){
                                for(int j=0; j<8; j++){
                                    temp[i][j] = new Square(board[i][j]);
                                }
                            }
                            moveIA(temp[x][y], temp[p.x][p.y]);
                            possibleMoves.add(temp);
                        }
                    }
                }
            }
        }
        return possibleMoves;
    }

    public void moveIA(Square start, Square end){
        if(end.piece != null){
            end.piece = null;
        }
        end.piece = start.piece;
        start.piece = null;
    }

    public int computeScore(Square[][] board){
        int score = 0;
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(board[x][y].piece != null){
                   score += board[x][y].piece.value;
                }
            }
        }
        return score;
    }

    public int minimaxPrunning(Square[][] board, int depth,int alpha, int beta, boolean maximizingPlayer){
        if(depth == 0){ // or game over
            return computeScore(board);
        }
        if(maximizingPlayer){
            int maxEval = Integer.MIN_VALUE;
            for(Square[][] b: getMoves(board)){
                int eval = minimaxPrunning(b, depth-1, alpha, beta, false);
                maxEval = Math.max(eval,maxEval);
                alpha = Math.max(alpha, eval);
                if(beta <= alpha){
                    break;
                }
            }
            return maxEval;
        }
        else{
            int minEval = Integer.MAX_VALUE;
            for(Square[][] b: getMoves(board)){
                int eval = minimaxPrunning(b, depth-1, alpha, beta, true);
                minEval = Math.min(eval,minEval);
                beta = Math.min(beta, eval);
                if(beta <= alpha){
                    break;
                }
            }
            return minEval;
        }
    }

    public HashMap<Square[][],List<Square>> getMovesBoard(Square[][] board){
        HashMap<Square[][],List<Square>> boardsMoves = new HashMap<Square[][],List<Square>>();
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(board[x][y].piece != null && board[x][y].piece.isWhite == p.isWhite){
                    if(board[x][y].getMoves(f.b).size() > 0){
                        for(Position p:board[x][y].getMoves(f.b)){
                            Square[][] b = new Square[8][8];
                            for(int i=0; i<8; i++){
                                for(int j=0; j<8; j++){
                                    b[i][j] = new Square(board[i][j]);
                                }
                            }
                            moveIA(b[x][y], b[p.x][p.y]);
                            List<Square> moves = new ArrayList<>();
                            moves.add(b[x][y]);
                            moves.add(b[p.x][p.y]);
                            boardsMoves.put(b,moves);
                        }
                    }
                }
            }
        }
        return boardsMoves;
    }

    public void makeMove(){
        List<Square> bestMove = new ArrayList<Square>();
        HashMap<Square[][],List<Square>> fina = getMovesBoard(f.b.board);
        int best = Integer.MAX_VALUE;
        for(Square[][] b: fina.keySet()){
            int tmp = minimaxPrunning(b, 1, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
            if(tmp < best){
                best = tmp;
                bestMove.clear();
                bestMove.addAll(fina.get(b));
            }
        }
        Square start = f.b.board[bestMove.get(0).position.x][bestMove.get(0).position.y];
        Square end = f.b.board[bestMove.get(1).position.x][bestMove.get(1).position.y];
        if(end.piece != null){
            f.b.history.add(new Action(start, end, end.piece));
        }
        else{
            f.b.history.add(new Action(start, end));
        }
        if(end.piece != null){
            f.panel.playSound(Sounds.EAT.getFile());
        }
        else{
            f.panel.playSound(Sounds.MOVE.getFile());
        }
        f.b.movePiece(start,end);
        f.panel.checkPromotion(end);
        f.b.cursorMoves ++;
        f.b.nbMoves ++;
        f.panel.displayPieces();
        f.panel.switchPlayer(f.b.time);
    }
}
