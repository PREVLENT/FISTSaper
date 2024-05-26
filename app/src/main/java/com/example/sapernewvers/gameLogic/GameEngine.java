package com.example.sapernewvers.gameLogic;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.sapernewvers.activity.GameActivity;
import com.example.sapernewvers.util.DifficultStorage;
import com.example.sapernewvers.util.Generator;
import com.example.sapernewvers.util.PrintGrid;
import com.example.sapernewvers.views.grid.Cell;


public class GameEngine {
    private static GameEngine instance;
    private Context context;

    private Cell[][] saperGrid;

    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public void createGrid(Context context) {
        this.context = context;

        int rows = DifficultStorage.get().getRows();
        int columns = DifficultStorage.get().getColumns();
        int mines = DifficultStorage.get().getMines();

        // create the grid and store it
        saperGrid = new Cell[rows][columns];
        int[][] GeneratedGrid = Generator.generate(mines, rows, columns);
        PrintGrid.print(GeneratedGrid, rows, columns);
        setGrid(context, GeneratedGrid);
    }

    private void setGrid(final Context context, final int[][] grid) {
        for (int x = 0; x < DifficultStorage.get().getRows(); x++) {
            for (int y = 0; y < DifficultStorage.get().getColumns(); y++) {
                if (saperGrid[x][y] == null) {
                    saperGrid[x][y] = new Cell(context, x, y);
                }
                saperGrid[x][y].setValue(grid[x][y]);
                saperGrid[x][y].invalidate();
            }
        }
    }

    public Cell getCellAt(int position) {
        int x = position % DifficultStorage.get().getRows();
        int y = position / DifficultStorage.get().getRows();

        return saperGrid[x][y];
    }

    public Cell getCellAt(int x, int y) {
        return saperGrid[x][y];
    }

    public void click(int x, int y) {
        if (x >= 0 && y >= 0 && x < DifficultStorage.get().getRows() &&
                y < DifficultStorage.get().getColumns() && !getCellAt(x, y).isClicked()) {
            getCellAt(x, y).setClicked();

            if (getCellAt(x, y).getValue() == 0) {
                for (int xt = -1; xt <= 1; xt++) {
                    for (int yt = -1; yt <= 1; yt++) {
                        if (xt != 0 || yt != 0) {
                            click(x + xt, y + yt);
                        }
                    }
                }
            }

            if (getCellAt(x, y).isBomb()) {
                onGameLost();
            }
        }
        checkEnd();
    }

    private boolean checkEnd() {
        int rows = DifficultStorage.get().getRows();
        int columns = DifficultStorage.get().getColumns();
        int mines = DifficultStorage.get().getMines();
        int bombNotFound = mines;
        int notRevealed = rows * columns;

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                if (getCellAt(x, y).isRevealed() || getCellAt(x, y).isFlagged()) {
                    notRevealed--;
                }

                if (getCellAt(x, y).isFlagged() && getCellAt(x, y).isBomb()) {
                    bombNotFound--;
                }
            }
        }

        if (bombNotFound == 0 && notRevealed == 0) {
            Toast.makeText(context, "Ты выйграл!!!", Toast.LENGTH_SHORT).show();
            GameActivity mainActivity = (GameActivity) context;
            mainActivity.openGameWinMenu();
            return true;
        } else {
            return false;
        }
    }

    public void flag(int x, int y) {
        boolean isFlagged = getCellAt(x, y).isFlagged();
        getCellAt(x, y).setFlagged(!isFlagged);
        getCellAt(x, y).invalidate();
        checkEnd();
    }

    private void onGameLost() {
        Toast.makeText(context, "Бах! Вы взорвались", Toast.LENGTH_SHORT).show();

        for (int x = 0; x < DifficultStorage.get().getRows(); x++) {
            for (int y = 0; y < DifficultStorage.get().getColumns(); y++) {
                getCellAt(x, y).setRevealed();
            }
        }
        GameActivity mainActivity = (GameActivity) context;
        mainActivity.openGameOverMenu();
    }
}
