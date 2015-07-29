package com.kokhouser.napod.api;

import com.kokhouser.napod.models.Astropic;
import com.kokhouser.napod.ui.MainView;

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
        apodapiInterface.getPictureWithKey(key, new Callback<Astropic>() {
            @Override
            public void success(Astropic astropic, Response response) {
                if (astropic.getMediaType()!=null && astropic.getMediaType().equals("video")){
                    getRandomPicture(key);
                }
                else{
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
                if (astropic.getMediaType()!=null && astropic.getMediaType().equals("video")){
                    getRandomPicture(key);
                }
                else{
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

}
