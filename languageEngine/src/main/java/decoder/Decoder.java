package decoder;

import java.util.ArrayList;
import java.util.List;

public class Decoder {
    private final List<DecoderLayer> layers;
    private final int numLayers;
    private final int embeddingDim;

    public Decoder(int numLayers, int embeddingDim) {
        this.numLayers = numLayers;
        this.embeddingDim = embeddingDim;
        this.layers = new ArrayList<>();
        initializeLayers();
    }

    private void initializeLayers() {
        for (int i = 0; i < numLayers; i++) {
            layers.add(new DecoderLayer(embeddingDim));
        }
    }

    public float[][] forward(float[][] inputEmbeddings, float[][] encoderOutput) {
        float[][] output = inputEmbeddings;
        for (DecoderLayer layer : layers) {
            output = layer.forward(output, encoderOutput); // Перекрестное внимание
        }
        return output;
    }
}
