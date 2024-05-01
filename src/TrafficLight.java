
//imports
import javax.swing.*;

public class TrafficLight {
    public static void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try{
                    //variables
                    final int W = 46;//light width
                    final int H = 125;//light height
                    ImageIcon green, yellow, red;
                    JLabel[] lights;
                    // frame and layout
                    JFrame frame = new JFrame("Traffic Light Controller");
                    BackgroundImage image = new BackgroundImage("misc/TrafficLight.png");
                    Listener listener = new Listener();// Must be included
                    frame.addWindowListener(listener);// Must be included
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Must be included
                    frame.add(image);
                    image.setLayout(null);
                    
                    //load images
                    green = new ImageIcon("misc/green.png");
                    yellow = new ImageIcon("misc/yellow.png");
                    red = new ImageIcon("misc/red.png");
                    
                    //initialize lights
                    lights = new JLabel[4];
                    //add to image
                    for (int i = 0 ; i < lights.length ; i++){
                        lights[i] = new JLabel();
                        lights[i].setOpaque(false);
                        image.add(lights[i]);
                    }
                    //direction 1
                    lights[0].setIcon(green);
                    lights[0].setBounds(410, 45, W, H);
                    lights[1].setIcon(green);
                    lights[1].setBounds(575, 300, W, H);
                    //direction 2
                    lights[2].setIcon(red);
                    lights[2].setBounds(250, 200, W, H);
                    lights[3].setIcon(red);
                    lights[3].setBounds(750, 115, W, H);
                    

                    //make frame visible
                    frame.setSize(1045, 800);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);

                    //continuous while loop. runs while frame is active
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }// end start
}// end class TrafficLight
