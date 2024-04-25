
//imports
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BitwiseOps {
    //variables
    private static JLabel label;
    private static ImageIcon[] image;
    public static void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try{
                    // frame and layout
                    JFrame frame = new JFrame("Bitwise Operations");
                    frame.setLayout(null);
                    Listener listener = new Listener();
                    frame.addWindowListener(listener);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setSize(600, 400);

                    //content
                    frame.add(drawContent());

                    //draw frame
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e){e.printStackTrace();}
            }
        });
    }// end start

    public static Container drawContent(){
        //variables
        String[] name = {"AND", "OR", "XOR", "NAND", "NOR", "XNOR", "NOT"};
        Container content; 
        JButton[] button;
        ActionListener action;

        //create container
        content = new Container();
        content.setLayout(null);
        content.setBounds(0, 0, 600, 400);
        //create action listener with main function
        action = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                //swaps image icon based on the clicked button
                label.setIcon(image[Integer.parseInt(event.getActionCommand())]);
            }
        };
        //initialize and add buttons
        button = new JButton[7];
        for (int i = 0 ; i < button.length ; i++){
            button[i] = new JButton(name[i]);
            button[i].setActionCommand(Integer.toString(i));
            button[i].addActionListener(action);
            button[i].setBounds( 50, 5 + (50*i), 100, 45);
            content.add(button[i]);
        }
        //initialize images
        image = new ImageIcon[7];
        for (int i = 0 ; i < image.length ; i++){
            image[i] = new ImageIcon("misc/logicGate" + Integer.toString(i) +".png");
        }
        //initialize label to contain the image
        label = new JLabel();
        label.setBounds(200, 25, 350, 300);
        label.setIcon(image[0]);
        content.add(label);
        return content;
    }
}// end class BitwiseOps
