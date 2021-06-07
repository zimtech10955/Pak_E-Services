package com.example.pake_services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;

import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import  com.example.pake_services.Verabls;
import java.util.ArrayList;
import java.util.List;



public class MainPage extends AppCompatActivity {
    CardView simphone, driving, finance, criminal, nadra, sarqa, zamanti, fir, wanted;
    FirebaseDatabase database;


    //  private AdView adView;
    private final String TAG = MainActivity.class.getSimpleName();
    public InterstitialAd interstitialAd;







      //  private final String TAG = "NativeAdActivity".getClass().getSimpleName();
    private NativeAd nativeAd;
    private NativeAdLayout nativeAdLayout;
    private LinearLayout adView;



    private NativeAd nativeAd1;
    private NativeAdLayout nativeAdLayout1;
    private LinearLayout adView1;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getSupportActionBar().hide();
        simphone = findViewById(R.id.simphone);
        driving = findViewById(R.id.driving);
        finance = findViewById(R.id.finance);
        criminal = findViewById(R.id.criminalrecord);
        nadra = findViewById(R.id.nadra);
        sarqa = findViewById(R.id.sarqa);
        zamanti = findViewById(R.id.zamanati);
        fir = findViewById(R.id.fir);
        wanted = findViewById(R.id.wanted);

        database = FirebaseDatabase.getInstance();


findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {




        Intent i = new Intent(

                android.content.Intent.ACTION_SEND);

        i.setType("text/plain");

        i.putExtra(

                android.content.Intent.EXTRA_TEXT, "Check out the App at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);

        startActivity(Intent.createChooser(

                i,

                "Title of your share dialog"));







    }
});
findViewById(R.id.privacypolicy).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), privacypolicy.class));
        finish();
    }
});

        simphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if(Verabls.flag())
    loadad();
                incresecount();


                Intent intent = new Intent(getApplicationContext(), SimDatabase.class);
                startActivity(intent);
            }
        });
        driving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Verabls.flag())
                    loadad();
                incresecount();

                Intent intent = new Intent(getApplicationContext(), DrivingActivity.class);
                startActivity(intent);
            }
        });
        finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Verabls.flag())
                    loadad();
                incresecount();

                Intent intent = new Intent(getApplicationContext(), VehicleData.class);
                startActivity(intent);
            }
        });
        criminal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Verabls.flag())
                    loadad();
                incresecount();
                startSite("criminal");
            }
        });
        nadra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Verabls.flag())
                    loadad();
                incresecount();
                startSite("nadra");
            }
        });
        sarqa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Verabls.flag())
                    loadad();
                incresecount();
                startSite("sarqa");
            }
        });
        zamanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Verabls.flag())
                    loadad();
                incresecount();
                startSite("zamanti");
            }
        });
        fir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Verabls.flag())
                    loadad();
                incresecount();
                startSite("fir");
            }
        });
        wanted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Verabls.flag())
                    loadad();
                incresecount();
                startSite("wanted");
            }
        });


       // loadad();


        loadNativeAd();
       loadNativeAd1();
    }

    private void incresecount() {
        Verabls.count++;
       // Toast.makeText(MainPage.this, ""+Verabls.count, Toast.LENGTH_SHORT).show();
    }


    // start a website by pressing a key

    void startSite(String key) {
        try {
            database.getReference().child("Main Websites").child(key)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {
                                WebModel webModel = snapshot.getValue(WebModel.class);
                                String url = webModel.getLink();
//                            Toast.makeText(SimDatabase.this, "URL: " + url, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), WebLauncher.class);
                                intent.putExtra("site", url);
                                startActivity(intent);
                            } catch (Exception e) {
                                Toast.makeText(MainPage.this, "Sorry! This Feature cannot be accessed at this moment", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(this, "Cannot Load The Site!", Toast.LENGTH_SHORT).show();
        }
    }


    // to initialize the buttons back links with firebase
    void createButtonsLinks() {
        String links = "http://freesimdata.com/";
        String[] strings = {"simphone", "driving", "finance", "criminal", "nadra", "sarqa", "zamanti", "fir", "wanted"};
        ArrayList<WebModel> models = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            WebModel model = new WebModel();
            model.setLink(links);
            models.add(model);
        }
        try {
            for (int i = 0; i < strings.length; i++) {
                database.getReference().child("Main Websites")
                        .child(strings[i])
                        .setValue(models.get(i)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainPage.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }





    // loadinng native Ad

    private void loadNativeAd() {

        // initializing nativeAd object

        //  nativeAd = new NativeAd(this,"VID_HD_9_16_39S_APP_INSTALL#399020257848931_459615015122788");
        nativeAd = new NativeAd(this,Verabls.getNative());


        // creating  NativeAdListener




        NativeAdListener nativeAdListener = new NativeAdListener() {

            @Override

            public void onMediaDownloaded(Ad ad) {

                // showing Toast message

               // Toast.makeText(MainPage.this,"onMediaDownloaded",Toast.LENGTH_SHORT).show();

            }

            @Override

            public void onError(Ad ad, AdError adError) {

                // showing Toast message

            //    Toast.makeText(MainPage.this, "onError", Toast.LENGTH_SHORT).show();

            }

            @Override

            public void onAdLoaded(Ad ad) {
                findViewById(R.id.adinfotext).setVisibility(View.GONE);
                // showing Toast message

               // Toast.makeText(MainPage.this, "onAdLoaded", Toast.LENGTH_SHORT).show();

                if (nativeAd == null || nativeAd != ad) {

                    return;

                }

                // Inflate Native Ad into Container

                inflateAd(nativeAd);

            }

            @Override

            public void onAdClicked(Ad ad) {

                // showing Toast message

             //   Toast.makeText(MainPage.this, "onAdClicked", Toast.LENGTH_SHORT).show();

            }

            @Override

            public void onLoggingImpression(Ad ad) {

                // showing Toast message

              //  Toast.makeText(MainPage.this, "onLoggingImpression",Toast.LENGTH_SHORT).show();

            }

        };

        // Load an ad

        nativeAd.loadAd(

                nativeAd.buildLoadAdConfig()

                        .withAdListener(nativeAdListener)

                        .build());

    }

    // inflating the Ad

    void inflateAd(NativeAd nativeAd) {

        // unregister the native Ad View

        nativeAd.unregisterView();

        // Add the Ad view into the ad container.

        nativeAdLayout = findViewById(R.id.native_ad_container);

        LayoutInflater inflater = LayoutInflater.from(MainPage.this);

        // Inflate the Ad view.

        adView = (LinearLayout) inflater.inflate(R.layout.native_ad_layout, nativeAdLayout, false);

        // adding view

        nativeAdLayout.addView(adView);

        // Add the AdOptionsView

        LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container);

        AdOptionsView adOptionsView = new AdOptionsView(MainPage.this, nativeAd, nativeAdLayout);

        adChoicesContainer.removeAllViews();

        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.

        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);

        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);

        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);

        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);

        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);

        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);

        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Setting  the Text.

        nativeAdTitle.setText(nativeAd.getAdvertiserName());

        nativeAdBody.setText(nativeAd.getAdBodyText());

        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());

        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);

        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());

        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views

        List<View> clickableViews = new ArrayList<>();

        clickableViews.add(nativeAdTitle);

        clickableViews.add(nativeAdCallToAction);

        // Register the Title and  button to listen for clicks.

        nativeAd.registerViewForInteraction(

                adView, nativeAdMedia, nativeAdIcon, clickableViews);

    }




    // native ad 1




    // loadinng native Ad

    private void loadNativeAd1() {

        // initializing nativeAd object

        //  nativeAd = new NativeAd(this,"VID_HD_9_16_39S_APP_INSTALL#399020257848931_459615015122788");
        nativeAd1 = new NativeAd(this,Verabls.getNative());


        // creating  NativeAdListener




        NativeAdListener nativeAdListener1 = new NativeAdListener() {

            @Override

            public void onMediaDownloaded(Ad ad) {

                // showing Toast message

             //   Toast.makeText(MainPage.this,"onMediaDownloaded",Toast.LENGTH_SHORT).show();

            }

            @Override

            public void onError(Ad ad, AdError adError) {

                // showing Toast message

             //   Toast.makeText(MainPage.this, "onError", /t.LENGTH_SHORT).show();

            }

            @Override

            public void onAdLoaded(Ad ad) {
findViewById(R.id.adinfotext1).setVisibility(View.GONE);
                // showing Toast message

             //   Toast.makeText(MainPage.this, "onAdLoaded", Toast.LENGTH_SHORT).show();

                if (nativeAd1 == null || nativeAd1 != ad) {

                    return;

                }

                // Inflate Native Ad into Container

                inflateAd1(nativeAd1);

            }

            @Override

            public void onAdClicked(Ad ad) {

                // showing Toast message

          //      Toast.makeText(MainPage.this, "onAdClicked", Toast.LENGTH_SHORT).show();

            }

            @Override

            public void onLoggingImpression(Ad ad) {

                // showing Toast message

             //   Toast.makeText(MainPage.this, "onLoggingImpression",Toast.LENGTH_SHORT).show();

            }

        };

        // Load an ad

        nativeAd1.loadAd(

                nativeAd1.buildLoadAdConfig()

                        .withAdListener(nativeAdListener1)

                        .build());

    }

    // inflating the Ad

    void inflateAd1(NativeAd nativeAd1) {

        // unregister the native Ad View

        nativeAd1.unregisterView();

        // Add the Ad view into the ad container.

        nativeAdLayout1 = findViewById(R.id.native_ad_container1);

        LayoutInflater inflater = LayoutInflater.from(MainPage.this);

        // Inflate the Ad view.

        adView1 = (LinearLayout) inflater.inflate(R.layout.native_ad_layout, nativeAdLayout1, false);

        // adding view

        nativeAdLayout1.addView(adView1);

        // Add the AdOptionsView

        LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container);

        AdOptionsView adOptionsView = new AdOptionsView(MainPage.this, nativeAd1, nativeAdLayout1);

        adChoicesContainer.removeAllViews();

        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.

        MediaView nativeAdIcon = adView1.findViewById(R.id.native_ad_icon);

        TextView nativeAdTitle = adView1.findViewById(R.id.native_ad_title);

        MediaView nativeAdMedia = adView1.findViewById(R.id.native_ad_media);

        TextView nativeAdSocialContext = adView1.findViewById(R.id.native_ad_social_context);

        TextView nativeAdBody = adView1.findViewById(R.id.native_ad_body);

        TextView sponsoredLabel = adView1.findViewById(R.id.native_ad_sponsored_label);

        Button nativeAdCallToAction = adView1.findViewById(R.id.native_ad_call_to_action);

        // Setting  the Text.

        nativeAdTitle.setText(nativeAd1.getAdvertiserName());

        nativeAdBody.setText(nativeAd1.getAdBodyText());

        nativeAdSocialContext.setText(nativeAd1.getAdSocialContext());

        nativeAdCallToAction.setVisibility(nativeAd1.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);

        nativeAdCallToAction.setText(nativeAd1.getAdCallToAction());

        sponsoredLabel.setText(nativeAd1.getSponsoredTranslation());

        // Create a list of clickable views

        List<View> clickableViews = new ArrayList<>();

        clickableViews.add(nativeAdTitle);

        clickableViews.add(nativeAdCallToAction);

        // Register the Title and  button to listen for clicks.

        nativeAd1.registerViewForInteraction(

                adView1, nativeAdMedia, nativeAdIcon, clickableViews);

    }
















    //***********************************in ad start



    private void loadad() {
        //interstitialAd = new InterstitialAd(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID");//test add

        interstitialAd = new InterstitialAd(this, Verabls.getIntestial());
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
                Log.e(TAG, "Interstitial ad failed to load: " +adError.getErrorMessage());
                Toast.makeText(MainPage.this, "Please Connect to Internet first", Toast.LENGTH_LONG).show();




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






    //***************************************in t ad end
















    @Override
    public void onDestroy() {
        if (nativeAd != null) {
            nativeAd.destroy();

        }
        if (nativeAd1 != null) {
            nativeAd1.destroy();

        }

        if (interstitialAd != null) {
            interstitialAd.destroy();
        }

        super.onDestroy();
    }












}