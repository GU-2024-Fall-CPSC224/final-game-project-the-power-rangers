package edu.gonzaga;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board implements ActionListener {

    private JButton[][] buttons = new JButton[3][3];
    private JFrame frame;
    private ImageIcon xIcon, oIcon;
    private JLabel playerXLabel, playerOLabel;
    private boolean isXTurn = true;

    public void createBoard() {
        try {
            Image xImage = new ImageIcon(getClass().getResource("/x.png")).getImage();
            xIcon = new ImageIcon(xImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
            Image oImage = new ImageIcon(getClass().getResource("/o.png")).getImage();
            oIcon = new ImageIcon(oImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            System.out.println("Could not load X and O images.");
        }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (e.getSource() == buttons[row][col] && buttons[row][col].getIcon() == null) {
                    buttons[row][col].setIcon(isXTurn ? xIcon : oIcon);
                    isXTurn = !isXTurn;
                    updateTurnHighlight();
                }
            }
        }
    }
}