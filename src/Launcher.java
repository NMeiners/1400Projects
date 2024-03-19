//imports
import javax.swing.*;
import java.awt.*;

public class Launcher {
    public static void main (String[] args){
        try{//main try block
            //frame and layout
            JFrame frame = new JFrame("Discord Mods Projects");
            GridLayout grid = new GridLayout(0, 2);

            //buttons
            JButton calc = new JButton("Calculator");
            JButton conv = new JButton("Converter");

            //frame parameters
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLayout(grid);
            //frame contents
            frame.getContentPane().add(calc);
            frame.getContentPane().add(conv);

            //open frame
            frame.setVisible(true);
        } catch (Exception e){
            e.printStackTrace();
        }//end main catch block
    }//end main
}//end class Launcher
