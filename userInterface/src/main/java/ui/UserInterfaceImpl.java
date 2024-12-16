package ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

public class UserInterfaceImpl implements UserInterface {

    private static final Logger logger = LoggerFactory.getLogger(UserInterfaceImpl.class);
    private String lastInput = null;
    private BlockingQueue<String> inputQueue;

    @Override
    public void startUI(BlockingQueue<String> inputQueue) {
        this.inputQueue = inputQueue;
        Main.setUiImpl(this);
        Main.main(new String[]{});
    }

    @Override
    public String getInput() {
        synchronized (this) {
            while (lastInput == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            String input = lastInput;
            lastInput = null;
            return input;
        }
    }

    @Override
    public void sendOutput(String output) {
//        if (mainApp != null) {
//            mainApp.addMessage(null, output, false);
//        } else {
//            System.out.println(output);
//        }
    }

    public synchronized void setInput(String input) {
        if (inputQueue != null) {
            try {
                inputQueue.put(input);
                logger.info("В очередь добавлено сообщение: {}", input);
            } catch (InterruptedException e) {
                logger.error("Ошибка при добавлении ввода в очередь: ", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}
