package com.example.midintentpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class MianDialAndCallReceiver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mian_dial_and_call_receiver);
        Intent _intent=getIntent();
        Uri _uri=_intent.getData();
        Toast.makeText(getApplicationContext(), "Uri Data: "+_uri.toString(), Toast.LENGTH_SHORT).show();
    }
}