//imports
import javax.swing.*;
import java.awt.*;

public class Library {
    public static void main (String[] args){
        try{//main try block
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
            frame.setSize(800, 400);
            frame.setLayout(grid);
            //frame contents
            frame.getContentPane().add(conv);
            frame.getContentPane().add(calc);
            frame.getContentPane().add(bitOps);
            frame.getContentPane().add(binAdd);
            frame.getContentPane().add(fcQuiz);
            frame.getContentPane().add(traffic);

            //open frame
            frame.setVisible(true);
        } catch (Exception e){
            e.printStackTrace();
        }//end main catch block
    }//end main
}//end class Library
