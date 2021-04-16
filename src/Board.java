

public class Board {
    
    Square [][] board;

    public Board(){
        board = new Square[8][8];
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(x%2 == y%2){
                    board[x][y] = new Square(x,y,false);
                }
                else{
                    board[x][y] = new Square(x,y,true);
                }
            }
        }
    }

    // Add a piece on the board
    public void addPiece(Piece piece, int x, int y){
        board[x][y].piece = piece;
        board[x][y].piece.x = x;
        board[x][y].piece.y = y;
        board[x][y].isFilled = true;
    }

    // Remove a piece from the board
    public void deletePiece(int x, int y){
        board[x][y].piece = null;
    }
}
