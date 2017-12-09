package com.example.admin.lazada.Model.RegisterLogin;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

/**
 * Created by ADMIN on 12/6/2017.
 */

public class LoginModel {

    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public AccessToken getAccessToken() {

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };

        accessToken = AccessToken.getCurrentAccessToken();

        return accessToken;

    }

    public void destroyTokenTracker(){
        accessTokenTracker.stopTracking();
    }
}
