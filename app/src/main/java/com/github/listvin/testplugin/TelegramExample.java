package com.github.listvin.testplugin;

import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Fyodor on 23.07.2016.
 */
public class TelegramExample {

    public static void main(){
        try {
            URL url = new URL("https://api.telegram.org/bot189231950:AAGM22LeZUpreQgXybQmg5NTWS9sB6ApC-s/sendMessage?chat_id=34948070&text=HI+out+of+Unity+under+Android...");
            ((HttpsURLConnection) url.openConnection()).getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
