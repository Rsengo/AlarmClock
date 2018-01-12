package com.example.ytgv8b.firsttry;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.CheckBoxPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import Library.DataHelpers.PreferenceHelper;

public class AddRing extends AppCompatActivity {

    final Context context = this;
    private byte[] days = new byte[7];
    private  AlertDialog alertDialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ring);

        TextView textView = (TextView)findViewById(R.id.textView2);
        TimePicker tp = (TimePicker)findViewById(R.id.timePicker2);
        tp.setIs24HourView(true);
        textView.setText(tp.getCurrentHour()+":"+tp.getCurrentMinute());
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String time = "";
                if(tp.getCurrentHour()<10)
                {
                    time+="0"+tp.getCurrentHour()+":";
                }
                else
                    time+=tp.getCurrentHour()+":";
                if (tp.getCurrentMinute()<10){
                    time+="0"+tp.getCurrentMinute();
                }
                else
                    time+=tp.getCurrentMinute();
                textView.setText(time);
            }
        });
        //Повторение
        ListView repeatDase = (ListView)findViewById(R.id.repeatDase);
        ArrayList<HashMap<String, String>> list1 = new ArrayList<>();
        HashMap<String, String> map;
        map = new HashMap<>();
        map.put("Name", "Повторять");
        map.put("Tel", "Никогда");
        list1.add(map);
        String [] strings = {"Name", "Tel"};
        int [] date = {android.R.id.text1, android.R.id.text2};
        SimpleAdapter adapter1 = new SimpleAdapter(this, list1, android.R.layout.simple_list_item_2,
               strings,
                date);
        repeatDase.setAdapter(adapter1);
        repeatDase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Получаем вид с файла prompt.xml, который применим для диалогового окна:
                LayoutInflater li = LayoutInflater.from(context);
                View repeatView = li.inflate(R.layout.repeat_style, null);

                //Создаем AlertDialog
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

                //Настраиваем prompt.xml для нашего AlertDialog:
                mDialogBuilder.setView(repeatView);

                //Настраиваем отображение поля для ввода текста в открытом диалоге:
                RadioGroup radioGroup= (RadioGroup)repeatView.findViewById(R.id.group);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId)
                        {
                            case R.id.radioNever:
                                for(int i = 0 ;i<days.length; i++)
                                {
                                    days[i] =0;
                                }
                                map.clear();
                                map.put("Name", "Повторять");
                                map.put("Tel", "Никогда");
                                alertDialog1.cancel();
                                break;
                            case R.id.radioButton5:
                                for(int i = 0 ;i<days.length; i++)
                                {
                                    if(i<5)
                                        days[i] =1;
                                    else
                                        days[i]=0;
                                }
                                map.clear();
                                map.put("Name", "Повторять");
                                map.put("Tel", "По будням");
                                alertDialog1.cancel();
                                break;
                            case R.id.radioButton7:
                                for(int i = 0 ;i<days.length; i++)
                                {
                                    days[i] =1;
                                }
                                map.clear();
                                map.put("Name", "Повторять");
                                map.put("Tel", "Каждый день");
                                alertDialog1.cancel();
                                break;
                            case R.id.radioButtonChoose:
                                map.clear();
                                map.put("Name", "Повторять");
                                map.put("Tel", "...");
                                alertDialog1.cancel();
                                break;
                        }
                    }
                });

                //Настраиваем сообщение в диалоговом окне:
                mDialogBuilder
                        .setCancelable(true)
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });


                //Создаем AlertDialog:
                alertDialog1 = mDialogBuilder.create();

                //и отображаем его:
                alertDialog1.show();
            }
        });
        ListView listView = (ListView)findViewById(R.id.listView)  ;
        String [] list = new String[] {"Вибрация", "Удалить после срабатывания"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, list);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listView.setItemChecked(position, !listView.isItemChecked(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button button = (Button)findViewById(R.id.button);
        TextView final_text = (TextView)findViewById(R.id.final_text);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                //Получаем вид с файла prompt.xml, который применим для диалогового окна:
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);

                //Создаем AlertDialog
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

                //Настраиваем prompt.xml для нашего AlertDialog:
                mDialogBuilder.setView(promptsView);

                //Настраиваем отображение поля для ввода текста в открытом диалоге:
                final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);
                userInput.setText(final_text.getText());

                //Настраиваем сообщение в диалоговом окне:
                mDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        //Вводим текст и отображаем в строке ввода на основном экране:
                                        final_text.setText(userInput.getText());
                                        dialog.cancel();
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                //Создаем AlertDialog:
                AlertDialog alertDialog = mDialogBuilder.create();

                //и отображаем его:
                alertDialog.show();

            }


    });
}}
