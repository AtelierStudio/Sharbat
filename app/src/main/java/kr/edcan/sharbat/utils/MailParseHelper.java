package kr.edcan.sharbat.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import io.realm.Realm;
import io.realm.RealmResults;
import kr.edcan.sharbat.models.MailData;

/**
 * Created by Chad on 7/17/16.
 */

public class MailParseHelper {
    Context c;
    Realm realm;

    public MailParseHelper(Context c) {
        this.c = c;
        String[] credentials = {"pop.naver.com", "sedah1999", "intel_inside"};
//        new MailParseClass().execute(credentials[0], credentials[1], credentials[2]);
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<MailData> realmResults = realm.where(MailData.class).findAll();
        Log.e("asdf", realmResults.size()+"");
        
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
            props = new Properties();
            session = Session.getDefaultInstance(props, null);
            try {
                Store store = session.getStore("pop3");
                store.connect(strings[0], strings[1], strings[2]);
                Folder folder = store.getFolder("INBOX");
                folder.open(Folder.READ_ONLY);
                message = folder.getMessages();
                for (int i = message.length - 1; i >= 0; i--) {
                    try {
                        Log.e("asdf", i + "/" + (message.length - 1) + "개 처리중 : " + message[i].getSubject());
                        MailData data = realm.createObject(MailData.class);
                        data.setTitle(message[i].getSubject());
                        data.setContent(message[i].getDescription());
                        data.setReceivedDate(message[i].getReceivedDate());
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
                realm.commitTransaction();
                store.close();
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
