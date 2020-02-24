import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;


public class View extends JFrame
{
    static int AI_TURN = 1;
    static int HUMAN_TURN = 2;
    static String AI_SIGN = "X";
    static String HUMAN_SIGN = "O";

    JButton[][] buttons = new JButton[3][3];
    JFrame frame = new JFrame("TicTacToe");
    JButton reset = new JButton("Reset");

    private KolkoIKrzyzyk logic;

    public View()
    {
        super();
        frame.setSize(350, 355);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        this.logic = new KolkoIKrzyzyk();

    }


    private void initialize()
    {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel menu = new JPanel(new BorderLayout());
        JPanel game = new JPanel(new GridLayout(3,3));

        frame.add(mainPanel);

        mainPanel.setPreferredSize(new Dimension(325,425));
        menu.setPreferredSize(new Dimension(300,50));
        game.setPreferredSize(new Dimension(300,300));

        mainPanel.add(menu, BorderLayout.NORTH);
        mainPanel.add(game, BorderLayout.SOUTH);

        menu.add(reset, BorderLayout.NORTH);

        reset.addActionListener(new myActionListener());

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {

                buttons[i][j] = new JButton();
                buttons[i][j].setText("");
                buttons[i][j].setVisible(true);

                game.add(buttons[i][j]);
                buttons[i][j].addActionListener(new myActionListener());
            }
        }

        mainPanel.setVisible(true);
        frame.repaint(0,frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
        frame.validate();

    }



    private class myActionListener implements ActionListener
    {

        private void checkWhoWon() {
            if(!logic.hasOWon() && !logic.hasXWon()) {
                JOptionPane.showMessageDialog(frame, "It's a draw!");
            } else {
                JOptionPane.showMessageDialog(frame, "Game finished. " + (logic.hasOWon() ? "O" : "X") + " won!");
            }
        }

        public void actionPerformed(ActionEvent a) {

            if (!logic.isGameOver()) {
                if (a.getSource() == buttons[0][0]) {
                    logic.placeAMove(new Point(0, 0), HUMAN_TURN);
                    buttons[0][0].setText(HUMAN_SIGN);
                    buttons[0][0].setEnabled(false);

                } else if (a.getSource() == buttons[0][1]) {
                    buttons[0][1].setText(HUMAN_SIGN);
                    buttons[0][1].setEnabled(false);
                    logic.placeAMove(new Point(0, 1), HUMAN_TURN);

                } else if (a.getSource() == buttons[1][0]) {
                    buttons[1][0].setText(HUMAN_SIGN);
                    buttons[1][0].setEnabled(false);
                    logic.placeAMove(new Point(1, 0), HUMAN_TURN);

                } else if (a.getSource() == buttons[1][1]) {
                    buttons[1][1].setText(HUMAN_SIGN);
                    buttons[1][1].setEnabled(false);
                    logic.placeAMove(new Point(1, 1), HUMAN_TURN);

                } else if (a.getSource() == buttons[1][2]) {
                    buttons[1][2].setText(HUMAN_SIGN);
                    buttons[1][2].setEnabled(false);
                    logic.placeAMove(new Point(1, 2), HUMAN_TURN);

                } else if (a.getSource() == buttons[2][2]) {
                    buttons[2][2].setText(HUMAN_SIGN);
                    buttons[2][2].setEnabled(false);
                    logic.placeAMove(new Point(2, 2), HUMAN_TURN);

                } else if (a.getSource() == buttons[0][2]) {
                    buttons[0][2].setText(HUMAN_SIGN);
                    buttons[0][2].setEnabled(false);
                    logic.placeAMove(new Point(0, 2), HUMAN_TURN);

                } else if (a.getSource() == buttons[2][1]) {
                    buttons[2][1].setText(HUMAN_SIGN);
                    buttons[2][1].setEnabled(false);
                    logic.placeAMove(new Point(2, 1), HUMAN_TURN);

                } else if (a.getSource() == buttons[2][0]) {
                    buttons[2][0].setText(HUMAN_SIGN);
                    buttons[2][0].setEnabled(false);
                    logic.placeAMove(new Point(2, 0), HUMAN_TURN);

                }

                if (logic.isGameOver()) {
                    checkWhoWon();
                } else {
                    logic.alphaBetaMinimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, AI_TURN);

                    Point bestMove = logic.returnBestMove();
                    logic.placeAMove(bestMove, AI_TURN);
                    buttons[bestMove.x][bestMove.y].setText(AI_SIGN);
                    buttons[bestMove.x][bestMove.y].setEnabled(false);


                    if (logic.isGameOver()) {
                        checkWhoWon();
                    }
                }
            }
            if (a.getSource() == reset) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");
                        buttons[i][j].setEnabled(true);
                        logic = new KolkoIKrzyzyk();

                    }
                }
            }

        }
    }

    public static void main (String[]args)
    {
        View game = new View();
        game.initialize();

    }
}

