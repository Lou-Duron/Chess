import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame {
    // Constants
    int  SQUARE_SIZE = 80; // Size of a square
    Color CL_PLAYER = new Color(55,55,60); // Players background color
    Color CL_GUI = new Color(37,37,45); // GUI background color
    Color CL_BK = new Color(65,65,67); // Frame background color
    Color CL_FONT = new Color(204,167,0); // Font color
    
    JFrame frame;
    JLayeredPane main;
    Board chessboard;
    JLabel player1, player2, name1, name2, timer1, timer2, deadPieces1, deadPieces2;
    JLabel menu, newGame, option, exit, analyse, concede, leftButton, rightButton;
    boolean selected = false;
    boolean resized = false;
    Piece selectedPiece;
    Point p;
    //DragAndDrop DaD = new DragAndDrop(); //DragAndDrop à revoir

    public Frame() {

        
        // Create a new board
        chessboard = new Board();
        chessboard.initBoard(); // Setup the board for a new game

        // Main frame
        frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SQUARE_SIZE*9, SQUARE_SIZE*9);
        frame.setLocationRelativeTo(null);
        ImageIcon logo = new ImageIcon("Images/logo.png");
        frame.setIconImage(logo.getImage());
        main = new JLayeredPane();
        main.setBackground(CL_BK);
        main.setOpaque(true);
        frame.add(main); 
        frame.setMinimumSize(new Dimension(450,450));

        //this.add(DaD);
        
        // Players panels
        // (player1 = black, player2 = white)
        player1 = new JLabel();
        player1.setBackground(CL_PLAYER);
        player1.setOpaque(true);
        main.add(player1, Integer.valueOf(0));
        player2 = new JLabel();
        player2.setBackground(CL_PLAYER);
        player2.setOpaque(true);
        main.add(player2, Integer.valueOf(0));
        
        // Timers
        timer1 = new JLabel("04:57", SwingConstants.CENTER);
        timer1.setBackground(CL_GUI);
        timer1.setOpaque(true);
        timer1.setForeground(CL_FONT);
        main.add(timer1, Integer.valueOf(2));
        timer2 = new JLabel("04:24", SwingConstants.CENTER);
        timer2.setBackground(CL_GUI);
        timer2.setOpaque(true);
        timer2.setForeground(CL_FONT);
        main.add(timer2, Integer.valueOf(2));

        // Names
        name1 = new JLabel(chessboard.playerB.name, SwingConstants.CENTER);
        name1.setBackground(CL_GUI);
        name1.setOpaque(true);
        name1.setForeground(CL_FONT);
        main.add(name1, Integer.valueOf(2));
        name2 = new JLabel(chessboard.playerW.name, SwingConstants.CENTER);
        name2.setBackground(CL_GUI);
        name2.setOpaque(true);
        name2.setForeground(CL_FONT);
        main.add(name2, Integer.valueOf(2));

        // DeadPieces
        deadPieces1 = new JLabel();
        deadPieces1.setBackground(CL_GUI);
        deadPieces1.setOpaque(true);
        main.add(deadPieces1, Integer.valueOf(2));
        deadPieces2 = new JLabel();
        deadPieces2.setBackground(CL_GUI);
        deadPieces2.setOpaque(true);
        main.add(deadPieces2, Integer.valueOf(2));

        // Menu panel
        // New Game button
        newGame = new JLabel("New game", SwingConstants.CENTER);
        newGame.setBackground(CL_GUI);
        newGame.setOpaque(true);
        newGame.setForeground(CL_FONT);
        main.add(newGame, Integer.valueOf(2));
        newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                reset();
            } 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        // Option button
        option = new JLabel("Option", SwingConstants.CENTER);
        option.setBackground(CL_GUI);
        option.setOpaque(true);
        option.setForeground(CL_FONT);
        main.add(option, Integer.valueOf(2));
        option.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {} 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        // Exit button
        exit = new JLabel("Exit", SwingConstants.CENTER);
        exit.setBackground(CL_GUI);
        exit.setOpaque(true);
        exit.setForeground(CL_FONT);
        main.add(exit, Integer.valueOf(2));
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {} 
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.dispose();
            }
        });
        // Analyse button
        analyse = new JLabel("A", SwingConstants.CENTER);
        analyse.setBackground(CL_GUI);
        analyse.setOpaque(true);
        analyse.setForeground(CL_FONT);
        main.add(analyse, Integer.valueOf(2));
        analyse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {} 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        // Concede button
        concede = new JLabel("C", SwingConstants.CENTER);
        concede.setBackground(CL_GUI);
        concede.setOpaque(true);
        concede.setForeground(CL_FONT);
        main.add(concede, Integer.valueOf(2));
        concede.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {} 
            @Override
            public void mouseReleased(MouseEvent e) {
                reset();
            }
        });
        // Last move button
        leftButton = new JLabel("Left", SwingConstants.CENTER);
        leftButton.setBackground(CL_GUI);
        leftButton.setOpaque(true);
        leftButton.setForeground(CL_FONT);
        main.add(leftButton, Integer.valueOf(2));
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {} 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        // Next move button
        rightButton = new JLabel("Right", SwingConstants.CENTER);
        rightButton.setBackground(CL_GUI);
        rightButton.setOpaque(true);
        rightButton.setForeground(CL_FONT);
        main.add(rightButton, Integer.valueOf(2));
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {} 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });   

        
        initBoardGraphics(); // Initialize the chessboard (graphically)
        frame.setVisible(true);  

        System.out.print(chessboard.board[1][1].getMoves(chessboard));
        // Listener in case of frame resizing
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                resize();
                resized = false;
            }
        });

        // Listener to make the frame a square when mouse exited (keep ?)
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                if(!resized){
                frame.setSize(Math.min(frame.getWidth(),frame.getHeight()),Math.min(frame.getWidth(),frame.getHeight()));
                resize();
                resized = true;
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {} 
            @Override
            public void mouseReleased(MouseEvent e) {}
        }); 
    }

    public void addListeners(){
        // Add listeners on squares and pieces
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                addSquareListener(chessboard.board[x][y]);
                if(chessboard.board[x][y].piece != null){
                    addPieceListener(chessboard.board[x][y].piece);
                }
            }     
        }
    }

    public void addSquareListener(Square square) {
        // Add listeners on squares
        square.image.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) { 
                if(selected && square.piece == null){   
                chessboard.movePiece(selectedPiece, square);
                selectedPiece.image.setBounds(square.position.x*SQUARE_SIZE, square.position.y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
                removeMoves(); 
                } 
            }   
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
    }

    public void addPieceListener(Piece piece){
        // Add listeners on pieces
        piece.image.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) { 
                if(!selected){
                    showMoves();
                    selectedPiece = piece;
                }
                else{
                    if(piece.getColor() != selectedPiece.getColor()){
                        eat(selectedPiece, piece);
                        chessboard.movePiece(selectedPiece, chessboard.board[piece.position.x][piece.position.y]);
                        selectedPiece.image.setBounds(piece.position.x*SQUARE_SIZE, piece.position.y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
                        chessboard.deletePiece(piece); 
                        removeMoves();   
                    }
                    else{
                        removeMoves();
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
    }

    // /!\ Need rework /!\ -> Cemetery dont work properly (create a cemetery array?)
    public void eat(Piece selectedP, Piece p){
        // When selected piece eat an other given piece (resize label and put in cemetery)
        Image image = p.icon.getImage();
        Image newimg = image.getScaledInstance(SQUARE_SIZE*3/8, SQUARE_SIZE*3/8,  java.awt.Image.SCALE_SMOOTH);  
        p.image.setIcon(new ImageIcon(newimg));
        if(p.getColor()){
            p.image.setBounds(90+chessboard.playerB.deadPieces*SQUARE_SIZE*5/16, SQUARE_SIZE*1/16, SQUARE_SIZE*9/2, SQUARE_SIZE*3/8);
        }
        else{
            p.image.setBounds(90+chessboard.playerW.deadPieces*SQUARE_SIZE*5/16, SQUARE_SIZE*8+SQUARE_SIZE/2+SQUARE_SIZE/16, SQUARE_SIZE*9/2, SQUARE_SIZE*3/8);
        }
  
    }


    public void reset(){
        // Reset frame and board (New game + new listeners)
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                main.remove(chessboard.board[x][y].image);
                main.remove(chessboard.board[x][y].moves);
                if(chessboard.board[x][y].piece != null){
                    main.remove(chessboard.board[x][y].piece.image);
                }
            }
        }
        if(selectedPiece != null){        
            main.remove(selectedPiece.image);
        }
        chessboard = new Board();
        chessboard.initBoard();
        initBoardGraphics();
    }

    // /!\ Need rework /!\ -> Shows all empty squares (implements only authorized moves + new graphics)
    public void showMoves(){
        //Display authorized moves
        selected = true;
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(chessboard.board[x][y].piece == null){      
                    main.setLayer(chessboard.board[x][y].moves,1);
                }
                
            }
        }
    }

    // /!\ Need rework /!\ -> Same as above
    public void removeMoves(){
        //Remove authorized moves
        selected = false;
        selectedPiece = null;
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(chessboard.board[x][y].piece == null){      
                    main.setLayer(chessboard.board[x][y].moves,0);
                }
            }
        }
    }

    public void resize(){
        // Keep proportion of all Labels when resizing the frame, based on new square size
        main.setBounds(0, 0,   frame.getWidth(), frame.getHeight()-35);
        SQUARE_SIZE = (int) main.getHeight()/9;
        player1.setBounds(0, 0, SQUARE_SIZE*8, SQUARE_SIZE/2);
        player2.setBounds(0, SQUARE_SIZE*8+SQUARE_SIZE/2 , SQUARE_SIZE*8, SQUARE_SIZE/2);
        timer1.setBounds(SQUARE_SIZE/8, SQUARE_SIZE/8, SQUARE_SIZE*3/4, SQUARE_SIZE/4);
        timer2.setBounds(SQUARE_SIZE/8, SQUARE_SIZE*8+SQUARE_SIZE/2+SQUARE_SIZE/8, SQUARE_SIZE*3/4, SQUARE_SIZE/4);
        name1.setBounds(SQUARE_SIZE*5/4, SQUARE_SIZE/8, SQUARE_SIZE*3/2, SQUARE_SIZE/4);
        name2.setBounds(SQUARE_SIZE*5/4, SQUARE_SIZE*8+SQUARE_SIZE/2+SQUARE_SIZE/8, SQUARE_SIZE*3/2, SQUARE_SIZE/4);
        deadPieces1.setBounds(SQUARE_SIZE*13/4, SQUARE_SIZE/16, SQUARE_SIZE*9/2, SQUARE_SIZE*3/8);
        deadPieces2.setBounds(SQUARE_SIZE*13/4, SQUARE_SIZE*8+SQUARE_SIZE/2+SQUARE_SIZE/16, SQUARE_SIZE*9/2, SQUARE_SIZE*3/8);
        newGame.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16, SQUARE_SIZE*7/8+22, SQUARE_SIZE*3/8);
        option.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE/2, SQUARE_SIZE*7/8+22, SQUARE_SIZE*3/8);
        exit.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE, SQUARE_SIZE*7/8+22, SQUARE_SIZE*3/8);
        analyse.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE*3/2, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
        concede.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16+SQUARE_SIZE*5/16+22, SQUARE_SIZE*1/16+SQUARE_SIZE*3/2, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
        leftButton.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE*2, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
        rightButton.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16+SQUARE_SIZE*5/16+22, SQUARE_SIZE*1/16+SQUARE_SIZE*2, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                Image imageCase = chessboard.board[x][y].icon.getImage();
                Image newimgCase = imageCase.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,  java.awt.Image.SCALE_SMOOTH);  
                chessboard.board[x][y].image.setIcon(new ImageIcon(newimgCase));
                chessboard.board[x][y].image.setBounds(x*SQUARE_SIZE, y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
                Image imageDispo = chessboard.board[x][y].iconDispo.getImage();
                Image newimgDispo = imageDispo.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,  java.awt.Image.SCALE_SMOOTH);  
                chessboard.board[x][y].moves.setIcon(new ImageIcon(newimgDispo));
                chessboard.board[x][y].moves.setBounds(x*SQUARE_SIZE, y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);

                if(chessboard.board[x][y].piece != null){
                    Image imagePiece = chessboard.board[x][y].piece.icon.getImage();
                    Image newimgPiece = imagePiece.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,  java.awt.Image.SCALE_SMOOTH);  
                    chessboard.board[x][y].piece.image.setIcon(new ImageIcon(newimgPiece));
                    chessboard.board[x][y].piece.image.setBounds(x*SQUARE_SIZE, y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
                    
                }
            }
        }
    }


    public void initBoardGraphics(){
        // Initialize board (graphically), add listeners and resize.
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                main.add(chessboard.board[x][y].image, Integer.valueOf(0));
                main.add(chessboard.board[x][y].moves, Integer.valueOf(0));
                if(chessboard.board[x][y].piece != null){
                    main.add(chessboard.board[x][y].piece.image, Integer.valueOf(3));
                }
            }
        }
        addListeners();
        resize();   
    }




    public static void main(String[] args){
        new Frame();
    }
}
