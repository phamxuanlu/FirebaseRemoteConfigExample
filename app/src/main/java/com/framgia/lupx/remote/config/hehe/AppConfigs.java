package com.framgia.lupx.remote.config.hehe;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/**
 * Created by pham.xuan.lu on 11/28/16.
 */

public class AppConfigs {
    private static AppConfigs _instance;
    private FirebaseRemoteConfig config;

    private AppConfigs(){

    }

    public FirebaseRemoteConfig getConfig(){
        return this.config;
    }

    public void setConfig(FirebaseRemoteConfig config){
        this.config = config;
    }

    public static AppConfigs getInstance(){
        if(_instance==null){
            _instance = new AppConfigs();
        }
        return _instance;
    }
}
