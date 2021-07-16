package com.id20048076.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditActivity extends AppCompatActivity {

    Button btnUpdate, btnDelete, btnCancel;
    Song data;
    EditText etID, etTitle, etSinger, etYear;
    RadioButton rb;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        etID = findViewById(R.id.etID);
        etID.setEnabled(false);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.radioGroup);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setText(String.valueOf(data.getId()));
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSinger());
        etYear.setText(String.valueOf(data.getYear()));
        int tempStar = data.getStar();

        switch (tempStar){
            case 1:
                rb = findViewById(R.id.radioButton1);
                rb.setChecked(true);
                break;
            case 2:
                rb = findViewById(R.id.radioButton2);
                rb.setChecked(true);
                break;
            case 3:
                rb = findViewById(R.id.radioButton3);
                rb.setChecked(true);
                break;
            case 4:
                rb = findViewById(R.id.radioButton4);
                rb.setChecked(true);
                break;
            case 5:
                rb = findViewById(R.id.radioButton5);
                rb.setChecked(true);
                break;
            default:
                break;
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setTitle(etTitle.getText().toString());
                data.setSinger(etSinger.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));

                int selectedId = rg.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(selectedId);
                int _star = Integer.parseInt((String) rb.getText());
                data.setStar(_star);

                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteNote(data.getId());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}