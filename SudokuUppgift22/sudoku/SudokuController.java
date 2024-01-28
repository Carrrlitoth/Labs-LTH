package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class SudokuController extends JFrame {
	private Color background = new Color(255, 120, 0);
	private JPanel p2;
	private JTextField[][] field;
	private Border fieldBorder;
	private Font font;
	private int counter = 0;
	private Scanner scanner;

	public static void main(String[] args) {
		new SudokuController();
	}

	public SudokuController() {

		fieldBorder = new LineBorder(Color.BLACK);
		font = new Font("Verdana", Font.CENTER_BASELINE, 20);
		JButton solve = new JButton("Solve");
		JButton clear = new JButton("Clear");
		JButton controlButton = new JButton("Control check");
		JButton newSud = new JButton("New Board");

		newSud.addActionListener(e -> {
			clearAll(getContentPane());
			newBoard();
		});

		controlButton.addActionListener(e -> {
			controlSolution();
		});

		clear.addActionListener(e -> {
			clearAll(getContentPane());
		});
		solve.addActionListener(e -> {
			solve();
		});
		p2 = null;
		field = new JTextField[9][9];
		for (int k = 0; k < 9; k++) {
			p2 = new JPanel(new GridLayout(9, 9));
			p2.setBorder(fieldBorder);
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					field[i][j] = new JTextField(2);
					field[i][j].addKeyListener(new KeyAdapter() {
						public void keyTyped(KeyEvent evt) {
							if (!Character.isDigit(evt.getKeyChar())) {
								evt.consume();
							}

						}
					});
					if (i < 3 && j < 3 || i < 9 && i > 5 && j < 3 ||
							j < 9 && j > 5 && i < 3
							|| j < 9 && j > 5 && i < 9 && i > 5 ||
							j < 6 && j > 2 && i < 6 && i > 2)
						field[i][j].setBackground(background);

					field[i][j].setDocument(new JTextFieldLimit(1));
					field[i][j].setFont(font);
					field[i][j].setHorizontalAlignment(JTextField.CENTER);
					field[i][j].setBorder(fieldBorder);
					p2.add(field[i][j]);
				}
			}
		}

		JPanel Buttons = new JPanel(new GridLayout(9, 1));
		Buttons.add(solve, BorderLayout.SOUTH);
		Buttons.add(clear, BorderLayout.SOUTH);
		Buttons.add(controlButton, BorderLayout.SOUTH);
		Buttons.add(newSud, BorderLayout.SOUTH);

		add(p2);
		add(Buttons);
		setTitle("Sudoku");
		setLayout(new GridLayout(1, 4));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane();
		pack();
		setLocationByPlatform(true);
		setVisible(true);
	}

	private void newBoard() {
		if (counter == 0) {
			try {
				// byt path
				scanner = new Scanner(
						new File("sudoku/sudokufile.txt"));
			} catch (FileNotFoundException e) {
				System.out.println("Could not open file");
				System.exit(1);
			}
		}
		SudokuSolv solver = new SudokuSolv();
		String line = scanner.nextLine();

		solver.setMatrix(helper(line));
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				field[i][j].setText(String.valueOf(solver.getNumber(i, j)));
			}
		}

		counter++;

		if (!scanner.hasNextLine())
			counter = 0;
	}

	private int[][] helper(String line) {
		int[][] temp = new int[9][9];
		int row = 0;
		int col = 0;
		for (String str : line.split(" ")) {

			if (col == 9) {
				row++;
				col = 0;
			}
			if (row == 9)
				break;
			int nbr = Integer.parseInt(str);
			if (nbr == 0) {
				field[row][col].setText(" ");

			} else {
				temp[row][col] = nbr;
			}
			col++;
		}
		return temp;
	}

	private void controlSolution() {
		int[][] temp = new int[9][9];
		SudokuSolv solver = new SudokuSolv();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (field[i][j].getText().isEmpty()) {
					temp[i][j] = 0;
				} else {
					temp[i][j] = Integer.parseInt(field[i][j].getText());
				}

			}
		}

		solver.setMatrix(temp);
		if (solver.solve()) {
			JOptionPane.showMessageDialog(getContentPane(), "Looks good!");
		} else {
			JOptionPane.showMessageDialog(getContentPane(), "Invalid solution");
		}
	}

	private void clearAll(Container myContainer) {

		Component myComps[] = myContainer.getComponents();

		for (int i = 0; i < myComps.length; i++) {
			if (myComps[i] instanceof JPanel) {
				JPanel myPanel = (JPanel) myComps[i];
				clearAll(myPanel);
			}
			if (myComps[i] instanceof JTextField) {
				JTextField myTextField = (JTextField) myComps[i];
				myTextField.setText("");
			}
		}
	}

	private void solve() {
		int[][] temp = new int[9][9];
		SudokuSolv solver = new SudokuSolv();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (field[i][j].getText().isEmpty()) {
					temp[i][j] = 0;
				} else
					temp[i][j] = Integer.parseInt(field[i][j].getText());
			}
		}
		solver.setMatrix(temp);
		solver.solve();
		if (solver.solve()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					field[i][j].setText(String.valueOf(solver.getNumber(i, j)));
				}
			}
		} else
			JOptionPane.showMessageDialog(getContentPane(), "Unsolvable sudoku");
	}
}
