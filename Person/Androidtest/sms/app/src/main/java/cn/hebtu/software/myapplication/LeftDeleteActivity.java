package cn.hebtu.software.myapplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

public class LeftDeleteActivity extends AppCompatActivity {
    private List<String> data ;
    private LeftDeleteAdapter adapter;
    private SwipeMenuListView listView;
    private User user;
    private Util util ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_delete);

        util = new Util(this);
        user = new User(1,"zs","123","6666");
       util.saveUserInfo(user);
       User user1 = util.getUserInfo();
       Log.e("user",user1.toString());

        intiData();
        listView = findViewById(R.id.sm_deleteListView);
        adapter = new LeftDeleteAdapter(this,data,R.layout.item_layout);
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(150);
                // set a icon
                deleteItem.setIcon(R.drawable.food);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index)
            {
                switch (index)
                {
                    case 0:
                        data.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
        listView.setAdapter(adapter);

    }



    private void intiData() {
        data = new ArrayList<>();
        for(int i=0;i<20;i++){
            data.add("aaaa"+i);
        }
    }


}
