import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class CustomPanel extends JLayeredPane implements MouseListener, MouseMotionListener{

	int  SQUARE_SIZE = 80; 
	boolean draging, clic;
	int oldX, oldY;
	Menu menu;
	Frame f;
	Piece selectedPiece;
	List<Position> selectedPieceMoves;
    Square tempSquare;
	List<JLabel> numbers, letters;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR	

	public CustomPanel(Frame f){
		super();
		this.f = f; 
		draging = false;
		clic = false;
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(300, 300));
		// Menu
		menu = new Menu(f); 
		this.add(menu.timerTop, Integer.valueOf(2));
		this.add(menu.timerBot, Integer.valueOf(2));
		this.add(menu.nameTop, Integer.valueOf(2));
		this.add(menu.nameBot, Integer.valueOf(2));
		this.add(menu.deadPiecesTop, Integer.valueOf(2));
		this.add(menu.deadPiecesBot, Integer.valueOf(2));
		this.add(menu.newGame, Integer.valueOf(2));
		this.add(menu.option, Integer.valueOf(2));
		this.add(menu.exit, Integer.valueOf(2));
		this.add(menu.concede, Integer.valueOf(2));
		this.add(menu.analyse, Integer.valueOf(2));
		this.add(menu.leftButton, Integer.valueOf(2));
		this.add(menu.rightButton, Integer.valueOf(2));
		// Coordinates
		numbers = new ArrayList<>();
		letters = new ArrayList<>();
		for(int i=8; i>0; i--){
			numbers.add(new JLabel(String.valueOf(i)));
		}
		letters.add(new JLabel("a"));
		letters.add(new JLabel("b"));
		letters.add(new JLabel("c"));
		letters.add(new JLabel("d"));
		letters.add(new JLabel("e"));
		letters.add(new JLabel("f"));
		letters.add(new JLabel("g"));
		letters.add(new JLabel("h"));
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MAIN FUNCTIONS

	// Initializes board (graphically) and resize.
	public void initBoardGraphics(){
		for(int x=0; x<8; x++){
			for(int y=0; y<8; y++){
				this.add(f.b.board[x][y].image, Integer.valueOf(0));
				this.add(f.b.board[x][y].movesEmpty, Integer.valueOf(0));
				this.add(f.b.board[x][y].movesFilled, Integer.valueOf(0));
				if(f.b.board[x][y].piece != null){
					this.add(f.b.board[x][y].piece.image, Integer.valueOf(3));
				}
			}
		}
		for(JLabel num:numbers){
			num.setForeground(menu.CL_LN);
			this.add(num, Integer.valueOf(1));
		}
		for(JLabel let:letters){
			let.setForeground(menu.CL_LN);
			this.add(let, Integer.valueOf(1));
		}
		// Timers
		menu.tT.stop();
		menu.tB.stop();
		menu.tTop = 600;
		menu.tBot = 600;
		if(f.b.playerTop.isWhite){
            menu.tT.start(); 
        }
        else {
            menu.tB.start();
        }
		resize();
	}

	// Selects a square if the piece can move
    public void select(Square s){
        if(!s.getMoves(f.b).isEmpty()){
            selectedPieceMoves = s.getMoves(f.b);
            selectedPiece = s.piece;
			this.setLayer(selectedPiece.image,4);
            tempSquare = s;
            for(Position pos: selectedPieceMoves){ 
				if(f.b.board[pos.x][pos.y].piece != null){
                	this.setLayer(f.b.board[pos.x][pos.y].movesFilled,1);
				}
				else{
					this.setLayer(f.b.board[pos.x][pos.y].movesEmpty,1);
				}
            }
        }
    }

    // Undoes the selection
    public void unselect(){ // previously removeMoves
		if(selectedPieceMoves != null){
			for(Position pos: selectedPieceMoves){ // for each moves
				this.setLayer(f.b.board[pos.x][pos.y].movesEmpty,0);
				this.setLayer(f.b.board[pos.x][pos.y].movesFilled,0); 
			}
        	selectedPieceMoves = null;
		}	
		if(selectedPiece != null){
			this.setLayer(selectedPiece.image,3);
        	selectedPiece = null;
		}
        tempSquare = null;
    }   

	// Puts a piece in the cemetery
	public void putToCemetery(Piece piece){
        Image image = piece.icon.getImage();
        Image newimg = image.getScaledInstance(SQUARE_SIZE*3/8, SQUARE_SIZE*3/8,  java.awt.Image.SCALE_SMOOTH);  
        piece.image.setIcon(new ImageIcon(newimg));
        if(piece.getColor()){
            piece.image.setBounds(SQUARE_SIZE*9/8+f.b.playerTop.cemetery.size()*SQUARE_SIZE*19/64, SQUARE_SIZE*1/16, SQUARE_SIZE*9/2, SQUARE_SIZE*3/8);
        }
        else{
            piece.image.setBounds(SQUARE_SIZE*9/8+f.b.playerBot.cemetery.size()*SQUARE_SIZE*19/64, SQUARE_SIZE*8+SQUARE_SIZE/2+SQUARE_SIZE/16, SQUARE_SIZE*9/2, SQUARE_SIZE*3/8);
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// LISTENERS
	// When mouse is clicked
	@Override
	public void mouseClicked(MouseEvent e) {
		draging = false;
		if(!clic){
			for(int x=0; x<8; x++){
				for(int y=0; y<8; y++){
					if(e.getX() <= f.b.board[x][y].image.getX()+f.b.board[x][y].image.getWidth() && e.getX() >= f.b.board[x][y].image.getX() && e.getY() <= f.b.board[x][y].image.getY()+f.b.board[x][y].image.getHeight() && e.getY() >= f.b.board[x][y].image.getY()){
						//if(f.b.board[x][y].image.contains(e.getX(), e.getY())) {// ???	
							if(selectedPiece == null){
								if (f.b.board[x][y].piece != null){
									if(f.b.currentPlayer.isWhite == f.b.board[x][y].piece.isWhite){
										select(f.b.board[x][y]);
										clic = true; 
									}	
								}
							}	
					}
				}
			}
		}
		else{
			clic = false;
		}	
	}
	// When mouse is pressed
	@Override
	public void mousePressed(MouseEvent e) {
		for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
				if(e.getX() <= f.b.board[x][y].image.getX()+f.b.board[x][y].image.getWidth() && e.getX() >= f.b.board[x][y].image.getX() && e.getY() <= f.b.board[x][y].image.getY()+f.b.board[x][y].image.getHeight() && e.getY() >= f.b.board[x][y].image.getY()){
					//if(f.b.board[x][y].image.contains(e.getX(), e.getY())) {// ???
						if(!clic){
							if(f.b.board[x][y].piece != null){
								if(f.b.currentPlayer.isWhite == f.b.board[x][y].piece.isWhite){
									select(f.b.board[x][y]);
									draging = true;
									oldX = e.getX();
									oldY = e.getY();
								}
							}	
						}	
						else{
							if(selectedPieceMoves != null){
								for(Position position: selectedPieceMoves){
									if(position.equals(f.b.board[x][y].position)){
										if(f.b.board[x][y].piece != null){
											putToCemetery(f.b.board[x][y].piece);
										}
										if(f.b.playerTop.isWhite == f.b.currentPlayer.isWhite){
											menu.tT.stop();
											menu.tB.start(); 
										}
										else {
											menu.tB.stop();
											menu.tT.start();
										}
										f.b.movePiece(tempSquare,f.b.board[x][y]);
										selectedPiece.image.setBounds(f.b.board[x][y].position.x*SQUARE_SIZE, f.b.board[x][y].position.y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
									}
								}
								unselect(); 
							}	 
						}		
				}
			}
		}
	}
	// When mouse is released
	@Override
	public void mouseReleased(MouseEvent e) {
		if(draging){
			boolean temp = false;
			for(int x=0; x<8; x++){
				for(int y=0; y<8; y++){
					if(e.getX() <= f.b.board[x][y].image.getX()+f.b.board[x][y].image.getWidth() && e.getX() >= f.b.board[x][y].image.getX() && e.getY() <= f.b.board[x][y].image.getY()+f.b.board[x][y].image.getHeight() && e.getY() >= f.b.board[x][y].image.getY()){
						if(selectedPieceMoves != null){
							for(Position position: selectedPieceMoves){
								if(position.equals(f.b.board[x][y].position)){
									if(f.b.board[x][y].piece != null){
										putToCemetery(f.b.board[x][y].piece);
									}
									if(f.b.playerTop.isWhite == f.b.currentPlayer.isWhite){
										menu.tT.stop();
										menu.tB.start(); 
									}
									else {
										menu.tB.stop();
										menu.tT.start();
									}
									f.b.movePiece(tempSquare,f.b.board[x][y]);
									selectedPiece.image.setBounds(f.b.board[x][y].position.x*SQUARE_SIZE, f.b.board[x][y].position.y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
									temp = true;
								}		
							}
						}						
					}
				}
			}
			if(!temp){
				if(selectedPiece != null){
					selectedPiece.image.setBounds(tempSquare.position.x*SQUARE_SIZE, tempSquare.position.y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);						
				}	
			}
		unselect();
		draging = false;
		}
	}
	// When mouse is dragged
	@Override
	public void mouseDragged(MouseEvent e) {
		if(draging){
			selectedPiece.image.setBounds(selectedPiece.image.getX() + e.getX() - oldX, selectedPiece.image.getY() + e.getY() - oldY, SQUARE_SIZE,SQUARE_SIZE);
			oldX = e.getX();
			oldY = e.getY();
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//  RESIZE : Keeps proportion of all Labels when resizing the frame, based on new square size
    public void resize(){
        this.setBounds(0, 0,   f.frame.getWidth(), f.frame.getHeight()-35);
        SQUARE_SIZE = (int) this.getHeight()/9;
		// Resize Menu
        menu.timerTop.setBounds(SQUARE_SIZE/8, SQUARE_SIZE/8, SQUARE_SIZE*3/4, SQUARE_SIZE/4);
        menu.timerBot.setBounds(SQUARE_SIZE/8, SQUARE_SIZE*8+SQUARE_SIZE/2+SQUARE_SIZE/8, SQUARE_SIZE*3/4, SQUARE_SIZE/4);
        menu.nameTop.setBounds(SQUARE_SIZE*5/4, SQUARE_SIZE/8, SQUARE_SIZE*3/2, SQUARE_SIZE/4);
        menu.nameBot.setBounds(SQUARE_SIZE*5/4, SQUARE_SIZE*8+SQUARE_SIZE/2+SQUARE_SIZE/8, SQUARE_SIZE*3/2, SQUARE_SIZE/4);
        menu.deadPiecesTop.setBounds(SQUARE_SIZE*13/4, SQUARE_SIZE/16, SQUARE_SIZE*9/2, SQUARE_SIZE*3/8);
        menu.deadPiecesBot.setBounds(SQUARE_SIZE*13/4, SQUARE_SIZE*8+SQUARE_SIZE/2+SQUARE_SIZE/16, SQUARE_SIZE*9/2, SQUARE_SIZE*3/8);
        menu.newGame.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16, SQUARE_SIZE*7/8+22, SQUARE_SIZE*3/8);
        menu.option.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE/2, SQUARE_SIZE*7/8+22, SQUARE_SIZE*3/8);
        menu.exit.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE, SQUARE_SIZE*7/8+22, SQUARE_SIZE*3/8);
        menu.analyse.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE*3/2, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
        menu.concede.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16+SQUARE_SIZE*5/16+22, SQUARE_SIZE*1/16+SQUARE_SIZE*3/2, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
        menu.leftButton.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE*2, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
        menu.rightButton.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16+SQUARE_SIZE*5/16+22, SQUARE_SIZE*1/16+SQUARE_SIZE*2, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
        // Resize squares
		for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                Image imageCase = f.b.board[x][y].icon.getImage();
                Image newimgCase = imageCase.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,  java.awt.Image.SCALE_SMOOTH);  
                f.b.board[x][y].image.setIcon(new ImageIcon(newimgCase));
                f.b.board[x][y].image.setBounds(x*SQUARE_SIZE, y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
                Image imageDispoEmpty = f.b.board[x][y].iconDispoEmpty.getImage();
                Image newimgDispoEmpty = imageDispoEmpty.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,  java.awt.Image.SCALE_SMOOTH);  
                f.b.board[x][y].movesEmpty.setIcon(new ImageIcon(newimgDispoEmpty));
                f.b.board[x][y].movesEmpty.setBounds(x*SQUARE_SIZE, y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
				Image imageDispoFilled = f.b.board[x][y].iconDispoFilled.getImage();
                Image newimgDispoFilled = imageDispoFilled.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,  java.awt.Image.SCALE_SMOOTH);  
                f.b.board[x][y].movesFilled.setIcon(new ImageIcon(newimgDispoFilled));
                f.b.board[x][y].movesFilled.setBounds(x*SQUARE_SIZE, y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
				// Resize pieces
                if(f.b.board[x][y].piece != null){
                    Image imagePiece = f.b.board[x][y].piece.icon.getImage();
                    Image newimgPiece = imagePiece.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,  java.awt.Image.SCALE_SMOOTH);  
                    f.b.board[x][y].piece.image.setIcon(new ImageIcon(newimgPiece));
                    f.b.board[x][y].piece.image.setBounds(x*SQUARE_SIZE, y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }
		// Resize cemetery
        for(Piece piece:f.b.playerTop.cemetery){
            Image image = piece.icon.getImage();
            Image newimg = image.getScaledInstance(SQUARE_SIZE*3/8, SQUARE_SIZE*3/8,  java.awt.Image.SCALE_SMOOTH);  
            piece.image.setIcon(new ImageIcon(newimg));
            piece.image.setBounds(SQUARE_SIZE*9/8+f.b.playerTop.cemetery.indexOf(piece)*SQUARE_SIZE*19/64, SQUARE_SIZE*1/16, SQUARE_SIZE*9/2, SQUARE_SIZE*3/8);
        }
        for(Piece piece:f.b.playerBot.cemetery){
            Image image = piece.icon.getImage();
            Image newimg = image.getScaledInstance(SQUARE_SIZE*3/8, SQUARE_SIZE*3/8,  java.awt.Image.SCALE_SMOOTH);  
            piece.image.setIcon(new ImageIcon(newimg));
            piece.image.setBounds(SQUARE_SIZE*9/8+f.b.playerBot.cemetery.indexOf(piece)*SQUARE_SIZE*19/64, SQUARE_SIZE*8+SQUARE_SIZE/2+SQUARE_SIZE/16, SQUARE_SIZE*9/2, SQUARE_SIZE*3/8);
        }
		// Resize coordinates
		for(JLabel num:numbers){
			num.setBounds(SQUARE_SIZE*1/16, numbers.indexOf(num)*SQUARE_SIZE+SQUARE_SIZE*17/32 , SQUARE_SIZE/4, SQUARE_SIZE/4);
		}
		for(JLabel let:letters){
			let.setBounds(letters.indexOf(let)*SQUARE_SIZE+SQUARE_SIZE*7/8, 8*SQUARE_SIZE+SQUARE_SIZE*1/4, SQUARE_SIZE/4, SQUARE_SIZE/4);
		}
    }

	public void reverseCoordinates(){
		Collections.reverse(numbers);
		Collections.reverse(letters);
	}
}