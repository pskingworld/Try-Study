package sample.atry.pskingworld.recyclerviewwithdnd;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by naver on 2017. 2. 12..
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private final ItemTouchHelperAdapter mAdapter;
    private Context mContext;
    Paint mPaint = new Paint();
    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter, Context context) {
        mAdapter = adapter;
        mContext = context;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }


    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }

        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;

    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlag = ItemTouchHelper.END;

        return makeMovementFlags(dragFlag, swipeFlag);
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
