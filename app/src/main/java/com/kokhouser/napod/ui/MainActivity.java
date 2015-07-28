package com.kokhouser.napod.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.kokhouser.napod.R;
import com.kokhouser.napod.api.APICaller;
import com.kokhouser.napod.models.Astropic;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements MainView {

    private static String apiKey = "zOIkuYtyT6JwZSG06BKhc9gD4GwNEVnMYuSPwLUN";
    private Astropic currentPicture;
    boolean isImageFitToScreen;
    private ImageView imageView;
    private ImageView refreshView;
    private APICaller apiCaller = new APICaller(this);
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        refreshView = (ImageView) findViewById(R.id.icon_refresh);
        imageView = (ImageView) findViewById(R.id.imageView);
        setListeners();
        isImageFitToScreen = false;
        apiCaller.callPictureAPIWithKey(apiKey);
        //getRandomPicture();
    }

    private void setListeners(){
        refreshView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCaller.getRandomPicture(apiKey);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    }

    private void updateImage(){
        Picasso.with(this)
                .load(currentPicture.getUrl())
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        hideProgress();
                    }

                    @Override
                    public void onError() {
                        hideProgress();
                    }
                });
    }

    private void updateTitle(){
        TextView titleView = (TextView) findViewById(R.id.picture_title);
        titleView.setText(currentPicture.getTitle());
    }


}
