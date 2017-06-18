package xyz.michaelobi.bakeaide.components.recyclerview.decorator;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * BakeAide
 * Michael Obi
 * 17 06 2017 8:12 AM
 */

public final class LinearSpaceDecorator extends RecyclerView.ItemDecoration {
    private final int space;

    public LinearSpaceDecorator(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space;
        }
    }
}