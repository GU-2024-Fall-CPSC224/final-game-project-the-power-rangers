package edu.gonzaga;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.border.Border;


public class Menu implements ActionListener{
    private JFrame frame;
    private JTextField textField;
    private JButton[] numButtons;
    private JPanel mainPanel;
    private JComboBox<String> playerCountComboBox;
    private ArrayList<JTextField> playerNameFields = new ArrayList<>();
    private JButton submitButton;
    private JPanel nameInputPanel;
    private int playerCount = 2;
    private ArrayList<String> playerNames = new ArrayList<>();
    public boolean sleep = true;

    Font myFont = new Font("Arial", Font.PLAIN, 30);

    public void optionScreen(Consumer<ArrayList<String>> onSubmit) {
        
        frame = new JFrame();
        frame.setTitle("Player Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titleLabel = new JLabel("Select Number of Players", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JLabel selectionLabel = new JLabel("Number of players:");
        selectionLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        playerCountComboBox = new JComboBox<>(new String[]{"2", "4", "16"});
        playerCountComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        selectionPanel.add(selectionLabel);
        selectionPanel.add(playerCountComboBox);
        mainPanel.add(selectionPanel, BorderLayout.WEST);

 
        nameInputPanel = new JPanel();
        nameInputPanel.setLayout(new BoxLayout(nameInputPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(nameInputPanel);
        scrollPane.setPreferredSize(new Dimension(600, 500));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Enter Player Names"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        submitButton.addActionListener(e -> {
            handleSubmit();
            onSubmit.accept(this.playerNames);
            frame.dispose();
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        updatePlayerInputs();

        playerCountComboBox.addActionListener(this);
        frame.setVisible(true);
    }

    private void handleSubmit() {
        for (JTextField field : playerNameFields) {
            String name = field.getText().trim();
            System.out.println(name);
            if (name.isEmpty()) {
                name = "Player " + (playerNames.size() + 1);
            }
            playerNames.add(name);
        }        
    }

    private void updatePlayerInputs() {
        nameInputPanel.removeAll();
        playerNameFields.clear();
        for (int i = 1; i <= playerCount; i++) {
            JLabel label = new JLabel("Player " + i + " Name:");
            label.setFont(new Font("Arial", Font.PLAIN, 18));
        
            JTextField textField = new JTextField(30);
            textField.setFont(new Font("Arial", Font.PLAIN, 16));

            nameInputPanel.add(label);
            nameInputPanel.add(textField);
            playerNameFields.add(textField);
        }
        nameInputPanel.revalidate();
        nameInputPanel.repaint();
    }
    public int getPlayerCount(){
        return this.playerCount;
    }
    public ArrayList<String> getPlayerNames(){
        return this.playerNames;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playerCountComboBox){
            playerCount = Integer.parseInt((String) playerCountComboBox.getSelectedItem());
            updatePlayerInputs();
        }
        
    }
    
}
