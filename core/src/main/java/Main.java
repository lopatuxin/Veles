import core.AI;
import tokenizer.TextTokenizer;

public class Main {

    public static void main(String[] args) {
        AI veles = new AI(new TextTokenizer());
        veles.start();
    }
}
