package ru.sal4i.sal4ibot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.apache.log4j.PropertyConfigurator;

import javax.security.auth.login.LoginException;
import java.io.IOException;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;
import static net.dv8tion.jda.api.interactions.commands.build.Commands.slash;

public class Sal4iBot {
    public static void main(String[] args) {
        PropertyConfigurator.configure(Sal4iBot.class.getResourceAsStream("/log4j.properties"));
        try {
            JDA jda = new Bot().run();
//            CommandListUpdateAction commands = jda.updateCommands();

            jda.updateCommands().addCommands(
                    slash("codeblock", "codeblock"),
                    slash("invslots", "inv slots")
                            .addOptions(new OptionData(STRING, "type", "Anvil, Player Inventory, etc")),
                    slash("anyone", "Directly ask your question"),
                    slash("ask", "Do not ask to ask, just ask"),
                    slash("lmgt", "let me google that for you")
                            .addOptions(new OptionData(STRING, "queue", "the queue")),
                    slash("admin", "Admin Command"),
                    slash("beforeasking", "<arguments> before asking")
                            .addOptions(new OptionData(STRING, "arguments", "the arguments")),
                    slash("blockf", "VisualBukkit blocks format"),
                    slash("buildtools", "buildtools link"),
                    slash("extensions", "Sal4iDev extensions"),
                    slash("github", "Sal4iDev github")
                            .addOptions(new OptionData(STRING, "usernamerepo", "userrepo", false)),
                    slash("java", "Java tips"),
                    slash("javajs", "Java is to JavaScript as Ham is to Hamster"),
                    slash("mre", "How to create a Minimal, Reproducible Example"),
                    slash("naming", "Java naming convention"),
                    slash("nohello", "Don't say just hello in help channels"),
                    slash("notworking", "informative message")
                            .addOptions(new OptionData(STRING, "message", "themessage", false)),
                    slash("paste", "paste.gg"),
                    slash("rl", "why not use /reload"),
                    slash("schedule", "spigot scheduler programming"),
                    slash("singleton", "singleton"),
                    slash("switch", "how switch works"),
                    slash("tryitandsee", "try it and see"),
                    slash("xy", "xy problem"),
                    slash("equals", "== and equals"),
                    slash("hashmap", "hashmap tips"),
                    slash("prcp", "programming concepts"),
                    slash("toblock", "convert java code to vb block")
                            .addOptions(new OptionData(STRING, "javacode", "javacode", false))
            ).queue();
//            commands.queue();
        } catch (LoginException | IOException e) {
            e.printStackTrace();
        }
    }
}
