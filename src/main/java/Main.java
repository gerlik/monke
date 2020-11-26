import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.Random;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "";
        builder.setToken(token);
        builder.addEventListeners(new Main());
        builder.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        // Avoid infinite loops
        if (event.getAuthor().isBot()) {
            return;
        }

        if (event.getMessage().getContentRaw().equals("!mcommands")) {
            event.getChannel().sendMessage("!ok" + "\ntest" + "\n!roll" + "\nmonke/monkey/bloons time/bloons/b").queue();
        }
        if (event.getMessage().getContentRaw().equals("!ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }

        if (event.getMessage().getContentRaw().equals("!ok")) {
            event.getChannel().sendMessage("Not ok!").queue();
        }

        if (event.getMessage().getContentRaw().equals("test")) {
            event.getChannel().sendMessage(":ok_hand:").queue();
        }

        if (event.getMessage().getContentRaw().contains("monkey")) {
            event.getChannel().sendMessage("OOOOH OOOOOOH AAAAAH AAAAAH").queue();
        }

        // Shuffle random monkey file
        if (event.getMessage().getContentRaw().contains("bloons time") ||
                event.getMessage().getContentRaw().contains("bloons") ||
                event.getMessage().getContentRaw().equals("b") ||
                event.getMessage().getContentRaw().contains("monke") ||
                event.getMessage().getContentRaw().contains("monki")
        ) {
            try {
                File directory = new File("src/main/resources/");
                File[] allFiles = directory.listFiles();
                Random rand = new Random();
                File file = allFiles[rand.nextInt(allFiles.length)];
                event.getChannel().sendFile(file).queue();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // Roll higher score to win
        if (event.getMessage().getContentRaw().equals("!roll")) {
            int number = (new Random().nextInt(99 + 1) + 1);
            int monkeNumber = (new Random().nextInt(99 + 1) + 1);
            event.getChannel().sendMessage("You: " + number + "      Monke: " + monkeNumber).queue();
            if (monkeNumber > number) {
                event.getChannel().sendMessage("I win :sunglasses:").queue();
            } else if (monkeNumber < number) {
                event.getChannel().sendMessage("You win :clap:").queue();
            } else {
                event.getChannel().sendMessage("It's a tie!").queue();
            }
        }

        // Summon the spin monke
        if (event.getMessage().getContentRaw().contains("spin")) {
            try {
                File file = new File("src/main/resources/48.gif");
                event.getChannel().sendFile(file).queue();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // Play theme song
        if (event.getMessage().getContentRaw().contains("bloons")) {
            event.getChannel().sendMessage("https://youtu.be/CXXb7tpnLmM").queue();
        }

        // Draw sth maybe


    }
}
