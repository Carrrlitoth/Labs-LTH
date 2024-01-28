package sudoku;

public class SudokuSolv implements SudokuSolver {
	private int[][] sudokuMatrix;

	public SudokuSolv() {
		sudokuMatrix = new int[9][9];

	}

	/**
	 * Sets a number on the specified row and column.
	 * 
	 * @param r   Rows
	 * @param c   Columns
	 * @param nbr Number to be placed
	 * 
	 */
	@Override
	public void set(int r, int c, int nbr) {
		if (r > 8 || r < 0 || c > 8 || c < 0)
			throw new IllegalArgumentException();
		sudokuMatrix[r][c] = nbr;
	}

	/**
	 * Get the number on the specified positon on the board
	 * 
	 * @param r Rows
	 * @param c Columns
	 * @return Integer from specified position
	 * @throws IllegalArgumentException if Row or Column is outside of [1..9]
	 */

	public int getNumber(int r, int c) {
		if (r > 8 || r < 0 || c > 8 || c < 0)
			throw new IllegalArgumentException();
		return sudokuMatrix[r][c];
	}

	/**
	 * Sets number on specified Row and Column to 0.
	 * 
	 * @param r Row
	 * @param c Column
	 */
	@Override
	public void remove(int r, int c) {
		sudokuMatrix[r][c] = 0;
	}

	/**
	 * Checks if a number can be placed on specified position according to the rules
	 * of Sudoku.
	 * 
	 * Controlls that the placed number is within bounds
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * 
	 * @return Looks for the same number in Row and column and
	 *         false if same number as parameter is found
	 * 
	 * @return Looks for the same number in the block as the parameter
	 *         false if the same number as parameter is found
	 * 
	 * @return true
	 */
	@Override
	public boolean legal(int r, int c, int nbr) {
		if (r > 8 || r < 0 || c > 8 || c < 0 || nbr > 9 || nbr < 1) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < 9; i++) {
			if (sudokuMatrix[r][i] == nbr && i != c) {
				return false;
			}
			if (sudokuMatrix[i][c] == nbr && i != r) {
				return false;
			}
		}
		int x = r - r % 3;
		int y = c - c % 3;
		for (int i = x; i < x + 3; i++) {
			for (int j = y; j < y + 3; j++) {
				if (sudokuMatrix[i][j] == nbr && i != r && j != c) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isAllValid() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (getNumber(i, j) < 1) {
					return false;
				} else if (!legal(i, j, getNumber(i, j)))
					return false;

			}
		}
		return true;
	}

	/**
	 * Calls the recursive method solve.
	 * 
	 * @return if the solution is valid
	 */
	@Override
	public boolean solve() {
		return solve(0, 0);
	}

	private boolean solve(int r, int c) {
		if (c == 9) {
			r++;
			c = 0;
		}
		if (r == 9) {
			return isAllValid();
		}
		if (getNumber(r, c) == 0) {
			for (int i = 1; i < 10; i++) {
				if (legal(r, c, i)) {

					set(r, c, i);
					if (solve(r, c + 1)) {
						return true;
					}
				}
			}
			remove(r, c);
			return false;
		} else if (legal(r, c, getNumber(r, c))) {
			solve(r, c + 1);
		} else
			return false;

		return isAllValid();

	}

	/**
	 * Empties the whole Sudoku board
	 */
	@Override
	public void clear() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				set(i, j, 0);
			}
		}

	}

	/**
	 * Returns the Sudoku board.
	 * 
	 * @return sudokuMatrix
	 */

	@Override
	public int[][] getMatrix() {

		return sudokuMatrix;
	}

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param nbrs the matrix with the digits to insert
	 * @throws IllegalArgumentException if nbrs has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	@Override
	public void setMatrix(int[][] nbrs) {
		if (nbrs.length > 9)
			throw new IllegalArgumentException();
		if (nbrs[0].length > 9)
			throw new IllegalArgumentException();
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				sudokuMatrix[i][j] = nbrs[i][j];
	}

}
