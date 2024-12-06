package attention;

public class SelfAttention {

    private final int vectorSize;

    public SelfAttention(int vectorSize) {
        this.vectorSize = vectorSize;
    }

    public float[][] computeAttention(float[][] inputVectors) {
        int seqLength = inputVectors.length;

        // Создаем матрицы Query, Key, Value
        float[][] query = linearTransform(inputVectors, vectorSize);
        float[][] key = linearTransform(inputVectors, vectorSize);
        float[][] value = linearTransform(inputVectors, vectorSize);

        // Вычисляем скалярное произведение Q и K (матрица весов)
        float[][] scores = new float[seqLength][seqLength];
        for (int i = 0; i < seqLength; i++) {
            for (int j = 0; j < seqLength; j++) {
                scores[i][j] = dotProduct(query[i], key[j]) / (float) Math.sqrt(vectorSize);
            }
        }

        // Применяем softmax для нормализации весов
        float[][] attentionWeights = applySoftmax(scores);

        // Вычисляем итоговые значения через взвешенную сумму Value
        float[][] output = new float[seqLength][vectorSize];
        for (int i = 0; i < seqLength; i++) {
            for (int j = 0; j < seqLength; j++) {
                for (int k = 0; k < vectorSize; k++) {
                    output[i][k] += attentionWeights[i][j] * value[j][k];
                }
            }
        }
        return output;
    }

    private float[][] linearTransform(float[][] input, int outputDim) {
        int inputDim = input[0].length;
        float[][] weights = new float[inputDim][outputDim];
        float[] bias = new float[outputDim];

        // Инициализация случайными значениями
        for (int i = 0; i < inputDim; i++) {
            for (int j = 0; j < outputDim; j++) {
                weights[i][j] = (float) Math.random() - 0.5f;
            }
        }
        for (int j = 0; j < outputDim; j++) {
            bias[j] = (float) Math.random() - 0.5f;
        }

        // Преобразование входа
        float[][] output = new float[input.length][outputDim];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < outputDim; j++) {
                output[i][j] = bias[j];
                for (int k = 0; k < inputDim; k++) {
                    output[i][j] += input[i][k] * weights[k][j];
                }
            }
        }
        return output;
    }

    private float dotProduct(float[] vector1, float[] vector2) {
        float result = 0;
        for (int i = 0; i < vector1.length; i++) {
            result += vector1[i] * vector2[i];
        }
        return result;
    }

    private float[][] applySoftmax(float[][] scores) {
        int seqLength = scores.length;
        float[][] softmaxScores = new float[seqLength][seqLength];
        for (int i = 0; i < seqLength; i++) {
            float sum = 0;
            for (int j = 0; j < seqLength; j++) {
                softmaxScores[i][j] = (float) Math.exp(scores[i][j]);
                sum += softmaxScores[i][j];
            }
            for (int j = 0; j < seqLength; j++) {
                softmaxScores[i][j] /= sum;
            }
        }
        return softmaxScores;
    }
}
