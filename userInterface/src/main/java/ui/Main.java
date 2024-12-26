package ui;

import core.AI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private final AI veles = new AI();

    @Override
    public void start(Stage stage) {
        stage.setTitle("ВЕЛЕС Чат");
        VBox layout = new VBox();

        ListView<String> chatHistory = new ListView<>();
        TextField inputField = new TextField();
        Button sendButton = new Button("Отправить");

        sendButton.setOnAction(event -> {
            String input = inputField.getText();

            if (!input.isEmpty()) {
                chatHistory.getItems().add(input);
                inputField.clear();

                String answer = veles.processInput(input);
                chatHistory.getItems().add(answer);
            }
        });

        layout.getChildren().addAll(chatHistory, inputField, sendButton);

        Scene scene = new Scene(layout, 400, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void addMessage(VBox chatArea, String message, boolean isUser) {
        // Создаем текстовое сообщение
        TextFlow messageBubble = new TextFlow(new Text(message));
        if (isUser) {
            messageBubble.setStyle("-fx-background-color: #4a90e2; -fx-text-fill: white; -fx-padding: 10; -fx-background-radius: 10;");
        } else {
            messageBubble.setStyle("-fx-background-color: #4a4a4a; -fx-text-fill: white; -fx-padding: 10; -fx-background-radius: 10;");
        }
        messageBubble.setMaxWidth(300);

        // Оборачиваем сообщение в HBox для управления выравниванием
        HBox messageContainer = new HBox(messageBubble);
        if (isUser) {
            messageContainer.setStyle("-fx-padding: 5;"); // Отступы между сообщениями
            HBox.setMargin(messageBubble, new Insets(5, 10, 5, 50)); // Отступы для пользователя
            messageContainer.setAlignment(javafx.geometry.Pos.CENTER_LEFT); // Выравнивание влево
        } else {
            messageContainer.setStyle("-fx-padding: 5;");
            HBox.setMargin(messageBubble, new Insets(5, 50, 5, 10)); // Отступы для Велеса
            messageContainer.setAlignment(javafx.geometry.Pos.CENTER_RIGHT); // Выравнивание вправо
        }

        // Добавляем контейнер сообщения в чат
        chatArea.getChildren().add(messageContainer);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
