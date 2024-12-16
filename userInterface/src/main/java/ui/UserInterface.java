package ui;

import java.util.concurrent.BlockingQueue;

public interface UserInterface {
    void startUI(BlockingQueue<String> inputQueue);
    String getInput();
    void sendOutput(String output);
}
