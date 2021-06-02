package com.example.contacts;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.contacts.R;

public class DetailFragment extends DialogFragment {
    TextView txt_name;
    TextView txt_email;
    TextView txt_username;
    TextView txt_phone;
    TextView txt_website;
    View vista;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = (RelativeLayout) inflater.inflate(R.layout.fragment_detail, container, false);
        fillValues();

        return vista;
    }

     void fillValues(){
         txt_name = vista.findViewById(R.id.detail_name);
         txt_name.setText(Global.currentContact.name);
         txt_email = vista.findViewById(R.id.detail_email);
         txt_email.setText(Global.currentContact.email);
         txt_username = vista.findViewById(R.id.detail_username);
         txt_username.setText(Global.currentContact.username);
         txt_phone = vista.findViewById(R.id.detail_phone);
         txt_phone.setText(Global.currentContact.phone);
         txt_website = vista.findViewById(R.id.detail_website);
         txt_website.setText(Global.currentContact.website);
    }
}