package ru.sal4i.sal4ibot.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class HelpChannelsListener extends ListenerAdapter {
    private final List<String> helpChannelsIds = List.of("954625312128401438", "954632831567527966", "958002349014663228", "964037819146313728");

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        if (!helpChannelsIds.contains(channel.getId())) return;

        User author = event.getAuthor();
        if (author.isBot() || author.isSystem()) return;
        Message message = event.getMessage();

        String contentDisplay = message.getContentDisplay();
        String s = contentDisplay.toLowerCase();
        if (contentDisplay.equalsIgnoreCase("i need help") || ((s.contains("i need help") || s.contains("help me")) && contentDisplay.split(" ").length < 6)) {

            message.reply("There's no time to wait! Ask your question, " + author.getAsMention() + "\nhttps://dontasktoask.com/").queue();
        } else if (contentDisplay.equalsIgnoreCase("hello") || contentDisplay.equalsIgnoreCase("hi")) {
            message.reply("https://nohello.net/").queue();
        }
    }
}
