
//imports
import javax.swing.*;
import java.awt.*;

public class BinaryAdder {
    public static void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // frame and layout
                JFrame frame = new JFrame("Binary Adder");
                Listener listener = new Listener();// Must be included
                frame.addWindowListener(listener);// Must be included
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Must be included
                
                //background image
                BackgroundImage image = new BackgroundImage("misc/BinaryAdderBackground.jpg");

                //frame parameters
                frame.setSize(1210, 527);
                frame.add(image);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }// end start
}// end class BinaryAdder
