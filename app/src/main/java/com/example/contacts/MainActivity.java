package com.example.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import adapters.ContactAdapter;
import helpers.QueueUtils;
import models.Contact;

public class MainActivity extends AppCompatActivity {

    ListView contactList;
    ContactAdapter contactAdapter;
    QueueUtils.QueueObject queue = null;
    ArrayList<Contact> items;
    FragmentTransaction ft;

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
                if (!Global.homeScreen){
                    return;
                }
                Contact contact = (Contact) parent.getItemAtPosition(position);
                openDetail(contact);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!Global.homeScreen){
            backHome();
        }else{
            minimizeApp();
        }
    }

    void backHome(){
        Intent intent = getIntent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
        Global.homeScreen = true;
    }

    void minimizeApp(){
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    void openDetail(Contact contact){
        Global.currentContact = contact;
        Global.homeScreen = false;
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setTitle(R.string.detail_title);
        }
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main , new DetailFragment());
        ft.commit();
    }

    public void refreshList(){
        if ( contactAdapter!= null ) {
            contactAdapter.notifyDataSetChanged();
        }
    }
}