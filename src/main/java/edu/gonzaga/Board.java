package edu.gonzaga;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board {

    private int[][] Board = new int[3][3]; // Keep track of game logic
    private JButton[][] buttons = new JButton[3][3]; // Use images for the X's and O's
    // Board Icons & framing
    private JFrame frame;
    private ImageIcon xIcon;
    private ImageIcon oIcon;



    public void createBoard() {
    // Load and scale icons
    xIcon = new ImageIcon(new ImageIcon("resources/x.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
    oIcon = new ImageIcon(new ImageIcon("resources/o.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));

    frame = new JFrame("Tic Tac Toe");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    // ==== Game Grid Panel ====
    JPanel gridPanel = new JPanel(new GridLayout(3, 3));
    buttons = new JButton[3][3];
    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(100, 100));
            button.setFocusPainted(false);
            buttons[row][col] = button;
            gridPanel.add(button);
        }
    }

    // ==== Turn Indicator Panel ====
    JPanel turnPanel = new JPanel();
    turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.Y_AXIS));
    JLabel turnLabel = new JLabel("Turn");
    turnLabel.setFont(new Font("Arial", Font.BOLD, 20));
    turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    currentTurnIcon = new JLabel(xIcon); // default to X
    currentTurnIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
    turnPanel.add(Box.createVerticalStrut(20));
    turnPanel.add(turnLabel);
    turnPanel.add(Box.createVerticalStrut(10));
    turnPanel.add(currentTurnIcon);

    // ==== Bottom Button Panel ====
    JPanel buttonPanel = new JPanel(new FlowLayout());
    JButton newGameBtn = new JButton("New Game");
    JButton mainMenuBtn = new JButton("Main Menu");

    newGameBtn.addActionListener(e -> resetBoard());
    mainMenuBtn.addActionListener(e -> {
        // logic for going back to main menu
        System.out.println("Main menu button pressed.");
    });

    buttonPanel.add(newGameBtn);
    buttonPanel.add(mainMenuBtn);

    // ==== Assemble Layout ====
    frame.add(gridPanel, BorderLayout.CENTER);
    frame.add(turnPanel, BorderLayout.EAST);
    frame.add(buttonPanel, BorderLayout.SOUTH);

    frame.pack();
    frame.setVisible(true);
}

    public static void updateMove(){

    }

    public static void getWinner(){

    }

    public static void checkBoard(){

    }










}
