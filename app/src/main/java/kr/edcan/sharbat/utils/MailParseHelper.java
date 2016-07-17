package kr.edcan.sharbat.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
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
    String[] popAddress = new String[]{"smtp.gmail.com", "pop.naver.com", "pop.worksmobile.com"};

    public MailParseHelper(Context c) {
        this.c = c;
        String[] credentials = {"pop.naver.com", "fluorine2015@gmail.com", "duftlagltkfwk!"};
    }

    public static class MailParseClass extends AsyncTask<String, String, Void> {
        Session session;
        Properties props;
        Store store;
        Message[] message;
        int type;
        String id, password;
        Realm realm;
        String[] popAddress = new String[]{"smtp.gmail.com", "pop.naver.com", "pop.worksmobile.com"};


        public MailParseClass(int type, String id, String password) {
            this.type = type;
            this.id = id;
            this.password = password;
        }

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
                store = session.getStore((type == 0) ? "imaps" : "pop3");
                store.connect(popAddress[type], id, password);
                Folder folder = store.getFolder("INBOX");
                folder.open(Folder.READ_ONLY);
                message = folder.getMessages();
                for (int i = message.length - 1; i >= 0; i--) {
                    if (message.length - i > 500) break;
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

    public static class LoginCheckClass extends AsyncTask<String, Boolean, Boolean> {
        Properties props;
        Message[] message;
        Session session;
        int type;
        String[] popAddress = new String[]{"smtp.gmail.com", "pop.naver.com", "pop.worksmobile.com"};
        String id, password;

        public LoginCheckClass(int type, String id, String password) {
            this.type = type;
            this.id = id;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            props = new Properties();
            session = Session.getDefaultInstance(props, null);
            Store store = null;
            try {
                store = session.getStore((type == 0) ? "imaps" : "pop3");
                store.connect(popAddress[type], id, password);
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
