package tokenizer;

import java.util.*;

public class BPETokenizer {

    private final Map<String, Integer> vocab; // Словарь токенов
    private final int maxVocabSize; // Максимальный размер словаря
    private final Map<String, String> merges; // Пары символов для объединения

    public BPETokenizer(int maxVocabSize) {
        this.vocab = new HashMap<>();
        this.maxVocabSize = maxVocabSize;
        this.merges = new HashMap<>();
    }

    public void train(List<String> corpus) {
        Map<String, Integer> tokenFrequencies = calculateTokenFrequencies(corpus);

        while (vocab.size() < maxVocabSize) {
            String mostFrequentPair = findMostFrequentPair(tokenFrequencies);
            if (mostFrequentPair == null) break;

            merges.put(mostFrequentPair, mostFrequentPair.replace(" ", ""));
            applyMerge(tokenFrequencies, mostFrequentPair);
        }
    }

    private Map<String, Integer> calculateTokenFrequencies(List<String> corpus) {
        Map<String, Integer> frequencies = new HashMap<>();
        for (String word : corpus) {
            String spacedWord = String.join(" ", word.split(""));
            frequencies.put(spacedWord, frequencies.getOrDefault(spacedWord, 0) + 1);
        }
        return frequencies;
    }

    private String findMostFrequentPair(Map<String, Integer> tokenFrequencies) {
        Map<String, Integer> pairFrequencies = new HashMap<>();

        for (String token : tokenFrequencies.keySet()) {
            String[] symbols = token.split(" ");
            for (int i = 0; i < symbols.length - 1; i++) {
                String pair = symbols[i] + " " + symbols[i + 1];
                pairFrequencies.put(pair, pairFrequencies.getOrDefault(pair, 0) + tokenFrequencies.get(token));
            }
        }

        return pairFrequencies.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private void applyMerge(Map<String, Integer> tokenFrequencies, String merge) {
        String newToken = merge.replace(" ", "");
        Map<String, Integer> updatedFrequencies = new HashMap<>();

        for (Map.Entry<String, Integer> entry : tokenFrequencies.entrySet()) {
            String token = entry.getKey().replace(merge, newToken);
            updatedFrequencies.put(token, updatedFrequencies.getOrDefault(token, 0) + entry.getValue());
        }

        tokenFrequencies.clear();
        tokenFrequencies.putAll(updatedFrequencies);
    }

    public List<String> tokenize(String text) {
        String spacedText = String.join(" ", text.split(""));
        for (Map.Entry<String, String> merge : merges.entrySet()) {
            spacedText = spacedText.replace(merge.getKey(), merge.getValue());
        }

        return Arrays.asList(spacedText.split(" "));
    }
}
