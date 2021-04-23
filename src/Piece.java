import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Piece {
    private boolean isWhite, isAlive; //Status //isAlive necessaire ?
    Position position; // Position of the piece // A enlever
    JLabel image; // Label
    ImageIcon icon; // Icon

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
        this.isAlive = true;
    }

    public void setAlive(boolean alive){
        this.isAlive = alive;
    }

    public void setColor(boolean isWhite){
        this.isWhite = isWhite;
    }

    public boolean getAlive(){
       return isAlive;
    }

    public boolean getColor(){
        return isWhite;
    }
    public void canMove(){
    }
}
