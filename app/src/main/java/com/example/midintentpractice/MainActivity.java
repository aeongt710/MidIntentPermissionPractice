package com.example.midintentpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button _btnDial;
    public EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.et1);
        findViewById(R.id.btn1_dial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setAction(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+et1.getText().toString()));
                if(i.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(i);
                }
            }
        });
        findViewById(R.id.btn2_xdial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setAction("android.intent.action.XDIAL");
                i.setData(Uri.parse("tel:"+et1.getText().toString()));
//                Log.i("",i.resolveActivity(getPackageManager()).toString());
                if(i.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "btn2_xdial failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.btn_mainDial).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + et1.getText().toString()));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();

                    if(shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE))
                    {
                        showAlertDialog("Permission Request Justification", "App requires this permission to place calls");

                    }
                    else{
                        requestPermissions(new String[]{"android.permission.CALL_PHONE"},99);
                    }
                }
                else
                {
                    if(i.resolveActivity(getPackageManager())!=null)
                    {
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(), "Main Dial Resolution failed", Toast.LENGTH_SHORT).show();
                    }
                }




            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==99)
        {
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(getApplicationContext(), "Requesting Permission successfull code here", Toast.LENGTH_SHORT).show();
            }else{
                showAlertDialog("Request Permission Justification", "To place calls using this dialer app, it requires CALL_PHONE permission");
            }
        }
    }

    public void showAlertDialog(String title, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Add the buttons
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // request permission when user presses the OK button of dialog
                requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 99);
            }
        });
        // do nothing when user presses cancel button on dialog
        builder.setNegativeButton("Cancel",null);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(text);
        // Set other dialog properties

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}

//    Intent i = new Intent();
////                i.setAction("android.intent.action.myMain");
////                i.setAction(Intent.ACTION_DIAL);
////                i.setData(Uri.parse("tel:12412412"));
//
//    //                i.setAction(Intent.ACTION_VIEW);
////                i.setData(Uri.parse("geo:10.6,-122.3?z=11"));
////                Toast.makeText(getApplicationContext(), Uri.parse("geo:47.6,-122.3").toString(), Toast.LENGTH_SHORT).show();
//    String a="tel:"+et1.getText().toString();
//                i.setAction(Intent.ACTION_DIAL);
//                        i.setData(Uri.parse(a));
//                        Toast.makeText(getApplicationContext(), "Dialing: "+a, Toast.LENGTH_SHORT).show();
//                        Intent newIntent = Intent.createChooser(i, "Select Dialer");
//                        if (i.resolveActivity(getPackageManager()) != null) {
//                        startActivity(newIntent);
//                        } else {
//                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
//                        }