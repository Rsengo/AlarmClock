package com.example.ytgv8b.firsttry;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import Toasts.ToastMaker;
import Library.DataHelpers.FileSystemHelper;
import Library.Messages.IMessage;
import Library.Messages.MessageFactory.SMSBuilder;
import Library.Signals.IRing;
import Library.Signals.Ring;
import Library.Signals.SignalBuilders.RingBuilder;
import Library.User.User;

public class AddRing extends AppCompatActivity {

    final Context context = this;
    private byte[] days = new byte[7];
    private  AlertDialog alertDialog1;
    private RadioButton rb;
    private RadioGroup rg;
    private boolean b = false;
    private boolean b1 = false;
    private String discriptionstring= "Будильник";
    private String phonenumber= "";
    private boolean vib =false;
    private boolean del =false;
    private byte method = 0;
    private RingBuilder ringBuilder =new RingBuilder();
    private IRing tempRing;


    @Override
    protected void onStart() {
        super.onStart();
        this.setVolumeControlStream(AudioManager.STREAM_ALARM);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainMenu.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ring);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);

        if (position != -1) {
            tempRing = User.getInstance().getRings().get(position);
        }

        // TODO: 14.01.2018 Установка времени!
        TimePicker tp = findViewById(R.id.timePicker2);
        tp.setIs24HourView(true);


        // TODO: 13.01.2018 Описание!
        ListView discription = (ListView)findViewById(R.id.discription);
        ArrayList<HashMap<String, String>> listdiscription =new ArrayList<>();
        HashMap<String, String> mapdiscription;
        mapdiscription = new HashMap<>();
        mapdiscription.put("Name", "Описание:");
        mapdiscription.put("Tel", "Будильник");
        listdiscription.add(mapdiscription);
        String [] strings1 = {"Name", "Tel"};
        int [] date1 = {android.R.id.text1, android.R.id.text2};
        SimpleAdapter adapter2 = new SimpleAdapter(this, listdiscription, android.R.layout.simple_list_item_2,
                strings1,
                date1);
        discription.setAdapter(adapter2);
        discription.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Получаем вид с файла prompt.xml, который применим для диалогового окна:
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);

                //Создаем AlertDialog
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

                //Настраиваем prompt.xml для нашего AlertDialog:
                mDialogBuilder.setView(promptsView);

                //Настраиваем отображение поля для ввода текста в открытом диалоге:
                EditText userInput = (EditText) promptsView.findViewById(R.id.number);
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
                        discriptionstring=userInput.getText().toString();
                        ringBuilder.setDescription(discriptionstring);//Билдер описания
                        mapdiscription.clear();
                        mapdiscription.put("Name", "Описание:");
                        mapdiscription.put("Tel", discriptionstring);
                        discription.setAdapter(adapter2);
                        alertDialog1.cancel();
                    }
                });

                userInput.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(userInput.getText().equals("Будильник"))
                        {
                            userInput.setText("");
                        }
                    }
                });
                    userInput.setText(discriptionstring);



                //Настраиваем сообщение в диалоговом окне:
                mDialogBuilder
                        .setCancelable(true)
                        .setTitle("Введите описание");

                //Создаем AlertDialog:
                alertDialog1 = mDialogBuilder.create();

                //и отображаем его:
                alertDialog1.show();
            }
        });


        // TODO: 14.01.2018 Дни повтора! 
        ListView repeatDase = (ListView)findViewById(R.id.repeatDase);
        repeatDase.setScrollContainer(false);
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

                Button ok = (Button)repeatView.findViewById(R.id.ok) ;
                Button cancel = (Button)repeatView.findViewById(R.id.cancel) ;
                RadioButton radioNever = (RadioButton)repeatView.findViewById(R.id.radioNever);
                RadioButton radioButton7 = (RadioButton)repeatView.findViewById(R.id.radioButton7);
                RadioButton radioButton5 = (RadioButton)repeatView.findViewById(R.id.radioButton5);
                RadioButton radioButtonChoose = (RadioButton)repeatView.findViewById(R.id.radioButtonChoose);
                rg = (RadioGroup)repeatView.findViewById(R.id.group);
                if(b==false)
                {
                    b=true;
                    rb = radioNever;
                }
                rg.check(rb.getId());
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int checkedRadioButtonId =radioGroup.getCheckedRadioButtonId();
                        RadioButton checked = (RadioButton)repeatView.findViewById(checkedRadioButtonId);
                        rb = checked;
                        switch (checkedRadioButtonId)
                        {
                            case R.id.radioNever:
                                for(int i = 0 ;i<days.length; i++)
                                {
                                    days[i] =0;
                                }
                                ringBuilder.setRepeatDays(days);//Билдер дней повтора
                                map.clear();
                                map.put("Name", "Повторять");
                                map.put("Tel", "Никогда");
                                repeatDase.setAdapter(adapter1);
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
                                ringBuilder.setRepeatDays(days);
                                map.clear();
                                map.put("Name", "Повторять");
                                map.put("Tel", "По будням");
                                repeatDase.setAdapter(adapter1);
                                alertDialog1.cancel();
                                break;
                            case R.id.radioButton7:
                                for(int i = 0 ;i<days.length; i++)
                                {
                                    days[i] =1;
                                }
                                ringBuilder.setRepeatDays(days);
                                map.clear();
                                map.put("Name", "Повторять");
                                map.put("Tel", "Каждый день");
                                repeatDase.setAdapter(adapter1);
                                alertDialog1.cancel();
                                break;
                            case R.id.radioButtonChoose:
                                /*map.clear();
                                map.put("Name", "Повторять");
                                map.put("Tel", "...");
                                repeatDase.setAdapter(adapter1);
                                alertDialog1.cancel();*/
                                ToastMaker.kostyl(context);
                                break;
                        }
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog1.cancel();
                    }
                });
                //Настраиваем сообщение в диалоговом окне:
                mDialogBuilder
                        .setCancelable(true)
                        .setTitle("Выберите период повтора");


                //Создаем AlertDialog:
                alertDialog1 = mDialogBuilder.create();



                //и отображаем его:
                alertDialog1.show();
            }
        });
        // TODO: 14.01.2018 Способо выключения!
        RadioGroup methodGroup = (RadioGroup)findViewById(R.id.radioGroupMethod);
        RadioButton simple = findViewById(R.id.radio_simple);
        RadioButton primer = findViewById(R.id.radio_primer);
        RadioButton head = findViewById(R.id.radio_head);
        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringBuilder.setPuzzle(Ring.PUZZLE_DEFAULT);
            }
        });
        primer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringBuilder.setPuzzle(Ring.PUZZLE_CALCULATE);
            }
        });
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringBuilder.setPuzzle(Ring.PUZZLE_CONNECT);
            }
        });
        methodGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                /*switch (checkedId)
                {
                    case R.id.radio_simple:
                        ringBuilder.setPuzzle(Ring.PUZZLE_DEFAULT);//Билдер способа выключения
                        break;
                    case R.id.primer:
                        ringBuilder.setPuzzle(Ring.PUZZLE_CALCULATE);
                        break;
                    case R.id.head:
                        ringBuilder.setPuzzle(Ring.PUZZLE_CONNECT);
                        break;
                }*/
            }
        });
        // TODO: 14.01.2018 Вибрация и автоудаление!
        ListView listView = (ListView)findViewById(R.id.listView)  ;
        String [] list = new String[] {"Вибрация", "Удалить после срабатывания"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, list);
        //VibrationAndAutodeleteAdapter adapter = new VibrationAndAutodeleteAdapter(this, list);
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0)
                {
                    vib =!vib;
                    ringBuilder.setVibrating(vib);//Билдер вибрации
                }
                else
                {
                    del = !del;
                    ringBuilder.setDeleteAfterUsing(del);//Билдер автоудаления

                }
            }
        });
        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(15);
        numberPicker.setMinValue(5);
        // TODO: 14.01.2018 Кнопка добавления!
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, tp.getCurrentHour());
                calendar.set(Calendar.MINUTE, tp.getCurrentMinute());
                Date date = calendar.getTime();
                ringBuilder.setSignalTime(date)
                        .setRepeatSignalInterval(numberPicker.getValue() * 60000);
                        //.setVibrating(adapter.getBox(0))
                        //.setDeleteAfterUsing(adapter.getBox(1));

                IRing ring = ringBuilder.build();

                if (position != -1) {
                    ring.setId(tempRing.getId());
                    User.getInstance().getRings().remove(position);
                }

                User.getInstance().addRing(ring);
                ring.turnOn(context);
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
                finish();
            }
        });
        // TODO: 14.01.2018 Кнопка закрытия и отмены
        FloatingActionButton cancelButton = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
                finish();
            }
        });
        // TODO: 14.01.2018 Добавление SMS
        Switch switchSms = (Switch)findViewById(R.id.switch1);
        switchSms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.phonesms, null);

                    //Создаем AlertDialog
                    AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

                    //Настраиваем prompt.xml для нашего AlertDialog:
                    mDialogBuilder.setView(promptsView);

                    //Настраиваем отображение поля для ввода текста в открытом диалоге:
                    EditText userInput = (EditText) promptsView.findViewById(R.id.number);
                    Button cancel = (Button) promptsView.findViewById(R.id.cancel);
                    Button ok = (Button) promptsView.findViewById(R.id.ok);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog1.cancel();
                            switchSms.setChecked(false);                        }
                    });
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switchSms.setText(" SMS "+userInput.getText().toString());
                            phonenumber=userInput.getText().toString();//добавить SMS
                            SMSBuilder smsb = new SMSBuilder();
                            smsb.setText("Разбуди меня, пожалуйста");
                            smsb.setRecepient("Получатель", phonenumber);
                            IMessage message1 = smsb.build();
                            ringBuilder.setMessage(message1);
                            alertDialog1.cancel();
                        }
                    });
                    //Настраиваем сообщение в диалоговом окне:
                    mDialogBuilder
                            .setCancelable(false)
                            .setTitle("Введите номер телефона:");

                    //Создаем AlertDialog:
                    alertDialog1 = mDialogBuilder.create();

                    //и отображаем его:
                    alertDialog1.show();
                }
                else
                {
                    switchSms.setText(" SMS ");
                    ringBuilder.setMessage(null);
                }
            }
        });
        // TODO: 14.01.2018 Добавление музыки!
        Button music = (Button)findViewById(R.id.music);
        TextView melody = (TextView)findViewById(R.id.namemelody) ;

        /**if (position == -1) {**/
            FileSystemHelper fileSystemHelper = FileSystemHelper.getInstance();
            melody.setText(fileSystemHelper.loadMelodies().get(0));
        /**} else {
            melody.setText(ring.getMelody());
        }**/


        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.music, null);

                //Создаем AlertDialog
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

                //Настраиваем prompt.xml для нашего AlertDialog:
                mDialogBuilder.setView(promptsView);

                //Настраиваем отображение поля для ввода текста в открытом диалоге:
                ListView musiclist = (ListView)promptsView.findViewById(R.id.musiclist);
                ArrayList<String> namesmusic=FileSystemHelper.getInstance().loadMelodies();
                ArrayAdapter<String> musicadapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_single_choice, namesmusic);
                musiclist.setAdapter(musicadapter);
                musiclist.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                musiclist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String musicname = namesmusic.get(position);
                        ringBuilder.setMelody(musicname);
                        melody.setText(musicname);
                        alertDialog1.cancel();
                    }
                });

                //Настраиваем сообщение в диалоговом окне:
                mDialogBuilder
                        .setCancelable(true)
                        .setTitle("Выберите мелодию:");

                //Создаем AlertDialog:
                alertDialog1 = mDialogBuilder.create();

                //и отображаем его:
                alertDialog1.show();
            }
        });
}}
