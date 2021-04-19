import javax.swing.ImageIcon;

public class Square {
    public int x, y; // Position
    public Piece piece; // Piece on the square
    boolean color;
    ImageIcon image; // Image 

    public Square(int x, int y, Piece piece, Boolean color){
        this.x = x;
        this.y = y;
        this.color = color;
        this.piece = piece;
        if(color){
            image = new ImageIcon("Images/CaseNoire.PNG");
        }
        else{
            image = new ImageIcon("Images/CaseBlanche.PNG");
        }
    }

    // À supprimer 
    // Return the image of the square (square + piece). 
    public ImageIcon returnImage(){
        if(this.piece != null){
            if(color){
                return new ImageIcon("Images/Bbb.png");
            }
            else{
                return new ImageIcon("Images/Wbb.png");
            }
        }
        else{
            return this.image;
        }
    }
    public boolean equals(Square s){
        return this.x == s.x && this.y == s.y;
    }

    // Remove piece from the square
    public void removePiece(){
        piece = null;
    }
}
