package sample.atry.pskingworld.recyclerviewwithdnd;

/**
 * Created by naver on 2017. 2. 12..
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPos, int toPos);
    void onItemDismiss(int position);

}
