package ui;

import core.AI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    private final AI veles = new AI();

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        VBox layout = new VBox();
        layout.setPadding(new Insets(10));
        layout.setSpacing(10);

        ListView<String> chatHistory = new ListView<>();
        TextArea inputField = new TextArea();
        inputField.setPromptText("Введите текст...");
        inputField.setWrapText(true);
        inputField.setPrefRowCount(5);

        VBox.setVgrow(chatHistory, javafx.scene.layout.Priority.ALWAYS);

        inputField.setOnKeyPressed(event -> {
            if (Objects.requireNonNull(event.getCode()) == KeyCode.ENTER) {
                String input = inputField.getText().trim();

                if (!input.isEmpty()) {
                    addMessage(layout, input, true);
                    inputField.clear();

                    String answer = veles.processInput(input);
                    addMessage(layout, answer, false);
                }
            }
        });

        root.setCenter(layout);
        root.setBottom(inputField);
        BorderPane.setMargin(inputField, new Insets(10, 10, 10, 10));

        Scene scene = new Scene(root, 600, 800);
        stage.setTitle("Велес");
        stage.setScene(scene);
        stage.show();
    }

    public static void addMessage(VBox chatArea, String message, boolean isUser) {
        Text text = new Text(message);
        TextFlow messageBubble = new TextFlow(text);
        messageBubble.setPadding(new Insets(10));
        messageBubble.setMaxWidth(300);

        // Задаем стили для сообщений пользователя и Велеса
        if (isUser) {
            messageBubble.setStyle("-fx-background-color: #4a90e2; -fx-text-fill: white; -fx-background-radius: 10;");
            text.setFill(javafx.scene.paint.Color.WHITE);
        } else {
            messageBubble.setStyle("-fx-background-color: #4a4a4a; -fx-text-fill: white; -fx-background-radius: 10;");
            text.setFill(javafx.scene.paint.Color.WHITE);
        }

        // Оборачиваем сообщение в HBox для управления выравниванием
        HBox messageContainer = new HBox(messageBubble);
        if (isUser) {
            messageContainer.setAlignment(javafx.geometry.Pos.CENTER_LEFT); // Пользовательские сообщения влево
            HBox.setMargin(messageBubble, new Insets(5, 50, 5, 10)); // Отступы
        } else {
            messageContainer.setAlignment(javafx.geometry.Pos.CENTER_RIGHT); // Сообщения Велеса вправо
            HBox.setMargin(messageBubble, new Insets(5, 10, 5, 50)); // Отступы
        }

        // Добавляем контейнер сообщения в чат
        chatArea.getChildren().add(messageContainer);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
