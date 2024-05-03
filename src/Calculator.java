//imports
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator {
    private static JFrame frame;
    private static JTextField display;
    private static double answer;
    private static boolean firstPress;

    public static void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //initialize answer and firstPress;
                answer = 0;
                firstPress = true;
                //action listener
                ActionListener action = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clicked = (JButton) e.getSource();
                        handleButtonInput(clicked.getText());
                    }
                };
                //frame and layout
                frame = new JFrame("Calculator");
                frame.setSize(400, 600);
                frame.setLayout(new BorderLayout());
                Listener listener = new Listener();
                frame.addWindowListener(listener);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                //output display
                display = new JTextField();
                display.setPreferredSize(new Dimension(frame.getWidth(), 60));
                display.setFont(new Font("Arial", Font.PLAIN, 32));
                display.setEditable(false);
                frame.add(display, BorderLayout.NORTH);
                JPanel buttonPanel = new JPanel(new GridLayout(0, 4));
                frame.add(buttonPanel, BorderLayout.CENTER);
                String[] buttonLabels = {"(", ")", "Clear", "Back",
                                         "7", "8", "9", "/",
                                         "4", "5", "6", "*",
                                         "1", "2", "3", "-",
                                         "0", ".", "=", "+"};
                for (String label : buttonLabels) {
                    JButton button = new JButton(label);
                    button.addActionListener(action);
                    buttonPanel.add(button);
                }
                //show frame
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }//end start

    public static void handleButtonInput(String buttonText) {
        switch (buttonText){
            case "Clear" :
                display.setText("");
                break;
            case "Back" :
                display.setText(display.getText().substring(0, (display.getText().length()-1)));
                break;
            case "=" :
                try{
                    firstPress = true;
                    answer = evaluate(display.getText());
                    display.setText(Double.toString(answer));
                }catch (Exception e){
                    display.setText("Error");
                }
                break;
            default :
                if(firstPress){
                    firstPress = false;
                    if (display.getText().contains("[E]") || isOperand(buttonText.charAt(0))){
                        display.setText("");
                    }
                }
                //handles parentheses multiplication
                if (buttonText == "(" && isOperand(display.getText().charAt(display.getText().length()-1)))
                    display.setText(display.getText() + "*");
                if (display.getText().length() > 0){
                    if (display.getText().charAt(display.getText().length()-1) == ')' && isOperand(buttonText.charAt(0)))
                        display.setText(display.getText() + "*");
                }
                display.setText(display.getText() + buttonText);
                break;
        }
    }//end handleButtonInput

    public static double evaluate(String expression) {
        String postfix = infixToPostfix(expression);
        return evaluatePostfix(postfix);
    }

    private static String infixToPostfix(String expression) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (isOperand(c)) {
                // If it's a digit or decimal point, add it to the postfix expression directly
                postfix.append(c);
                if (i < (expression.length() - 1) && !isOperand(expression.charAt(i + 1)))
                    postfix.append(" ");
            } else if (isOperator(c)) {
                // If it's an operator, add it to the postfix expression
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    postfix.append(" ");
                    postfix.append(stack.pop());
                    postfix.append(" ");
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(" ");
            postfix.append(stack.pop());
            postfix.append(" ");
        }

        return postfix.toString();
    }

    private static double evaluatePostfix(String postfix) {
        Stack<Double> evalStack = new Stack<>();

        for (String token : postfix.trim().split("\\s+")) {
            if (isOperand(token.charAt(0))) {
                evalStack.push(Double.parseDouble(token));
            } else if (isOperator(token.charAt(0))) {
                double operand2 = evalStack.pop();
                double operand1 = evalStack.pop();
                double result = performOperation(token.charAt(0), operand1, operand2);
                evalStack.push(result);
            }
        }

        return evalStack.pop();
    }

    private static boolean isOperand(char c) {
        return Character.isDigit(c) || c == '.';
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private static double performOperation(char op, double operand1, double operand2) {
        switch (op) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    throw new ArithmeticException("Division by zero");
                }
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}//end class calculator
