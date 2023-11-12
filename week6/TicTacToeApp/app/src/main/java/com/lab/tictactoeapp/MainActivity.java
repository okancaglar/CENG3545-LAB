package com.lab.tictactoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String PLAYER_1="X";
    private static final String PLAYER_2="O";

    byte[][] board = new byte[3][3];
    boolean player1Turn = true;
    private TableLayout tableLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = findViewById(R.id.board);
        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                Button btn = (Button) row.getChildAt(j);
                btn.setOnClickListener(new CellListener(i,j));
            }
        }


    }

    class CellListener implements View.OnClickListener{

        int row,col;

        public CellListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void onClick(View view) {

            if (!isValidMove(row, col))
            {
                Toast.makeText(MainActivity.this, "Cell is already occupied!", Toast.LENGTH_LONG).show();
                return;
            }
            if (player1Turn) {
                ((Button) view).setText(PLAYER_1);
                board[row][col] = 1;
            }else {
                ((Button) view).setText(PLAYER_2);
                board[row][col] = 2;
            }
            if (gameEnded(row, col) == -1)
                player1Turn = !player1Turn;
            else if (gameEnded(row, col) == 0) {
                Toast.makeText(MainActivity.this, "its draw", Toast.LENGTH_LONG).show();
            } else if (gameEnded(row, col) == 1) {
                Toast.makeText(MainActivity.this, "Player 1 wins", Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(MainActivity.this, "Player 2 wins", Toast.LENGTH_LONG).show();
        }

        public boolean isValidMove(int row, int col)
        {
            return board[row][col] == 0;
        }
        public int gameEnded(int row, int col){
            int symbol = board[row][col];
            boolean win = true;
            for (int i = 0; i < 3; i++) {
                if (board[i][col] != symbol)
                {
                    win = false;
                    break;
                }
            }
            if (win) return symbol;
            win = true;
            for (int i = 0; i < 3; i++) {
                if (board[row][i] != symbol)
                {
                    win = false;
                    break;
                }
            }
            if (win) return symbol;
            win = true;
            for (int i = 0; i < 3; i++)
            {
                if (board[i][i] != symbol)
                {
                    win = false;
                }
            }
            if (win) return symbol;
            win = true;
            for (int i = 0; i < 3; i++)
            {
                if (board[2-i][i] != symbol)
                {
                    win = false;
                }
            }
            if (win) return symbol;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0)
                        return -1;
                }
            }

            return 0;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean("player1Turn", player1Turn);
        byte[] singleBoard = new byte[9];
        for (int i = 0; i < 9; i++) {
            singleBoard[i] = board[i/3][i%3];

        }
        outState.putByteArray("singleBoard", singleBoard);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        player1Turn = (boolean) savedInstanceState.get("player1Turn");
        byte[] singleBoard = (byte[]) savedInstanceState.get("singleBoard");
        for (int i = 0; i < 9; i++) {
            board[i/3][i%3] = singleBoard[i];

        }

        TableLayout table = findViewById(R.id.board);
        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                Button btn = (Button) row.getChildAt(j);
                if (board[i][j] == 1)
                    btn.setText(PLAYER_1);
                else if (board[i][j] == 2)
                    btn.setText(PLAYER_2);

            }
        }
    }
}