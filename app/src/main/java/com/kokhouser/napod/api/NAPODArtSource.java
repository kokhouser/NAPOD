package com.kokhouser.napod.api;

import android.content.Intent;
import android.net.Uri;

import com.google.android.apps.muzei.api.Artwork;
import com.google.android.apps.muzei.api.MuzeiArtSource;
import com.kokhouser.napod.models.Astropic;
import com.kokhouser.napod.ui.MainView;

/**
 * Created by hkok on 7/30/2015.
 */
public class NAPODArtSource extends MuzeiArtSource implements MainView {
    private static final String SOURCE_NAME = "NAPODSource";
    private static String apiKey = "zOIkuYtyT6JwZSG06BKhc9gD4GwNEVnMYuSPwLUN";
    APICaller apiCaller;
    private Astropic currentPicture;

    public NAPODArtSource() {
        super(SOURCE_NAME);
        apiCaller = new APICaller(this);
    }

    @Override
    protected void onUpdate(int reason) {
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
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setPicture(Astropic picture) {
        currentPicture = picture;
        publishArtwork(new Artwork.Builder()
                .imageUri(Uri.parse(currentPicture.getUrl()))
                .title(currentPicture.getTitle())
                .viewIntent(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://apod.nasa.gov/apod/astropix.html")))
                .build());
    }
}
