package edu.gonzaga;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board implements ActionListener
{

    private int[][] Board = new int[3][3]; // Keep track of game logic
    private JButton[][] buttons = new JButton[3][3]; // Use images for the X's and O's
    // Board Icons & framing
    private JFrame frame;
    private ImageIcon xIcon;
    private ImageIcon oIcon;
    private JLabel currentTurnIcon;

    public void createBoard()
    {
        // Load and scale icons
        try
        {
            Image xImage = new ImageIcon(getClass().getResource("/x.png")).getImage();
            xIcon = new ImageIcon(xImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
    
            Image oImage = new ImageIcon(getClass().getResource("/o.png")).getImage();
            oIcon = new ImageIcon(oImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // ✅ Start in full screen
        frame.setResizable(true);                      // ✅ Allow manual resize
        frame.setLayout(new BorderLayout());
    
        // ==== Game Grid Panel ====
        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 10, 10)); // spacing between buttons
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // margin around grid
        buttons = new JButton[3][3];
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                JButton button = new JButton();
                button.addActionListener(this);
                button.setFocusPainted(false);
                button.setContentAreaFilled(true);
                button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                button.setFont(new Font("Arial", Font.BOLD, 60)); // for text fallback or larger icons
                buttons[row][col] = button;
                gridPanel.add(button);
            }
        }
    
        // ==== Turn Indicator Panel ====
        JPanel turnPanel = new JPanel();
        turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.Y_AXIS));
        turnPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
        turnPanel.setPreferredSize(new Dimension(200, 0)); // fixed width, auto height
    
        JLabel turnLabel = new JLabel("Turn");
        turnLabel.setFont(new Font("Arial", Font.BOLD, 24));
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        currentTurnIcon = new JLabel(xIcon);
        currentTurnIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        turnPanel.add(Box.createVerticalGlue());
        turnPanel.add(turnLabel);
        turnPanel.add(Box.createVerticalStrut(20));
        turnPanel.add(currentTurnIcon);
        turnPanel.add(Box.createVerticalGlue());
    
        // ==== Bottom Button Panel ====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        JButton newGameBtn = new JButton("New Game");
        JButton mainMenuBtn = new JButton("Main Menu");
    
        newGameBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        mainMenuBtn.setFont(new Font("Arial", Font.PLAIN, 18));
    
        buttonPanel.add(newGameBtn);
        buttonPanel.add(mainMenuBtn);
    
        // ==== Assemble Layout ====
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(turnPanel, BorderLayout.EAST);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    
        frame.setVisible(true);
    }

    public void updateMove(Multiplayer multiplayer)
    {
        if(currentTurnIcon.getIcon() == xIcon){
            // multiplayer.playTurn(0);
            currentTurnIcon.setIcon(oIcon);
            
        }
        else{
            // multiplayer.playTurn(1);
            currentTurnIcon.setIcon(xIcon);
        }
    }

    public static void getWinner()
    {

    }

    public static void checkBoard()
    {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if(e.getSource() == buttons[row][col] && buttons[row][col].getIcon() == null){
                    buttons[row][col].setIcon(currentTurnIcon.getIcon());
                    updateMove(null);
                } 
            }
        }
       
    }










}
