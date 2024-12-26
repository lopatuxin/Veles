package util;

public class Main {

    public static void main(String[] args) {
        SpeechPartDeterminer speechPartDeterminer = new SpeechPartDeterminer(33, 16, 12);
        String word = "кошка";
        double[] inputVector = Encoder.encodeWord(word);
        double[] outputVector = speechPartDeterminer.forward(inputVector);
        String result = Decoder.interpretOutput(outputVector);
        System.out.println(result);
    }
}
