import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame {

    JFrame frame;
    CustomPanel panel;
    Board b;
    boolean resized = false;
    IA ia;
    boolean IA;

    public Frame() {
        // Frame setup
        frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 720);
        frame.setLocationRelativeTo(null);
        ImageIcon logo = new ImageIcon("Images/logo.png");
        frame.setIconImage(logo.getImage());
        frame.setMinimumSize(new Dimension(450,450));
        // Panel setup
        panel = new CustomPanel(this);
        panel.setBackground(panel.menu.CL_BK);
        panel.setOpaque(true);
		frame.add(panel);
        addListeners(); 

        newGame("Player 1", "Player 2", 600, false);
        frame.setVisible(true);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void newGame(String topName, String botName, int time, boolean IA){
        if(b != null){
            panel.removeGraphics();
        }
        b = new Board(topName, botName);
        b.initBoard();
        panel.setCoordinates();
        panel.addGraphics();
        panel.menu.nameTop.setText(topName);
		panel.menu.nameBot.setText(botName);
        panel.initTimer(time);
        panel.menu.reverse = false;
        if(IA){
            ia = new IA(b.playerTop, this);
            this.IA = true;
            if(b.playerTop.isWhite){
                ia.makeMove();
            }
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addListeners(){
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                panel.resize();
                resized = false;
            }
        });
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

