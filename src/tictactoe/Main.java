package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Starting game of Tic Tac Toe");
        System.out.println("Make moves according to the following numberings:");
        System.out.println("1 | 2 | 3 \n---------\n4 | 5 | 6\n---------\n7 | 8 | 9");
        TicTacToe game = new TicTacToe();
        System.out.println("Please enter your next move:");

        while (in.hasNext()) {
            int move = in.nextInt();
            if (move < 1 || move > 9) {
                System.out.println("Please enter a valid move between 1-9.");
            }
            else {
                int row = (move-1)/3;
                int col = (move-1)%3;
                if (!game.makeMove(row, col)) {
                    System.out.println("Bad move. Please enter a new move.");
                    continue;
                }
                else {
                    System.out.println(game.toString());
                    State state = game.getState();
                    if (state == State.ONGOING) {
                        System.out.println("Please enter your next move:");
                        continue;
                    }
                    switch (state) {
                        case PLAYERX_WINS:
                            System.out.println("Player X wins!");
                            break;
                        case PLAYERO_WINS:
                            System.out.println("Player O wins!");
                            break;
                        case TIE:
                            System.out.println("Draw.");
                    }
                    break;
                }
            }
        }
    }
}
