package sample.atry.pskingworld.recyclerviewwithdnd;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by naver on 2017. 2. 12..
 * Adapter class . Typed ViewHolder
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {
    private final String TAG = ItemAdapter.class.getSimpleName();
    private final OnStartDragListener mDragListener;
    private final Context mContext;
    private final TextView mTextView;
    static List<Item> itemList = new ArrayList<>();

    public interface OnStartDragListener {
        void onStartDrag(RecyclerView.ViewHolder holder);
    }

    public ItemAdapter(Context context, OnStartDragListener mDragListener, TextView textView) {
        this.mDragListener = mDragListener;
        this.mTextView = textView;
        this.mContext = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_adapter, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        final Item item = itemList.get(position);
        holder.tvItemName.setText(itemList.get(position).getItemName());
        holder.relativeReorder.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onItemMove(int fromPos, int toPos) {
        Log.d(TAG, " >> onItemMove " + fromPos + " : " + toPos);
        if (fromPos < toPos) {
            for (int i = fromPos; i < toPos; i++) {
                Collections.swap(itemList, i, i + 1);
            }
        } else {
            for (int j = fromPos; j > toPos; j--) {
                Collections.swap(itemList, j,j-1);
            }
        }
        notifyItemMoved(fromPos, toPos);

    }

    @Override
    public void onItemDismiss(int position) {

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder, View.OnClickListener {

        protected RelativeLayout container;
        protected TextView tvItemName;
        protected ImageView ivReorder;
        protected RelativeLayout relativeReorder;

        public ItemViewHolder(View itemView) {
            super(itemView);
            container = (RelativeLayout) itemView.findViewById(R.id.container);
            tvItemName = (TextView) itemView.findViewById(R.id.tvItemName);
            ivReorder = (ImageView) itemView.findViewById(R.id.ivReorder);
            relativeReorder = (RelativeLayout) itemView.findViewById(R.id.relativeReorder);

        }


        @Override
        public void onClick(View v) {
            Log.d(TAG, " >> onClick Call...");
        }

        @Override
        public void onItemSelected(Context context) {
            container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
            tvItemName.setTextColor(ContextCompat.getColor(context, R.color.white));
            ivReorder.setColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN);

        }

        @Override
        public void onItemClear(Context context) {
            container.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            ivReorder.setColorFilter(ContextCompat.getColor(context, R.color.textlight), PorterDuff.Mode.SRC_IN);
            tvItemName.setTextColor(ContextCompat.getColor(context, R.color.textlight));

        }
    }

}
