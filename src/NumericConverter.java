import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NumericConverter {
    private static int ConversionResult = 0;
    private static String hexString = "";

    public static void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // frame and layout
                JFrame frame = new JFrame("Numeric Converter");
                frame.setLayout(new GridLayout(3, 3));
                Listener listener = new Listener();// Must be included
                frame.addWindowListener(listener);// Must be included
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Must be included
                frame.setSize(600, 400);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);

                String[] inputOptions = { "Decimal", "Hex", "Binary" };
                JComboBox<String> inputType = new JComboBox<>(inputOptions);

                inputType.setSelectedIndex(0);

                JLabel inputLabel = new JLabel("Select an input type: ");
                JPanel panel = new JPanel(new BorderLayout());
                JPanel panel1 = new JPanel();
                panel1.setPreferredSize(new Dimension(200, 50)); // Set size for panel1
                panel1.add(inputLabel);
                panel1.add(inputType);

                panel.add(panel1, BorderLayout.WEST);

                frame.add(panel);

                inputType.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JComboBox<String> inputType = (JComboBox<String>) e.getSource();
                        String inputSelection = (String) inputType.getSelectedItem();
                        System.out.println("Selected option: " + inputSelection);
                    }
                });

                String[] outputOptions = { "Decimal", "Hex", "Binary" };
                JComboBox<String> outputType = new JComboBox<>(outputOptions);

                outputType.setSelectedIndex(0);
                JPanel panel2 = new JPanel();
                panel2.setPreferredSize(new Dimension(200, 50));
                JLabel outputLabel = new JLabel("Select an output type: ");
                panel2.add(outputLabel);
                panel2.add(outputType);
                panel.add(panel2, BorderLayout.EAST);

                outputType.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JComboBox<String> outputType = (JComboBox<String>) e.getSource();
                        String outputSelection = (String) outputType.getSelectedItem();
                        System.out.println("output:" + outputSelection);
                    }
                });
                JPanel panel3 = new JPanel();
                panel3.setPreferredSize(new Dimension(200, 50));
                JTextField input = new JTextField(10);
                JButton convertButton = new JButton("Convert");
                panel3.add(input);
                panel3.add(convertButton);
                frame.add(panel3);
                JLabel output = new JLabel("0");
                JPanel panel4 = new JPanel();
                panel4.add(output);
                frame.add(panel4);
                panel4.setPreferredSize(new Dimension(200, 50));
                convertButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int inputDecision = inputType.getSelectedIndex();
                        int outputDecision = outputType.getSelectedIndex();

                        if (inputDecision == 0) {
                            // dec to dec
                            if (outputDecision == 0) {
                                ConversionResult = Integer.parseInt(input.getText());
                            }
                            // dec to hex
                            if (outputDecision == 1) {
                                
                                ConversionResult = Integer.parseInt(input.getText(),16);
                            }
                            // dec to bin
                            if (outputDecision == 2) {
                                
                                ConversionResult = Integer.parseInt(input.getText(),2);

                            }

                        }
                        else if (inputDecision == 1) {
                            //hex to dec
                            if (outputDecision == 0) {
                                ConversionResult = Integer.parseInt(input.getText(),16);
                            }
                            // dec to hex
                            if (outputDecision == 1) {
                                
                                ConversionResult = Integer.parseInt(input.getText(),16);
                            }
                            // dec to bin
                            if (outputDecision == 2) {
                                
                                ConversionResult = Integer.parseInt(input.getText(),2);

                            }

                        }
                    output.setText(Integer.toString(ConversionResult));
                    }
                });



            }
        });
    }// end start

    public static int convertToDecimal(String input, int base) {
        int decimal = 0;
        int power = 0;
        
        // Loop through the input string from right to left
        for (int i = input.length() - 1; i >= 0; i--) {
            char digitChar = input.charAt(i);
            int digit;
            
            // Convert character to digit based on the base
            if (Character.isDigit(digitChar)) {
                digit = digitChar - '0';
            } else {
                digit = Character.toUpperCase(digitChar) - 'A' + 10;
            }
            
            // Multiply the digit by the base raised to the power of its position
            decimal += digit * Math.pow(base, power);
            power++;
        }
        
        return decimal;
    }
}// end class NumericConverter