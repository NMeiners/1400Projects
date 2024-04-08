import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Calculator {
    public static void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // frame and layout
                JFrame frame = new JFrame("Calculator");
                Listener listener = new Listener();// Must be included
                frame.addWindowListener(listener);// Must be included
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Must be included
                JTextField display = new JTextField();
                display.setEditable(false);
                frame.add(display, BorderLayout.NORTH);
                JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
                frame.add(buttonPanel, BorderLayout.CENTER);
                String [] buttonLabels = {"7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"};
                for(String label: buttonLabels){
                    JButton button = new JButton(label);
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            String buttonText = button.getText();

                            String currentText = display.getText();
                            currentText += buttonText;
                            
                            display.setText(currentText);
                        }
                    });
                    buttonPanel.add(button);
                }

                frame.setSize(600, 400);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }// end start
    public static void main(String[] args){
        start();
    }
}// end Class Calculator
