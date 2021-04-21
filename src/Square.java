import javax.swing.*;

public class Square {
    int x, y; // Position
    boolean isWhite, isFilled; // Color and presence of a piece(boolean)
    Piece piece; // Piece on the square
    JLabel image, moves; // Labels 

    public Square(int x, int y, boolean isWhite){
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        isFilled = false;
        piece = null;
        if(isWhite){
            image = new JLabel(new ImageIcon("Images/cb.png"));
            moves = new JLabel(new ImageIcon("Images/cbo.png"));
        }
        else{
            image = new JLabel(new ImageIcon("Images/cn.png"));
            moves = new JLabel(new ImageIcon("Images/cno.png"));
        }
    }

    // Remove piece from the square
    public void removePiece(){
        piece = null;
        isFilled = false;
    }

    public boolean equals(Square s){
        return this.x == s.x && this.y == s.y;
    }
}

    



