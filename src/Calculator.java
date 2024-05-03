import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Calculator {
    private static JFrame frame;
    private static JTextField display;
    private static double num1 = 0;
    private static double num2 = 0;
    private static char operator;
    private static boolean isNewNumber = true;
    private static boolean isOperatorClicked = false;

    public static void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
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
                        public void actionPerformed(ActionEvent e) {
                            String buttonText = button.getText();
                            handleButtonInput(buttonText);
                        }
                    });
                    buttonPanel.add(button);
                }
                frame.setSize(600, 400);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }

    public static void handleButtonInput(String buttonText) {
        if (isNewNumber) {
            display.setText("");
            isNewNumber = false;
        }

        if (buttonText.matches("[0-9]")) {
            display.setText(display.getText() + buttonText);
        } else if (buttonText.matches("[/+*-]")) {
            if (isOperatorClicked) {
                display.setText("Error");
                isNewNumber = true;
                isOperatorClicked = false;
            } else {
                num1 = Double.parseDouble(display.getText());
                operator = buttonText.charAt(0);
                isNewNumber = true;
                isOperatorClicked = true;
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
                isNewNumber = true;
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
                if (num2!= 0) {
                    result = num1 / num2;
                } else {
                    display.setText("Error: Division by zero");
                    isNewNumber = true;
                    isOperatorClicked = false;
                }
                break;
        }
        return result;

    }

    public static void main(String[] args) {
        start();
    }
}