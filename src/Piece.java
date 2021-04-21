

import javax.swing.ImageIcon;

public abstract class Piece {
    boolean isBlack, isAlive; // Color and status
    int x, y; // Position
    ImageIcon image; // Image

    public void setAlive(boolean alive){
        this.isAlive = alive;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }   
}
