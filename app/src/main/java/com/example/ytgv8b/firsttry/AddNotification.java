package com.example.ytgv8b.firsttry;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import Library.Messages.IMessage;
import Library.Messages.MessageFactory.SMSBuilder;
import Library.Signals.INotification;
import Library.Signals.Notification;
import Library.Signals.SignalBuilders.NotificationBuilder;
import Library.User.User;

public class AddNotification extends AppCompatActivity {

    private AlertDialog alertDialog1;
    private Context context = this;
    private  Calendar _calendar=Calendar.getInstance();
    private RadioGroup rg;
    private RadioButton rb;
    private boolean b = false;
    private RadioGroup rg1;
    private RadioButton rb1;
    private boolean b1 = false;
    private NotificationBuilder _notificationBuilder = new NotificationBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);
        Intent intent1 = getIntent();
        String editdate = "";
        String edittime = "";
        String periods="Никогда";
        String prioritet="Средний";
        int position = intent1.getIntExtra("position",-1);
        if(position!=-1)
        {
            EditText edit = findViewById(R.id.editText3);
            MultiAutoCompleteTextView auto = findViewById((R.id.multiAutoCompleteTextView));
            edit.setText(User.getInstance().getNotifications().get(position).getName());
            auto.setText(User.getInstance().getNotifications().get(position).getDescription());
            Date d =User.getInstance().getNotifications().get(position).getSignalTime();
            _calendar.setTime(d);
            _notificationBuilder.setGeneralPeriodicity(User.getInstance().getNotifications().get(position).getGeneralPeriodicity());
            _notificationBuilder.setPriority(User.getInstance().getNotifications().get(position).getPriority());
            switch (User.getInstance().getNotifications().get(position).getGeneralPeriodicity())
            {
                case 0:
                    periods = "Никогда";
                    break;
                case 1:
                    periods = "Каждый день";
                    break;
                case 2:
                    periods = "Каждую неделю";
                    break;
                case 3:
                    periods = "Каждый месяц";
                    break;
                case 4:
                    periods = "Каждый год";
                    break;
            }
            switch (User.getInstance().getNotifications().get(position).getPriority())
            {
                case 0:
                    prioritet = "Низкий";
                    break;
                case 1:
                    prioritet = "Средний";
                    break;
                case 2:
                    prioritet = "Высокий";
                    break;
            }
        }
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
        map3.put("Tel", periods);
        list1.add(map3);
        HashMap<String, String> map4;
        map4 = new HashMap<>();
        map4.put("Name", prioritet);
        map4.put("Tel", "Средний");
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
                        break;
                    case 2:
                        LayoutInflater li2 = LayoutInflater.from(context);
                        View repeatView = li2.inflate(R.layout.periodstyle, null);

                        //Создаем AlertDialog
                        AlertDialog.Builder mDialogBuilder2 = new AlertDialog.Builder(context);

                        //Настраиваем prompt.xml для нашего AlertDialog:
                        mDialogBuilder2.setView(repeatView);

                        //Настраиваем отображение поля для ввода текста в открытом диалоге:
                        RadioGroup radioGroup= (RadioGroup)repeatView.findViewById(R.id.group);

                        Button ok2 = (Button)repeatView.findViewById(R.id.ok) ;
                        Button cancel2 = (Button)repeatView.findViewById(R.id.cancel) ;
                        RadioButton radioNever = (RadioButton)repeatView.findViewById(R.id.radioNever);
                        RadioButton radioDay = (RadioButton)repeatView.findViewById(R.id.radioDay);
                        RadioButton radioWeek = (RadioButton)repeatView.findViewById(R.id.radioWeek);
                        RadioButton radioMonth = (RadioButton)repeatView.findViewById(R.id.radioMonth);
                        RadioButton radioYear = (RadioButton)repeatView.findViewById(R.id.radioYear);
                        rg = (RadioGroup)repeatView.findViewById(R.id.group);
                        if(b==false)
                        {
                            b=true;
                            rb = radioNever;
                        }
                        rg.check(rb.getId());
                        ok2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int checkedRadioButtonId =radioGroup.getCheckedRadioButtonId();
                                RadioButton checked = (RadioButton)repeatView.findViewById(checkedRadioButtonId);
                                rb = checked;
                                switch (checkedRadioButtonId)
                                {
                                    case R.id.radioNever:
                                        _notificationBuilder.setGeneralPeriodicity(Notification.PEREODICITY_NOREPEAT);
                                        map3.clear();
                                        map3.put("Name", "Периодичность");
                                        map3.put("Tel", "Никогда");
                                        adapter1.notifyDataSetChanged();
                                        alertDialog1.cancel();
                                        break;
                                    case R.id.radioDay:
                                        _notificationBuilder.setGeneralPeriodicity(Notification.PEREODICITY_EVERYDAY);
                                        map3.clear();
                                        map3.put("Name", "Периодичность");
                                        map3.put("Tel", "Каждый день");
                                        adapter1.notifyDataSetChanged();
                                        alertDialog1.cancel();
                                        break;

                                    case R.id.radioWeek:
                                        _notificationBuilder.setGeneralPeriodicity(Notification.PEREODICITY_EVERYWEEK);
                                        map3.put("Name", "Периодичность");
                                        map3.put("Tel", "Каждую неделю");
                                        adapter1.notifyDataSetChanged();
                                        alertDialog1.cancel();
                                        break;
                                    case R.id.radioMonth:
                                        _notificationBuilder.setGeneralPeriodicity(Notification.PEREODICITY_EVERYMOUNTH);
                                        map3.clear();
                                        map3.put("Name", "Периодичность");
                                        map3.put("Tel", "Каждый месяц");
                                        adapter1.notifyDataSetChanged();
                                        alertDialog1.cancel();
                                        break;
                                    case R.id.radioYear:
                                        _notificationBuilder.setGeneralPeriodicity(Notification.PEREODICITY_EVERYYEAR);
                                        map3.clear();
                                        map3.put("Name", "Периодичность");
                                        map3.put("Tel", "Каждый год");
                                        adapter1.notifyDataSetChanged();
                                        alertDialog1.cancel();
                                        break;
                                }
                            }
                        });
                        cancel2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog1.cancel();
                            }
                        });
                        //Настраиваем сообщение в диалоговом окне:
                        mDialogBuilder2
                                .setCancelable(true)
                                .setTitle("Выберите период повтора");

                        //Создаем AlertDialog:
                        alertDialog1 = mDialogBuilder2.create();

                        //и отображаем его:
                        alertDialog1.show();
                        break;
                    case 3:
                        LayoutInflater li3 = LayoutInflater.from(context);
                        View repeatView3 = li3.inflate(R.layout.prioritetstyle, null);

                        //Создаем AlertDialog
                        AlertDialog.Builder mDialogBuilder3 = new AlertDialog.Builder(context);

                        //Настраиваем prompt.xml для нашего AlertDialog:
                        mDialogBuilder3.setView(repeatView3);

                        //Настраиваем отображение поля для ввода текста в открытом диалоге:
                        RadioGroup radioGroup3= (RadioGroup)repeatView3.findViewById(R.id.group);

                        Button ok3 = (Button)repeatView3.findViewById(R.id.ok) ;
                        Button cancel3 = (Button)repeatView3.findViewById(R.id.cancel) ;
                        RadioButton radioHight = (RadioButton)repeatView3.findViewById(R.id.radioHight);
                        RadioButton radioMedium = (RadioButton)repeatView3.findViewById(R.id.radioMedium);
                        RadioButton radioLow = (RadioButton)repeatView3.findViewById(R.id.radioLow);
                        rg1 = (RadioGroup)repeatView3.findViewById(R.id.group);
                        if(b1==false)
                        {
                            b1=true;
                            rb1 = radioMedium;
                        }
                        rg1.check(rb1.getId());
                        ok3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int checkedRadioButtonId =radioGroup3.getCheckedRadioButtonId();
                                RadioButton checked = (RadioButton)repeatView3.findViewById(checkedRadioButtonId);
                                rb1 = checked;
                                switch (checkedRadioButtonId)
                                {
                                    case R.id.radioHight:
                                        _notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
                                        map4.clear();
                                        map4.put("Name", "Приоритет");
                                        map4.put("Tel", "Высокий");
                                        adapter1.notifyDataSetChanged();
                                        alertDialog1.cancel();
                                        break;
                                    case R.id.radioMedium:
                                        _notificationBuilder.setPriority(Notification.PRIORITY_DEFAULT);
                                        map4.clear();
                                        map4.put("Name", "Приоритет");
                                        map4.put("Tel", "Средний");
                                        adapter1.notifyDataSetChanged();
                                        alertDialog1.cancel();
                                        break;

                                    case R.id.radioLow:
                                        _notificationBuilder.setPriority(Notification.PRIORITY_LOW);
                                        map4.clear();
                                        map4.put("Name", "Приоритет");
                                        map4.put("Tel", "Низкий");
                                        adapter1.notifyDataSetChanged();
                                        alertDialog1.cancel();
                                        break;
                                }
                            }
                        });
                        cancel3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog1.cancel();
                            }
                        });
                        //Настраиваем сообщение в диалоговом окне:
                        mDialogBuilder3
                                .setCancelable(true)
                                .setTitle("Выберите период повтора");

                        //Создаем AlertDialog:
                        alertDialog1 = mDialogBuilder3.create();

                        //и отображаем его:
                        alertDialog1.show();
                        break;

                }
                }
        });
        EditText edit = findViewById(R.id.editText3);
        MultiAutoCompleteTextView auto = findViewById((R.id.multiAutoCompleteTextView));
        FloatingActionButton f1 = findViewById(R.id.floatingActionButton);
        FloatingActionButton f2 = findViewById(R.id.floatingActionButton2);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _notificationBuilder.setName(edit.getText().toString());
                _notificationBuilder.setDescription(auto.getText().toString());
                _notificationBuilder.setSignalTime(_calendar.getTime());
                INotification notification = _notificationBuilder.build();
                if (position!=-1)
                {
                    notification.setId(User.getInstance().getNotifications().get(position).getId());
                    User.getInstance().getNotifications().remove(position);
                }
                User.getInstance().addNotification(notification);
                notification.turnOn(context);
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
            }
        });
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
            }
        });
    }
}
