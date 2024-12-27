package util;

public class Encoder {

    private Encoder() {
    }

    public static double[] encodeWord(String word) {
        double[] vector = new double[33];
        int a = 'а';
        for (char c : word.toCharArray()) {
            int index = Math.abs(Character.toLowerCase(c) - a);
            if (index < vector.length) {
                vector[index]++;
            }
        }
        return vector;
    }
}
