//imports
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class FlashcardQuiz {
    //variables
    private static ArrayList<String[]> questions;
    private static Stack<String[]> stack;
    private static JLabel display;
    private static JButton[] answers;
    private static Container buttons;
    private static ActionListener action;

    public static void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try{//start main try/catch block
                    //variables
                    BufferedReader reader;
                    String str;

                    questions = new ArrayList<String[]>();
                    stack = new Stack<String[]>();

                    //action listener (main code)
                    action = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event){
                            JButton clicked = (JButton) event.getSource();
                            if (clicked.getText() == stack.peek()[1]){
                                display.setText("Correct!");
                            } else {
                                display.setText("<html>Incorrect.<br>Answer: " + stack.peek()[1] + "</html>");
                            }
                            //cause a five second delay
                            Timer timer = new Timer(3500, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    popStack(stack, questions);
                                    display.setText(stack.peek()[0]);
                                }
                            });
                            timer.setRepeats(false); // Only fire the timer once
                            timer.start();
                        }
                    };

                    // frame and layout
                    JFrame frame = new JFrame("Flashcard Quiz");
                    frame.setLayout(null);
                    frame.setSize(800, 600);
                    Listener listener = new Listener();// Must be included
                    frame.addWindowListener(listener);// Must be included
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Must be included

                    //load questions
                    reader = new BufferedReader(new FileReader("misc/quiz.txt"));
                    while ((str = reader.readLine()) != null){
                        try{
                            questions.add(new String[] {str, (str = reader.readLine())});
                        } catch (Exception e){
                            continue;
                        }
                    }
                    reader.close();
                    
                    //initialize buttons
                    buttons = new Container();
                    buttons.setBounds(0, (frame.getHeight()/2), frame.getWidth(), (frame.getHeight()/2));//container constraints and positioning
                    buttons.setLayout(new GridLayout(0, 4));//grid layout
                    //create buttons
                    answers = new JButton[questions.size()];
                    for (int i = 0 ; i < questions.size() ; i++){
                        if (questions.get(i).length > 1){
                            answers[i] = new JButton(questions.get(i)[1]);
                            answers[i].setFont(new Font("Arial", Font.PLAIN, 16));
                            answers[i].addActionListener(action);
                            buttons.add(answers[i]);
                        }
                    }
                    frame.add(buttons);

                    //load stack
                    loadStack(questions, stack);
                    //initialize display label
                    display = new JLabel(stack.peek()[0]);
                    display.setFont(new Font("Arial", Font.PLAIN, 24));
                    display.setHorizontalAlignment(SwingConstants.CENTER);
                    display.setBounds(0, 0, frame.getWidth(), (frame.getHeight()/2));
                    frame.add(display);


                    //show frame
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e){
                    e.printStackTrace();
                }//end main try/catch block
            }
        });
    }// end start

    public static void loadStack(ArrayList<String[]> source, Stack<String[]> destination){//move all quesions from the array list to a stack
        Collections.shuffle(source);
        for (int i = (source.size() - 1) ; i > -1 ; i--){
            destination.push(source.get(i));
            source.remove(i);
        }
    }//end loadStack
    public static void popStack(Stack<String[]> source, ArrayList<String[]> destination){//remove a single question from the stack and put it back in the array list
        destination.add(source.pop());
        if (source.isEmpty())//reload stack if the stack is empty
            loadStack(destination, source);
    }//end popStack

}// end class FlashcardQuiz
