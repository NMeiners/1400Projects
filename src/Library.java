
//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Library {
    public static JFrame f;// library frame

    public static void main(String[] args) {
        try {// main try block
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // frame and layout
                    f = new JFrame("Discord Mods Projects");
                    GridLayout grid = new GridLayout(0, 2);

                    // buttons
                    JButton conv = new JButton("Numeric Converter");
                    JButton calc = new JButton("Basic Calculator");
                    JButton bitOps = new JButton("Bitwise Operations");
                    JButton binAdd = new JButton("Binary Adder");
                    JButton fcQuiz = new JButton("CPU Components Quiz");
                    JButton traffic = new JButton("Traffic Light Controller");

                    // frame parameters
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    f.setSize(500, 300);
                    f.setLayout(grid);
                    f.setLocationRelativeTo(null);
                    // testing
                    // test2
                    // button action commands
                    conv.setActionCommand("conv");
                    calc.setActionCommand("calc");
                    bitOps.setActionCommand("bitOps");
                    binAdd.setActionCommand("binAdd");
                    fcQuiz.setActionCommand("fcQuiz");
                    traffic.setActionCommand("traffic");
                    // button action listeners
                    addListener(f, conv);
                    addListener(f, calc);
                    addListener(f, bitOps);
                    addListener(f, binAdd);
                    addListener(f, fcQuiz);
                    addListener(f, traffic);
                    // frame contents
                    f.getContentPane().add(conv);
                    f.getContentPane().add(calc);
                    f.getContentPane().add(bitOps);
                    f.getContentPane().add(binAdd);
                    f.getContentPane().add(fcQuiz);
                    f.getContentPane().add(traffic);
                    // open frame
                    f.setVisible(true);
                }// end run
            });// end invokeLater
        } catch (Exception e) {
            e.printStackTrace();
        } // end main catch block
    }// end main

    private static void addListener(JFrame frame, JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // hide Library window
                frame.setVisible(false);
                // open clicked project
                switch (event.getActionCommand()) {
                    case "conv":
                        NumericConverter.start();
                        break;
                    case "calc":
                        Calculator.start();
                        break;
                    case "bitOp":
                        BitwiseOps.start();
                        break;
                    case "binAdd":
                        BinaryAdder.start();
                        break;
                    case "fcQuiz":
                        FlashcardQuiz.start();
                        break;
                    case "traffic":
                        TrafficLight.start();
                        break;
                }
            }
        });
    }// end addListener
}// end class Library

class Listener extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        // shows the Library window when a project closes
        Library.f.setVisible(true);
    }
}
