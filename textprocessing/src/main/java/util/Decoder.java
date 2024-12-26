package util;

public class Decoder {

    public static String interpretOutput(double[] outputVector) {
        String[] partsOfSpeech = {"существительное", "прилагательное", "глагол", "наречие", "числительное", "местоимение",
                "причастие", "деепричастие", "предлог", "союз", "частица", "междометие"};

        int maxIndex = 0;
        for (int i = 1; i < outputVector.length; i++) {
            if (outputVector[i] > outputVector[maxIndex]) {
                maxIndex = i;
            }
        }
        return partsOfSpeech[maxIndex];
    }
}
