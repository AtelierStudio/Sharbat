package kr.edcan.sharbat.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import io.realm.Realm;
import io.realm.RealmResults;
import kr.edcan.sharbat.R;
import kr.edcan.sharbat.models.MailData;

public class SplashActivity extends AppCompatActivity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        new MailParseClass().execute();
//        realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        RealmResults<MailData> result2 = realm.where(MailData.class)
//                .findAll();
//        for(MailData d : result2){
//            Log.e("asdf", d.getTitle());
//        }
//        realm.commitTransaction();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 1000);
    }

}

