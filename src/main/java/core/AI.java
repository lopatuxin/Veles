package core;

import textprocessing.LanguageProcessor;
import memory.KnowledgeBase;

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
        }
    }
}
