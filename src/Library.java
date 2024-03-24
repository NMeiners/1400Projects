
//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Library {
    private static JFrame frame;// library frame

    public static void main(String[] args) {
        try {// main try block
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // frame and layout
                    frame = new JFrame("Discord Mods Projects");
                    GridLayout grid = new GridLayout(0, 2);

                    // buttons
                    JButton conv = new JButton("Numeric Converter");
                    JButton calc = new JButton("Basic Calculator");
                    JButton bitOps = new JButton("Bitwise Operations");
                    JButton binAdd = new JButton("Binary Adder");
                    JButton fcQuiz = new JButton("CPU Components Quiz");
                    JButton traffic = new JButton("Traffic Light Controller");

                    //functions
                    ActionListener action = new ActionListener() {
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
                    };

                    // frame parameters
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(500, 300);
                    frame.setLayout(grid);
                    frame.setLocationRelativeTo(null);
                    
                    
                    // button action commands
                    conv.setActionCommand("conv");
                    calc.setActionCommand("calc");
                    bitOps.setActionCommand("bitOps");
                    binAdd.setActionCommand("binAdd");
                    fcQuiz.setActionCommand("fcQuiz");
                    traffic.setActionCommand("traffic");
                    // adding actionListeners to all buttons
                    conv.addActionListener(action);
                    calc.addActionListener(action);
                    bitOps.addActionListener(action);
                    binAdd.addActionListener(action);
                    fcQuiz.addActionListener(action);
                    traffic.addActionListener(action);
                    // frame contents
                    frame.getContentPane().add(conv);
                    frame.getContentPane().add(calc);
                    frame.getContentPane().add(bitOps);
                    frame.getContentPane().add(binAdd);
                    frame.getContentPane().add(fcQuiz);
                    frame.getContentPane().add(traffic);
                    // open frame
                    frame.setVisible(true);
                }// end run
            });// end invokeLater
        } catch (Exception e) {
            e.printStackTrace();
        } // end main catch block
    }// end main
    public static void projClosing(){
        Library.frame.setVisible(true);
    }
}// end class Library

class Listener extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        // shows the Library window when a project closes
        Library.projClosing();
    }
}
