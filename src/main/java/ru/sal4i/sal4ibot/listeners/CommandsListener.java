package ru.sal4i.sal4ibot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import ru.sal4i.sal4ibot.Bot;
import ru.sal4i.sal4ibot.utils.Hastebin;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SuppressWarnings({"RedundantLabeledSwitchRuleCodeBlock", "TextBlockMigration"})
public class CommandsListener extends ListenerAdapter {
    private static final String DELETE_BUTTON_PREFIX = "sal4i_delete_";
    private final Bot bot;

    public CommandsListener(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String error = "I can't handle that command right now :(";
        if (event.getGuild() == null) {
            event.reply(error).setEphemeral(true).queue();
            return;
        }
        if (event.getMember() == null) {
            event.reply(error).setEphemeral(true).queue();
            return;
        }
        Button button = makeDeleteButton(event.getMember().getId());

        switch (event.getName()) {
            case "codeblock" -> {
                event.reply("You can format your code using codeblocks! Discord supports syntax highlighting for most languages:\n" + "\n" + "\\`\\`\\`python\n" + "print(\"hello world\")\n" + "\\`\\`\\`\n" + "\n" + "This will produce\n" + "```python\n" + "print(\"hello world\")\n" + "```\n" + "\n" + "Replace 'python' with the language you're using (e.g. 'cpp' for C++;  'js' for JavaScript;  'delphi' for Delphi, etc)").addActionRow(button).queue();
            }
            case "invslots" -> {
                OptionMapping option = event.getOption("type");
                if (option == null) {
                    event.reply("Usage: `/invslots <inventory_type>`").setEphemeral(true).queue();
                    break;
                }
                event.reply("<https://wiki.vg/Inventory#" + option.getAsString().replace(' ', '_') + ">").addActionRow(button).queue();
            }
            case "anyone" -> {
                event.reply("Don't ask if anyone knows XYZ, just directly ask your question/share your issue etc.").addActionRow(button).queue();
            }
            case "ask" -> {
                event.reply("https://dontasktoask.com/").addActionRow(button).queue();
            }
            case "lmgt" -> {
                OptionMapping option = event.getOption("queue");
                if (option == null) {
                    event.reply("Usage: `/lmgt <query>`").setEphemeral(true).queue();
                    break;
                }
                event.reply("<https://letmegooglethat.com/?q=" + option.getAsString().replace(' ', '+').replace("#", "%23") + ">").addActionRow(button).queue();
            }
            case "admin" -> {
                if (event.getMember() == null || event.getMember().getId().equals("923314054011965461")) {
                    event.reply("Done!").setEphemeral(true).queue();
                    bot.getJda().shutdown();
                } else event.reply("This command is only available to Sal4iDev!").setEphemeral(true).queue();
            }
            case "beforeasking" -> {
                OptionMapping optionMapping = event.getOption("arguments");
                if (optionMapping == null) {
                    event.reply("Try before asking!").addActionRow(button).queue();
                    break;
                }
                event.reply(optionMapping.getAsString() + " before asking!").addActionRow(button).queue();
            }
            case "blockf" -> {
                event.reply("https://cdn.discordapp.com/attachments/954625534569091083/959520765491957760/blockf.png").addActionRow(button).queue();
            }
            case "buildtools" -> {
                event.reply("https://www.spigotmc.org/wiki/buildtools/").addActionRow(button).queue();
            }
            case "extensions" -> {
                OptionMapping optionMapping = event.getOption("boolean");
                if (optionMapping == null)
                    event.reply("<https://github.com/OfficialDonut/VisualBukkitExtensions/releases>").addActionRow(button).queue();
                else if (optionMapping.getAsBoolean())
                    event.reply("<https://github.com/Sal4iDev/sVisualBukkit>").addActionRow(button).queue();
            }
            case "github" -> {
                OptionMapping optionMapping = event.getOption("usernamerepo");
                if (optionMapping == null) {
                    event.reply("<https://github.com/Sal4iDev/>").addActionRow(button).queue();
                    break;
                }
                event.reply("<https://github.com/" + optionMapping.getAsString() + ">").addActionRow(button).queue();
            }
            case "java" -> {
                event.reply("Major LTS Versions: **8**, **11**, **17** (Latest) | For security reasons, don't use **7** or below.\n" + "Downloads may be found underneath **Oracle **or **OpenJDK **(Recommended for newer releases)\n" + "\n" + "Java API:\n" + "<https://docs.oracle.com/en/java/javase> (Click a version, then click **API Documentation**)\n" + "\n" + "Basic Java Tutorials (Free): \n" + "<https://docs.oracle.com/javase/tutorial>\n" + "<https://w3schools.com/java> (Great for starting)\n" + "<https://baeldung.com/java-tutorial> (Great for examples)\n" + "<https://geeksforgeeks.org/java> (Great for examples)\n" + "\n" + "Java Courses: \n" + "<https://codecademy.com/learn/learn-java> (Free or Paid)\n" + "<https://jetbrains.com/academy> (Trial or Paid)\n" + "<https://java-programming.mooc.fi/> (Free)").addActionRow(button).queue();
            }
            case "javajs" -> {
                event.reply("https://www.apollo-formation.com/wp-content/uploads/segue-blog-java-vs-javascript.png").addActionRow(button).queue();
            }
            case "mre" -> {
                event.reply("https://stackoverflow.com/help/minimal-reproducible-example").addActionRow(button).queue();
            }
            case "naming" -> {
                event.reply("Java naming conventions can be found here:\n" + "<https://www.geeksforgeeks.org/java-naming-conventions/>").addActionRow(button).queue();
            }
            case "nohello" -> {
                event.reply("https://nohello.net/").addActionRow(button).queue();
            }
            case "notworking" -> {
                OptionMapping optionMapping = event.getOption("message");
                String message = "Does not working";
                if (optionMapping != null) {
                    message = optionMapping.getAsString();
                }
                event.reply("\"" + message + "\"" + " is a useless statement. Please describe what exactly is not working, what you expect it to do, and what actually happens. If you get any console errors, also `?paste` the entire stacktrace.").addActionRow(button).queue();
            }
            case "paste" -> {
                event.reply("<https://paste.gg/>").addActionRow(button).queue();
            }
            case "rl" -> {
                event.reply("https://madelinemiller.dev/blog/problem-with-reload/").addActionRow(button).queue();
            }
            case "schedule" -> {
                event.reply("https://www.spigotmc.org/wiki/scheduler-programming/").addActionRow(button).queue();
            }
            case "singleton" -> {
                event.reply("```java\n" + "private static ClassName instance = new ClassName();\n" + "\n" + "public static ClassName getInstance() {\n" + "    return instance;\n" + "}\n" + "```\n" + "Non-static methods and variable in this class can then be accessed with `ClassName.getInstance()...`.\n" + "If you want to use this in your main class, then remove the`  = new ClassName()` part and put `instance = this;` in your `onLoad` or `onEnable`.").addActionRow(button).queue();
            }
            case "switch" -> {
                event.reply("https://media.discordapp.net/attachments/954622083982327861/954772075850133603/unknown.png?width=483&height=676").addActionRow(button).queue();
            }
            case "tryitandsee" -> {
                event.reply("https://tryitands.ee/").addActionRow(button).queue();
            }
            case "xy" -> {
                event.reply("<https://xyproblem.info/>").addActionRow(button).queue();
            }
            case "equals" -> {
                event.reply("**What is the difference between == and .equals()?**\n" + "• `==` compares whether two Objects are pointing to the same location in memory.\n" + "> For example, if you compare two `String`s with the same text, they still are two independent String instances stored at different locations and therefore `==` will return false.\n" + "• `.equals()` does what it is told to do. It entirely depends on the implementation of the class you are comparing.\n" + "> As an example the most basic form of `.equals()` defined in the Object class just checks for `==`.\n" + "• `.equals()` is usually implemented in such a way that it checks for meaningful equality. In case of a `String`, that is the text stored in it.").addActionRow(button).queue();
            }
            case "hashmap" -> {
                event.replyEmbeds(embed("By CodedRed", "How HashMaps work in Java", "```java\n" + "Map<Key, Value> example = new HashMap<>();\n" + "```\n" + "HashMaps have two things a key and a value. You call the key and get the value it is holding.\n" + "key -- > value\n" + "\n" + "```java\n" + "Map<String, Integer> playersBalance = new HashMap<>();\n" + "```\n" + "\n" + "The hashmap above is a <String, Integer> meaning it will hold a string that corresponds to a certain Integer.\n" + "For example lets store player's balances in this hashmap. To do this you can do the following code:\n" + "```java\n" + "playerBalance.put(\"CodedRed\", 10000);\n" + "playerBalance.put(\"Insprill\", 5003);\n" + "playerBalance.put(\"danc\", 4665);\n" + "playerBalance.put(\"Deerjump\", 3761);\n" + "```\n" + "You can store as many different pairs of information you want in a hashmap. However, if you try to put value in with the same key name then it will overwrite the old key. Hence, you cannot have the same key names.\n" + "\n" + "key returns the value so...\n" + "Our info in the hashmap look like this:\n" + "`CodedRed --> 10000`\n" + "`Insprill --> 5003`\n" + "`danc --> 4665`\n" + "`Deerjump --> 3761`\n" + "\n" + "Say we wanted to get someone's balance:\n" + "```java\n" + "if (playerBalance.containsKey(\"CodedRed\"))\n" + "    int money = playerBalance.get(\"CodedRed\");\n" + "```\n" + "If you try to get a value with a key that doesn't exist, it will simply return `null`.").setColor(new Color(57, 203, 152)).build()).addActionRow(button).queue();
            }
            case "prcp" -> {
                event.replyEmbeds(embed(null, "Programming Concepts", "- [Variables](https://www.cs.utah.edu/~germain/PPS/Topics/variables.html)\n" + "- [Control Structures](https://towardsdatascience.com/essential-programming-control-structures-2e5e73285df4)\n" + "- [Statements vs Expressions](https://therenegadecoder.com/code/the-difference-between-statements-and-expressions/)").setColor(new Color(113, 201, 97)).build()).addActionRow(button).queue();
            }
            // The Beginning of a Strong Shitcode
            case "toblock" -> {
                OptionMapping optionMapping = event.getOption("javacode");
                if (optionMapping == null) {
                    event.reply("Usage `/toblock <java code>`\n`<java code>`: Class#method#returnType\nExample: `/toblock OfflinePlayer#hasPlayedBefore#Boolean`").setEphemeral(true).queue();
                    break;
                }
                String[] option = optionMapping.getAsString().split("#");
                String message = null;

                if (option.length == 1 && option[0].contains("Event")) {
                    message = "[Event] " + formatClassName(option[0]);
                } else if (option.length == 2) {
                    message = String.format("[%s] %s", formatClassName(option[0]), formatLowerCamelCase(option[1]));
                } else if (option.length == 3) {
                    String returns = option[2].trim();
                    if (returns.equalsIgnoreCase("int")) {
                        returns = "Integer";
                    } else if (returns.equalsIgnoreCase("float") || returns.equalsIgnoreCase("double")) {
                        returns = "Number";
                    } else {
                        returns = formatClassName(option[2]);
                    }

                    message = String.format("[%s] %s" + ((!option[2].trim().equalsIgnoreCase("void")) ? " → (%s)" : ""), formatClassName(option[0]), formatLowerCamelCase(option[1]), formatClassName(returns));
                }

                if (message != null)
                    event.reply("Your block:\n```css\n" + message + "\n```").addActionRow(button).queue();
                else
                    event.reply("Usage `/toblock <java code>`\n`<java code>`: Class#method#returnType\nExample: `/toblock OfflinePlayer#hasPlayedBefore#Boolean`").setEphemeral(true).queue();
            }
            // The End of the Strong Shitcode
            case "binfile" -> {
                OptionMapping optionMapping = event.getOption("id");
//                OptionMapping optionMapping1 = event.getOption("raw");
                if (optionMapping == null) {
                    event.reply("Invalid message id").queue();
                    break;
                }
//                boolean raw;
//                if (optionMapping1 == null) raw = false;
//                else raw = optionMapping1.getAsBoolean();

                event.getChannel().retrieveMessageById(optionMapping.getAsString()).queue(message -> {
//                    try {
                    List<Message.Attachment> attachments = message.getAttachments();
                    if (attachments.isEmpty()) {
                        event.reply("Which file should i upload on paste?").setEphemeral(true).queue();
                    } else {
                        try {
                            event.reply("<" + Hastebin.post(IOUtils.toString(attachments.get(0).retrieveInputStream().get(), StandardCharsets.UTF_8), /*raw*/true) + ">").addActionRow(button).queue();
                        } catch (IOException | InterruptedException | ExecutionException e) {
                            System.err.println(e.getMessage());
                        }
                    }
//                    } catch (Exception exception) {
//                        event.reply("There was an error!").setEphemeral(true).queue();
//                    }
                }, failure -> event.reply("Invalid message id").setEphemeral(true).queue());
            }

            default -> {
                event.reply(error).setEphemeral(true).queue();
            }
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getMember() == null) return;
        if (!event.getComponentId().equals(DELETE_BUTTON_PREFIX + event.getMember().getId())) {
            event.reply("This is not your message!").setEphemeral(true).queue();
            return;
        }

        event.getMessage().delete().queue();
    }

    @SuppressWarnings("SameParameterValue")
    private EmbedBuilder embed(String author, String title, String description) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setAuthor(author);
        embedBuilder.setTitle(title);
        embedBuilder.setDescription(description);

        return embedBuilder;
    }

    private Button makeDeleteButton(String id) {
        return Button.danger(DELETE_BUTTON_PREFIX + id, Emoji.fromMarkdown("<:trash:959522681164816465>"));
    }

    private String formatClassName(String str) {
        str = str.trim();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                return str.substring(i);
            }
        }

        return str;
    }

    private String formatLowerCamelCase(String str) {
        str = str.trim();
        str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            builder.append(c);
            if (i + 1 < str.length() && Character.isUpperCase(str.charAt(i + 1)) && (Character.isLowerCase(c) || (i + 2 < str.length() && Character.isLowerCase(str.charAt(i + 2))))) {
                builder.append(' ');
            }
        }
        return builder.toString();
    }
}
