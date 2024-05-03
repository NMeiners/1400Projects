
//imports
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class TrafficLight {
    //variables
    private static ImageIcon green, yellow, red;
    private static JLabel[] lights;
    private static Timer shift, change;
    private static JLabel[] timers;
    private static int seconds;
    private static int current;

    public static void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try{
                    //variables
                    final int W = 46;//light width
                    final int H = 125;//light height
                    seconds = 15;
                    current = 0;
                    
                    // frame and layout
                    JFrame frame = new JFrame("Traffic Light Controller");
                    BackgroundImage image = new BackgroundImage("misc/TrafficLight.png");
                    frame.add(image);
                    image.setLayout(null);
                    
                    //load images
                    green = new ImageIcon("misc/green.png");
                    yellow = new ImageIcon("misc/yellow.png");
                    red = new ImageIcon("misc/red.png");
                    
                    //initialize lights
                    lights = new JLabel[4];
                    //add to image
                    for (int i = 0 ; i < lights.length ; i = i+2){
                        for (int x = i ; x < i + 2 ; x++){
                            lights[x] = new JLabel();
                            lights[x].setOpaque(false);
                            image.add(lights[x]);
                        }
                        lights[i].setIcon(green);
                        lights[i+1].setIcon(red);
                    }
                    //direction 1
                    lights[0].setBounds(410, 45, W, H);
                    lights[2].setBounds(575, 300, W, H);
                    //direction 2
                    lights[1].setBounds(250, 200, W, H);
                    lights[3].setBounds(750, 115, W, H);
                    
                    //initialize timers and labels
                    timers = new JLabel[2];
                    for (int i = 0 ; i < timers.length ; i++){
                        timers[i] = new JLabel("0");
                        timers[i].setFont(new Font("Arial", Font.PLAIN, 32));
                        timers[i].setOpaque(true);
                        timers[i].setBounds(490, (600 + 60*i), 65, 50);
                        image.add(timers[i]);
                    }
                    //two timers that shift back and forth between each other
                    shift = new Timer(1000, new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent event){
                            seconds--;
                            timers[0].setText(Integer.toString(seconds));
                            if (seconds == 0){
                                shift.stop();//stop first timer
                                seconds = 5;
                                timers[1].setText(Integer.toString(seconds));
                                lights[current].setIcon(yellow);
                                lights[current+2].setIcon(yellow);
                                change.start();//start second timer
                            }
                        }
                    });
                    change = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event){
                            seconds--;
                            timers[1].setText(Integer.toString(seconds));
                            if (seconds == 2){//shift to red
                                lights[current].setIcon(red);
                                lights[current+2].setIcon(red);
                                current = (int) Math.abs(current-1);
                            }else if (seconds == 0){
                                change.stop();//stop second timer
                                seconds = 15;
                                timers[0].setText(Integer.toString(seconds));
                                lights[current].setIcon(green);
                                lights[current+2].setIcon(green);
                                shift.start();//start first
                            }
                        }
                    });
                    //start the initial timer
                    shift.start();
                    //make frame visible
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Must be included
                    Listener listener = new Listener(shift, change);// Must be included
                    frame.addWindowListener(listener);// Must be included
                    frame.setSize(1045, 800);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }// end start
}// end class TrafficLight
