package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        VBox chatArea = new VBox();
        chatArea.setPadding(new Insets(10));
        chatArea.setSpacing(10);

        TextArea inputArea = new TextArea();
        inputArea.setPromptText("Введите текст...");
        inputArea.setWrapText(true); // Включить перенос текста по словам
        inputArea.setPrefRowCount(5);

        inputArea.setOnKeyPressed(event -> {
            if (Objects.requireNonNull(event.getCode()) == KeyCode.ENTER) {
                String userInput = inputArea.getText().trim();
                if (!userInput.isEmpty()) {
                    addMessage(chatArea, userInput, true); // Сообщение пользователя
                    inputArea.clear();

                    // Генерация ответа Велеса (заглушка)
                    String velesResponse = "Ответ Велеса на: " + userInput;
                    addMessage(chatArea, velesResponse, false); // Сообщение Велеса
                }
                event.consume(); // Убираем добавление новой строки
            }
        });

        // Добавляем элементы в корневой контейнер
        root.setCenter(chatArea);
        root.setBottom(inputArea);
        BorderPane.setMargin(inputArea, new Insets(10, 10, 10, 10));

        Scene scene = new Scene(root, 600, 800);
        stage.setTitle("Велес");
        stage.setScene(scene);
        stage.show();
    }

    private void addMessage(VBox chatArea, String message, boolean isUser) {
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
