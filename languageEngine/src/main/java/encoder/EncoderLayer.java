package encoder;

import attention.SelfAttention;
import learn.FeedForwardNetwork;

public class EncoderLayer {
    private final SelfAttention selfAttention;
    private final FeedForwardNetwork feedForwardNetwork;

    public EncoderLayer(int embeddingDim) {
        this.selfAttention = new SelfAttention(embeddingDim);
        this.feedForwardNetwork = new FeedForwardNetwork(embeddingDim);
    }

    public float[][] forward(float[][] inputEmbeddings) {
        // Step 1: Self-Attention
        float[][] attentionOutput = selfAttention.computeAttention(inputEmbeddings);

        // Step 2: Add & Norm (Attention)
        float[][] attentionNormalized = addAndNormalize(inputEmbeddings, attentionOutput);

        // Step 3: Feed-Forward Network
        float[][] feedForwardOutput = feedForwardNetwork.compute(attentionNormalized);

        // Step 4: Add & Norm (FFN)
        return addAndNormalize(attentionNormalized, feedForwardOutput);
    }

    private float[][] addAndNormalize(float[][] input, float[][] output) {
        int seqLength = input.length;
        int dim = input[0].length;
        float[][] result = new float[seqLength][dim];

        for (int i = 0; i < seqLength; i++) {
            for (int j = 0; j < dim; j++) {
                result[i][j] = (input[i][j] + output[i][j]) / 2; // Упрощенная нормализация
            }
        }

        return result;
    }
}
