import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TrisGUI {


    private JFrame frame;
    private JButton[] buttons;
    private boolean isCrossTurn = true;
    private int drawCounter = 0;
    private final String EMPTY="empty";
    private final String CROSS="cross";
    private final String CIRCLE="circle";
    private ImageIcon iconCross = new ImageIcon("C:/Users/Filippo/IdeaProjects/TrisGUI/Icons/IconCross.png");
    private ImageIcon iconCircle = new  ImageIcon("C:/Users/Filippo/IdeaProjects/TrisGUI/Icons/IconCircle.png");
    private int PlayAgain;



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
        Random random = new Random();
        int randomValue;
        if (isCrossTurn == true) {
            buttons[ButtonIndex].setText(CROSS);
            buttons[ButtonIndex].setIcon(iconCross);
            Sign[ButtonIndex] = CROSS;
            isCrossTurn = false;
            drawCounter++;

            if (CheckWin(Sign,CROSS) == true) {
                System.out.println("Cross is the winner!");
                PlayAgain = JOptionPane.showInternalConfirmDialog(null, "Cross is the winner! play again?");
                if(PlayAgain == JOptionPane.YES_OPTION)
                    Reset();

                for (JButton button : buttons)
                    button.setEnabled(false);
            }

        } else {
            ArrayList<Integer> myArrayList = new ArrayList<>();
            for(int i=0; i<buttons.length; i++) {
                if (buttons[i].getText().equals(EMPTY))
                    myArrayList.add(i);
            }

            randomValue = random.nextInt(myArrayList.size());
            int choosenValue =myArrayList.get(randomValue);
            buttons[choosenValue].setText(CIRCLE);
            buttons[choosenValue].setIcon(iconCircle);
            Sign[choosenValue] = CIRCLE;
            isCrossTurn = true;
            drawCounter++;
            if (CheckWin(Sign,CIRCLE) == true) {
                System.out.println("Circle is the winner!");
                PlayAgain = JOptionPane.showInternalConfirmDialog(null, "Circle is the winner! play again?");
                if(PlayAgain == JOptionPane.YES_OPTION)
                    Reset();

                for (JButton button : buttons)
                    button.setEnabled(false);
            }

        }
    }

    public void Reset(){
        TrisGUI newgame = new TrisGUI();
        frame.dispose();
        frame = newgame.frame;
        newgame.frame.setVisible(true); }


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
                    int width = buttons[ButtonIndex].getWidth();
                    int length = buttons[ButtonIndex].getHeight();

                    if (drawCounter < buttons.length) {
                        if (Sign[ButtonIndex].equals(EMPTY)) {
                            TurnAndMoves(Sign,ButtonIndex);


                        } else {
                            System.out.println("Box position already choosen,chose another");
                            JOptionPane.showMessageDialog(null, "Box position already choosen,chose another");
                        }


                    }   else{
                        System.out.println("Match ended in a draw");
                        PlayAgain = JOptionPane.showInternalConfirmDialog(null, "Match ended in a draw,play again?");
                        if(PlayAgain == JOptionPane.YES_OPTION)
                            Reset();

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