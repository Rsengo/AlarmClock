package com.example.ytgv8b.firsttry;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import Library.Signals.IRing;
import Library.Signals.Ring;
import Library.User.User;


public class RingFragment extends Fragment {

    //public Button button;
    public TextView textview;
    public ListView listview;
    public FloatingActionButton button;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.ringfragment, container, false);
       button = rootView.findViewById(R.id.addRing);

       button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               Intent i  =new Intent(v.getContext(),AddRing.class);
               startActivity(i);
            }
        });
       ListView listview = (ListView) rootView.findViewById(R.id.listView);

        ArrayList<IRing> rings =User.getInstance().getRings();

// Упаковываем данные
        ArrayList<HashMap<String, Object>> data = new ArrayList<>(
                rings.size());
        HashMap<String, Object> map;
        for (int i = 0; i < rings.size(); i++) {
            map = new HashMap<>();
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            String time =format.format(rings.get(i).getSignalTime());
            //Calendar calendar = Calendar.getInstance();
           // calendar.setTime(rings.get(i).getSignalTime());
            //String time = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY))+
            //        ":"+String.valueOf(calendar.get(Calendar.MINUTE));
            map.put("RingName", time);
            map.put("IsState", rings.get(i).isOnState());;
            data.add(map);
        }

// Массив имен атрибутов, из которых будут читаться данные
        String[] from = {"RingName", "IsState"};

// Массив идентификаторов компонентов, в которые будем вставлять данные
        int[] to = {R.id.textbox_name, R.id.switch2};

// создаем адаптер
        SimpleAdapter adapter = new SimpleAdapter(this.getContext(), data, R.layout.list_item,
                from, to);

// Устанавливаем адаптер для списка
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), EditRing.class);
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                User.getInstance().removeRing(i);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        return rootView;

    }

}
