package textprocessing;

import memory.KnowledgeBase;

public class LanguageProcessor {

    public String process(String input, KnowledgeBase knowledgeBase) {
        if (input.startsWith("добавить связь")) {
            String[] parts = input.split(" ");
            if (parts.length < 4) {
                return "Неверный формат. Используйте: добавить связь [тип1] [связь] [тип2]";
            }
            String fromType = parts[1];
            String connectionType = parts[2];
            String toType = parts[3];

            knowledgeBase.addConnection(fromType, connectionType, toType);
            return "Связь добавлена: " + fromType + " " + connectionType + " " + toType;
        }

        if (input.startsWith("показать связи")) {
            String[] parts = input.split(" ");
            if (parts.length < 3) {
                return "Неверный формат. Используйте: показать связи [тип] [связь]";
            }
            String type = parts[1];
            String connectionType = parts[2];

            return "Связи: " + knowledgeBase.getConnections(type, connectionType);
        }

        return "Команда не распознана.";
    }
}
