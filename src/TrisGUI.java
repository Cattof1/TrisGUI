import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class TrisGUI {

    // small change
    private JFrame frame;
    private JButton[] buttons;
    private boolean turn = true;
    private int drawCounter = 0;


    public boolean Controlv(String[] BoardPos, String Symbol) {
        for(int i = 0; i<3; i++){
            if(BoardPos[i].equals(Symbol) && BoardPos[i*3+1].equals(Symbol) && BoardPos[i*3+2].equals(Symbol))
                return true;}
        for(int i = 0; i<3; i++){
            if(BoardPos[i].equals(Symbol) && BoardPos[i+3].equals(Symbol) && BoardPos[i+6].equals(Symbol))
                return true;}
        if(BoardPos[0].equals(Symbol) && BoardPos[4].equals(Symbol) && BoardPos[8].equals(Symbol))
            return true;
        if(BoardPos[2].equals(Symbol) && BoardPos[4].equals(Symbol) && BoardPos[6].equals(Symbol))
            return true;

        return false;


    }


    public TrisGUI() {
        frame = new JFrame("TrisGUI");
        buttons = new JButton[9];
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(3, 3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] Sign = new String[9];
        Arrays.fill(Sign, "Empty");
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("Empty");
            final int ButtonIndex = i;
            frame.add(buttons[i]);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (drawCounter < buttons.length) {
                        if (Sign[ButtonIndex].equals("Empty")) {
                            if (turn == true) {
                                buttons[ButtonIndex].setText("cross");
                                Sign[ButtonIndex] = "cross";
                                turn = false;
                                drawCounter++;
                                System.out.println("" + drawCounter);

                                if (Controlv(Sign,"cross") == true) {
                                    System.out.println("Cross is the winner!");
                                    JOptionPane.showMessageDialog(null, "Cross is the winner!");
                                    for (JButton button : buttons)
                                        button.setEnabled(false);
                                }

                            } else {
                                buttons[ButtonIndex].setText("circle");
                                Sign[ButtonIndex] = "circle";
                                turn = true;
                                drawCounter++;
                                if (Controlv(Sign,"circle") == true) {
                                    System.out.println("Circle is the winner!");
                                    JOptionPane.showMessageDialog(null, "Circle is the winner!");
                                    for (JButton button : buttons)
                                        button.setEnabled(false);
                                }

                            }


                        } else {
                            System.out.println("Box position already choosen,chose another");
                            JOptionPane.showMessageDialog(null, "Box position already choosen,chose another");
                        }
                    } else {
                        System.out.println("Match ended in a draw");
                        JOptionPane.showMessageDialog(null, "Match ended in a draw");
                        for (JButton button : buttons)
                            button.setEnabled(false);

                    }


                }
            });


        }


    }


    public static void main(String[] args) {
        TrisGUI gui = new TrisGUI();
        gui.frame.setVisible(true);


    }

}