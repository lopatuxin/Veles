package memory;

public class KnowledgeBase {

    private final String[] knowledge = {
            "как дела:У меня всё хорошо, спасибо!",
            "что ты умеешь:Я могу отвечать на простые вопросы."
    };

    public String findAnswer(String question) {
        for (String entry : knowledge) {
            String[] parts = entry.split(":");
            if (question.toLowerCase().contains(parts[0])) {
                return parts[1];
            }
        }
        return "Извините, я не знаю ответа на этот вопрос.";
    }
}
