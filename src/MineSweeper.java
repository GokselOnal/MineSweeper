import javax.swing.*;

public class MineSweeper {
    public static final int NUMBER_OF_MINES = 10;
    public static final int SIZE = 10;
    public static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Mine Sweeper | # of mines: " + NUMBER_OF_MINES);
        frame.add(new MineSweeperGUI(SIZE, SIZE, NUMBER_OF_MINES));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
