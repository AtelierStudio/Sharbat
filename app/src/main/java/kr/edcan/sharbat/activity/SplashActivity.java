package kr.edcan.sharbat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;
import kr.edcan.sharbat.R;
import kr.edcan.sharbat.utils.DataManager;

public class SplashActivity extends AppCompatActivity {

    DataManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        manager = new DataManager();
        manager.initializeManager(getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (manager.getIsFirst()) {
                    startActivity(new Intent(getApplicationContext(), TutorialActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        }, 1000);
    }
}

