package com.lab.tictactoemvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BoardView{

    TableLayout board;
    BoardPresenter boardPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        board = findViewById(R.id.board);
        boardPresenter = new BoardPresenter(this);
        for (byte i = 0; i < 3; i++) {
            TableRow tableRow = (TableRow) board.getChildAt(i);
            for (byte j = 0; j < 3; j++) {
                Button button = (Button) tableRow.getChildAt(j);
                BoardPresenter.CellClickListener cellClickListener = new BoardPresenter.CellClickListener(
                        boardPresenter, (byte) i, (byte)j);
                button.setOnClickListener(cellClickListener);
                boardPresenter.addCellClickListener(cellClickListener);
            }
        }

    }

    @Override
    public void newGame() {
        TableLayout boardView = findViewById(R.id.board);
        for (byte i = 0;  i< 3; ++i) {
            TableRow tableRow = (TableRow) boardView.getChildAt(i);
            for (byte j = 0; j < 3; j++) {
                Button button = (Button)tableRow.getChildAt(j);
                button.setText("");
                button.setEnabled(true);
            }
        }
    }

    @Override
    public void putSymbol(char symbol, byte row, byte col) {
        TableRow tableRow = (TableRow) board.getChildAt(row);
        Button button = (Button) tableRow.getChildAt(col);
        button.setText(Character.toString(symbol));
    }

    @Override
    public void gameEnded(byte winner) {
        TableLayout boardView = findViewById(R.id.board);
        for (byte i = 0;  i< 3; ++i) {
            TableRow tableRow = (TableRow) boardView.getChildAt(i);
            for (byte j = 0; j < 3; j++) {
                Button button = (Button)tableRow.getChildAt(j);
                button.setText("");
                button.setEnabled(false);
            }
        }
        switch (winner){
            case BoardView.draw:
                Toast.makeText(this, "Game is over", Toast.LENGTH_LONG).show();
                break;
            case BoardView.PLAYER_1_WINNER:
                Toast.makeText(this, "Player 1 wins", Toast.LENGTH_LONG).show();
                break;
            case BoardView.PLAYER_2_WINNER:
                Toast.makeText(this, "Player 2 wins", Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void invalidPlay(byte row, byte col) {
        Toast.makeText(this, "Invalid move", Toast.LENGTH_LONG).show();
    }
}