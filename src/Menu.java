import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu {
    JLabel name1, name2, timer1, timer2, deadPieces1, deadPieces2;
    JLabel menu, newGame, option, exit, analyse, concede, leftButton, rightButton;
    Color CL_LN = new Color(150,125,100); // Numbers and letters color
    Color CL_GUI = new Color(26,33,41); // GUI background color
    Color CL_FONT = new Color(227,215,199); // Font color
    Color CL_BK = new Color(47,53,62); // Frame background color
    Board b;

    public Menu(Frame f){
        b = f.b;
        
        // Timers
        timer1 = new JLabel("04:57", SwingConstants.CENTER);
        timer1.setBackground(CL_GUI);
        timer1.setOpaque(true);
        timer1.setForeground(CL_FONT);
        timer2 = new JLabel("04:24", SwingConstants.CENTER);
        timer2.setBackground(CL_GUI);
        timer2.setOpaque(true);
        timer2.setForeground(CL_FONT);
 
        // Names
        name1 = new JLabel(b.playerB.name, SwingConstants.CENTER);
        name1.setBackground(CL_GUI);
        name1.setOpaque(true);
        name1.setForeground(CL_FONT);
        name2 = new JLabel(b.playerW.name, SwingConstants.CENTER);
        name2.setBackground(CL_GUI);
        name2.setOpaque(true);
        name2.setForeground(CL_FONT);
        

        // DeadPieces
        deadPieces1 = new JLabel();
        deadPieces1.setBackground(CL_GUI);
        deadPieces1.setOpaque(true);
        deadPieces2 = new JLabel();
        deadPieces2.setBackground(CL_GUI);
        deadPieces2.setOpaque(true);

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
        analyse = new JLabel("A", SwingConstants.CENTER);
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
            public void mousePressed(MouseEvent e) {} 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        // Concede button
        concede = new JLabel("C", SwingConstants.CENTER);
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
        leftButton = new JLabel("Left", SwingConstants.CENTER);
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
            public void mousePressed(MouseEvent e) {} 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        // Next move button
        rightButton = new JLabel("Right", SwingConstants.CENTER);
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
            public void mousePressed(MouseEvent e) {} 
            @Override
            public void mouseReleased(MouseEvent e) {}
        });   

    }
}
