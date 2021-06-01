package com.example.robots;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailFragment extends DialogFragment {
    TextView txt_name;
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
        System.out.println("*** Recibido el contacto de: " + Global.currentContact.name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = (RelativeLayout) inflater.inflate(R.layout.fragment_detail, container, false);
        txt_name = vista.findViewById(R.id.detail_name);
        txt_name.setText(Global.currentContact.name);
        return vista;
    }
}