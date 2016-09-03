package com.github.listvin.testplugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 * @author listvin
 *         14.08.16
 */
public class Server implements Runnable{
    private static final Random random = new Random();
	
	/*
	Developer or any one, just create your own bot
	
	Done! Congratulations on your new bot. You will find it at telegram.me/vrtelealphabot.
	You can now add a description, about section and profile picture for your bot, see /help for a list of commands.
	By the way, when you've finished creating your cool bot, ping our Bot Support if you want a better username for it.
	Just make sure the bot is fully operational before you do this.

    Use this token to access the HTTP API:
    210671806:AAEgAYcJVTeCULwOxqBz_aBdIJMjZZHeUi8
	*/

    private static final String teleUrl = "https://api.telegram.org/bot210671806:AAEgAYcJVTeCULwOxqBz_aBdIJMjZZHeUi8-s/getUpdates";
    private static Commands commands;
    private static int lastHashCode = -1;

    public static void start(Commands commandsImpl) {
        Server.commands = commandsImpl;
        if (commands == null) {
            System.out.println("object from unity was null");
            return;
        }
        new Thread(new Server()).start();
    }

    private static String getStringFromInputStream(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String s;
        try {
            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    public void run() {
        while (true) {
            try {
                URL url = new URL(teleUrl);
                URLConnection urlConnection = url.openConnection();
                String response = getStringFromInputStream(urlConnection.getInputStream());
                if (!response.isEmpty() && lastHashCode != response.hashCode()) {
                    commands.repaint();
                    System.out.println("change " + System.currentTimeMillis());
                    lastHashCode = response.hashCode();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
