package com.github.listvin.testplugin;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import java.util.List;

/**
 * Created by Fyodor on 24.07.2016.
 */
public class TelegramExample_pengrad {

    public static void main() {
        //building a bot
        TelegramBot bot = TelegramBotAdapter.build("183780134:AAFv5wWPGEqQS5girocWl8te1r2f-N86M3s");

        //this cycle is querying server with long polling
        int offset = (int) 0;
        boolean shutdown = false;
        while (!shutdown) {
            GetUpdatesResponse updatesResponse = bot.execute(new GetUpdates().offset(offset).timeout(2));
            List<Update> updates = updatesResponse.updates();

            for (Update update : updates) {
                    offset = Math.max(update.updateId()+1, offset);
                    Message message = update.message();

                    String s = message.text() == null ? "<no text assigned>" : message.text();

                if (s.equals("/shutdown")) {
//                    shutdown = message.date() >= System.tim
                } else {
                    bot.execute(new SendMessage(message.chat().id(), s));
                }
            }
        }
    }
}
