import javax.swing.JFrame;

public class BinaryAdder {
    public static void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // frame and layout
                JFrame frame = new JFrame("Binary Adder");
                Listener listener = new Listener();// Must be included
                frame.addWindowListener(listener);// Must be included
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Must be included
                frame.setSize(600, 400);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }// end start
}// end class BinaryAdder
