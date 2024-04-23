
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
                    frame.setLayout(new GridLayout(0, 2));

                    // buttons
                    JButton conv = new JButton("Numeric Converter");
                    JButton calc = new JButton("Basic Calculator");
                    JButton bitOps = new JButton("Bitwise Operations");
                    JButton binAdd = new JButton("Binary Adder");
                    JButton fcQuiz = new JButton("CPU Components Quiz");
                    JButton traffic = new JButton("Traffic Light Controller");

                    // functions
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
                                case "bitOps":
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
                    frame.setSize(800, 600);
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

    public static void projClosing() {//brings library to foreground when a program closes
        Library.frame.setVisible(true);
    }//end projClosing
}// end class Library

class Listener extends WindowAdapter{//returns to Library after a program closes
    @Override
    public void windowClosing(WindowEvent e) {
        // shows the Library window when a project closes
        Library.projClosing();
    }
}//end class Listener

class BackgroundImage extends JPanel{//sets a background image
    Image pic;
    public BackgroundImage(String image){
        setLayout(null);
        ImageIcon img = new ImageIcon(image);
        pic = img.getImage();
    }//end constructor BackgroundImage

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(pic, 0, 0, null);
    }//end paintComponent
}//end class BackgroundImage
