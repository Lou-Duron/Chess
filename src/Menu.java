import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Menu {
    JLabel nameTop, nameBot, timerTop, timerBot, deadPiecesTop, deadPiecesBot;
    JLabel menu, newGame, option, exit, analyse, concede, leftButton, rightButton, promotionPanel;
    ImageIcon iconInv, iconFlag, left, right; 
    List<JLabel> promotionWhite, promotionBlack;
    Color CL_LN = new Color(150,125,100); // Numbers and letters color
    Color CL_GUI = new Color(26,33,41); // GUI background color
    Color CL_FONT = new Color(227,215,199); // Font color
    Color CL_BK = new Color(47,53,62); // Frame background color
    int tTop, tBot;
    Timer tT, tB;

    public Menu(Frame f){

         // Promotion POPUP
         promotionPanel = new JLabel();
         promotionPanel.setBackground(CL_GUI);
         promotionPanel.setOpaque(true); 
         promotionWhite = new ArrayList<>();
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
                     if(f.popUp){
                         switch(promotionWhite.indexOf(piece)){
                            case 0:
                            f.panel.replacePiecePromotion(new Knight(true), f.panel.promotion);
                            break;
                         case 1:
                            f.panel.replacePiecePromotion(new Bishop(true), f.panel.promotion);
                            break;
                         case 2:
                            f.panel.replacePiecePromotion(new Rook(true), f.panel.promotion);
                            break;
                         case 3:
                            f.panel.replacePiecePromotion(new Queen(true), f.panel.promotion);
                            break;
                         }
                     }
                 } 
                 @Override
                 public void mouseReleased(MouseEvent e) {}
             });
         }
 
         promotionBlack = new ArrayList<>();
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
                     if(f.popUp){
                         switch(promotionBlack.indexOf(piece)){
                             case 0:
                             f.panel.replacePiecePromotion(new Knight(false), f.panel.promotion);
                             break;
                         case 1:
                             f.panel.replacePiecePromotion(new Bishop(false), f.panel.promotion);
                             break;
                         case 2:
                             f.panel.replacePiecePromotion(new Rook(false), f.panel.promotion);
                             break;
                         case 3:
                             f.panel.replacePiecePromotion(new Queen(false), f.panel.promotion);
                             break;
                         }
                     }
                 } 
                 @Override
                 public void mouseReleased(MouseEvent e) {}
             });
         }

        // Timers
        timerTop = new JLabel("10:00", SwingConstants.CENTER);
        timerTop.setBackground(CL_GUI);
        timerTop.setOpaque(true);
        timerTop.setForeground(CL_FONT);
        ActionListener Top = new ActionListener(){
            public void actionPerformed(ActionEvent ae){            
                tTop --;
                timerTop.setText(String.valueOf((int) tTop/60) +":"+ String.valueOf(tTop % 60));    
            }
        };
        tT = new Timer(1000, Top);

        timerBot = new JLabel("10:00", SwingConstants.CENTER);
        timerBot.setBackground(CL_GUI);
        timerBot.setOpaque(true);
        timerBot.setForeground(CL_FONT);
        ActionListener Bot = new ActionListener(){
            public void actionPerformed(ActionEvent ae){            
                tBot --;    
                timerBot.setText(String.valueOf((int) tBot/60) +":"+ String.valueOf(tBot % 60)); 
            }
        };
        tB = new Timer(1000, Bot); 
 
        // Names
        nameTop = new JLabel(f.b.playerTop.name, SwingConstants.CENTER);
        nameTop.setBackground(CL_GUI);
        nameTop.setOpaque(true);
        nameTop.setForeground(CL_FONT);
        nameBot = new JLabel(f.b.playerBot.name, SwingConstants.CENTER);
        nameBot.setBackground(CL_GUI);
        nameBot.setOpaque(true);
        nameBot.setForeground(CL_FONT);
        

        // DeadPieces
        deadPiecesTop = new JLabel();
        deadPiecesTop.setBackground(CL_GUI);
        deadPiecesTop.setOpaque(true);
        deadPiecesBot = new JLabel();
        deadPiecesBot.setBackground(CL_GUI);
        deadPiecesBot.setOpaque(true);

        // New Game button
        newGame = new JLabel("New game", SwingConstants.CENTER);
        newGame.setBackground(CL_GUI);
        newGame.setOpaque(true);
        newGame.setForeground(CL_FONT);  
        newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                f.reset();
                f.b.initBoard();
                f.panel.initBoardGraphics();
            } 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        // Option button
        option = new JLabel("Option", SwingConstants.CENTER);
        option.setBackground(CL_GUI);
        option.setOpaque(true);
        option.setForeground(CL_FONT);       
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
        // Analyse button
        iconInv = new ImageIcon("Images/inverse.png");
        analyse = new JLabel(iconInv, SwingConstants.CENTER);
        analyse.setBackground(CL_GUI);
        analyse.setOpaque(true);
        analyse.setForeground(CL_FONT);
        analyse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                f.panel.reverseCoordinates();
                f.b.reverseBoard();
                f.b.reverseHistory();
                f.panel.initBoardGraphics();
                f.panel.resize();
            } 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        // Concede button
        iconFlag = new ImageIcon("Images/flag.png");
        concede = new JLabel(iconFlag, SwingConstants.CENTER);
        concede.setBackground(CL_GUI);
        concede.setOpaque(true);
        concede.setForeground(CL_FONT);        
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
               f.reset();
            }
        });
        // Last move button
        left = new ImageIcon("Images/left.png");
        leftButton = new JLabel(left, SwingConstants.CENTER);
        leftButton.setBackground(CL_GUI);
        leftButton.setOpaque(true);
        leftButton.setForeground(CL_FONT);        
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(f.b.history);
                if(f.b.cursorMoves > 0){
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
                    f.b.cursorMoves --;
                    f.panel.displayPieces();
                }
            } 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        // Next move button
        right = new ImageIcon("Images/right.png");
        rightButton = new JLabel(right, SwingConstants.CENTER);
        rightButton.setBackground(CL_GUI);
        rightButton.setOpaque(true);
        rightButton.setForeground(CL_FONT);       
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                if(f.b.cursorMoves < f.b.history.size()){
                    f.b.movePiece(f.b.history.get(f.b.cursorMoves).start, f.b.history.get(f.b.cursorMoves).end);
                    f.b.cursorMoves ++;
                    f.panel.displayPieces();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
        });   

    }
}
