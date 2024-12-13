import core.AI;
import memory.repository.JsonFileRepository;
import tokenizer.TextTokenizer;
import ui.UserInterfaceImpl;

public class Main {

    public static void main(String[] args) {
        AI veles = new AI(new TextTokenizer(), new JsonFileRepository(), new UserInterfaceImpl());
        veles.start();
    }
}
