package com.kokhouser.napod.ui;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kokhouser.napod.R;
import com.kokhouser.napod.api.APICaller;
import com.kokhouser.napod.models.Astropic;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements MainView {

    //Dependencies
    private static String apiKey = "zOIkuYtyT6JwZSG06BKhc9gD4GwNEVnMYuSPwLUN";
    private Astropic currentPicture;
    private APICaller apiCaller = new APICaller(this);

    //View injections, using Butterknife
    @InjectView(R.id.imageView)
    ImageView imageView;

    @InjectView(R.id.icon_download)
    ImageView downloadView;

    @InjectView(R.id.progressbar)
    ProgressBar progressBar;

    @InjectView(R.id.picture_title)
    TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        try{
            apiCaller.callPictureAPIWithKey("DEMO_KEY");
        }
        catch (Exception e){
            e.printStackTrace();
            try {
                apiCaller.callPictureAPIWithKey(apiKey);
            }
            catch (Exception f) {
                f.printStackTrace();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void setPicture(Astropic picture) {
        currentPicture = picture;
        updateImage();
        updateTitle();
        setListeners();
    }

    private void setListeners(){
        downloadView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage();
            }
        });
        imageView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeLeft() {
                try{
                    apiCaller.getRandomPicture("DEMO_KEY");
                }
                catch (Exception e){
                    e.printStackTrace();
                    try {
                        apiCaller.getRandomPicture(apiKey);
                    }
                    catch (Exception f) {
                        f.printStackTrace();
                    }
                }
            }

            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    // Obtaining bitmap resource using Picasso and loading into imageview
    // May be prudent to move this to another abstraction?
    private void updateImage(){
        Picasso.with(this)
                .load(currentPicture.getUrl())
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE) //Reduces memory usage by 50%, we likely won't use images after refresh.
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("Succeeded", "Loaded image");
                        hideProgress();
                    }

                    @Override
                    public void onError() {
                        Log.d("Failed", "Didn't load image");
                        hideProgress();
                    }
                });
    }

    public void updateImageWithTarget(Target target){
        Picasso.with(this)
                .load(currentPicture.getUrl())
                .into(target);
    }

    public void showSnackBar(String message){
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
                .show();
    }

    public void showDownloadedSnackBar(final String fileName){
        Snackbar.make(findViewById(android.R.id.content), "Image downloaded", Snackbar.LENGTH_LONG)
                .setAction("View", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(new File(fileName)), "image/*");
                        startActivity(intent);
                    }
                })
                .show();
    }

    private void downloadImage(){
        apiCaller.downloadImage(this, currentPicture);
    }

    private void updateTitle(){
        titleView.setText(currentPicture.getTitle());
    }
}
