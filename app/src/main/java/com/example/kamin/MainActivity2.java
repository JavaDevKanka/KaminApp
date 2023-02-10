package com.example.kamin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;



public class MainActivity2<request, binding, pref> extends AppCompatActivity  implements OnItemSelectedListener
{
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        new Udp_client();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener((OnItemSelectedListener) this);
        //Создаем массив элементов выпадающего списка:
        List<String> elements = new ArrayList<String>();
        elements.add("Красный");
        elements.add("Желтый");
        elements.add("Синий");
        //Создаем для spinner адаптер:
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, elements);
        //Настраиваем внешний вид выпадающего списка, используя готовый системный шаблон:
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Присоединяем адаптер данных к spinner:
        spinner.setAdapter(dataAdapter);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class MainActivity22 extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener
    {

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar2);
            seekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);

            mTextView = (TextView) findViewById(R.id.textView6);
            mTextView.setText("0");
        }

        @Override
        public void onProgressChanged(SeekBar seekBar2, int i, boolean b) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar2) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar2) {
            mTextView.setText(String.valueOf(seekBar2.getProgress()));
        }
    }

}