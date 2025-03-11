import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class TrisGUI {

    // small change
    private JFrame frame;
    private JButton[] buttons;
    private boolean turno = true;
    private int drawCounter = 0;


    public boolean Controllov(String[] BoardPos, String Symbol) {
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
        Arrays.fill(Sign, "vuoto");
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("Vuoto");
            final int ButtonIndex = i;
            frame.add(buttons[i]);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (drawCounter < buttons.length) {
                        if (Sign[ButtonIndex].equals("vuoto")) {
                            if (turno == true) {
                                buttons[ButtonIndex].setText("croce");
                                Sign[ButtonIndex] = "croce";
                                turno = false;
                                drawCounter++;
                                System.out.println("" + drawCounter);

                                if (Controllov(Sign,"croce") == true) {
                                    System.out.println("Croce ha vinto");
                                    JOptionPane.showMessageDialog(null, "Croce ha vinto");
                                    for (JButton button : buttons)
                                        button.setEnabled(false);
                                }

                            } else {
                                buttons[ButtonIndex].setText("cerchio");
                                Sign[ButtonIndex] = "cerchio";
                                turno = true;
                                drawCounter++;
                                if (Controllov(Sign,"cerchio") == true) {
                                    System.out.println("Cerchio ha vinto");
                                    JOptionPane.showMessageDialog(null, "Cerchio ha vinto");
                                    for (JButton button : buttons)
                                        button.setEnabled(false);
                                }

                            }


                        } else {
                            System.out.println("Casella già occupata sceglierne una vuota");
                            JOptionPane.showMessageDialog(null, "Casella già occupata sceglierne una vuota");
                        }
                    } else {
                        System.out.println("Partita finita in pareggio");
                        JOptionPane.showMessageDialog(null, "Partita finita in pareggio");
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