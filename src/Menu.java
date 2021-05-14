import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Menu {
    JLabel nameTop, nameBot, timerTop, timerBot, deadPiecesTop, deadPiecesBot, player1, player2;
    JLabel menu, newGame, exit, inverse, concede, leftButton, rightButton, popupPanel, timer;
    JLabel play, cancel, vsPlayer, vsIA, endNewGame, endExit, endMessage;
    JTextField p1name, p2name;
    JSlider slider;
    ImageIcon iconInv, iconFlag, left, right; 
    List<JLabel> promotionWhite, promotionBlack;
    Color CL_LN = new Color(150,125,100); // Numbers and letters color
    Color CL_GUI = new Color(26,33,41); // GUI background color
    Color CL_FONT = new Color(227,215,199); // Font color
    Color CL_BK = new Color(47,53,62); // Frame background color
    Color CL_ACTIVE = new Color(90,100,111); // Active button color
    int tTop, tBot;
    Timer tT, tB;
    boolean IA = false;
    boolean reverse = false;

    public Menu(Frame f){

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//  New game pop-up 
        popupPanel = new JLabel();
        popupPanel.setBackground(CL_BK);
        popupPanel.setOpaque(true);
        popupPanel.setBorder(new LineBorder(CL_GUI)); 
        p1name = new JTextField("Player_1_name");
        p1name.setBackground(CL_GUI);
        p1name.setForeground(CL_FONT);
        p1name.setBorder(null);
        p2name = new JTextField("Player_2_name");
        p2name.setBackground(CL_GUI);
        p2name.setForeground(CL_FONT);
        p2name.setBorder(null);
        slider = new JSlider(0,30);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(CL_BK);
        slider.setForeground(CL_FONT); 
        slider.setValue(10);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
              timer.setText("Time : " + Integer.toString(slider.getValue())+" min");
              if(slider.getValue() == 0){
                timer.setText("Time : unlimited");
              }
            }
          });
        play = new JLabel("Play", SwingConstants.CENTER);
        play.setBackground(CL_ACTIVE);
        play.setOpaque(true);
        play.setForeground(CL_FONT);
        play.setBorder(new LineBorder(CL_GUI));
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                f.newGame(p2name.getText(), p1name.getText(), slider.getValue()*60, IA);
                removeNewGamePopUp(f.panel);
            } 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        cancel = new JLabel("Cancel", SwingConstants.CENTER);
        cancel.setBackground(CL_ACTIVE);
        cancel.setOpaque(true);
        cancel.setForeground(CL_FONT);
        cancel.setBorder(new LineBorder(CL_GUI));
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                removeNewGamePopUp(f.panel);
            } 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        vsPlayer = new JLabel("Versus player", SwingConstants.CENTER);
        vsPlayer.setBackground(CL_ACTIVE);
        vsPlayer.setOpaque(true);
        vsPlayer.setForeground(CL_FONT);
        vsPlayer.setBorder(new LineBorder(CL_FONT));
        vsPlayer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                IA = false;
                vsPlayer.setBackground(CL_ACTIVE);
                vsPlayer.setBorder(new LineBorder(CL_FONT));
                vsIA.setBackground(CL_GUI);
                vsIA.setBorder(null);
            } 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        vsIA = new JLabel("Versus IA", SwingConstants.CENTER);
        vsIA.setBackground(CL_GUI);
        vsIA.setOpaque(true);
        vsIA.setForeground(CL_FONT);
        vsIA.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                IA = true;
                vsPlayer.setBackground(CL_GUI);
                vsPlayer.setBorder(null);
                vsIA.setBackground(CL_ACTIVE);
                vsIA.setBorder(new LineBorder(CL_FONT));
            } 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        timer = new JLabel("Time : 10 min", SwingConstants.LEFT);
        timer.setBackground(CL_BK);
        timer.setOpaque(true);
        timer.setForeground(CL_FONT);
        player1 = new JLabel("Player 1 :", SwingConstants.LEFT);
        player1.setBackground(CL_BK);
        player1.setOpaque(true);
        player1.setForeground(CL_FONT);
        player2 = new JLabel("Player 2 :", SwingConstants.LEFT);
        player2.setBackground(CL_BK);
        player2.setOpaque(true);
        player2.setForeground(CL_FONT);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End game pop-up
        endNewGame = new JLabel("New Game", SwingConstants.CENTER);
        endNewGame.setBackground(CL_ACTIVE);
        endNewGame.setOpaque(true);
        endNewGame.setForeground(CL_FONT);
        endNewGame.setBorder(new LineBorder(CL_GUI));
        endNewGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                removeEndGamePanel(f.panel);
                addNewGamePopUp(f.panel);
            } 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        endExit = new JLabel("Exit", SwingConstants.CENTER);
        endExit.setBackground(CL_ACTIVE);
        endExit.setOpaque(true);
        endExit.setForeground(CL_FONT);
        endExit.setBorder(new LineBorder(CL_GUI));
        endExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                f.frame.dispose();
            } 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        endMessage = new JLabel("", SwingConstants.CENTER);
        endMessage.setBackground(CL_BK);
        endMessage.setOpaque(true);
        endMessage.setForeground(CL_FONT);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Promotion
        promotionWhite = new ArrayList<JLabel>();
        promotionWhite.add(new JLabel(new ImageIcon("Images/wn.png")));
        promotionWhite.add(new JLabel(new ImageIcon("Images/wb.png")));
        promotionWhite.add(new JLabel(new ImageIcon("Images/wr.png")));
        promotionWhite.add(new JLabel(new ImageIcon("Images/wq.png")));
        for(JLabel piece: promotionWhite){
            piece.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}
                @Override
                public void mousePressed(MouseEvent e) {
                    if(f.panel.popUp){
                        switch(promotionWhite.indexOf(piece)){
                            case 0:
                            f.panel.replacePiecePromotion(new Knight(true), f.panel.promotion);
                    		f.b.history.get(f.b.history.size()-1).promoted = new Knight(true);
                            break;
                        case 1:
                            f.panel.replacePiecePromotion(new Bishop(true), f.panel.promotion);
                            f.b.history.get(f.b.history.size()-1).promoted = new Bishop(true);
                            break;
                        case 2:
                            f.panel.replacePiecePromotion(new Rook(true), f.panel.promotion);
                            f.b.history.get(f.b.history.size()-1).promoted = new Rook(true);
                            break;
                        case 3:
                            f.panel.replacePiecePromotion(new Queen(true), f.panel.promotion);
                            f.b.history.get(f.b.history.size()-1).promoted = new Queen(true);
                            break;
                        }
                    }
                } 
                @Override
                public void mouseReleased(MouseEvent e) {}
            });
        }
        promotionBlack = new ArrayList<JLabel>();
        promotionBlack.add(new JLabel(new ImageIcon("Images/bn.png")));
        promotionBlack.add(new JLabel(new ImageIcon("Images/bb.png")));
        promotionBlack.add(new JLabel(new ImageIcon("Images/br.png")));
        promotionBlack.add(new JLabel(new ImageIcon("Images/bq.png")));
        for(JLabel piece: promotionBlack){
            piece.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}
                @Override
                public void mousePressed(MouseEvent e) {
                    if(f.panel.popUp){
                        switch(promotionBlack.indexOf(piece)){
                            case 0:
                            f.panel.replacePiecePromotion(new Knight(false), f.panel.promotion);
                            f.b.history.get(f.b.history.size()-1).promoted = new Knight(false);
                            break;
                        case 1:
                            f.panel.replacePiecePromotion(new Bishop(false), f.panel.promotion);
                            f.b.history.get(f.b.history.size()-1).promoted = new Bishop(false);
                            break;
                        case 2:
                            f.panel.replacePiecePromotion(new Rook(false), f.panel.promotion);
                            f.b.history.get(f.b.history.size()-1).promoted = new Rook(false);
                            break;
                        case 3:
                            f.panel.replacePiecePromotion(new Queen(false), f.panel.promotion);
                            f.b.history.get(f.b.history.size()-1).promoted = new Queen(false);
                            break;
                        }
                    }
                } 
                @Override
                public void mouseReleased(MouseEvent e) {}
             });
         }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Timers
        timerTop = new JLabel("", SwingConstants.CENTER);
        timerTop.setBackground(CL_GUI);
        timerTop.setOpaque(true);
        timerTop.setForeground(CL_FONT);
        ActionListener Top = new ActionListener(){
            public void actionPerformed(ActionEvent ae){            
                tTop --;
                timerTop.setText(String.valueOf((int) tTop/60) +":"+ String.valueOf(tTop % 60));
                if(tTop == 0){
                    endGame(f.b.playerBot, f.panel);
                }    
            }
        };
        tT = new Timer(1000, Top);

        timerBot = new JLabel("", SwingConstants.CENTER);
        timerBot.setBackground(CL_GUI);
        timerBot.setOpaque(true);
        timerBot.setForeground(CL_FONT);
        ActionListener Bot = new ActionListener(){
            public void actionPerformed(ActionEvent ae){            
                tBot --;    
                timerBot.setText(String.valueOf((int) tBot/60) +":"+ String.valueOf(tBot % 60)); 
                if(tBot == 0){
                    endGame(f.b.playerTop, f.panel);
                } 
            }
        };
        tB = new Timer(1000, Bot); 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Names
        nameTop = new JLabel("", SwingConstants.CENTER);
        nameTop.setBackground(CL_GUI);
        nameTop.setOpaque(true);
        nameTop.setForeground(CL_FONT);
        nameBot = new JLabel("", SwingConstants.CENTER);
        nameBot.setBackground(CL_GUI);
        nameBot.setOpaque(true);
        nameBot.setForeground(CL_FONT);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DeadPieces
        deadPiecesTop = new JLabel();
        deadPiecesTop.setBackground(CL_GUI);
        deadPiecesTop.setOpaque(true);
        deadPiecesBot = new JLabel();
        deadPiecesBot.setBackground(CL_GUI);
        deadPiecesBot.setOpaque(true);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// New Game button
        newGame = new JLabel("New game", SwingConstants.CENTER);
        newGame.setBackground(CL_GUI);
        newGame.setOpaque(true);
        newGame.setForeground(CL_FONT);  
        newGame.setBorder(new LineBorder(CL_GUI));
        newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                if(!f.panel.popUp){
                    addNewGamePopUp(f.panel);
                }
            } 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Exit button
        exit = new JLabel("Exit", SwingConstants.CENTER);
        exit.setBackground(CL_ACTIVE);
        exit.setOpaque(true);
        exit.setForeground(CL_FONT);        
        exit.setBorder(new LineBorder(CL_GUI));
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
                f.frame.dispose();
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Reverse button
        iconInv = new ImageIcon("Images/inverse.png");
        inverse = new JLabel(iconInv, SwingConstants.CENTER);
        inverse.setBackground(CL_GUI);
        inverse.setOpaque(true);
        inverse.setForeground(CL_FONT);
        inverse.setBorder(new LineBorder(CL_GUI));
        inverse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                f.panel.unselect();
                if(!f.panel.popUp){
                    reverse = !reverse;
                    f.panel.removeGraphics();
                    f.b.reverseBoard();
                    f.panel.reverseCoordinates();
                    f.panel.addGraphics();
                    
                }
            } 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Concede button
        iconFlag = new ImageIcon("Images/flag.png");
        concede = new JLabel(iconFlag, SwingConstants.CENTER);
        concede.setBackground(CL_GUI);
        concede.setOpaque(true);
        concede.setForeground(CL_FONT);
        concede.setBorder(new LineBorder(CL_GUI));        
        concede.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                endGame(null, f.panel);
            } 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Last move button
        left = new ImageIcon("Images/left.png");
        leftButton = new JLabel(left, SwingConstants.CENTER);
        leftButton.setBackground(CL_GUI);
        leftButton.setOpaque(true);
        leftButton.setForeground(CL_FONT);      
        leftButton.setBorder(new LineBorder(CL_GUI));  
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                f.panel.unselect();
                if(f.b.cursorMoves > 0 && !f.panel.popUp){
                    f.b.movePiece(f.b.history.get(f.b.cursorMoves-1).end, f.b.history.get(f.b.cursorMoves-1).start);
                    if(f.b.history.get(f.b.cursorMoves-1).piece != null){
                        f.b.addPiece(f.b.history.get(f.b.cursorMoves-1).piece, f.b.history.get(f.b.cursorMoves-1).end);
                        if(f.b.history.get(f.b.cursorMoves-1).end.piece.isWhite){
                            if(f.b.playerTop.isWhite){
                                f.b.playerBot.cemetery.remove(f.b.history.get(f.b.cursorMoves-1).end.piece);
                            }
                            else{
                                f.b.playerTop.cemetery.remove(f.b.history.get(f.b.cursorMoves-1).end.piece);
                            }
                        }
                        else{
                            if(!f.b.playerTop.isWhite){
                                f.b.playerBot.cemetery.remove(f.b.history.get(f.b.cursorMoves-1).end.piece);
                            }
                            else{
                                f.b.playerTop.cemetery.remove(f.b.history.get(f.b.cursorMoves-1).end.piece);
                            }
                        }
                    }
                    if(f.b.history.get(f.b.cursorMoves-1).promoted != null){
		                f.panel.remove(f.b.history.get(f.b.cursorMoves-1).start.piece.image);
                        f.b.board[f.b.history.get(f.b.cursorMoves-1).start.position.x][f.b.history.get(f.b.cursorMoves-1).start.position.y].piece = new Pawn(f.b.history.get(f.b.cursorMoves-1).start.piece.isWhite);
                        f.panel.add(f.b.board[f.b.history.get(f.b.cursorMoves-1).start.position.x][f.b.history.get(f.b.cursorMoves-1).start.position.y].piece.image, Integer.valueOf(3));
                    }
                    f.b.cursorMoves --;
                    f.panel.displayPieces();
                }
            } 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Next move button
        right = new ImageIcon("Images/right.png");
        rightButton = new JLabel(right, SwingConstants.CENTER);
        rightButton.setBackground(CL_GUI);
        rightButton.setOpaque(true);
        rightButton.setForeground(CL_FONT);     
        rightButton.setBorder(new LineBorder(CL_GUI));  
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                f.panel.unselect();
                if(f.b.cursorMoves < f.b.history.size() && !f.panel.popUp) {
                    f.b.movePiece(f.b.history.get(f.b.cursorMoves).start, f.b.history.get(f.b.cursorMoves).end);
                    if(f.b.history.get(f.b.cursorMoves).promoted != null){
                        f.panel.setLayer(f.b.history.get(f.b.cursorMoves).end.piece.image,0);
		                f.panel.remove(f.b.history.get(f.b.cursorMoves).end.piece.image);//f.b.board[f.b.history.get(f.b.cursorMoves-1).start.position.x][f.b.history.get(f.b.cursorMoves-1).start.position.y].piece = new Pawn(f.b.history.get(f.b.cursorMoves-1).start.piece.isWhite);
                        f.b.history.get(f.b.cursorMoves).end.piece = f.b.history.get(f.b.cursorMoves).promoted;
                        f.panel.add(f.b.history.get(f.b.cursorMoves).end.piece.image, Integer.valueOf(3));
                    }
                    f.b.cursorMoves ++;
                    f.panel.displayPieces();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
        });   
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void updateButtons(Frame f){
        if(f.panel.popUp){
            newGame.setBackground(CL_GUI);
            inverse.setBackground(CL_GUI);
            concede.setBackground(CL_GUI);
            leftButton.setBackground(CL_GUI);
            rightButton.setBackground(CL_GUI);
        }
        else{
            newGame.setBackground(CL_ACTIVE);
            inverse.setBackground(CL_ACTIVE);
            concede.setBackground(CL_ACTIVE);
            if(reverse){
                inverse.setBorder(new LineBorder(CL_FONT));
            }
            else{
                inverse.setBorder(new LineBorder(CL_GUI));
            }
            if(f.b.cursorMoves == 0){
                leftButton.setBackground(CL_GUI);
            }
            else{
                leftButton.setBackground(CL_ACTIVE);
            }
            if(f.b.cursorMoves == f.b.nbMoves){
                rightButton.setBackground(CL_GUI);
            }
            else{
                rightButton.setBackground(CL_ACTIVE);
            }
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addNewGamePopUp(CustomPanel p){
        p.popUp = true;
        p.add(popupPanel, Integer.valueOf(5));
        popupPanel.setBounds(p.SQUARE_SIZE*19/8,p.SQUARE_SIZE*23/8, p.SQUARE_SIZE*26/8, p.SQUARE_SIZE*26/8);
        p.add(vsPlayer, Integer.valueOf(6));
        vsPlayer.setBounds(p.SQUARE_SIZE*21/8,p.SQUARE_SIZE*25/8, p.SQUARE_SIZE*11/8, p.SQUARE_SIZE*2/8);
        p.add(vsIA, Integer.valueOf(6));
        vsIA.setBounds(p.SQUARE_SIZE*32/8,p.SQUARE_SIZE*25/8, p.SQUARE_SIZE*11/8, p.SQUARE_SIZE*2/8);
        p.add(p1name, Integer.valueOf(6));
        p1name.setBounds(p.SQUARE_SIZE*5/2,p.SQUARE_SIZE*30/8, p.SQUARE_SIZE*18/8, p.SQUARE_SIZE*2/8);
        p.add(p2name, Integer.valueOf(6));
        p2name.setBounds(p.SQUARE_SIZE*5/2,p.SQUARE_SIZE*35/8, p.SQUARE_SIZE*18/8, p.SQUARE_SIZE*2/8);
        p.add(player1, Integer.valueOf(6));
        player1.setBounds(p.SQUARE_SIZE*39/16,p.SQUARE_SIZE*28/8, p.SQUARE_SIZE*25/8, p.SQUARE_SIZE*2/8);
        p.add(player2, Integer.valueOf(6));
        player2.setBounds(p.SQUARE_SIZE*39/16,p.SQUARE_SIZE*33/8, p.SQUARE_SIZE*25/8, p.SQUARE_SIZE*2/8);
        p.add(timer, Integer.valueOf(6));
        timer.setBounds(p.SQUARE_SIZE*39/16,p.SQUARE_SIZE*19/4, p.SQUARE_SIZE*25/8, p.SQUARE_SIZE*2/8);
        p.add(slider, Integer.valueOf(6));
        slider.setBounds(p.SQUARE_SIZE*39/16,p.SQUARE_SIZE*20/4, p.SQUARE_SIZE*25/8, p.SQUARE_SIZE*5/8);
        p.add(play, Integer.valueOf(6));
        play.setBounds(p.SQUARE_SIZE*47/16,p.SQUARE_SIZE*93/16, p.SQUARE_SIZE, p.SQUARE_SIZE*1/4);
        p.add(cancel, Integer.valueOf(6));
        cancel.setBounds(p.SQUARE_SIZE*65/16,p.SQUARE_SIZE*93/16, p.SQUARE_SIZE, p.SQUARE_SIZE*1/4);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void removeNewGamePopUp(CustomPanel p){
        p.popUp = false;
        p.remove(popupPanel);
        p.remove(vsPlayer);
        p.remove(vsIA);
        p.remove(p1name);
        p.remove(p2name);
        p.remove(player1);
        p.remove(player2);
        p.remove(timer);
        p.remove(slider);
        p.remove(play);
        p.remove(cancel);
        p.displayPieces();
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addEndGamePanel(CustomPanel p){
        p.popUp = true;
        p.add(popupPanel, Integer.valueOf(5));
        popupPanel.setBounds(p.SQUARE_SIZE*19/8,p.SQUARE_SIZE*27/8, p.SQUARE_SIZE*13/4, p.SQUARE_SIZE*5/4);
        p.add(endMessage, Integer.valueOf(6));
        endMessage.setBounds(p.SQUARE_SIZE*5/2,p.SQUARE_SIZE*7/2, p.SQUARE_SIZE*3, p.SQUARE_SIZE*1/2);
        p.add(endNewGame, Integer.valueOf(6));
        endNewGame.setBounds(p.SQUARE_SIZE*23/8,p.SQUARE_SIZE*17/4, p.SQUARE_SIZE*9/8, p.SQUARE_SIZE*1/4);
        p.add(endExit, Integer.valueOf(6));
        endExit.setBounds(p.SQUARE_SIZE*129/32,p.SQUARE_SIZE*17/4, p.SQUARE_SIZE*9/8, p.SQUARE_SIZE*1/4);
    }

    public void endGame(Player player, CustomPanel p){
        addEndGamePanel(p);
        if(player == null){
            endMessage.setText("Draw !");
        }
        else{
            endMessage.setText(player.name + " has won !");
        }
    }

    public void removeEndGamePanel(CustomPanel p){
        p.popUp = false;
        p.remove(popupPanel);
        p.remove(endMessage);
        p.remove(endNewGame);
        p.remove(endExit);
        p.displayPieces();
    }
}
