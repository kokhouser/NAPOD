package com.kokhouser.napod.api;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import com.kokhouser.napod.models.Astropic;
import com.kokhouser.napod.ui.MainActivity;
import com.kokhouser.napod.ui.MainView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hkok on 7/28/2015.
 * Class to handle API requests
 */

public class APICaller {

    private static final String BASE_URL = "https://api.nasa.gov";
    private RestAdapter restAdapter;
    private APODAPIInterface apodapiInterface;
    private MainView mainView;

    public APICaller(MainView mainView1){
        mainView = mainView1;
        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .build();
        apodapiInterface = restAdapter.create(APODAPIInterface.class);
    }

    public void callPictureAPIWithKey(final String key){
        mainView.showProgress();
        apodapiInterface.getPictureWithKey(key, new Callback<Astropic>() {
            @Override
            public void success(Astropic astropic, Response response) {
                if (astropic.getMediaType() != null && astropic.getMediaType().equals("video")) {
                    getRandomPicture(key);
                } else {
                    mainView.setPicture(astropic);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                mainView.hideProgress();
            }
        });
    }

    public void getRandomPicture(String apiKey){
        Random rand = new Random();
        int randomNum = rand.nextInt(365);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -randomNum);
        String yesterday = dateFormat.format(cal.getTime());
        callPictureAPIWithKeyAndDate(apiKey, yesterday);
    }

    public void callPictureAPIWithKeyAndDate(final String key, String date){
        mainView.showProgress();
        apodapiInterface.getPictureWithKeyAndDate(key, date, new Callback<Astropic>() {
            @Override
            public void success(Astropic astropic, Response response) {
                if (astropic.getMediaType() != null && astropic.getMediaType().equals("video")) {
                    getRandomPicture(key);
                } else {
                    mainView.setPicture(astropic);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                mainView.hideProgress();
            }
        });
    }

    public void downloadImage(final MainActivity parentActivity, final Astropic currentPicture){

        Target target = new Target() {

            @Override
            public void onPrepareLoad(Drawable arg0) {
                // Do nothing
            }

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom arg1) {
                try {
                        String fileName1 = currentPicture.getTitle() + ".jpg";
                        final String fileName = fileName1.replace(" ","_");
                        File file = new File(Environment.getExternalStorageDirectory() + File.separator+ "NAPOD");
                        Log.d("File Path", file.getAbsolutePath());
                        file.mkdirs();
                        file = new File(Environment.getExternalStorageDirectory() + File.separator+ "NAPOD" + File.separator + fileName);
                        file.createNewFile();
                        FileOutputStream ostream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                        ostream.close();
                    parentActivity.showDownloadedSnackBar(file.getAbsolutePath());
                } catch (Exception e){
                e.printStackTrace();
                parentActivity.showSnackBar("Error occurred with download.");
                }
            }
            @Override
            public void onBitmapFailed(Drawable arg0) {
                parentActivity.showSnackBar("Error occurred with download.");
            }
        };
        parentActivity.updateImageWithTarget(target);
    }

}
