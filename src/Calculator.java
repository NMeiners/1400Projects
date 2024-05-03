//imports
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Calculator {
    private static JFrame frame;
    private static JTextField display;
    private static double num1 = 0;
    private static double num2 = 0;
    private static char operator;
    private static boolean num2Entered = false;
    private static boolean isOperatorClicked = false;
    private static Timer timer;

    public static void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //variables
                timer = new Timer(0, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        try {Thread.sleep(2000);} catch (InterruptedException e) {}//cause a 2 second delay
                    }
                });
                timer.setRepeats(false); // Only fire the timer once

                //frame and layout
                frame = new JFrame("Calculator");
                Listener listener = new Listener();
                frame.addWindowListener(listener);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                display = new JTextField();
                display.setEditable(false);
                frame.add(display, BorderLayout.NORTH);
                JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
                frame.add(buttonPanel, BorderLayout.CENTER);
                String[] buttonLabels = {"7", "8", "9", "/",
                                         "4", "5", "6", "*",
                                         "1", "2", "3", "-",
                                         "0", ".", "=", "+"};
                for (String label : buttonLabels) {
                    JButton button = new JButton(label);
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String buttonText = button.getText();
                            handleButtonInput(buttonText);
                        }
                    });
                    buttonPanel.add(button);
                }
                //reset button
                JButton clear = new JButton("Clear Entry");
                clear.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        num1 = 0;
                        num2 = 0;
                        display.setText("");
                    }
                });
                frame.add(clear, BorderLayout.SOUTH);
                frame.setSize(600, 400);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }

    public static void reset(){
        num1 = 0;
        num2 = 0;
        display.setText("");
    }

    public static void handleButtonInput(String buttonText) {
        if (buttonText.matches("[0-9]")) {
            display.setText(display.getText() + buttonText);
            isOperatorClicked = false;
        } else if (buttonText.matches("[/+*-]")) {
            if (!isOperatorClicked) {
                if (!num2Entered){
                    if (display.getText() == ""){
                        
                    }
                    num1 = Double.parseDouble(display.getText());
                } else {
                    num1 = calculateResult(num1, num2, operator);
                }
                operator = buttonText.charAt(0);
                display.setText("");
                isOperatorClicked = true;
            } else {
                String temp = display.getText();
                display.setText("Invalid operator. Enter a number.");
                timer.start();
                display.setText(temp);
            }
        } else if (buttonText.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + buttonText);
            }
        } else if (buttonText.equals("=")) {
            if (isOperatorClicked) {
                num2 = Double.parseDouble(display.getText());
                double result = calculateResult(num1, num2, operator);
                display.setText(Double.toString(result));
                num2Entered = false;
                isOperatorClicked = false;
            }
        }
    }

    public static double calculateResult(double num1, double num2, char operator) {
        double result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    display.setText("Error: Division by zero");
                    isOperatorClicked = false;
                }
                break;
        }
        return result;
    }
}//end class calculator
