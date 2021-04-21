<<<<<<< HEAD


=======
>>>>>>> d2c614d42b07518459d846fb65af7ffee76f91d4
import javax.swing.ImageIcon;

public abstract class Piece {
    private boolean color; // Color
    private boolean isAlive; //Status
    ImageIcon image; // Image

    public Piece(boolean color) {
        this.color = color;
        this.isAlive = true;
    }

    public void setAlive(boolean alive){
        this.isAlive = alive;
    }

    public void setColor(boolean color){
        this.color = color;
    }
    public boolean getColor(){
        return this.color;
    }
    public void canMove(){
    }
}
