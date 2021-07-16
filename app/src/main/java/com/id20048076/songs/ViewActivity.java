package com.id20048076.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<Song> al;
    ListView lv;
    ArrayAdapter<Song> aa;
    ArrayAdapter<Integer> adapter;
    Spinner spin;
    ArrayList<Integer> alyear;
    Button btnFiveStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        lv = findViewById(R.id.listView);
        spin = findViewById(R.id.spinner);
        btnFiveStar = findViewById(R.id.button3);

        spin.setOnItemSelectedListener(this);

        alyear = new ArrayList<Integer>();
        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, alyear);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spin.setAdapter(adapter);


        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        update();

        btnFiveStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ViewActivity.this);
                al.clear();
                ArrayList<Song> temp = dbh.getAllSongs();
                for(int i = 0;i<temp.size();i++){
                    if(temp.get(i).getStar()==5){
                        al.add(temp.get(i));
                    }
                }
                aa.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = al.get(position);
                Intent i = new Intent(ViewActivity.this,
                        EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int data = alyear.get(position);
        DBHelper dbh = new DBHelper(ViewActivity.this);
        al.clear();
        ArrayList<Song> temp = dbh.getAllSongs();
        for(int i = 0;i<temp.size();i++){
            if(temp.get(i).getYear()==data){
                al.add(temp.get(i));
            }
        }
        aa.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void update(){
        DBHelper dbh = new DBHelper(ViewActivity.this);
        al.clear();
        al.addAll(dbh.getAllSongs());

        aa.notifyDataSetChanged();
        alyear.clear();
        for(int i = 0;i<al.size();i++){
            boolean repeat = false;
            for(int j = 0;j<alyear.size();j++){
                if(al.get(i).getYear()==alyear.get(j)){
                    repeat = true;
                }
            }
            if(!repeat){
                alyear.add(al.get(i).getYear());
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        update();
    }
}