package com.example.sapernewvers.views.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.sapernewvers.gameLogic.GameEngine;
import com.example.sapernewvers.util.DifficultStorage;

public class Grid extends GridView {

    public Grid(Context context, AttributeSet attrs) {
        super(context, attrs);

        GameEngine gameEngine = GameEngine.getInstance();
        gameEngine.createGrid(context);

        setNumColumns(DifficultStorage.get().getRows());
        setAdapter(new GridAdapter());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return DifficultStorage.get().getRows() * DifficultStorage.get().getColumns();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return GameEngine.getInstance().getCellAt(position);
        }
    }
}