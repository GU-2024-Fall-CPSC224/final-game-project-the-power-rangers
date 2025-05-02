package edu.gonzaga;
import java.util.*;
import java.util.function.Consumer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
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
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class Board implements ActionListener
{

    private int[][] Board = new int[3][3]; // Keep track of game logic
    private JButton[][] buttons = new JButton[3][3]; // Use images for the X's and O's
    // Board Icons & framing
    private JFrame frame;
    private ImageIcon xIcon, oIcon;
    private JLabel playerXLabel, playerOLabel;
    private boolean isXTurn = true;
    private JLabel currentTurnIcon = new JLabel();
    private Multiplayer multiplayer;
    private int p = 1;
    public Board(Multiplayer multiplayer){
        this.multiplayer = multiplayer;
    }
    

    public void createBoard() {
        // Load and scale icons
        try
        {
            Image xImage = new ImageIcon(getClass().getResource("/x.png")).getImage();
            xIcon = new ImageIcon(xImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
    
            Image oImage = new ImageIcon(getClass().getResource("/o.png")).getImage();
            oIcon = new ImageIcon(oImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        }
        catch (Exception e)
        {
            System.out.println("Could not load X and O images.");
        }
        currentTurnIcon.setIcon(xIcon);
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 750);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        JPanel verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.setBackground(new Color(245, 245, 245));

        JLabel titleLabel = new JLabel("Tic Tac Toe");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel turnIndicatorPanel = new JPanel();
        turnIndicatorPanel.setLayout(new BoxLayout(turnIndicatorPanel, BoxLayout.X_AXIS));
        turnIndicatorPanel.setBackground(new Color(245, 245, 245));
        turnIndicatorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        playerXLabel = new JLabel("PLAYER X");
        playerXLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        playerXLabel.setOpaque(true);
        playerXLabel.setBackground(new Color(255, 200, 200));
        playerXLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        playerOLabel = new JLabel("PLAYER O");
        playerOLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        playerOLabel.setOpaque(true);
        playerOLabel.setBackground(new Color(230, 230, 230));
        playerOLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        turnIndicatorPanel.add(playerXLabel);
        turnIndicatorPanel.add(Box.createHorizontalStrut(20));
        turnIndicatorPanel.add(playerOLabel);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 12, 12));
        gridPanel.setPreferredSize(new Dimension(400, 400));
        gridPanel.setMaximumSize(new Dimension(400, 400));
        gridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gridPanel.setBackground(new Color(245, 245, 245));
        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // frame.setResizable(true);
        // frame.setLayout(new BorderLayout());


    
        // // ==== Game Grid Panel ====
        // JPanel gridPanel = new JPanel(new GridLayout(3, 3, 10, 10)); // spacing between buttons
        // gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // margin around grid
        // buttons = new JButton[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                button.setFocusPainted(false);
                button.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));
                button.addActionListener(this);
                buttons[row][col] = button;
                gridPanel.add(button);
            }
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));
    
        JButton newGameBtn = new JButton("New Game");
        JButton mainMenuBtn = new JButton("Main Menu");
        Font uiFont = new Font("SansSerif", Font.BOLD, 20);
        newGameBtn.setFont(uiFont);
        mainMenuBtn.setFont(uiFont);

        newGameBtn.setPreferredSize(new Dimension(140, 40));
        mainMenuBtn.setPreferredSize(new Dimension(140, 40));

        newGameBtn.addActionListener(e -> resetBoard());
    
        // newGameBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        // mainMenuBtn.setFont(new Font("Arial", Font.PLAIN, 18));
    
        buttonPanel.add(newGameBtn);
        buttonPanel.add(mainMenuBtn);

        verticalPanel.add(Box.createVerticalStrut(20));
        verticalPanel.add(titleLabel);
        verticalPanel.add(Box.createVerticalStrut(15));
        verticalPanel.add(turnIndicatorPanel);
        verticalPanel.add(Box.createVerticalStrut(20));
        verticalPanel.add(gridPanel);
        verticalPanel.add(Box.createVerticalStrut(20));
        verticalPanel.add(buttonPanel);

        frame.add(verticalPanel, BorderLayout.CENTER);
    
        // ==== Assemble Layout ====
        // frame.add(gridPanel, BorderLayout.CENTER);
        // frame.add(turnPanel, BorderLayout.EAST);
        // frame.add(buttonPanel, BorderLayout.SOUTH);
    
        frame.setVisible(true);
    }

    private void resetBoard() {
        isXTurn = true;
        updateTurnHighlight();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setIcon(null);
                buttons[row][col].setEnabled(true);
            }
        }
    }
    
    private void updateTurnHighlight() {
        if (isXTurn) {
            playerXLabel.setBackground(new Color(255, 200, 200));
            playerOLabel.setBackground(new Color(230, 230, 230));
        } else {
            playerXLabel.setBackground(new Color(230, 230, 230));
            playerOLabel.setBackground(new Color(200, 200, 255));
        }
    }

    public void updateMove(int row, int col)
    {
        String name = "";
        int result = 0;

        if(currentTurnIcon.getIcon() == xIcon){
            currentTurnIcon.setIcon(oIcon);
            System.out.println("Flag 3  Xicon " + p);
        }
        else{
            currentTurnIcon.setIcon(xIcon);
            System.out.println("Flag 4 oicon " + p);
        }

        result = multiplayer.updateMove(col, row, p);
        System.out.println(p);
        
        if(result == 1 || result == 3){
            if(p == 1){
                name = multiplayer.GetPlayer().getName();
            }
            else{
                System.out.println("Flag 17");
                name = multiplayer.GetOpponent().getName();
            }
        }
        
        System.out.println("Flag 6   The return result = "+ result);
        switch(result){
            case 0:
                System.out.println("Game continues");
                if(p == 1){
                    p = 2;
                }
                else{
                    p = 1;
                }
                break;
            case 1:
                System.out.println(name + " Wins!");
                clearBoard();
                break;
            case 2:
                System.out.println("It's a tie!");
                clearBoard();
                break;
                case 3:
                System.out.println("Final Winner: " + name);
                clearBoard();
                break;
            case 4:
                System.out.println("Final round tie!");
                clearBoard();
                break;
            default:
                System.out.println("error");
        }
        
        
        
    }

    public void clearBoard(){
        for(int row = 0; row < buttons.length; row++){
            for(int col = 0; col < buttons[0].length; col++){
                buttons[row][col].setIcon(null);
            }
        }
        currentTurnIcon.setIcon(xIcon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // for(int row = 0; row < 3; row++){
        //     for(int col = 0; col < 3; col++){
        //         if(e.getSource() == buttons[row][col] && buttons[row][col].getIcon() == null){
        //             buttons[row][col].setIcon(currentTurnIcon.getIcon());
        //             updateMove(row, col);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (e.getSource() == buttons[row][col] && buttons[row][col].getIcon() == null) {
                    buttons[row][col].setIcon(isXTurn ? xIcon : oIcon);
                    updateTurnHighlight();
                    updateMove(row, col);
                    isXTurn = !isXTurn;
                } 
            }
        }
    }










}
