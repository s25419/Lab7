package com.myweb.lab7_mysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txtName;
    private Button btnSave;
    private ListView dataView;
    private MySQLConnect mySQLConnect;
    private List<String> items;
    private ArrayAdapter<String> adt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        update();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySQLConnect.sentData(txtName.getText().toString());
                items.add(txtName.getText().toString());
                adt.notifyDataSetChanged();
            }
        });
    }

    public void update(){
        items = mySQLConnect.getData();
        adt = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, items);
        dataView.setAdapter(adt);
    }

    public void init(){
        txtName = findViewById(R.id.txtName);
        btnSave = findViewById(R.id.btnSave);
        dataView = findViewById(R.id.dataView);
        mySQLConnect = new MySQLConnect(MainActivity.this);
    }
}
