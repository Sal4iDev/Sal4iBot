package ru.sal4i.sal4ibot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import ru.sal4i.sal4ibot.listeners.CommandsListener;
import ru.sal4i.sal4ibot.listeners.ShowcaseListener;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Properties;

public class Bot {
    private JDA jda;

    protected Bot() {
    }

    public JDA run() throws LoginException, IOException {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("/settings.properties"));

        jda = JDABuilder.createDefault(properties.getProperty("token"))
                .addEventListeners(
                        new CommandsListener(this),
                        new ShowcaseListener(this)
                )
                .build();
        jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda.getPresence().setActivity(Activity.watching("Sal4iDev#4767"));

        return jda;
    }

    public JDA getJda() {
        return jda;
    }
}
