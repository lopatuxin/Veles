package core;

import textprocessing.LanguageProcessor;
import memory.KnowledgeBase;

import java.util.HashMap;
import java.util.Map;
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

            if (response.startsWith("Я не знаю ответа")) {
                System.out.println("Хотите добавить эту информацию в базу знаний? (да/нет)");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("да")) {
                    handleNewKnowledge(knowledgeBase);
                }
            }
        }
    }

    private void handleNewKnowledge(KnowledgeBase knowledgeBase) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите тип сущности:");
        String type = scanner.nextLine();

        System.out.println("Введите название сущности:");
        String name = scanner.nextLine();

        System.out.println("Введите свойства (в формате ключ=значение, разделяя их запятыми):");
        String propertiesInput = scanner.nextLine();

        Map<String, String> properties = new HashMap<>();
        for (String pair : propertiesInput.split(",")) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                properties.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }

        knowledgeBase.addEntity(type, name, properties);
    }
}
