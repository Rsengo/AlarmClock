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

import Library.Signals.INotification;
import Library.Signals.IRing;
import Library.Signals.Ring;
import Library.User.User;

public class NotificationFragment extends Fragment {

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

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i  =new Intent(v.getContext(),AddNotification.class);
                startActivity(i);
            }
        });
        ListView listview = rootView.findViewById(R.id.listView);

        ArrayList<INotification> notifications = User.getInstance().getNotifications();


// создаем адаптер
        NotificationAdapter adapter = new NotificationAdapter(getContext(), notifications);
// Устанавливаем адаптер для списка
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), EditRing.class);
                // TODO: 15.01.2018 notif[position]
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                User.getInstance().removeNotification(i);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        return rootView;

    }
}
