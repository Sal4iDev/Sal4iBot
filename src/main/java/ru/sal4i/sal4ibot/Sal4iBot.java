package ru.sal4i.sal4ibot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.apache.log4j.PropertyConfigurator;

import javax.security.auth.login.LoginException;
import java.io.IOException;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class Sal4iBot {
    public static void main(String[] args) {
        PropertyConfigurator.configure(Sal4iBot.class.getResourceAsStream("/log4j.properties"));
        try {
            JDA jda = new Bot().run();
//            CommandListUpdateAction commands = jda.updateCommands();

            jda.updateCommands().addCommands(
                    Commands.slash("codeblock", "codeblock"),
                    Commands.slash("invslots", "inv slots")
                            .addOptions(new OptionData(STRING, "type", "Anvil, Player Inventory, etc")),
                    Commands.slash("anyone", "Directly ask your question"),
                    Commands.slash("ask", "Do not ask to ask, just ask"),
                    Commands.slash("lmgt", "let me google that for you")
                            .addOptions(new OptionData(STRING, "queue", "the queue")),
                    Commands.slash("admin", "Admin Command"),
                    Commands.slash("beforeasking", "<arguments> before asking")
                            .addOptions(new OptionData(STRING, "arguments", "the arguments")),
                    Commands.slash("blockf", "VisualBukkit blocks format"),
                    Commands.slash("buildtools", "buildtools link"),
                    Commands.slash("extensions", "Sal4iDev extensions"),
                    Commands.slash("github", "Sal4iDev github")
                            .addOptions(new OptionData(STRING, "usernamerepo", "userrepo", false)),
                    Commands.slash("java", "Java tips"),
                    Commands.slash("javajs", "Java is to JavaScript as Ham is to Hamster"),
                    Commands.slash("mre", "How to create a Minimal, Reproducible Example"),
                    Commands.slash("naming", "Java naming convention"),
                    Commands.slash("nohello", "Don't say just hello in help channels"),
                    Commands.slash("notworking", "informative message")
                            .addOptions(new OptionData(STRING, "message", "themessage", false)),
                    Commands.slash("paste", "paste.gg"),
                    Commands.slash("rl", "why not use /reload"),
                    Commands.slash("schedule", "spigot scheduler programming"),
                    Commands.slash("singleton", "singleton"),
                    Commands.slash("switch", "how switch works"),
                    Commands.slash("tryitandsee", "try it and see"),
                    Commands.slash("xy", "xy problem"),
                    Commands.slash("equals", "== and equals"),
                    Commands.slash("hashmap", "hashmap tips"),
                    Commands.slash("prcp", "programming concepts"),
                    Commands.slash("toblock", "convert java code to vb block")
                            .addOptions(new OptionData(STRING, "javacode", "javacode", false))
            ).queue();
//            commands.queue();
        } catch (LoginException | IOException e) {
            e.printStackTrace();
        }
    }
}
