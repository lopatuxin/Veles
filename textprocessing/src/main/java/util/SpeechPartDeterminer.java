package util;

public class SpeechPartDeterminer {

    private final double[][] weightsInputHidden;
    private final double[][] weightsHiddenOutput;
    private final double[] hiddenBias;
    private final double[] outputBias;

    public SpeechPartDeterminer(int inputSize, int hiddenSize, int outputSize) {
        weightsInputHidden = randomMatrix(inputSize, hiddenSize);
        weightsHiddenOutput = randomMatrix(hiddenSize, outputSize);
        hiddenBias = new double[hiddenSize];
        outputBias = new double[outputSize];
    }

    private double[][] randomMatrix(int rows, int cols) {
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Math.random() - 0.5;
            }
        }
        return matrix;
    }

    public double[] forward(double[] input) {
        double[] hiddenLayer = activate(dotProduct(input, weightsInputHidden, hiddenBias));
        return softmax(dotProduct(hiddenLayer, weightsHiddenOutput, outputBias));
    }

    private double[] dotProduct(double[] input, double[][] weights, double[] bias) {
        double[] result = new double[weights[0].length];
        for (int j = 0; j < weights[0].length; j++) {
            for (int i = 0; i < input.length; i++) {
                result[j] += input[i] * weights[i][j];
            }
            result[j] += bias[j];
        }
        return result;
    }

    private double[] activate(double[] layer) {
        for (int i = 0; i < layer.length; i++) {
            layer[i] = Math.max(0, layer[i]);
        }
        return layer;
    }

    private double[] softmax(double[] layer) {
        double sum = 0;
        for (double val : layer) {
            sum += Math.exp(val);
        }
        for (int i = 0; i < layer.length; i++) {
            layer[i] = Math.exp(layer[i]) / sum;
        }
        return layer;
    }
}
