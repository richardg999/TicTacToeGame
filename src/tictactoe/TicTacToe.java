package tictactoe;

public class TicTacToe {
    private static final int GRID_SIZE = 3;

    enum Player { X, O }
    private final Player[][] board;
    private Player currPlayer;

    public TicTacToe() {
        board = new Player[GRID_SIZE][GRID_SIZE];
        currPlayer = Player.X;
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col] == null) {
            board[row][col] = currPlayer;
            currPlayer = nextPlayer(currPlayer);
            return true;
        }
        else {
            return false;
        }
    }

    public State getState() {
        if (hasWon(Player.X)) {
            return State.PLAYERX_WINS;
        }
        else if (hasWon(Player.O)) {
            return State.PLAYERO_WINS;
        }
        else if (isFull()) {
            return State.TIE;
        }
        else {
            return State.ONGOING;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                String next = " ";
                if (board[i][j] != null) {
                    next = board[i][j].toString();
                }
                sb.append(next);
                if (j != GRID_SIZE-1) {
                    sb.append(" | ");
                }
            }
            sb.append("\n---------\n");
        }
        return sb.toString();
    }

    private Player nextPlayer(Player player) {
        if (player == Player.X) {
            return Player.O;
        }
        else {
            return Player.X;
        }
    }

    private boolean hasWon(Player player) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (isWin(player, row, 0, 0, 1))
                return true;
        }

        for (int col = 0; col < GRID_SIZE; col++) {
            if (isWin(player, 0, col, 1, 0))
                return true;
        }

        return isWin(player, 0, 0, 1, 1) ||
                isWin(player, 0, GRID_SIZE - 1, 1, -1);
    }

    private boolean isWin(Player player, int startRow, int startCol, int rowInc, int colInc) {
        for(int i = 0; i < GRID_SIZE; i++) {
            if (board[startRow][startCol] != player)
                return false;
            startRow += rowInc;
            startCol += colInc;
        }
        return true;
    }

    private boolean isFull() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
