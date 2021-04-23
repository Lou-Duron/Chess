import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Frame {

    int  SQUARE_SIZE = 80; // Size of a square

    JFrame frame;
    JLayeredPane main;
    Board b;
    boolean resized = false;
    Menu menu;
    Piece selectedPiece;
    // Temp
    List<Position> selectedPieceMoves;
    Square tempSquare;

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

        // Main frame
        frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SQUARE_SIZE*9, SQUARE_SIZE*9);
        frame.setLocationRelativeTo(null);
        ImageIcon logo = new ImageIcon("Images/logo.png");
        frame.setIconImage(logo.getImage());
        main = new JLayeredPane();
        main.setBackground(new Color(65,65,67));
        main.setOpaque(true);
        frame.add(main); 
        frame.setMinimumSize(new Dimension(450,450));

        // Menu
        menu = new Menu(this); 
        main.add(menu.player1, Integer.valueOf(0));
        main.add(menu.player2, Integer.valueOf(0));
        main.add(menu.timer1, Integer.valueOf(2));
        main.add(menu.timer2, Integer.valueOf(2));
        main.add(menu.name1, Integer.valueOf(2));
        main.add(menu.name2, Integer.valueOf(2));
        main.add(menu.deadPieces1, Integer.valueOf(2));
        main.add(menu.deadPieces2, Integer.valueOf(2));
        main.add(menu.newGame, Integer.valueOf(2));
        main.add(menu.option, Integer.valueOf(2));
        main.add(menu.exit, Integer.valueOf(2));
        main.add(menu.concede, Integer.valueOf(2));
        main.add(menu.analyse, Integer.valueOf(2));
        main.add(menu.leftButton, Integer.valueOf(2));
        main.add(menu.rightButton, Integer.valueOf(2));

        //b.initBoard(); // Setup the board for a new game
        customSetup();
        initBoardGraphics(); // Initalize squares and pieces labels
        addListeners(); // Add listeners
        frame.setVisible(true);  
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MAIN FUNCTIONS

    // When clicing on a square
    public void simpleClic(Square s){
        if(selectedPiece == null){
            if (s.piece != null){
                select(s); 
            }
        }
        else{   
            for(Position position: selectedPieceMoves){
                if(position.equals(s.position)){
                    if(s.piece != null){
                        putToCemetery(s.piece);
                    }
                    b.movePiece(tempSquare,s);
                    selectedPiece.image.setBounds(s.position.x*SQUARE_SIZE, s.position.y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
            unselect();    
        }
    }

    // Cemetery
    public void putToCemetery(Piece p){
        Image image = p.icon.getImage();
        Image newimg = image.getScaledInstance(SQUARE_SIZE*3/8, SQUARE_SIZE*3/8,  java.awt.Image.SCALE_SMOOTH);  
        p.image.setIcon(new ImageIcon(newimg));
        if(p.getColor()){
            p.image.setBounds(90+b.playerW.cemetery.size()*SQUARE_SIZE*19/64, SQUARE_SIZE*1/16, SQUARE_SIZE*9/2, SQUARE_SIZE*3/8);
        }
        else{
            p.image.setBounds(90+b.playerB.cemetery.size()*SQUARE_SIZE*19/64, SQUARE_SIZE*8+SQUARE_SIZE/2+SQUARE_SIZE/16, SQUARE_SIZE*9/2, SQUARE_SIZE*3/8);
        }
    }    

    // Select a square/piece if the piece can move
    public void select(Square s){ // previously displayMoves
        if(!s.getMoves(b).isEmpty()){
            selectedPieceMoves = s.getMoves(b);
            selectedPiece = s.piece;
            tempSquare = s;
            for(Position square: selectedPieceMoves){ // for each moves
                main.setLayer(b.board[square.x][square.y].moves,1); //display JLabel
            }
        }
    }

    // Undo the selection
    public void unselect(){ // previously removeMoves
        for(Position square: selectedPieceMoves){ // for each moves
            main.setLayer(b.board[square.x][square.y].moves,0); //display JLabel
        }
        selectedPiece = null;
        selectedPieceMoves = null;
        tempSquare = null;
    }        

    // Reset frame and board (New game + new listeners)
    public void reset(){
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                main.remove(b.board[x][y].image);
                main.remove(b.board[x][y].moves);
                if(b.board[x][y].piece != null){
                    main.remove(b.board[x][y].piece.image);
                }
            }
        }
        for(Piece p: b.playerW.cemetery){
            main.remove(p.image);
        }
        for(Piece p: b.playerB.cemetery){
            main.remove(p.image);
        }
        b = new Board();
        customSetup();
        addListeners();
        initBoardGraphics();
    }
    // Initialize board (graphically) and resize.
    public void initBoardGraphics(){
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                main.add(b.board[x][y].image, Integer.valueOf(0));
                main.add(b.board[x][y].moves, Integer.valueOf(0));
                if(b.board[x][y].piece != null){
                    main.add(b.board[x][y].piece.image, Integer.valueOf(3));
                }
            }
        }
        resize();
    }

    // Keep proportion of all Labels when resizing the frame, based on new square size
    public void resize(){
        main.setBounds(0, 0,   frame.getWidth(), frame.getHeight()-35);
        SQUARE_SIZE = (int) main.getHeight()/9;
        menu.player1.setBounds(0, 0, SQUARE_SIZE*8, SQUARE_SIZE/2);
        menu.player2.setBounds(0, SQUARE_SIZE*8+SQUARE_SIZE/2 , SQUARE_SIZE*8, SQUARE_SIZE/2);
        menu.timer1.setBounds(SQUARE_SIZE/8, SQUARE_SIZE/8, SQUARE_SIZE*3/4, SQUARE_SIZE/4);
        menu.timer2.setBounds(SQUARE_SIZE/8, SQUARE_SIZE*8+SQUARE_SIZE/2+SQUARE_SIZE/8, SQUARE_SIZE*3/4, SQUARE_SIZE/4);
        menu.name1.setBounds(SQUARE_SIZE*5/4, SQUARE_SIZE/8, SQUARE_SIZE*3/2, SQUARE_SIZE/4);
        menu.name2.setBounds(SQUARE_SIZE*5/4, SQUARE_SIZE*8+SQUARE_SIZE/2+SQUARE_SIZE/8, SQUARE_SIZE*3/2, SQUARE_SIZE/4);
        menu.deadPieces1.setBounds(SQUARE_SIZE*13/4, SQUARE_SIZE/16, SQUARE_SIZE*9/2, SQUARE_SIZE*3/8);
        menu.deadPieces2.setBounds(SQUARE_SIZE*13/4, SQUARE_SIZE*8+SQUARE_SIZE/2+SQUARE_SIZE/16, SQUARE_SIZE*9/2, SQUARE_SIZE*3/8);
        menu.newGame.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16, SQUARE_SIZE*7/8+22, SQUARE_SIZE*3/8);
        menu.option.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE/2, SQUARE_SIZE*7/8+22, SQUARE_SIZE*3/8);
        menu.exit.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE, SQUARE_SIZE*7/8+22, SQUARE_SIZE*3/8);
        menu.analyse.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE*3/2, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
        menu.concede.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16+SQUARE_SIZE*5/16+22, SQUARE_SIZE*1/16+SQUARE_SIZE*3/2, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
        menu.leftButton.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE*2, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
        menu.rightButton.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16+SQUARE_SIZE*5/16+22, SQUARE_SIZE*1/16+SQUARE_SIZE*2, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                Image imageCase = b.board[x][y].icon.getImage();
                Image newimgCase = imageCase.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,  java.awt.Image.SCALE_SMOOTH);  
                b.board[x][y].image.setIcon(new ImageIcon(newimgCase));
                b.board[x][y].image.setBounds(x*SQUARE_SIZE, y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
                Image imageDispo = b.board[x][y].iconDispo.getImage();
                Image newimgDispo = imageDispo.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,  java.awt.Image.SCALE_SMOOTH);  
                b.board[x][y].moves.setIcon(new ImageIcon(newimgDispo));
                b.board[x][y].moves.setBounds(x*SQUARE_SIZE, y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);

                if(b.board[x][y].piece != null){
                    Image imagePiece = b.board[x][y].piece.icon.getImage();
                    Image newimgPiece = imagePiece.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,  java.awt.Image.SCALE_SMOOTH);  
                    b.board[x][y].piece.image.setIcon(new ImageIcon(newimgPiece));
                    b.board[x][y].piece.image.setBounds(x*SQUARE_SIZE, y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
                    
                }
            }
        }
    }

 
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// LISTENERS
    public void addListeners(){
        // Squares
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                addSquareListener(b.board[x][y]);
            }     
        }

        // If frame resize
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                resize();
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
    // SQUARES LISTENERS
    public void addSquareListener(Square square) {

        square.image.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) { 
                simpleClic(square);
            }   
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MAIN
    public static void main(String[] args){
        new Frame();
    }
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   /* Inutile pour l'instant, à garder pour le DragAndDrop    
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
                    displayMoves();
                    selectedPiece = piece;
                }
                else{
                    if(piece.getColor() != selectedPiece.getColor()){
                        eat(selectedPiece, piece);
                        b.movePiece(selectedPiece, b.board[piece.position.x][piece.position.y]);
                        selectedPiece.image.setBounds(piece.position.x*SQUARE_SIZE, piece.position.y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
                        b.deletePiece(piece); 
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
    

  
    }*/
