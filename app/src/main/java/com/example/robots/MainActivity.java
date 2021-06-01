package com.example.robots;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import adapters.RobotAdapter;
import helpers.QueueUtils;
import models.Robot;

public class MainActivity extends AppCompatActivity {

    ListView robotList;
    RobotAdapter robotAdapter;
    QueueUtils.QueueObject queue = null;
    ArrayList<Robot> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        robotList = findViewById(R.id.robotList);
        queue = QueueUtils.getInstance(this.getApplicationContext());
        items = new ArrayList<>();
        Robot.injectContactsFromCloud(queue, items, this);
        robotAdapter = new RobotAdapter(this, items);
        robotList.setAdapter(robotAdapter);
    }
    public void refreshList(){
        if ( robotAdapter!= null ) {
            robotAdapter.notifyDataSetChanged();
        }
    }
}