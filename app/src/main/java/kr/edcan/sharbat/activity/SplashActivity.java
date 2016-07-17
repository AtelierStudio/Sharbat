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

    class MailParseClass extends AsyncTask<String, String, Void> {

        String host, username, password;
        Properties props;
        Message[] message;
        Session session;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            Date date = new Date(System.currentTimeMillis());
            Log.e("asdf", "start");
            host = "pop.naver.com";
            username = "sedah1999";
            password = "intel_inside";
            props = new Properties();
            session = Session.getDefaultInstance(props, null);
            try {
                Log.e("asdf", "start");
                Store store = session.getStore("pop3");
                store.connect(host, username, password);
                Folder folder = store.getFolder("INBOX");
                Log.e("asdf", folder.exists() + "" + folder.getMessageCount() + "");
                folder.open(Folder.READ_ONLY);
                message = folder.getMessages();
                for (int i = message.length - 1; i >= 0; i--) {
                    try {
                        Log.e("asdf", i + "/" + (message.length - 1) + "개 처리중 : " + message[i].getSubject());
                        MailData data = realm.createObject(MailData.class);
                        data.setTitle(message[i].getSubject());
                        data.setContent(message[i].getDescription());
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
                realm.commitTransaction();
                store.close();
                Date finalDate = new Date(System.currentTimeMillis());
                int second = finalDate.getSeconds() - date.getSeconds();
                Log.e("asdf", "총 " + second + "초 걸림");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}

