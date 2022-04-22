package ru.sal4i.sal4ibot.listeners;

import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

@SuppressWarnings({"FieldCanBeLocal", "unused", "unchecked", "ResultOfMethodCallIgnored"})
public class ShowcaseListener extends ListenerAdapter {
    //    private final Bot bot;
    private final HashMap<String, String> cache = new HashMap<>();

    public ShowcaseListener(/*Bot bot*/) {
//        this.bot = bot;
        File file;
        try {
            file = makeFile();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(file)) {
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            cache.putAll(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getChannel().getId().equals("959844012926312498")) return;

        event.getMessage().addReaction("U+1F44D").queue();
        event.getMessage().addReaction("U+1F44E").queue();

        if (event.getMember() != null) cache.put(event.getMessageId(), event.getMember().getId());
    }

    @Override
    public void onMessageDelete(MessageDeleteEvent event) {
        if (!event.getChannel().getId().equals("959844012926312498")) return;
        cache.remove(event.getMessageId());
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (!event.getChannel().getId().equals("959844012926312498")) return;
        try {
            if (event.getUser() == null || event.getUser().isBot()) return;
        } catch (Exception exception) {
            return;
        }
        if (event.getMember() != null && cache.get(event.getMessageId()) != null && cache.get(event.getMessageId()).equals(event.getMember().getId()))
            event.getReaction().removeReaction(event.getUser()).queue();
    }


    @Override
    public void onShutdown(@NotNull ShutdownEvent event) {
        File file;
        try {
            file = makeFile();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(cache);

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File makeFile() throws IOException {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File("data/data.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
}
