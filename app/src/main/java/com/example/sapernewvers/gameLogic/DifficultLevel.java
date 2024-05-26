package com.example.sapernewvers.gameLogic;

public enum DifficultLevel {

    EASY(1, 10, 15, 10),
    MIDDLE(10, 10, 15, 20),
    HARD(20, 10, 15, 30);

    private int id;
    private int rows;
    private int columns;
    private int mines;

    DifficultLevel(int id, int rows, int columns, int mines) {
        this.id = id;
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
    }

    public int getId() {
        return id;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMines() {
        return mines;
    }

    public static DifficultLevel getById(int id) {
        if (id == EASY.id) {
            return EASY;
        } else if (id == MIDDLE.id){
            return MIDDLE;
        } else {
            return  HARD;
        }
    }
}
