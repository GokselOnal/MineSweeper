import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Controller
public class ButtonHandler extends MouseAdapter implements ActionListener {
	private int row;
	private int column;
	private MineGrid mineGrid;

	public ButtonHandler(int row, int column, MineGrid mineGrid) {
		this.row = row;
		this.column = column;
		this.mineGrid = mineGrid;
	}

	public void actionPerformed(ActionEvent actionEvent) {
		JButton button = mineGrid.buttons[this.row][this.column];
		if (!button.getText().equals("F") || button.getText().equals("  ")) {

			if (this.mineGrid.getCellContent(this.row, this.column) == MineGrid.MINE) {
				mineGrid.buttons[this.row][this.column].setBackground(Color.red);
				JOptionPane.showMessageDialog(null, "GAME OVER!");
				System.exit(0);
			}

			else if (this.mineGrid.getCellContent(this.row, this.column) == 0) {
				openButtons(this.row, this.column);
			} else {// (this.mineGrid.getCellContent(this.row, this.column) != 0)
				button.setBackground(Color.LIGHT_GRAY);
				button.setText(String.valueOf(this.mineGrid.getCellContent(this.row, this.column)));
			}

		} else { // button text is F in that situation so it doesnt do anything
		}

		if (isAllButtonSetted() && (numberOfF() == MineSweeper.NUMBER_OF_MINES)) {
			JOptionPane.showMessageDialog(null, "You are a genius.");
			System.exit(0);
		}
	}

	public void mousePressed(MouseEvent e) {
		JButton button = mineGrid.buttons[this.row][this.column];
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (!button.getText().equals("F") && (button.getText().isEmpty() || button.getText().equals("  "))) {
				button.setText("F");
				button.setBackground(Color.orange);
			} else if (button.getText().equals("F")) {

				button.setText("  "); // F icon is disappeared
				button.setBackground(Color.GRAY);
			}
			if (isAllButtonSetted() && (numberOfF() == MineSweeper.NUMBER_OF_MINES)) {
				JOptionPane.showMessageDialog(null, "You are a genius.");
				System.exit(0);
			}
		}
	}

	private boolean isAllButtonSetted() {
		for (int i = 0; i < mineGrid.buttons.length; i++) {
			for (int j = 0; j < mineGrid.buttons[0].length; j++) {
				if (mineGrid.buttons[i][j].getText().isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}

	private int numberOfF() {
		int count = 0;
		for (int i = 0; i < mineGrid.buttons.length; i++) {
			for (int j = 0; j < mineGrid.buttons[0].length; j++) {
				if (mineGrid.buttons[i][j].getText().equals("F")) {
					count++;
				}
			}
		}
		return count;
	}

	private void openButtons(int row, int col) {
		if (this.mineGrid.isInsideGrid(row, col) && (mineGrid.buttons[row][col].getText().equals("")
				|| mineGrid.buttons[row][col].getText().equals("  "))) {
			int value = mineGrid.getCellContent(row, col);
			mineGrid.buttons[row][col].setBackground(Color.LIGHT_GRAY);
			mineGrid.buttons[row][col].setText(String.valueOf(value));
			if (value == 0) {
				openButtons(row - 1, col - 1);
				openButtons(row - 1, col);
				openButtons(row - 1, col + 1);
				openButtons(row, col - 1);
				openButtons(row, col + 1);
				openButtons(row + 1, col - 1);
				openButtons(row + 1, col);
				openButtons(row + 1, col + 1);
			}
		}
	}
}
