import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame {

    JFrame frame;
    CustomPanel panel;
    Board b;
    boolean resized = false;
    boolean popUp = false;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Custom setup for tests
public void customSetup(){
    b.addPiece(new Pawn(true), b.board[1][1]);
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
        for(Piece piece: b.playerTop.cemetery){
            panel.remove(piece.image);
        }
        for(Piece piece: b.playerBot.cemetery){
            panel.remove(piece.image);
        }
        for(JLabel num:panel.numbers){
			panel.remove(num);
		}
        b = new Board();
        addListeners();
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

