package com.example.pake_services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DrivingActivity extends AppCompatActivity {
    FirebaseDatabase database;
    Button btn1, btn2, btn3, btn4;
    private AdView adView;
    //  private AdView adView;
    private final String TAG = MainActivity.class.getSimpleName();
    public InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving);


        // Instantiate an AdView object.
        // NOTE: The placement ID from the Facebook Monetization Manager identifies your App.
        // To get test ads, add IMG_16_9_APP_INSTALL# to your placement id. Remove this when your app is ready to serve real ads.

        adView = new AdView(this, Verabls.banner, AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();


        database = FirebaseDatabase.getInstance();
        btn1 = findViewById(R.id.btn31);
        btn2 = findViewById(R.id.btn32);
        btn3 = findViewById(R.id.btn33);
        btn4 = findViewById(R.id.btn34);
        database = FirebaseDatabase.getInstance();

//        createButtonsLinks();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Verabls.flag())
                    loadad();
                incresecount();
                startSite(btn1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Verabls.flag())
                    loadad();
                incresecount();
                startSite(btn2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Verabls.flag())
                    loadad();
                incresecount();
                startSite(btn3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Verabls.flag())
                    loadad();
                incresecount();
                startSite(btn4);
            }
        });
    }

    private void incresecount() {
        Verabls.count++;
    //    Toast.makeText(DrivingActivity.this, ""+Verabls.count, Toast.LENGTH_SHORT).show();
    }

    void startSite(Button btn) {
        String key = btn.getText().toString();
        try {
            database.getReference().child("Driving Websites").child(key)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try{WebModel webModel = snapshot.getValue(WebModel.class);
                                String url = webModel.getLink();
//                            Toast.makeText(SimDatabase.this, "URL: " + url, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), WebLauncher.class);
                                intent.putExtra("site", url);
                                startActivity(intent);}
                            catch (Exception e){
                                Toast.makeText(DrivingActivity.this, "Sorry! This Feature cannot be accessed at this moment", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
        } catch (Exception e){
            Toast.makeText(this, "Cannot Load The Site!", Toast.LENGTH_SHORT).show();
        }
    }


    void createButtonsLinks(){
        String links = "https://google.com";
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        ArrayList<WebModel> models = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            WebModel model = new WebModel();
            model.setLink(links);
            models.add(model);
        }
        try {
            for (int i = 0; i < 4; i++) {
                database.getReference().child("Driving Websites")
                        .child(buttons.get(i).getText().toString())
                        .setValue(models.get(i)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(DrivingActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadad() {
        //interstitialAd = new InterstitialAd(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID");//test add

        interstitialAd = new InterstitialAd(this,Verabls.getIntestial());
//   237530441306988_237532874640078

        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");


            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
                Toast.makeText(DrivingActivity.this, "Please Connect to Internet first", Toast.LENGTH_LONG).show();


            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad


                interstitialAd.show();


            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());
    }






    @Override
    public void onDestroy() {
      /*  if (nativeAd != null) {
            nativeAd.destroy();

        }
        if (nativeAd1 != null) {
            nativeAd1.destroy();

        }*/

        if (interstitialAd != null) {
            interstitialAd.destroy();
        }

        super.onDestroy();
    }





}