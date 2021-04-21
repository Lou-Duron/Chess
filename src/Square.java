import javax.swing.*;

public class Square {
    Position position; // Position
    boolean isWhite; // Color and presence of a piece(boolean)
    Piece piece; // Piece on the square
    JLabel image, moves; // Labels 
    ImageIcon icon, iconDispo;

    public Square(Position position, boolean isWhite){
        this.position = position;
        this.isWhite = isWhite;
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
    }

    public boolean equals(Square s){
        return this.position == s.position;
    }
}

    



