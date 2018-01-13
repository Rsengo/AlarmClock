package com.example.ytgv8b.firsttry;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import android.widget.Toast;

import java.util.ArrayList;

import Library.Signals.Ring;


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
       listview = (ListView) rootView.findViewById(R.id.listView);

        Ring r1 = new Ring();
        r1.setDescription("r1");
        Ring r2 = new Ring();
        r2.setDescription("r2");
        Ring r3 = new Ring();
        r3.setDescription("r3");
        ArrayList<Ring> list1= new ArrayList<Ring>();
        list1.add(r1);
        list1.add(r2);
        list1.add(r3);
        ArrayList<String> list2= new ArrayList<String>();
        for (int i = 0; i<list1.size();i++)
        {
            list2.add(list1.get(i).getDescription());
        }
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), EditRing.class);
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });


       ArrayAdapter<String> list = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_list_item_1,list2 ) ;
        listview.setAdapter(list);
        return rootView;

    }

}
