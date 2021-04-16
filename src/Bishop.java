

import javax.swing.*;

public class Bishop extends Piece {
    
    public Bishop(boolean isBlack, int x, int y){
        this.isBlack = isBlack;
        isAlive = true;
        this.x = x;
        this.y = y;
        if(isBlack){
            image = new ImageIcon("Images/bb.png");
        }
        else{
        image = new ImageIcon("Images/wb.png");
        }
    }
}
