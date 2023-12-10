package com.lab.tictactoemvp;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoardPresenter implements BoardListener{

    private BoardView boardView;
    private Board board;
    private List<CellClickListener> cellClickListeners = new ArrayList<>();

    public BoardPresenter(BoardView boardView) {
        this.boardView = boardView;
        this.board = new Board(this);
    }

    @Override
    public void playerAt(byte player, byte row, byte col) {
            if (player == BoardListener.PLAYER_1)
            {
                boardView.putSymbol(BoardView.PLAYER_1_SYMBOL, row, col);
            }else
            {
                boardView.putSymbol(BoardView.PLAYER_2_SYMBOL, row, col);
            }
    }
    public void move(byte row, byte col){
        board.move(row, col);
    }

    public void addCellClickListener(CellClickListener listener)
    {
        cellClickListeners.add(listener);
    }

    @Override
    public void gameEnded(byte winner) {
        switch (winner){
            case BoardListener.NO_ONE:
                boardView.gameEnded(BoardView.draw);
                break;
            case BoardListener.PLAYER_1:
                boardView.gameEnded(BoardView.PLAYER_1_WINNER);
                break;
            case BoardListener.PLAYER_2:
                boardView.gameEnded(BoardView.PLAYER_2_WINNER);
                break;
        }
    }

    @Override
    public void invalidPlay(byte row, byte col) {
        boardView.invalidPlay(row, col);
    }

    static class CellClickListener implements View.OnClickListener{

        BoardPresenter boardPresenter;
        byte row, col;

        public CellClickListener(BoardPresenter boardPresenter, byte row, byte col) {
            this.boardPresenter = boardPresenter;
            this.row = row;
            this.col = col;
        }

        @Override
        public void onClick(View view) {
            boardPresenter.move(row, col);
        }
    }
}
