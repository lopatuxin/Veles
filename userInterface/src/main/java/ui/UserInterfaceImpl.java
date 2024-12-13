package ui;

public class UserInterfaceImpl implements UserInterface {

    private String lastInput = null;
    private Main mainApp;

    @Override
    public void startUI() {
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
        if (mainApp != null) {
            mainApp.addMessage(null, output, false);
        } else {
            System.out.println(output);
        }
    }

    public void setInput(String input) {
        synchronized (this) {
            this.lastInput = input;
            notifyAll();
        }
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
