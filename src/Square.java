import javax.swing.*;

public class Square {
    int x, y; // Position
    boolean isWhite, isFilled; // Color and presence of a piece(boolean)
    Piece piece; // Piece on the square
    JLabel image, moves; // Labels 
    ImageIcon icon, iconDispo;

    public Square(int x, int y, boolean isWhite){
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        isFilled = false;
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
        isFilled = false;
    }

    public boolean equals(Square s){
        return this.x == s.x && this.y == s.y;
    }
}

    



