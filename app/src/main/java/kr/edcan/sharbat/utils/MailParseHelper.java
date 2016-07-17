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
import kr.edcan.sharbat.models.MailData;

/**
 * Created by Chad on 7/17/16.
 */

public class MailParseHelper {
    Context c;
    Realm realm;

    public MailParseHelper(Context c) {
        this.c = c;
        realm = Realm.getDefaultInstance();
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
            realm.beginTransaction();
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
