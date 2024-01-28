package testqueue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sudoku.Solver;
import sudoku.SudokuSolv;
public class SolverTests {
    private SudokuSolv board;
    private int[][] matrix;
    
    @BeforeEach
    public void setUp() throws Exception{
        board = new SudokuSolv();
        matrix = new int[][] {
            { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
            { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
            { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
            { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
            { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
            { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 4, 0, 0 } 
          };
        
    }

    @AfterEach
    public void tearDown() throws Exception{
        board.clear();
    }

    @Test
    void testSolve(){
        board.setMatrix(matrix);
        assertTrue(board.solve(), "Did not solve");
    }


    @Test
	void TryImpossibleSudoku() {
		int [][] iSudoku1 =  {
				  { 8, 0, 0, 0, 0, 8, 0, 0, 0 },
				  { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
				  { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
				  { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
				  { 0, 7, 0, 0, 4, 5, 7, 0, 0 },
				  { 0, 5, 0, 1, 0, 0, 0, 3, 0 },
				  { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
				  { 0, 8, 8, 5, 0, 0, 0, 1, 0 },
				  { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
				};
		board.setMatrix(iSudoku1);
		assertFalse(board.solve(),"Solved Impossible Sudoku");
	}

    @Test
    void SolveEmpty(){
        board.setMatrix(matrix);
		board.clear();
		assertTrue(board.solve(), "Did not solve empty sudoku");
    }

}
