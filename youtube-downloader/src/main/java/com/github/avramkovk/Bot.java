package com.github.avramkovk;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class Bot {

    public static void main(String[] args) {
        String apiToken = "1366521247:AAGILBnxFHouYyOK4Aycsv-eDuwmW980GwY";
        TelegramBot bot = new TelegramBot(apiToken);
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                Message message = update.message();

                if (message == null) {
                    continue;
                }

                long chatId = message.chat().id();
                String incomeMessage = message.text();
                String outcomeMessage = "Your message was:\n" + incomeMessage;
                bot.execute(new SendMessage(chatId, outcomeMessage));
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
