package com.example.robots;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

import adapters.ContactAdapter;
import helpers.QueueUtils;
import models.Contact;

public class MainActivity extends AppCompatActivity {

    ListView robotList;
    ContactAdapter contactAdapter;
    QueueUtils.QueueObject queue = null;
    ArrayList<Contact> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("**** Carga ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        robotList = findViewById(R.id.robotList);
        queue = QueueUtils.getInstance(this.getApplicationContext());
        items = new ArrayList<>();
        System.out.println("**** Inyectando ");
        Contact.injectContactsFromCloud(queue, items, this);
        contactAdapter = new ContactAdapter(this, items);
        robotList.setAdapter(contactAdapter);
    }
    public void refreshList(){
        if ( contactAdapter!= null ) {
            contactAdapter.notifyDataSetChanged();
        }
    }
}