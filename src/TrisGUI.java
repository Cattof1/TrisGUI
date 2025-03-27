import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class TrisGUI {


    private JFrame frame;
    private JButton[] buttons;
    private boolean isCrossTurn = true;
    private int drawCounter = 0;
    private final String EMPTY = "empty";
    private final String CROSS = "cross";
    private final String CIRCLE = "circle";
    private int PlayAgain;
    private boolean whoFirst;
    private boolean CircleFirstTurnDone=false;
    private ImageIcon iconCross = new ImageIcon(TrisGUI.class.getResource("/IconCross.png")) ;
    private ImageIcon iconCircle = new ImageIcon(TrisGUI.class.getResource("/IconCircle.png"));
    private JPanel Line;



    public boolean CheckWin(String[] BoardPos, String Symbol) {
        for (int i = 0; i < 3; i++) {
            if (BoardPos[i * 3].equals(Symbol) && BoardPos[i * 3 + 1].equals(Symbol) && BoardPos[i * 3 + 2].equals(Symbol))
                return true;
        }
        for (int i = 0; i < 3; i++) {
            if (BoardPos[i].equals(Symbol) && BoardPos[i + 3].equals(Symbol) && BoardPos[i + 6].equals(Symbol))
                return true;
        }
        if (BoardPos[0].equals(Symbol) && BoardPos[4].equals(Symbol) && BoardPos[8].equals(Symbol))
            return true;
        if (BoardPos[2].equals(Symbol) && BoardPos[4].equals(Symbol) && BoardPos[6].equals(Symbol))
            return true;

        return false;
    }

    /*public void winLine(){
        Line = new JPanel(){
            protected void paintComponent (Graphics g){
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.drawLine(0,0,1000,1000);
                g.fillRect(50,50,20,20);
                System.out.println("test color");

            }
        };
        Line.setOpaque(false);
        Line.setBackground(new Color(0,0,0,0));
        //frame.add(Line);
        frame.getLayeredPane().add(Line,JLayeredPane.PALETTE_LAYER);
        Line.repaint();
    }*/

    public boolean GoFirst(){
        int dialog = JOptionPane.showInternalConfirmDialog(null,"Do you want to start first? You'll play X");

        if(dialog == JOptionPane.YES_OPTION)
            return true;
        else if (dialog == JOptionPane.NO_OPTION)
            return false;
        else if (dialog == JOptionPane.CANCEL_OPTION)
            System.exit(0);
        else if (dialog == JOptionPane.CLOSED_OPTION)
            System.exit(0);

        return true;
    }

    public boolean GameEnded(String[] Sign) {
        if (CheckWin(Sign, CROSS) == true || CheckWin(Sign, CIRCLE) == true)
            return true;
        if (drawCounter == buttons.length)
            return true;
        else
            return false;

    }

    public void CrossTurn(String[] Sign, final int ButtonIndex) {
        //System.out.println("CrossTurn: Start");
        //System.out.println("whoFirst="+whoFirst+"/isCrossTurn="+isCrossTurn);

        Random random = new Random();
        int randomValue;
        if (whoFirst == true){
            if (isCrossTurn == true) {
                //System.out.println("CrossTurn: isCrossTurn true");
                buttons[ButtonIndex].setIcon(iconCross);
                Sign[ButtonIndex] = CROSS;
                //System.out.println("system out cross"+Arrays.toString(Sign));
                isCrossTurn = false;
                drawCounter++;
                System.out.println("drawcounter ="+drawCounter);
                if (CheckWin(Sign, CROSS) == true){
                    //winLine();
                    PlayAgain(CROSS);}
                }
            //System.out.println("CrossTurn: end");
        } else {
            if (isCrossTurn == true) {
                ArrayList<Integer> myArrayList = new ArrayList<>();
                for (int i = 0; i < buttons.length; i++) {
                    if (Sign[i].equals(EMPTY))
                        myArrayList.add(i);
                }

                randomValue = random.nextInt(myArrayList.size());
                int choosenValue = myArrayList.get(randomValue);
                buttons[choosenValue].setIcon(iconCross);
                Sign[choosenValue] = CROSS;
                isCrossTurn = false;
                drawCounter++;
                if (CheckWin(Sign, CROSS) == true)
                    PlayAgain(CROSS);
            }

        }
    }

    public void CircleTurn(String[] Sign, final int ButtonIndex) {
        Random random = new Random();
        int randomValue;
        if (whoFirst == false) {
            if (isCrossTurn == false) {
                buttons[ButtonIndex].setIcon(iconCircle);
                Sign[ButtonIndex] = CIRCLE;
                isCrossTurn = true;
                drawCounter++;
                if (CheckWin(Sign, CIRCLE) == true)
                    PlayAgain(CIRCLE);
            }
        } else {
            if (isCrossTurn == false) {
                ArrayList<Integer> myArrayList = new ArrayList<>();
                //System.out.println("System out circle turn"+Arrays.toString(Sign));
                for (int i = 0; i < buttons.length; i++) {
                    if (Sign[i].equals(EMPTY)){
                        //System.out.print("Sign["+i+"] = "+Sign[i]+"-");
                        myArrayList.add(i);}
                }
                System.out.println();

                //for(int i=0; i<myArrayList.size(); i++)
                //System.out.println("valori possibili"+myArrayList.get(i));

                randomValue = random.nextInt(myArrayList.size());
                int choosenValue = myArrayList.get(randomValue);
                //System.out.println("valore scelto"+choosenValue);
                buttons[choosenValue].setIcon(iconCircle);
                Sign[choosenValue] = CIRCLE;
                isCrossTurn = true;
                drawCounter++;
                if (CheckWin(Sign, CIRCLE) == true)
                    PlayAgain(CIRCLE);
            }
        }
    }

    public void CrossFirstTurn(String[] Sign){
        Random random = new Random();
        int randomValue;
            ArrayList<Integer> myArrayList = new ArrayList<>();
            for (int i = 0; i < buttons.length; i++) {
                if (Sign[i].equals(EMPTY))
                    myArrayList.add(i);
            }

            randomValue = random.nextInt(myArrayList.size());
            int choosenValue = myArrayList.get(randomValue);
            buttons[choosenValue].setIcon(iconCross);
            Sign[choosenValue] = CROSS;
            isCrossTurn = false;
            drawCounter++;

    }



    public void Reset() {
        TrisGUI newgame = new TrisGUI();
        frame.dispose();
        frame = newgame.frame;
        newgame.frame.setVisible(true);
    }

    public void PlayAgain(String WinnerDraw){
        if(WinnerDraw == CROSS){
            System.out.println("Cross is the winner!");
            PlayAgain = JOptionPane.showInternalConfirmDialog(null, "Cross is the winner! play again?");
            if (PlayAgain == JOptionPane.YES_OPTION)
                Reset();
            for (JButton button : buttons)
                button.setEnabled(false);
        } else if (WinnerDraw == CIRCLE) {
            System.out.println("Circle is the winner!");
            PlayAgain = JOptionPane.showInternalConfirmDialog(null, "Circle is the winner! play again?");
            if (PlayAgain == JOptionPane.YES_OPTION)
                Reset();
            for (JButton button : buttons)
                button.setEnabled(false);
        } else {
            System.out.println("Match ended in a draw");
            PlayAgain = JOptionPane.showInternalConfirmDialog(null, "Match ended in a draw,play again?");
            if (PlayAgain == JOptionPane.YES_OPTION)
                Reset();
            for (JButton button : buttons)
                button.setEnabled(false);
        }

    }

    public void createButtonActionListener(String[] Sign) {
        for (int i = 0; i < buttons.length; i++) {
            final int ButtonIndex = i;

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Button " + ButtonIndex + " clicked");
                    if (GameEnded(Sign) == true) {
                        if (drawCounter == buttons.length)
                            PlayAgain("");
                        return;
                    }
                    if (Sign[ButtonIndex].equals(EMPTY)) {
                        turnLogic(Sign,ButtonIndex);

                    } else {
                        System.out.println("Box position already choosen,chose another");
                        JOptionPane.showMessageDialog(frame, "Box position already choosen,chose another");
                    }
                }
            });
        }
    }

    public void turnLogic(String[] Sign, final int ButtonIndex) {
        System.out.println("turnLogic: Start");
        if (whoFirst == true) {
            CrossTurn(Sign, ButtonIndex);
            System.out.println("turnLogic: CrossTurn called, AiTurns called");
            AiTurns(Sign,ButtonIndex);
        } else {
            CircleTurn(Sign, ButtonIndex);
            System.out.println("turnLogic: CircleTurn called, AiTurns called");
            AiTurns(Sign, ButtonIndex);
        }
        System.out.println("turnLogic: End");
    }

    public void AiTurns(String[] Sign, final int ButtonIndex) {
        System.out.println("AiTurns: Start");
        System.out.println("AiTurns: isCrossTurn = " + isCrossTurn + ", GameEnded = " + GameEnded(Sign));

                if (isCrossTurn == false && GameEnded(Sign) == false)
                    CircleTurn(Sign, ButtonIndex);
                else if (isCrossTurn == true && GameEnded(Sign) == false)
                    CrossTurn(Sign, ButtonIndex);

        System.out.println("AiTurns: End");
    }

    public TrisGUI() {
        frame = new JFrame("TrisGUI");
        buttons = new JButton[9];
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(3, 3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        String[] Sign = new String[9];
        Arrays.fill(Sign, EMPTY);
        whoFirst = GoFirst();
        for(int i=0; i < buttons.length; i++){
            buttons[i] = new JButton(EMPTY);
            frame.add(buttons[i]);}
        if(whoFirst == false ){
            CrossFirstTurn(Sign);
        }
        createButtonActionListener(Sign);


    }


    public static void main(String[] args) {
        TrisGUI gui = new TrisGUI();
        gui.frame.setVisible(true);





    }

}