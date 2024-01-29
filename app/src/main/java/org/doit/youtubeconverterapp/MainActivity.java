package org.doit.youtubeconverterapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import org.doit.youtubeconverterapp.fragment.DownloadContentFragment;
import org.doit.youtubeconverterapp.fragment.LoadingAnimationFragment;
import org.doit.youtubeconverterapp.fragment.UncorrectUrlFragment;
import org.doit.youtubeconverterapp.model.youTubeContent.general.YouTubeContentModelGeneral;
import org.doit.youtubeconverterapp.model.youTubeContent.step1.ProcessStep1;
import org.doit.youtubeconverterapp.model.youTubeContent.step1.YouTubeContentModelStep1;
import org.doit.youtubeconverterapp.model.youTubeContent.step2.ProcessStep2;
import org.doit.youtubeconverterapp.model.youTubeContent.step2.YouTubeContentModelStep2;
import org.doit.youtubeconverterapp.utility.Format;
import org.doit.youtubeconverterapp.utility.MobileAds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button input_request;
    private Spinner input_spinner_format;
    private AdView ads_banner_view;
    private LinkedHashMap<String, String> formatMap;
    RewardedAd rewardedAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds mobileAds = new MobileAds();

        mobileAds.initMobileAds(this);

        input_spinner_format = findViewById(R.id.input_spinner_format);
        input_request = findViewById(R.id.input_request);
        ads_banner_view  = findViewById(R.id.ads_banner_view);

        setSpinnerFormat(getFormatKayList());
        setupPrivacyLinks();

        mobileAds.loadBannerAds(ads_banner_view);
        loadRewardAds();

        input_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setLoadingAnimationFragment();
                    onClickRequestButton();
                    mobileAds.showRewardAds(rewardedAd, MainActivity.this);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ;
            }
        });
    }


    //region Set Fragments
    private void setLoadingAnimationFragment(){
        setNewFragment(new LoadingAnimationFragment());
    }
    private void setDownloadContentFragment(YouTubeContentModelGeneral contentModelGeneral){
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        DownloadContentFragment downloadContentFragment = new DownloadContentFragment(contentModelGeneral, downloadManager);
        setNewFragment(downloadContentFragment);
    }
    private void setUncorrectUrlFragment(){
        setNewFragment(new UncorrectUrlFragment());
    }
    private void setNewFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_response, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    //endregion



    //region Utility
    private String getUrl(){
        TextView url = findViewById(R.id.input_url);
        return url.getText().toString();
    }
    private void setSpinnerFormat(List<String> formatList){
        ArrayAdapter<String> spinnerFormatArrayAdapter = new ArrayAdapter<>(this, R.layout.input_spinner_item, formatList);
        input_spinner_format.setAdapter(spinnerFormatArrayAdapter);
    }
    private List<String> getFormatKayList(){
        List<String> formatList =new ArrayList<>();
        Format format = new Format();
        formatMap = format.getFormatMap();

        for ( String key : formatMap.keySet() ) {
            formatList.add(key);
        }
        return  formatList;
    }

    private void setupPrivacyLinks(){
        TextView privacy_terms;
        TextView privacy_policy;

        privacy_terms = findViewById(R.id.privacy_terms);
        privacy_policy = findViewById(R.id.privacy_policy);

        privacy_terms.setMovementMethod(LinkMovementMethod.getInstance());
        privacy_policy.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void  loadRewardAds(){
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-6741442489323230/7773412142",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d("Error Reward ads", loadAdError.toString());
                        rewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                        Log.d(TAG, "Ad was loaded.");
                    }
                });
    }
    //endregion

    private void onClickRequestButton() throws InterruptedException, IOException {
        String youTubeUrl = getUrl();
        String formatKey = input_spinner_format.getSelectedItem().toString();
        String formatValue = formatMap.get(formatKey);

        if(!youTubeUrl.equals("")) {
            runGetAsyncModelStep1(youTubeUrl, formatValue);
        }
        else {
            setUncorrectUrlFragment();
        }
    }


    //region Async Task
    private void runGetAsyncModelStep1(String youTubeUrl, String formatValue){
        ProcessStep1 processStep1 = new ProcessStep1();

        new AsyncTask<Void, Void, YouTubeContentModelStep1>(){
            @Override
            protected void onPostExecute(YouTubeContentModelStep1 contentModelStep1) {
                super.onPostExecute(contentModelStep1);
                runGetAsyncModelStep2(contentModelStep1);
            }

            @Override
            protected YouTubeContentModelStep1 doInBackground(Void... voids) {

                try {
                    return processStep1.getContentModelStep1(youTubeUrl, formatValue);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        }.execute();
    }

    private void runGetAsyncModelStep2(YouTubeContentModelStep1 contentModelStep1){
        ProcessStep2 processStep2 = new ProcessStep2();

        new AsyncTask<Void, Void, YouTubeContentModelStep2>(){

            @Override
            protected void onPostExecute(YouTubeContentModelStep2 contentModelStep2) {
                super.onPostExecute(contentModelStep2);
                checkResult(contentModelStep1, contentModelStep2);
            }

            @Override
            protected YouTubeContentModelStep2 doInBackground(Void... voids) {
                try {
                    return processStep2.getContentModelStep2(contentModelStep1.getUniqueId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
        }.execute();
    }

    //endregion

    private void checkResult(YouTubeContentModelStep1 contentModelStep1, YouTubeContentModelStep2 contentModelStep2){

        YouTubeContentModelGeneral contentModelGeneral = new YouTubeContentModelGeneral();


        if (contentModelStep1 != null && contentModelStep2 != null && contentModelStep2.getDownloadUrl() != null) {
            contentModelGeneral.setTitle(contentModelStep1.getYouTubeContentModelInfoStep1().getTitle());
            contentModelGeneral.setImage(contentModelStep1.getYouTubeContentModelInfoStep1().getImage());
            contentModelGeneral.setExtensionFile(contentModelStep1.getExtensionFile());
            contentModelGeneral.setDownloadUrl(contentModelStep2.getDownloadUrl());

            setDownloadContentFragment(contentModelGeneral);
        }else {
            setUncorrectUrlFragment();
        }

        Log.v("My", contentModelGeneral.toString());
    }
}