package vector;

import java.util.HashMap;
import java.util.Map;

public class VectorLayer {
    private final int vocabSize;
    private final int vectorSize;
    private final float[][] vectors;
    private final Map<String, Integer> vocab;

    public VectorLayer(int vocabSize, int vectorSize) {
        this.vocabSize = vocabSize;
        this.vectorSize = vectorSize;
        this.vectors = new float[vocabSize][vectorSize];
        this.vocab = new HashMap<>();
        initializeEmbeddings();
    }

    private void initializeEmbeddings() {
        for (int i = 0; i < vocabSize; i++) {
            for (int j = 0; j < vectorSize; j++) {
                vectors[i][j] = (float) Math.random() - 0.5f; // Случайные значения
            }
        }
    }

    public float[] getVector(String token) {
        Integer index = vocab.get(token);
        if (index == null) {
            throw new IllegalArgumentException("Не найден токен в словаре: " + token);
        }
        return vectors[index];
    }

    public void addToVocabulary(String token, int index) {
        if (vocab.size() >= vocabSize) {
            throw new IllegalStateException("Достигнут предел размера словарного запаса.");
        }
        vocab.put(token, index);
    }
}
