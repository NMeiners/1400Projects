//imports
import javax.swing.*;

public class TrafficLight{
    public static void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //frame and layout
                JFrame frame = new JFrame("Traffic Light Controller");
                Listener listener = new Listener();//Must be included
                frame.addWindowListener(listener);//Must be included
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Must be included
                frame.setSize(100, 100);
                frame.setVisible(true);
            }
        });
    }//end start
}//end class TrafficLight
