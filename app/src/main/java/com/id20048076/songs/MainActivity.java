package com.id20048076.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText title, singer, year;
    RadioGroup star;
    RadioButton radioButton;
    Button insert, showList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.etTitle);
        singer = findViewById(R.id.etSinger);
        year = findViewById(R.id.etYear);
        star = findViewById(R.id.rgStar);
        insert = findViewById(R.id.btnInsert);
        showList = findViewById(R.id.btnShowList);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _title = title.getText().toString();
                String _singer = singer.getText().toString();
                int _year = Integer.parseInt(year.getText().toString());

                int selectedId = star.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                int _star = Integer.parseInt((String) radioButton.getText());

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(_title, _singer, _year, _star);

                if (inserted_id != -1) {
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        ViewActivity.class);
                startActivity(i);
            }
        });


    }
}