import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class TrisGUI {


    private JFrame frame;
    private JButton[] buttons;
    private boolean isCrossTurn = true;
    private int drawCounter = 0;
    private final String EMPTY="empty";
    private final String CROSS="cross";
    private final String CIRCLE="circle";



    public boolean CheckWin(String[] BoardPos, String Symbol) {
        for(int i = 0; i<3; i++){
            if(BoardPos[i*3].equals(Symbol) && BoardPos[i*3+1].equals(Symbol) && BoardPos[i*3+2].equals(Symbol))
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

    public void TurnAndMoves(String[] Sign,final int ButtonIndex ){
        if (isCrossTurn == true) {
            buttons[ButtonIndex].setText(CROSS);
            Sign[ButtonIndex] = CROSS;
            isCrossTurn = false;
            drawCounter++;
            System.out.println("" + drawCounter);

            if (CheckWin(Sign,CROSS) == true) {
                System.out.println("Cross is the winner!");
                JOptionPane.showMessageDialog(null, "Cross is the winner!");
                for (JButton button : buttons)
                    button.setEnabled(false);
            }

        } else {
            buttons[ButtonIndex].setText(CIRCLE);
            Sign[ButtonIndex] = CIRCLE;
            isCrossTurn = true;
            drawCounter++;
            if (CheckWin(Sign,CIRCLE) == true) {
                System.out.println("Circle is the winner!");
                JOptionPane.showMessageDialog(null, "Circle is the winner!");
                for (JButton button : buttons)
                    button.setEnabled(false);
            }

        }
    }



    public TrisGUI() {
        frame = new JFrame("TrisGUI");
        buttons = new JButton[9];
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(3, 3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] Sign = new String[9];
        Arrays.fill(Sign, EMPTY);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(EMPTY);
            final int ButtonIndex = i;
            frame.add(buttons[i]);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (drawCounter < buttons.length) {
                        if (Sign[ButtonIndex].equals(EMPTY)) {
                            TurnAndMoves(Sign,ButtonIndex);


                        } else {
                            System.out.println("Box position already choosen,chose another");
                            JOptionPane.showMessageDialog(null, "Box position already choosen,chose another");
                        }


                    }   else{
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