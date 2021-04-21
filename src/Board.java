public class Board {
    
    public Square [][] board;

    public Board(){
        initBoard();
    }

    public void initBoard(){
        board[0][0] = new Square(0,0, new Rook(true), true);
        board[0][1] = new Square(0,0, new Rook(true), true);
        board[0][2] = new Square(0,0, new Rook(true), true);
        board[0][3] = new Square(0,0, new Rook(true), true);
        board[0][4] = new Square(0,0, new Rook(true), true);
        board[0][5] = new Square(0,0, new Rook(true), true);
        board[0][6] = new Square(0,0, new Rook(true), true);
        board[0][7] = new Square(0,0, new Rook(true), true);
        board[1][0] = new Square(0,0, new Rook(true), true);
        board[1][1] = new Square(0,0, new Rook(true), true);
        board[1][0] = new Square(0,0, new Rook(true), true);
        board[1][0] = new Square(0,0, new Rook(true), true);
        board[1][0] = new Square(0,0, new Rook(true), true);

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i, j, null, true);
            }
        }

    }
    // Add a piece on the board
    public void addPiece(Piece piece, int x, int y){
        board[x][y].piece = piece;

    }

    // Remove a piece from the board
    public void deletePiece(int x, int y){
        board[x][y].piece = null;
    }

    public void display(){

    }
}
