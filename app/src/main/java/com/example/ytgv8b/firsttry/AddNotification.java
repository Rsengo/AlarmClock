package com.example.ytgv8b.firsttry;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import Library.Messages.IMessage;
import Library.Messages.MessageFactory.SMSBuilder;
import Library.Signals.SignalBuilders.NotificationBuilder;

public class AddNotification extends AppCompatActivity {

    private AlertDialog alertDialog1;
    private Context context = this;
    private  Calendar _calendar=Calendar.getInstance();
    private NotificationBuilder _notificationBuilder = new NotificationBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);
        ListView listnotif = findViewById(R.id.listnotif);
        ArrayList<HashMap<String, String>> list1 = new ArrayList<>();
        HashMap<String, String> map1;
        map1 = new HashMap<>();
        map1.put("Name", "Дата");
        map1.put("Tel", "");
        list1.add(map1);
        HashMap<String, String> map2;
        map2 = new HashMap<>();
        map2.put("Name", "Время");
        map2.put("Tel", "");
        list1.add(map2);
        HashMap<String, String> map3;
        map3 = new HashMap<>();
        map3.put("Name", "Периодичность");
        map3.put("Tel", "Никогда");
        list1.add(map3);
        HashMap<String, String> map4;
        map4 = new HashMap<>();
        map4.put("Name", "Интервал уведомлений");
        map4.put("Tel", "Нет");
        list1.add(map4);
        String [] strings = {"Name", "Tel"};
        int [] date = {android.R.id.text1, android.R.id.text2};
        SimpleAdapter adapter1 = new SimpleAdapter(this, list1, android.R.layout.simple_list_item_2,
                strings,
                date);
        listnotif.setAdapter(adapter1);
        listnotif.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        LayoutInflater li = LayoutInflater.from(context);
                        View promptsView = li.inflate(R.layout.datestyle, null);

                        //Создаем AlertDialog
                        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

                        //Настраиваем prompt.xml для нашего AlertDialog:
                        mDialogBuilder.setView(promptsView);

                        //Настраиваем отображение поля для ввода текста в открытом диалоге:
                        DatePicker datePicker = promptsView.findViewById(R.id.datePicker2);
                        datePicker.updateDate(_calendar.get(Calendar.YEAR),
                                _calendar.get(Calendar.MONTH),_calendar.get(Calendar.DAY_OF_MONTH));
                        Button cancel = (Button) promptsView.findViewById(R.id.cancel);
                        Button ok = (Button) promptsView.findViewById(R.id.ok);
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog1.cancel();
                            }
                        });
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //_calendar.set(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());
                                _calendar.set(Calendar.YEAR,datePicker.getYear());
                                _calendar.set(Calendar.MONTH,datePicker.getMonth());
                                _calendar.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
                                SimpleDateFormat simpledate = new SimpleDateFormat("dd.MM.yy");
                                String strTime = simpledate.format(_calendar.getTime());
                                map1.clear();
                                map1.put("Name", "Дата");
                                map1.put("Tel", strTime);
                                adapter1.notifyDataSetChanged();
                                alertDialog1.cancel();
                            }
                        });
                        //Настраиваем сообщение в диалоговом окне:
                        mDialogBuilder
                                .setCancelable(false)
                                .setTitle("Установите дату:");

                        //Создаем AlertDialog:
                        alertDialog1 = mDialogBuilder.create();

                        //и отображаем его:
                        alertDialog1.show();
                        break;
                    case 1:
                        LayoutInflater li1 = LayoutInflater.from(context);
                        View promptsView1 = li1.inflate(R.layout.timestyle, null);

                        //Создаем AlertDialog
                        AlertDialog.Builder mDialogBuilder1 = new AlertDialog.Builder(context);

                        //Настраиваем prompt.xml для нашего AlertDialog:
                        mDialogBuilder1.setView(promptsView1);

                        //Настраиваем отображение поля для ввода текста в открытом диалоге:
                        TimePicker timePicker= promptsView1.findViewById(R.id.timePicker);
                        timePicker.setIs24HourView(true);
                        timePicker.setCurrentHour(_calendar.get(Calendar.HOUR_OF_DAY));
                        timePicker.setCurrentMinute(_calendar.get(Calendar.MINUTE));
                        Button cancel1 = (Button) promptsView1.findViewById(R.id.cancel);
                        Button ok1 = (Button) promptsView1.findViewById(R.id.ok);
                        cancel1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog1.cancel();
                            }
                        });
                        ok1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //calendar.set(timePicker.getCurrentHour(),timePicker.getCurrentMinute());
                                _calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                                _calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
                                SimpleDateFormat simpledate = new SimpleDateFormat("HH.mm");
                                String strTime = simpledate.format(_calendar.getTime());
                                map2.clear();
                                map2.put("Name", "Время");
                                map2.put("Tel", strTime);
                                adapter1.notifyDataSetChanged();
                                alertDialog1.cancel();
                            }
                        });
                        //Настраиваем сообщение в диалоговом окне:
                        mDialogBuilder1
                                .setCancelable(false)
                                .setTitle("Установите время:");

                        //Создаем AlertDialog:
                        alertDialog1 = mDialogBuilder1.create();

                        //и отображаем его:
                        alertDialog1.show();
                }

            }
        });
    }
}
