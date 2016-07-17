package kr.edcan.sharbat.activity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Chad on 7/17/16.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
