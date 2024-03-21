//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Library{
    public static volatile boolean open;//must be set to false after a window is closed;
    public static void main (String[] args){
        try{//main try block
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    //frame and layout
                    JFrame frame = new JFrame("Discord Mods Projects");
                    GridLayout grid = new GridLayout(0, 2);

                    //buttons
                    JButton conv = new JButton("Numeric Converter");
                    JButton calc = new JButton("Basic Calculator");
                    JButton bitOps = new JButton("Bitwise Operations");
                    JButton binAdd = new JButton("Binary Adder");
                    JButton fcQuiz = new JButton("CPU Components Quiz");
                    JButton traffic = new JButton("Traffic Light Controller");

                    //frame parameters
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(500, 300);
                    frame.setLayout(grid);
                    //button action commands
                    conv.setActionCommand("conv");
                    calc.setActionCommand("calc");
                    bitOps.setActionCommand("bitOps");
                    binAdd.setActionCommand("binAdd");
                    fcQuiz.setActionCommand("fcQuiz");
                    traffic.setActionCommand("traffic");
                    //button action listeners
                    addListener(frame, conv);
                    addListener(frame, calc);
                    addListener(frame, bitOps);
                    addListener(frame, binAdd);
                    addListener(frame, fcQuiz);
                    addListener(frame, traffic);

                    //frame contents
                    frame.getContentPane().add(conv);
                    frame.getContentPane().add(calc);
                    frame.getContentPane().add(bitOps);
                    frame.getContentPane().add(binAdd);
                    frame.getContentPane().add(fcQuiz);
                    frame.getContentPane().add(traffic);

                    //open frame
                    frame.setVisible(true);
                }//end run
            });//end invokeLater
        } catch (Exception e){
            e.printStackTrace();
        }//end main catch block
    }//end main
    private static void addListener (JFrame frame, JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //hide Library window
                frame.setVisible(false);
                open = true;
                //open clicked project
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
                //on close, show library window
                frame.setVisible(true);
            }
        });
    }//end addListener
}//end class Library
