import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class CustomPanel extends JLayeredPane implements MouseListener, MouseMotionListener{

	int  SQUARE_SIZE = 80; 
	boolean draging, clic, popUp;
	int oldX, oldY;
	Menu menu;
	Frame f;
	Piece selectedPiece;
	List<Position> selectedPieceMoves;
    Square tempSquare, promotion;
	List<JLabel> numbers, letters;

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
		this.add(menu.exit, Integer.valueOf(2));
		this.add(menu.concede, Integer.valueOf(2));
		this.add(menu.inverse, Integer.valueOf(2));
		this.add(menu.leftButton, Integer.valueOf(2));
		this.add(menu.rightButton, Integer.valueOf(2));
		setCoordinates();
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void addGraphics(){
		for(int x=0; x<8; x++){
			for(int y=0; y<8; y++){
				add(f.b.board[x][y].image, Integer.valueOf(0));
				add(f.b.board[x][y].imageLastMove, Integer.valueOf(0));
				add(f.b.board[x][y].imageCheck, Integer.valueOf(0));
				add(f.b.board[x][y].movesEmpty, Integer.valueOf(0));
				add(f.b.board[x][y].movesFilled, Integer.valueOf(0));
				if(f.b.board[x][y].piece != null){
					add(f.b.board[x][y].piece.image, Integer.valueOf(3));
				}
			}
		}
		for(Piece piece: f.b.playerTop.cemetery){
            add(piece.image, Integer.valueOf(3));
        }
        for(Piece piece: f.b.playerBot.cemetery){
            add(piece.image, Integer.valueOf(3));
        }
		for(JLabel num:numbers){
			num.setForeground(menu.CL_LN);
			add(num, Integer.valueOf(1));
		}
		for(JLabel let:letters){
			let.setForeground(menu.CL_LN);
			add(let, Integer.valueOf(1));
		}		
		resize();
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void removeGraphics(){
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                remove(f.b.board[x][y].image);
                remove(f.b.board[x][y].movesEmpty);
                remove(f.b.board[x][y].movesFilled);
				remove(f.b.board[x][y].imageLastMove);
				remove(f.b.board[x][y].imageCheck);
                if(f.b.board[x][y].piece != null){
                    remove(f.b.board[x][y].piece.image);
                }
            }
        }
        for(Piece piece: f.b.playerTop.cemetery){
            remove(piece.image);
        }
        for(Piece piece: f.b.playerBot.cemetery){
            remove(piece.image);
        }
        for(JLabel num:numbers){
			remove(num);
		}
		for(JLabel let:letters){
			remove(let);
		}
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void initTimer(int time){
		menu.tT.stop();
		menu.tB.stop();
		if(time == 0){
			menu.timerTop.setText("00:00");
			menu.timerBot.setText("00:00");
			f.b.time = false;
		}
		else{
			menu.tTop = time;
			menu.tBot = time;
			menu.timerTop.setText(String.valueOf((int) time/60) +":"+ String.valueOf(time % 60)+"0");
			menu.timerBot.setText(String.valueOf((int) time/60) +":"+ String.valueOf(time % 60)+"0");
			f.b.time = true;
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void select(Square s){
        if(!s.getMoves(f.b).isEmpty()){
            selectedPieceMoves = s.getMoves(f.b);
            selectedPiece = s.piece;
			this.setLayer(selectedPiece.image,4);
            tempSquare = s;
            for(Position pos: selectedPieceMoves){ 
				if(f.b.board[pos.x][pos.y].piece != null){
                	this.setLayer(f.b.board[pos.x][pos.y].movesFilled,2);
				}
				else{
					this.setLayer(f.b.board[pos.x][pos.y].movesEmpty,2);
				}
            }
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// LISTENERS
	@Override
	public void mouseClicked(MouseEvent e) {
		draging = false;
		if(!popUp && f.b.nbMoves == f.b.cursorMoves){
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
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void mousePressed(MouseEvent e) {
		if(!popUp && f.b.nbMoves == f.b.cursorMoves && !menu.reverse){
			for(int x=0; x<8; x++){
				for(int y=0; y<8; y++){
					if(e.getX() <= f.b.board[x][y].image.getX()+f.b.board[x][y].image.getWidth() && e.getX() >= f.b.board[x][y].image.getX() && e.getY() <= f.b.board[x][y].image.getY()+f.b.board[x][y].image.getHeight() && e.getY() >= f.b.board[x][y].image.getY()){
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
											f.b.history.add(new Action(tempSquare, f.b.board[x][y], f.b.board[x][y].piece));
										}
										else{
											f.b.history.add(new Action(tempSquare, f.b.board[x][y]));
										}
										f.b.movePiece(tempSquare,f.b.board[x][y]);
										checkPromotion(f.b.board[x][y]);
										f.b.cursorMoves ++;
										f.b.nbMoves ++;
										switchPlayer(f.b.time);
										displayPieces();
									}
								}
								unselect(); 
							}	 
						}		
					}
				}
			}
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void mouseReleased(MouseEvent e) {
		if(draging && f.b.nbMoves == f.b.cursorMoves && !menu.reverse){
			boolean temp = false;
			for(int x=0; x<8; x++){
				for(int y=0; y<8; y++){
					if(e.getX() <= f.b.board[x][y].image.getX()+f.b.board[x][y].image.getWidth() && e.getX() >= f.b.board[x][y].image.getX() && e.getY() <= f.b.board[x][y].image.getY()+f.b.board[x][y].image.getHeight() && e.getY() >= f.b.board[x][y].image.getY()){
						if(selectedPieceMoves != null){
							for(Position position: selectedPieceMoves){
								if(position.equals(f.b.board[x][y].position)){
									if(f.b.board[x][y].piece != null){
										f.b.history.add(new Action(tempSquare, f.b.board[x][y], f.b.board[x][y].piece));
									}
									else{
										f.b.history.add(new Action(tempSquare, f.b.board[x][y]));
									}
									f.b.movePiece(tempSquare,f.b.board[x][y]);
									/*if (f.b.isCheck(f.b.currentPlayer.isWhite)){
										f.b.uncurrentPlayer.check = true;
									}*/
									checkPromotion(f.b.board[x][y]);
									f.b.cursorMoves ++;
									f.b.nbMoves ++;
									
									temp = true;
									switchPlayer(f.b.time);
									displayPieces();
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void mouseDragged(MouseEvent e) {
		if(draging && f.b.nbMoves == f.b.cursorMoves && !menu.reverse && selectedPiece != null){
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
        menu.exit.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE/2, SQUARE_SIZE*7/8+22, SQUARE_SIZE*3/8);
		Image imageInv = menu.iconInv.getImage();
        Image newimgInv = imageInv.getScaledInstance(SQUARE_SIZE*9/32, SQUARE_SIZE*15/62,  java.awt.Image.SCALE_SMOOTH);  
        menu.inverse.setIcon(new ImageIcon(newimgInv));
        menu.inverse.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
		Image imageFlag = menu.iconFlag.getImage();
        Image newimgFlag = imageFlag.getScaledInstance(SQUARE_SIZE*9/32, SQUARE_SIZE*8/32,  java.awt.Image.SCALE_SMOOTH);  
        menu.concede.setIcon(new ImageIcon(newimgFlag));
        menu.concede.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16+SQUARE_SIZE*5/16+22, SQUARE_SIZE*1/16+SQUARE_SIZE, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
		Image imageLeft = menu.left.getImage();
        Image newimgLeft = imageLeft.getScaledInstance(SQUARE_SIZE*11/32, SQUARE_SIZE*9/32,  java.awt.Image.SCALE_SMOOTH);  
        menu.leftButton.setIcon(new ImageIcon(newimgLeft));
        menu.leftButton.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16, SQUARE_SIZE*1/16+SQUARE_SIZE*3/2, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
		Image imageRight = menu.right.getImage();
        Image newimgRight = imageRight.getScaledInstance(SQUARE_SIZE*11/32, SQUARE_SIZE*9/32,  java.awt.Image.SCALE_SMOOTH);  
        menu.rightButton.setIcon(new ImageIcon(newimgRight));
        menu.rightButton.setBounds(SQUARE_SIZE*8+SQUARE_SIZE*1/16+SQUARE_SIZE*5/16+22, SQUARE_SIZE*1/16+SQUARE_SIZE*3/2, SQUARE_SIZE*9/16, SQUARE_SIZE*3/8);
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
				Image imageLastMove = f.b.board[x][y].iconLastMove.getImage();
                Image newimgLastMove = imageLastMove.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,  java.awt.Image.SCALE_SMOOTH);  
                f.b.board[x][y].imageLastMove.setIcon(new ImageIcon(newimgLastMove));
                f.b.board[x][y].imageLastMove.setBounds(x*SQUARE_SIZE, y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
				Image imageCheck = f.b.board[x][y].iconCheck.getImage();
                Image newimgCheck = imageCheck.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,  java.awt.Image.SCALE_SMOOTH);  
                f.b.board[x][y].imageCheck.setIcon(new ImageIcon(newimgCheck));
                f.b.board[x][y].imageCheck.setBounds(x*SQUARE_SIZE, y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
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
		displayPieces();
		menu.updateButtons(f);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void reverseCoordinates(){
		Collections.reverse(numbers);
		Collections.reverse(letters);
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void displayPieces(){
		for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
				this.setLayer(f.b.board[x][y].imageLastMove,0);
				this.setLayer(f.b.board[x][y].imageCheck,0);
                if(f.b.board[x][y].piece != null){
                    Image imagePiece = f.b.board[x][y].piece.icon.getImage();
                    Image newimgPiece = imagePiece.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,  java.awt.Image.SCALE_SMOOTH);  
                    f.b.board[x][y].piece.image.setIcon(new ImageIcon(newimgPiece));
                    f.b.board[x][y].piece.image.setBounds(x*SQUARE_SIZE, y*SQUARE_SIZE+SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
					if(f.b.board[x][y].piece instanceof King){
						if(f.b.currentPlayer.check && f.b.board[x][y].piece.isWhite == f.b.currentPlayer.isWhite){
							this.setLayer(f.b.board[x][y].imageCheck,1);
						}
					}
                }
            }
        }
		if(f.b.cursorMoves != 0){
			this.setLayer(f.b.board[f.b.history.get(f.b.cursorMoves-1).start.position.x][f.b.history.get(f.b.cursorMoves-1).start.position.y].imageLastMove,1);
			this.setLayer(f.b.board[f.b.history.get(f.b.cursorMoves-1).end.position.x][f.b.history.get(f.b.cursorMoves-1).end.position.y].imageLastMove,1);
		}
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
		menu.updateButtons(f);
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void switchPlayer(boolean time){
		if(time){
			if(f.b.playerTop.isWhite == f.b.currentPlayer.isWhite){
				menu.tT.stop();
				menu.tB.start(); 
			}
			else {
				menu.tB.stop();
				menu.tT.start();
			}
		}
		if(f.b.currentPlayer.isWhite == f.b.playerTop.isWhite){
            f.b.currentPlayer = f.b.playerBot;
            f.b.uncurrentPlayer = f.b.playerTop;
        }
        else{
            f.b.currentPlayer = f.b.playerTop;
            f.b.uncurrentPlayer = f.b.playerBot;
        }

        //New current player in check ?
        if (f.b.isCheck(!f.b.currentPlayer.isWhite)){
        	f.b.currentPlayer.check = true;
		}

        //Game Over ?
		if (f.b.winLose()) {
			menu.endGame(f.b.uncurrentPlayer, this);
		}
		if (f.b.equality()){
			menu.endGame(null, this);

		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void checkPromotion(Square s){
		if(s.piece instanceof Pawn){
			if(f.b.currentPlayer.isTop){
				if(s.position.y == 7){
					promotion = s;
					popUpPromotion(s);
				}
			}
			else{
				if(s.position.y == 0){
					promotion = s;
					popUpPromotion(s);
				}
			}
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void popUpPromotion(Square s){
		f.b.playSound("promotion");
		this.add(menu.popupPanel, Integer.valueOf(5));
		if(s.piece.isWhite){
			for(JLabel piece: menu.promotionWhite){		
				this.add(piece, Integer.valueOf(6));
				piece.setBorder(new LineBorder(menu.CL_FONT));
			}
		}
		else {
			for(JLabel piece: menu.promotionBlack){		
				this.add(piece, Integer.valueOf(6));
				piece.setBorder(new LineBorder(menu.CL_FONT));
			}
		}
		menu.popupPanel.setBounds(SQUARE_SIZE*23/8,SQUARE_SIZE*27/8, SQUARE_SIZE*18/8, SQUARE_SIZE*18/8);
		if(s.piece.isWhite){
			for(JLabel piece: menu.promotionWhite){
				switch(menu.promotionWhite.indexOf(piece)){
					case 0:
						piece.setBounds(SQUARE_SIZE*3, SQUARE_SIZE*7/2,SQUARE_SIZE*15/16,SQUARE_SIZE*15/16);
						break;
					case 1:
						piece.setBounds(SQUARE_SIZE*65/16, SQUARE_SIZE*7/2,SQUARE_SIZE*15/16,SQUARE_SIZE*15/16);
						break;
					case 2:
						piece.setBounds(SQUARE_SIZE*3, SQUARE_SIZE*73/16,SQUARE_SIZE*15/16,SQUARE_SIZE*15/16);
						break;
					case 3:
						piece.setBounds(SQUARE_SIZE*65/16, SQUARE_SIZE*73/16,SQUARE_SIZE*15/16,SQUARE_SIZE*15/16);
						break;
				}
			}
		}
		else{
			for(JLabel piece: menu.promotionBlack){
				switch(menu.promotionBlack.indexOf(piece)){
					case 0:
						piece.setBounds(SQUARE_SIZE*3, SQUARE_SIZE*7/2,SQUARE_SIZE*15/16,SQUARE_SIZE*15/16);
						break;
					case 1:
						piece.setBounds(SQUARE_SIZE*65/16, SQUARE_SIZE*7/2,SQUARE_SIZE*15/16,SQUARE_SIZE*15/16);
						break;
					case 2:
						piece.setBounds(SQUARE_SIZE*3, SQUARE_SIZE*73/16,SQUARE_SIZE*15/16,SQUARE_SIZE*15/16);
						break;
					case 3:
						piece.setBounds(SQUARE_SIZE*65/16, SQUARE_SIZE*73/16,SQUARE_SIZE*15/16,SQUARE_SIZE*15/16);
						break;
				}
			}
		}
		popUp = true;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void replacePiecePromotion(Piece p, Square s){
		this.setLayer(menu.popupPanel,0);
		this.remove(menu.popupPanel);
		for(JLabel piece: menu.promotionWhite){	
			this.setLayer(piece,0);	
			this.remove(piece);
		}
		for(JLabel piece: menu.promotionBlack){		
			this.setLayer(piece,0);
			this.remove(piece);
		}
		this.setLayer(s.piece.image,0);
		this.remove(s.piece.image);
		this.add(p.image, Integer.valueOf(3));
		f.b.board[s.position.x][s.position.y].piece = p;
		f.panel.displayPieces();
		promotion = null;
		popUp = false;
		menu.updateButtons(f);
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setCoordinates(){
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
		if(f.b != null){
			if(!f.b.playerBot.isWhite){
				reverseCoordinates();
			}
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}