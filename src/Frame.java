

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Frame{

    JFrame frame;
    JMenuBar menuBar;
    JMenu menu, play, option;
    JMenuItem IA, hotSeat, exit;
    JPanel main, grid, downMenu, player1, player2, game;
    JLabel [][] board;
    Board chessBoard;
    BorderLayout bl, bl2;
    GridLayout gd;
    FlowLayout fl;
    ImageIcon caseBlancheDispo, caseNoireDispo, woodBackground;
    boolean selected = false;
    Piece selectedPiece;

    public Frame(){
        chessBoard = new Board();
    
        //Board and Pieces images
        caseBlancheDispo = new ImageIcon("Images/CaseBlancheDispo.PNG");
        caseNoireDispo = new ImageIcon("Images/CaseNoireDispo.PNG");
        woodBackground = new ImageIcon("Images/woodBackground.jpg");

        // Layouts
        bl = new BorderLayout();
        bl2 = new BorderLayout();
        gd = new GridLayout(8,8,0,0);
        fl = new FlowLayout();

        board = new JLabel[8][8];

        // Main frame
        frame = new JFrame("chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 705);
        frame.setLocationRelativeTo(null);
        main = new JPanel(bl);
        main.setBorder(null);
        main.setBackground(Color.GREEN);
        game = new JPanel(bl2);
        game.setBorder(null);
        grid = new JPanel(gd);
        grid.setBorder(null);
        downMenu = new JPanel();
        player1 = new JPanel();
        player2 = new JPanel();
        player1.add(new JLabel(woodBackground));
        player2.add(new JLabel(woodBackground));

        frame.add(main);
        main.add(downMenu,BorderLayout.PAGE_END);
        main.add(game,BorderLayout.CENTER);
        game.add(grid,BorderLayout.CENTER);
        game.add(player1, BorderLayout.PAGE_END);
        game.add(player2, BorderLayout.PAGE_START);
      
        // Menu bar
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        play = new JMenu("Play");
        option = new JMenu("Option");
        IA = new JMenuItem("Player vs IA");
        hotSeat = new JMenuItem("HotSeat");
        exit = new JMenuItem("Exit");
        play.add(IA);
        play.add(hotSeat);
        menu.add(play);
        menu.add(option);
        menu.add(exit);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        

        // Init chess board
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(x%2 != y%2){
                    board[x][y] = new JLabel();
                    grid.add(board[x][y]);
                }
                else{
                    board[x][y] = new JLabel();
                    grid.add(board[x][y]);

                }
            }
        }

        display();
        frame.setVisible(true);    

        //ActionListeners
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });


        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                int a = x;
                int b = y;
                board[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //board[a][b].setIcon(bb);
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        //board[a][b].setIcon(bb);
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        //board[a][b].setIcon(bb);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(!selected && chessBoard.board[a][b].isFilled){
                            displayMouvements();
                            selectedPiece = chessBoard.board[a][b].piece;
                        }
                        else{
                            chessBoard.board[selectedPiece.x][selectedPiece.y].removePiece();
                            chessBoard.addPiece(selectedPiece, a, b);
                            display();
                            selected = false;
                            selectedPiece = null;
                        }
                        

                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //board[a][b].setIcon(bb);
                    }
                });
            }
        }
        
    
    } 

    public void displayMouvements(){
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(!chessBoard.board[x][y].isFilled){
                    if(chessBoard.board[x][y].isBlack){
                        board[x][y].setIcon(caseNoireDispo);
                    }
                    else{
                        board[x][y].setIcon(caseBlancheDispo);
                    }
                }
            }
        }
        selected = true;
    }

    public void display(){
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                board[x][y].setIcon(chessBoard.board[x][y].returnImage());
            }
        }
    }

    public static void main(String[] args){
        Frame f = new Frame();
        f.chessBoard.addPiece(new Bishop(true, 3, 3), 3, 3);
        f.chessBoard.addPiece(new Bishop(true, 2, 2), 2, 2);
        f.chessBoard.addPiece(new Bishop(true, 5, 5), 5, 5);
        f.chessBoard.addPiece(new Bishop(true, 6, 6), 6, 6);
        f.display();
        
    }
}
