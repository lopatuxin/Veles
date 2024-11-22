package core;

import memory.KnowledgeBase;
import textprocessing.LanguageProcessor;

import java.util.Scanner;

public class AI {

    private final LanguageProcessor languageProcessor;
    private final KnowledgeBase knowledgeBase;

    public AI() {
        this.languageProcessor = new LanguageProcessor();
        this.knowledgeBase = new KnowledgeBase();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Здравствуйте! Как я могу помочь?");

        while (true) {
            System.out.println("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("выход")) {
                System.out.println("До свидания!");
                break;
            }

            String response = languageProcessor.process(input, knowledgeBase);
            System.out.println(response);

            if (response.startsWith("Связей не найдено.")) {
                System.out.println("Хотите добавить связь? (да/нет)");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("да")) {
                    handleAddConnection();
                }
            }
        }
    }

    private void handleAddConnection() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите исходный узел:");
        String from = scanner.nextLine();

        System.out.println("Введите тип связи:");
        String connectionType = scanner.nextLine();

        System.out.println("Введите целевой узел:");
        String to = scanner.nextLine();

        knowledgeBase.addConnection(from, connectionType, to);
        System.out.println("Связь успешно добавлена.");
    }
}
