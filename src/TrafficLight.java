//imports
import javax.swing.*;

public class TrafficLight{
    public static void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //frame and layout
                JFrame frame = new JFrame("Traffic Light Controller");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(100, 100);
                frame.setVisible(true);
            }
        });
    }//end start
}//end class TrafficLight
