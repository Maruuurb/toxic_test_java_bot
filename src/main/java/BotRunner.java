import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException, TelegramApiException {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream("keys.properties"));
        String username = (String) appProps.get("Username");
        String botToken = (String) appProps.get("BotToken");

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new ToxicBot(username, botToken));

    }
}
