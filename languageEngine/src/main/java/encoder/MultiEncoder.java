package encoder;

import java.util.ArrayList;
import java.util.List;

public class MultiEncoder {
    private final List<EncoderLayer> layers;
    private final int numLayers;
    private final int embeddingDim;

    public MultiEncoder(int numLayers, int embeddingDim) {
        this.numLayers = numLayers;
        this.embeddingDim = embeddingDim;
        this.layers = new ArrayList<>();
        initializeLayers();
    }

    private void initializeLayers() {
        for (int i = 0; i < numLayers; i++) {
            layers.add(new EncoderLayer(embeddingDim));
        }
    }

    public float[][] forward(float[][] inputEmbeddings) {
        float[][] output = inputEmbeddings;
        for (EncoderLayer layer : layers) {
            output = layer.forward(output); // Передаем результат предыдущего слоя в следующий
        }
        return output;
    }
}
