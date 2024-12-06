package decoder;

import attention.SelfAttention;
import learn.FeedForwardNetwork;

public class DecoderLayer {
    private final SelfAttention selfAttention;
    private final SelfAttention crossAttention;
    private final FeedForwardNetwork feedForward;

    public DecoderLayer(int embeddingDim) {
        this.selfAttention = new SelfAttention(embeddingDim); // Внимание внутри декодера
        this.crossAttention = new SelfAttention(embeddingDim); // Перекрестное внимание
        this.feedForward = new FeedForwardNetwork(embeddingDim);
    }

    public float[][] forward(float[][] inputEmbeddings, float[][] encoderOutput) {
        // Self-Attention: изучаем внутренние зависимости
        float[][] selfAttended = selfAttention.computeAttention(inputEmbeddings);

        // Cross-Attention: изучаем связь с энкодером
        float[][] crossAttended = crossAttention.computeAttention(encoderOutput);

        // Feed-forward для финальной обработки
        return feedForward.compute(crossAttended);
    }
}
