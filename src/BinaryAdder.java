
//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BinaryAdder {
    //variables
    private static JButton[][] nums;
    private static JLabel[][] ops;
    private static JLabel[] output;
    //action listener. Updates everything in the frame
    private static ActionListener action = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent event){
            //additional variables
            int out[] = {0, 0};
            //changes button text
            nums[Integer.parseInt(event.getActionCommand())%5][Integer.parseInt(event.getActionCommand())%2].setText(
                swap(nums[Integer.parseInt(event.getActionCommand())%5][Integer.parseInt(event.getActionCommand())%2].getText()));

            //updates all labels according to there operator
            //first adder
            ops[0][0].setText(XOR(nums[0][0].getText(), nums[0][1].getText()));
            ops[0][1].setText(XOR(ops[0][0].getText(), "0"));
            ops[0][2].setText(AND(ops[0][0].getText(), "0"));
            ops[0][3].setText(AND(nums[0][0].getText(), nums[0][1].getText()));
            ops[0][4].setText(OR(ops[0][2].getText(), ops[0][3].getText()));
            ops[0][5].setText(ops[0][1].getText());
            ops[0][6].setText(ops[0][4].getText());
            //remaining adders
            for (int i = 1 ; i < ops.length ; i++){//loop that updates each adder, skipping first one due to special case
                ops[i][0].setText(XOR(nums[i][0].getText(), nums[i][1].getText()));
                ops[i][1].setText(XOR(ops[i][0].getText(), ops[i-1][6].getText()));
                ops[i][2].setText(AND(ops[i][0].getText(), ops[i-1][6].getText()));
                ops[i][3].setText(AND(nums[i][0].getText(), nums[i][1].getText()));
                ops[i][4].setText(OR(ops[i][2].getText(), ops[i][3].getText()));
                ops[i][5].setText(ops[i][1].getText());
                ops[i][6].setText(ops[i][4].getText());
            }
            //updates output at bottom
            for (int x = 0 ; x < nums[0].length ; x++){
                for (int i = 0 ; i < nums.length ; i++){
                    out[x] += (int)(Integer.parseInt(nums[i][x].getText())*Math.pow(2, i));
                }
            }
            output[0].setText(getNum(nums, 0) + " + " + getNum(nums, 1) + " = " + getNum(ops));
            output[1].setText(Integer.toString(out[0]) + " + " + Integer.toString(out[1]) + " = " + Integer.toString(out[0] + out[1]));
        }
    };//end ActionListener action
    public static String swap(String str){//swaps o and 1
        if (str == "0")
            return "1";
        return "0";
    }

    //Bitwise operations
    public static String AND(String str1, String str2){
        if ((Integer.parseInt(str1) == 1) && (Integer.parseInt(str2) == 1))
            return "1";
        return "0";
    }
    public static String OR(String str1, String str2){
        if ((Integer.parseInt(str1) == 1) || (Integer.parseInt(str2) == 1))
            return "1";
        return "0";
    }
    public static String XOR(String str1, String str2){
        if ((Integer.parseInt(str1) + Integer.parseInt(str2)) == 1)
            return "1";
        return "0";
    }

    public static void start() {//main program. Sets up the frame and buttons
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try{
                    //additional variables
                    int count;
                    //frame and close operations
                    JFrame frame = new JFrame("Binary Adder");
                    Listener listener = new Listener();// Must be included
                    frame.addWindowListener(listener);// Must be included
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Must be included

                    //background image
                    BackgroundImage image = new BackgroundImage("misc/BinaryAdderBackground.jpg");
                    //image.setLayout(new GridLayout(0, 5));

                    //button initialization
                    nums = new JButton[5][2];
                    count = 0;
                    while (count < (nums.length * nums[0].length)){
                        nums[count%5][count%2] = new JButton("0");
                        nums[count%5][count%2].setActionCommand(Integer.toString((count)));//sets action command to a number 0-9
                        nums[count%5][count%2].addActionListener(action);
                        count++;
                    }

                    //label initialization
                    ops = new JLabel[5][7];
                    for (int i = 0 ; i < ops.length ; i++){
                        for (int x = 0 ; x < ops[i].length ; x++){
                            ops[i][x] = new JLabel("0");
                        }
                    }
                    ops[4][6] = null;//special case for last cOUT. fixed in drawAdder
                    output = new JLabel[2];
                    for (int i = 0 ; i < output.length ; i++){
                        output[i] = new JLabel("0 + 0 = 0");
                        output[i].setBounds(590, 530, 300, (41+i*41));
                        image.add(output[i]);
                    }

                    //set content layout
                    image.add(drawAdder(0, nums[4], ops[4]));
                    image.add(drawAdder(235, nums[3], ops[3]));
                    image.add(drawAdder(470, nums[2], ops[2]));
                    image.add(drawAdder(705, nums[1], ops[1]));
                    image.add(drawAdder(943, nums[0], ops[0]));

                    //frame parameters
                    frame.setSize(1216, 625);
                    frame.add(image);

                    //show frame
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
            } catch (Exception e){e.printStackTrace();}
            }
        });
    }// end start

    //draws buttons into the layout, drawn from right to left, top to bottom
    public static Container drawAdder(int x, JButton[] buttons, JLabel[] labels){
        //container settings
        Container content = new Container();
        content.setLayout(null);
        content.setBounds(x , 0, 200, 525);
        //buttons
        buttons[0].setBounds(140, 20, 41, 41);
        content.add(buttons[0]);

        buttons[1].setBounds(110, 65, 41, 41);
        content.add(buttons[1]);
        //XOR labels
        labels[0].setBounds(145, 180, 41, 41);
        content.add(labels[0]);

        labels[1].setBounds(155, 310, 41, 41);
        content.add(labels[1]);
        //AND labels
        labels[2].setBounds(97, 285, 41, 41);
        content.add(labels[2]);

        labels[3].setBounds(47, 285, 41, 41);
        content.add(labels[3]);
        //OR label
        labels[4].setBounds(72, 367, 41, 41);
        content.add(labels[4]);
        //output labels
        labels[5].setBounds(155, 475, 41, 41);
        content.add(labels[5]);
        
        if (labels[6] != null)//creates a special case for last cOUT
            labels[6].setBounds(70, 430, 41, 41);
        else{
            labels[6] = new JLabel("0");//reinitialize last cOUT
            labels[6].setBounds(70, 475, 41, 41);
        }
        content.add(labels[6]);
        return content;
    }//end drawAdder

    //extract binary number
    public static String getNum(JButton[][] num, int x){
        String str = "";
        for (int i = (num.length - 1); i >= 0 ; i--){
            str += num[i][x].getText();
        }
        return str;
    }//end getNum(JButton)
    public static String getNum(JLabel[][] num){
        String str = num[(num.length - 1)][(num[(num.length - 1)].length - 1)].getText();;
        for (int i = (num.length - 1) ; i >= 0 ; i--){
            str += num[i][(num[(num.length-1)].length - 2)].getText();
        }
        return str;
    }//end getNum(JButton)
    
}// end class BinaryAdder
