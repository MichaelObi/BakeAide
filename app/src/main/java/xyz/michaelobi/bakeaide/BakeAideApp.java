package xyz.michaelobi.bakeaide;

import android.app.Application;

import io.realm.Realm;

/**
 * BakeAide
 * Michael Obi
 * 02 07 2017 4:15 PM
 */

public class BakeAideApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
