package ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ConsoleHandler {

    private final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(ConsoleHandler.class);

    public ConsoleHandler() {
        logger.info("Инициализирован класс ConsoleHandler: готов к обработке консольного ввода и вывода.");
    }

    public void sayHello() {
        System.out.println("Здравствуйте! Как я могу помочь?");
    }

    public String readInput() {
        System.out.println("> ");
        return scanner.nextLine();
    }

    public void output(String answer) {
        System.out.println(answer);
    }
}
