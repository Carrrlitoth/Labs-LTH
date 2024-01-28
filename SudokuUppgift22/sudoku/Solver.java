package sudoku;
public class Solver implements SudokuSolver{
    private int[][] board;
    public Solver() {
        board = new int[9][9];
    }

    @Override
    public boolean solve() {
        return solve(0, 0);
    }

    private boolean solve(int row, int col){
        if(row == 9){
            return isAllOK();
        }

        if(col == 9) {
            row++;
            col = 0;
        }

        if(board[row][col] == 0){
            for(int i = 1; i <= 9; i++){
                
                if(legal(i, row, col)){
                    set(row, col, i);
            
                    if(legal(i, row, col + 1)) {
                        return true;
                    }
                }
            }
            board[row][col] = 0;
           // return false;
        } else if(legal(getNumber(row, col), row, col)){
            solve(row, col+1);

        } else {
            return false;
        }
        return isAllOK();

    }

	public int getNumber(int r, int c) {
		if(r > 8 || r < 0 || c > 8 || c < 0) throw  new IllegalArgumentException();
		return board[r][c];
	}


    @Override
    public boolean legal(int num, int row, int col) {
        for (int i = 0; i < 9; i++){
            if(board[row][i] == num && i != col){
                return false;
            }
            if(board[i][col] == num && i != row){
                return false;
            }
        }
        int strow = row-(row%3);
        int stcol = col-(col%3);
    
        for(int x=strow; x<strow+3; x++) {
            for(int y=stcol; y<stcol+3; y++) {
                if(board[x][y]==num && strow != row && stcol != col) {
                    return false;
                }
            }
        }
        return true; 
    }

    public boolean isAllOK(){
        for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(getNumber(i,j) < 1) {
					return false;
				} else if (!legal(i,j,getNumber(i,j))) return false;
			}
		}
		return true;
    }

    @Override
    public void set(int row, int col, int i) {
        if(row > 8 || row < 0 || col > 8 || col < 0) throw  new IllegalArgumentException();
        board[row][col] = i;
    }

    @Override
    public void remove(int row, int col) {
        board[row][col] = 0;
    }

    @Override
    public void clear() {
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                board[row][col] = 0;
            }
        }
    }

    @Override
    public void setMatrix(int[][] matrix) {
        if(matrix.length > 9) throw new IllegalArgumentException();
		if(matrix[0].length > 9) throw new IllegalArgumentException();
		for(int i=0; i<9; i++)
			  for(int j=0; j<9; j++)
				  board[i][j]=matrix[i][j];
    }

    @Override
    public int[][] getMatrix() {
        return board;
    }
}
