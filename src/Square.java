package Projet_Echec;

import javax.swing.ImageIcon;

public class Square {
    int ligne, colonne; // Position
    boolean isBlack, isFilled; // Color and presence of a piece(boolean)
    Piece piece; // Piece on the square
    ImageIcon image; // Image 

    public Square(int ligne, int colonne, boolean isBlack){
        this.ligne = ligne;
        this.colonne = colonne;
        this.isBlack = isBlack;
        isFilled = false;
        piece = null;
        if(isBlack){
            image = new ImageIcon("C:/Users/USER/Desktop/Master/Java/src/Images/CaseNoire.PNG");
        }
        else{
        image = new ImageIcon("C:/Users/USER/Desktop/Master/Java/src/Images/CaseBlanche.PNG");
        }
    }

    // À supprimer 
    // Return the image of the square (square + piece). 
    public ImageIcon returnImage(){
        if(isFilled){
            if(isBlack){
                return new ImageIcon("C:/Users/USER/Desktop/Master/Java/src/Images/Bbb.png");
            }
            else{
                return new ImageIcon("C:/Users/USER/Desktop/Master/Java/src/Images/Wbb.png");
            }
        }
        else{
            return this.image;
        }
    }

    // Remove piece from the square
    public void removePiece(){
        piece = null;
        isFilled = false;
    }
}
