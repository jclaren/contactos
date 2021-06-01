package com.example.robots;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

import adapters.ContactAdapter;
import helpers.QueueUtils;
import models.Contact;

public class MainActivity extends AppCompatActivity {

    ListView contactList;
    ContactAdapter contactAdapter;
    QueueUtils.QueueObject queue = null;
    ArrayList<Contact> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = findViewById(R.id.contactList);
        queue = QueueUtils.getInstance(this.getApplicationContext());
        items = new ArrayList<>();
        Contact.injectContactsFromCloud(queue, items, this);
        contactAdapter = new ContactAdapter(this, items);
        contactList.setAdapter(contactAdapter);
        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = (Contact) parent.getItemAtPosition(position);
                System.out.println("*** Name: " + contact.name);

                DetailFragment detailFragment = new DetailFragment();
                detailFragment.show(getSupportFragmentManager(), "Detalle del contacto");
            }
        });
    }
    public void refreshList(){
        if ( contactAdapter!= null ) {
            contactAdapter.notifyDataSetChanged();
        }
    }
}