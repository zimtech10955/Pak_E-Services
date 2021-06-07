package com.example.pake_services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    String TAG = "tag";
    String flag="on";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.splashstatusbar, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.splashstatusbar));
        }


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor));
        }



        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        flag=preferences.getString("flag","");
      //  final SharedPreferences.Editor editor = preferences.edit();

//editor.putString("flag","on");


        // Write a message to the database
        DatabaseReference myRef = database.getReference("control");

        //  myRef.setValue("Hello, World!");

        database.getReference("interstial").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = snapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                Verabls.intestial = (value);
                Log.d(TAG, "control is Value is: " + Verabls.intestial);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        database.getReference("banner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = snapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                Verabls.banner = (value);
                Log.d(TAG, "control is Value is: " + Verabls.banner);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        database.getReference("native").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = snapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                Verabls.setNative(value.trim());
                Log.d(TAG, "control is Value is: " + Verabls.Native);

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


// Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                Verabls.control = Integer.parseInt(value);
                Log.d(TAG, "control is Value is: " + Verabls.control);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (flag.trim().contains("off")) {
                    startActivity(new Intent(getApplicationContext(), MainPage.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), privacypolicy.class));
                    finish();

                }

            }
        }, 2500);

    }
}