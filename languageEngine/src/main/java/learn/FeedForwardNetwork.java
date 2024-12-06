package learn;

public class FeedForwardNetwork {
    private final int embeddingDim;
    private final float[][] weights1;
    private final float[][] weights2;
    private final float[] bias1;
    private final float[] bias2;

    public FeedForwardNetwork(int embeddingDim) {
        this.embeddingDim = embeddingDim;
        this.weights1 = new float[embeddingDim][embeddingDim];
        this.weights2 = new float[embeddingDim][embeddingDim];
        this.bias1 = new float[embeddingDim];
        this.bias2 = new float[embeddingDim];
        initializeWeightsAndBiases();
    }

    private void initializeWeightsAndBiases() {
        for (int i = 0; i < embeddingDim; i++) {
            for (int j = 0; j < embeddingDim; j++) {
                weights1[i][j] = (float) Math.random() - 0.5f;
                weights2[i][j] = (float) Math.random() - 0.5f;
            }
            bias1[i] = (float) Math.random() - 0.5f;
            bias2[i] = (float) Math.random() - 0.5f;
        }
    }

    public float[][] compute(float[][] input) {
        int seqLength = input.length;

        // Layer 1
        float[][] layer1Output = new float[seqLength][embeddingDim];
        for (int i = 0; i < seqLength; i++) {
            for (int j = 0; j < embeddingDim; j++) {
                layer1Output[i][j] = bias1[j];
                for (int k = 0; k < embeddingDim; k++) {
                    layer1Output[i][j] += input[i][k] * weights1[k][j];
                }
                layer1Output[i][j] = Math.max(0, layer1Output[i][j]); // ReLU
            }
        }

        // Layer 2
        float[][] layer2Output = new float[seqLength][embeddingDim];
        for (int i = 0; i < seqLength; i++) {
            for (int j = 0; j < embeddingDim; j++) {
                layer2Output[i][j] = bias2[j];
                for (int k = 0; k < embeddingDim; k++) {
                    layer2Output[i][j] += layer1Output[i][k] * weights2[k][j];
                }
            }
        }

        return layer2Output;
    }
}
