import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class ToxicBot extends TelegramLongPollingBot {
    private String username;
    private String botToken;

    public ToxicBot(String username, String botToken) {
        this.username = username;
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // TODO
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            if ("/start".equals(text)) {
                // Вызов обработчика команды /start
                StartCommandHandler.handleStartCommand(update);
            }

            Message message = update.getMessage();
            long chatId = message.getChatId();

            // Создайте объект SendMessage
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Выберите опцию:");
            sendMessage.setChatId(chatId);

            // Создайте клавиатуру с кнопками
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(keyboardMarkup);
            keyboardMarkup.setResizeKeyboard(true); // Разрешить изменение размера клавиатуры

            // Создайте строку с кнопками
            KeyboardRow row = new KeyboardRow();
            row.add(new KeyboardButton("Кнопка 1"));
            row.add(new KeyboardButton("Кнопка 2"));

            List<KeyboardRow> keyboard = new ArrayList<>();
            keyboard.add(row);

            // Добавьте строку с кнопками к клавиатуре
            keyboardMarkup.setKeyboard(keyboard);

            try {
                // Отправьте сообщение с клавиатурой
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


}
