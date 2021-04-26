import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Frame {

    JFrame frame;
    CustomPanel panel;
    Board b;
    boolean resized = false;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Custom setup for tests
public void customSetup(){
    b.addPiece(new Pawn(true), b.board[4][4]);
    b.addPiece(new Knight(true), b.board[6][6]);
    b.addPiece(new Bishop(true), b.board[5][5]);
    b.addPiece(new Rook(true), b.board[3][3]);
    b.addPiece(new King(true), b.board[2][2]);
    b.addPiece(new Queen(true), b.board[1][1]);
    b.addPiece(new Pawn(false), b.board[4][3]);
    b.addPiece(new Knight(false), b.board[6][5]);
    b.addPiece(new Bishop(false), b.board[5][4]);
    b.addPiece(new Rook(false), b.board[3][2]);
    b.addPiece(new King(false), b.board[2][1]);
    b.addPiece(new Queen(false), b.board[1][0]);
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
    public Frame() {
        // Board
        b = new Board();

        // Frame setup
        frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 720);
        frame.setLocationRelativeTo(null);
        ImageIcon logo = new ImageIcon("Images/logo.png");
        frame.setIconImage(logo.getImage());
        frame.setMinimumSize(new Dimension(450,450));

        b.initBoard(); // Clasic Setup
        //customSetup(); //Custom Setup

        // Panel setup
        panel = new CustomPanel(this);
        panel.setBackground(panel.menu.CL_BK);
        panel.setOpaque(true);
        panel.initBoardGraphics();
		frame.add(panel);

        addListeners(); // Resize Listener 
        frame.setVisible(true);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MAIN FUNCTIONS

    // Resets frame and board (New game + new listeners)
    public void reset(){
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                panel.remove(b.board[x][y].image);
                panel.remove(b.board[x][y].movesEmpty);
                panel.remove(b.board[x][y].movesFilled);
                if(b.board[x][y].piece != null){
                    panel.remove(b.board[x][y].piece.image);
                }
            }
        }
        for(Piece piece: b.playerW.cemetery){
            panel.remove(piece.image);
        }
        for(Piece piece: b.playerB.cemetery){
            panel.remove(piece.image);
        }
        for(JLabel num:panel.numbers){
			panel.remove(num);
		}
        b = new Board();
        customSetup();
        addListeners();
        panel.initBoardGraphics();
    }

    public void addListeners(){
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                panel.resize();
                resized = false;
            }
        });
        // Listener to make the frame a square when mouse exited 
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                if(!resized){
                frame.setSize(Math.min(frame.getWidth(),frame.getHeight()),Math.min(frame.getWidth(),frame.getHeight()));
                panel.resize();
                resized = true;
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {} 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args){
        new Frame();
    }
}

