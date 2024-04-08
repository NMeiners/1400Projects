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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NumericConverter {
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
                int ConversionResult = 0;
                String hexString = "";
                convertButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int userInput = Integer.parseInt(input.getText());
                        int inputDecision = inputType.getSelectedIndex();
                        int outputDecision = outputType.getSelectedIndex();

                        if (inputDecision == 0) {
                            // dec to dec
                            if (outputDecision == 0) {
                                ConversionResult = userInput;
                            }
                            // dec to bin
                            if (outputDecision == 1) {

                                hexString = Integer.toHexString(userInput);
                            }

                        }

                    }
                });

                JPanel panel4 = new JPanel();
                panel4.setPreferredSize(new Dimension(200, 50));
                if (hexString != "") {
                    JLabel output = new JLabel(hexString);
                    panel4.add(output);
                    frame.add(panel4);
                } else {
                    JLabel output = new JLabel(String.valueOf(ConversionResult));
                    panel4.add(output);
                    frame.add(panel4);
                }

            }
        });
    }// end start
}// end class NumericConverter
