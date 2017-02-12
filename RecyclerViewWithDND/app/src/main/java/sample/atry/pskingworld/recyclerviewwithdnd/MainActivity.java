package sample.atry.pskingworld.recyclerviewwithdnd;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static sample.atry.pskingworld.recyclerviewwithdnd.R.id.fab;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnStartDragListener {
    private TextView tvNumber;
    private int num = 0;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTabs();

        TextView tvDate = (TextView) findViewById(R.id.tvDate);
        TextView tvDay = (TextView) findViewById(R.id.tvDay);

        tvNumber = (TextView) findViewById(R.id.tvNumber);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy", Locale.getDefault());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        final ItemAdapter itemAdapter = new ItemAdapter(MainActivity.this, this, tvNumber);
        recyclerView.setAdapter(itemAdapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(itemAdapter, getApplicationContext());
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        loadItems();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Item item = new Item();
                item.setItemName("item : " + ItemAdapter.itemList.size() + 1);
                llm.scrollToPositionWithOffset();

            }
        });
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder holder) {
        mItemTouchHelper.startDrag(holder);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setTabs() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("FIRST TAB"));
        tabLayout.addTab(tabLayout.newTab().setText("SECOND TAB"));
        tabLayout.addTab(tabLayout.newTab().setText("THIRD TAB"));

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int i = 0; i < tabsCount; i++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(i);
            int tabChildsCount = vgTab.getChildCount();
            for (int j = 0; j < tabChildsCount; j++) {
                View tabViewChild = vgTab.getChildAt(j);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTextSize(3);
//                    ((TextView) tabViewChild).setTypeface(Typefaces.get);
                }
            }
        }
    }

    private void loadItems() {
        for (int i = 10; i > 0; i++) {
            Item item = new Item();
            item.setItemName("item : " + i);

        }
    }
}
